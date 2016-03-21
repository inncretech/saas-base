package com.inncretech.multitenancy.datasource.tenant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.inncretech.multitenancy.datasource.dto.TenantAware;
import com.inncretech.multitenancy.datasource.master.entity.AbstractPersistentObject;

@Entity
@Table(name = "test_data")
@DynamicInsert
@DynamicUpdate
public class TestData extends AbstractPersistentObject implements TenantAware {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "tenant_id")
    private Long tenantId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getTenantId() {
        return tenantId;
    }

    @Override
    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
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
        builder.append("TestData [id=").append(id).append(", tenantId=").append(tenantId).append(", name=").append(name).append(", description=")
                .append(description).append("]");
        return builder.toString();
    }
}