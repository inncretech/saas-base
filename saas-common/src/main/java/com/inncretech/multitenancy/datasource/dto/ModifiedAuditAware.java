package com.inncretech.multitenancy.datasource.dto;

import java.util.Date;

public interface ModifiedAuditAware {

    Integer getVersion();

    void setVersion(Integer version);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);

    Long getModifiedBy();

    void setModifiedBy(Long modifiedBy);
}
