package com.inncretech.identity.service.impl;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.identity.config.IdentityConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { IdentityConfig.class })
@ActiveProfiles("dev")
public class BaseTest {

}
