package com.inncretech.identity.service;

public interface EncryptDecryptService {

    String encrypt(String message);

    String decrypt(String encrptedMessage);

}
