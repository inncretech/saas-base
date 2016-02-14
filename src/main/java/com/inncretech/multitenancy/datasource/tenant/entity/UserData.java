package com.inncretech.multitenancy.datasource.tenant.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.inncretech.multitenancy.datasource.master.entity.AuditDate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "user")
public class UserData extends AuditDate {

    private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserData [id=").append(id).append(", name=").append(name).append("]");
        return builder.toString();
    }
}