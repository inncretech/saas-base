package com.inncretech.multitenancy.db.test;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.multitenancy.datasource.config.DbContextHolder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/context.xml" })
@ActiveProfiles(profiles = "dev")
public class BaseTest {

	@BeforeClass
	public static void a() {
		DbContextHolder.setDbType(1l);
	}

}
