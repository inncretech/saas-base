package com.inncretech.multitenancy.datasource.dto;

public interface SoftDeleteAware {

    public Boolean getDeleted();
    
    public void setDeleted(Boolean deleted);
}
