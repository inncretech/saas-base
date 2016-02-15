package com.inncretech.multitenancy.datasource.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.inncretech.multitenancy.datasource.enums.DbLeaseType;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "data_source_config")
public class DataSourceConfig extends AuditDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private DbLeaseType dbLeaseType;

    @Column(name = "db_url")
    private String dbUrl;

    @Column(name = "user_name")
    private String dbUserName;

    @Column(name = "password")
    private String dbPassword;

    @Column(name = "db_name")
    private String dbName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DbLeaseType getDbLeaseType() {
        return dbLeaseType;
    }

    public void setDbLeaseType(DbLeaseType dbType) {
        this.dbLeaseType = dbType;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

}
