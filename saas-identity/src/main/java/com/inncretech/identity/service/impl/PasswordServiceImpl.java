package com.inncretech.identity.service.impl;

import java.security.SecureRandom;
import java.security.Security;
import java.util.UUID;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.inncretech.identity.service.PasswordService;
import com.inncretech.multitenancy.datasource.tenant.entity.User;
import com.inncretech.multitenancy.datasource.tenant.entity.UserAccessToken;

@Component
public class PasswordServiceImpl implements PasswordService {

    @Value("${mastekey.salt:W0JANzQzNDE5NjA=}")
    private String masterKeySalt;

    @Autowired
    private EncryptDecryptServiceImpl encryptDecryptService;

    public PasswordServiceImpl() {
        Security.addProvider(new BouncyCastleProvider());
    }

    private byte[] getSalt() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];
            sr.nextBytes(salt);
            return salt;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String generateStrongPasswordHash(char[] password, byte[] salt) {
        try {
            int iterations = 1000;
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, 64 * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.toBase64String(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkPassword(char[] inputPassword, User userDbEntity) {
        byte[] userSalt = Base64.decode(userDbEntity.getPasswordSalt());
        String hashedPassword = generateStrongPasswordHash(inputPassword, userSalt);
        return hashedPassword.equals(userDbEntity.getPassword());
    }

    @Override
    public UserAccessToken generateAccessToken(User user, char[] userInputPassword) {
        try {
            UserAccessToken userAccessToken = new UserAccessToken();
            String hashedPasswordWithMasterSalt = generateStrongPasswordHash(userInputPassword, Base64.decode(masterKeySalt));
            String masterKey = decrypt(user.getMasterKey(), hashedPasswordWithMasterSalt);
            KeyGenerator generator = KeyGenerator.getInstance("AES", "BC");
            generator.init(256);
            byte[] tokenKey = generator.generateKey().getEncoded();
            String accessTokenPlainText = Base64.toBase64String(masterKey.getBytes()) + "-" + user.getId() + "-" + System.currentTimeMillis();
            String accessToken = encrypt(accessTokenPlainText, new String(tokenKey));
            userAccessToken.setAccessToken(accessToken);
            userAccessToken.setUserId(user.getId());
            userAccessToken.setExpiryAt(DateTime.now().plusDays(365).toDate());
            userAccessToken.setTokenKey(Base64.toBase64String(tokenKey));
            return userAccessToken;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String retrieveMasterKey(UserAccessToken userAccessToken) {
        try {
            byte[] tokenKey = Base64.decode(userAccessToken.getTokenKey().getBytes());
            String accessTokenPlainText = decrypt(userAccessToken.getAccessToken(), new String(tokenKey));
            return accessTokenPlainText.substring(0, accessTokenPlainText.indexOf("-"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String encrypt(String text, String password) {
        return getEncryptor(password).encrypt(text);
    }

    private StandardPBEStringEncryptor getEncryptor(String password) {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setProvider(Security.getProvider(BouncyCastleProvider.PROVIDER_NAME));
        standardPBEStringEncryptor.setAlgorithm("PBEWITHSHA256AND256BITAES-CBC-BC");
        standardPBEStringEncryptor.setPassword(password);
        return standardPBEStringEncryptor;
    }

    private String decrypt(String text, String password) {
        return getEncryptor(password).decrypt(text);
    }

    @Override
    public String generateResetPasswordToken() {
        return encryptDecryptService.encrypt(UUID.randomUUID().toString());
    }

    public void initializeCryptoForNewUser(User user) {
        byte[] userSalt = getSalt();
        String userPassword = user.getPassword();
        user.setPassword(generateStrongPasswordHash(userPassword.toCharArray(), userSalt));
        user.setPasswordSalt(Base64.toBase64String(userSalt));
        user.setMasterKey(generateMasterKey(userPassword.toCharArray()));
    }

    private String generateMasterKey(char[] pasword) {
        try {
            String hashedPasswordWithMasterSalt = generateStrongPasswordHash(pasword, Base64.decode(masterKeySalt));
            KeyGenerator generator = KeyGenerator.getInstance("AES", "BC");
            generator.init(256);
            byte[] masterKey = generator.generateKey().getEncoded();
            return encrypt(new String(masterKey), hashedPasswordWithMasterSalt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] aesEncryptText(String text, String key, String salt) {
        return null;
    }

    public boolean isPasswordValid(String encPass, char[] rawPass, Object salt) {
        if (salt == null) {
            return false;
        }
        byte[] userSalt = Base64.decode((String) salt);
        String hashedPassword = generateStrongPasswordHash(rawPass, userSalt);
        return hashedPassword.equals(encPass);
    }
}
