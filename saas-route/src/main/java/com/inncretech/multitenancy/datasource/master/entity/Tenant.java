package com.inncretech.multitenancy.datasource.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "tenant")
public class Tenant extends AbstractPersistentObject {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "master_tenant_id")
    private Long masterTenantId;

    @Column(name = "domain", unique = true)
    private String domain;

    private String name;

    @ManyToOne(targetEntity = DataSourceConfig.class)
    @JoinColumn(name = "data_source_config_id")
    private DataSourceConfig tenantDataSourceConfig;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public DataSourceConfig getTenantDataSourceConfig() {
        return tenantDataSourceConfig;
    }

    public void setTenantDataSourceConfig(DataSourceConfig tenantDataSourceConfig) {
        this.tenantDataSourceConfig = tenantDataSourceConfig;
    }

    public Long getMasterTenantId() {
        return masterTenantId;
    }

    public void setMasterTenantId(Long masterTenantId) {
        this.masterTenantId = masterTenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}