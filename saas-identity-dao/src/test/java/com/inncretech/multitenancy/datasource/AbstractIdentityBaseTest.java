package com.inncretech.multitenancy.datasource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.inncretech.multitenancy.datasource.config.MultiTenancyConfiguration;
import com.inncretech.multitenancy.datasource.tenant.dao.GroupRepository;
import com.inncretech.multitenancy.datasource.tenant.dao.RoleRepository;
import com.inncretech.multitenancy.datasource.tenant.dao.UserAccessTokenRepository;
import com.inncretech.multitenancy.datasource.tenant.dao.UserDataRepository;
import com.inncretech.multitenancy.datasource.utils.IdGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MultiTenancyConfiguration.class })
@ActiveProfiles("dev")
public abstract class AbstractIdentityBaseTest {

    @Autowired
    protected IdGenerator idGenerator;

    @Autowired
    protected GroupRepository groupRepository;

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected UserAccessTokenRepository userAccessTokenRepository;

    @Autowired
    protected UserDataRepository userDataRepository;

    @Test
    public void repositories() {
        Assert.notNull(groupRepository);
        Assert.notNull(roleRepository);
        Assert.notNull(userAccessTokenRepository);
        Assert.notNull(userDataRepository);
    }
}
