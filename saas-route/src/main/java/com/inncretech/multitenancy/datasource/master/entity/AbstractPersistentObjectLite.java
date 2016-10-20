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
public abstract class AbstractPersistentObjectLite extends AbstractIdentityAuditData {

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name = "active")
	private Boolean active;

	@PrePersist
	public void onPersist() {
		this.setCreatedDate(new Date());
		onUpdate();
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "AbstractPersistentObjectLite [active=" + active + "]";
	}

}
