package com.inncretech.multitenancy.datasource.dto;

import java.io.Serializable;

public interface IdentityAware extends Serializable {

    Long getId();

    void setId(Long id);
}
