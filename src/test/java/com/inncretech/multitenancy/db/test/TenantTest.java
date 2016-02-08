package com.inncretech.multitenancy.db.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import com.inncretech.multitenancy.master.dto.TenantDTO;
import com.inncretech.multitenancy.service.TenantService;
import com.inncretech.multitenancy.service.exception.InvalidArgumentException;

public class TenantTest extends BaseTest {

	@Autowired
	private TenantService tenantService;

	@Rollback(false)
	@Test(expected = InvalidArgumentException.class)
	public void validationTest() throws InvalidArgumentException {
		TenantDTO tenantDTO = new TenantDTO();
		tenantService.addTenant(tenantDTO);
	}

	@Rollback(false)
	@Test(expected = InvalidArgumentException.class)
	public void addTenantNegative() throws InvalidArgumentException {
		TenantDTO tenantDTO = new TenantDTO();
		tenantDTO.setDataSourceConfigId(10l);
		tenantDTO.setDomain("test");
		tenantDTO.setTenantName("test");
		tenantService.addTenant(tenantDTO);
	}

	@Rollback(false)
	@Test
	public void addTenant() throws InvalidArgumentException {
		TenantDTO tenantDTO = new TenantDTO();
		tenantDTO.setDataSourceConfigId(1l);
		tenantDTO.setDomain("test");
		tenantDTO.setTenantName("test");
		TenantDTO addTenant = tenantService.addTenant(tenantDTO);
		Assert.notNull(addTenant.getTenantId());
	}

	@Rollback(false)
	@Test(expected = InvalidArgumentException.class)
	public void getTenants() throws InvalidArgumentException {
		tenantService.getTenantMetaData(null);
	}
}
