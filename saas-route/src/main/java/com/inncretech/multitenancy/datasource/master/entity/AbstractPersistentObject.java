package com.inncretech.multitenancy.datasource.master.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractPersistentObject extends AuditDate {

    @Version
    private Integer version;

    public abstract Long getId();

    public abstract void setId(Long id);

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
