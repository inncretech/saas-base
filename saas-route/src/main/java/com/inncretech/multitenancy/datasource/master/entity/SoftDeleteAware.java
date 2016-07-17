package com.inncretech.multitenancy.datasource.master.entity;

public interface SoftDeleteAware {

    public Boolean getDeleted();
    
    public void setDeleted(Boolean deleted);
}
