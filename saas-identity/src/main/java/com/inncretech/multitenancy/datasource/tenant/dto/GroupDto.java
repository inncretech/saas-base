package com.inncretech.multitenancy.datasource.tenant.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.appanalytixs.dto.AbstractDto;

public class GroupDto extends AbstractDto {

	private Long groupId;

	@NotBlank(message = "Group Name Can't Be Blank")
	private String groupName;

	@NotBlank(message = "Group Description Can't Be Blank")
	private String groupDescription;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

}
