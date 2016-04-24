package com.inncretech.multitenancy.datasource.master.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractPersistentObject extends AuditData {

    @Version
    private Integer version;
    
    @Column
    private String name;

    @Column
    private String description;


    public abstract Long getId();

    public abstract void setId(Long id);

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AbstractPersistentObject [version=").append(version).append(", name=").append(name).append(", description=")
                .append(description).append("]");
        return builder.toString();
    }
}
