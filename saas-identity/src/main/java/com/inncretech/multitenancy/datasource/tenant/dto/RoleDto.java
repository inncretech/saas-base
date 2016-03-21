package com.inncretech.multitenancy.datasource.tenant.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.appanalytixs.dto.AbstractDto;

public class RoleDto extends AbstractDto {

	private Long roleId;

	@NotBlank(message = "Role Name Can't Be Blank")
	private String roleName;

	@NotBlank(message = "Role Description")
	private String roleDescription;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

}
