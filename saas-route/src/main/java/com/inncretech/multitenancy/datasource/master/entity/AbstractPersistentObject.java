package com.inncretech.multitenancy.datasource.master.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@MappedSuperclass
@Audited
public abstract class AbstractPersistentObject extends AbstractPersistentObjectLite {

	@Column
	private String name;

	@Column
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@PrePersist
	public void onPersist() {
		this.setCreatedDate(new Date());
		onUpdate();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AbstractPersistentObject [name=").append(name).append(", description=").append(description)
				.append("]");
		return builder.toString();
	}
}
