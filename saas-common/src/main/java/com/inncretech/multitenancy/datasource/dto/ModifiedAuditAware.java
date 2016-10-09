package com.inncretech.multitenancy.datasource.dto;

import java.util.Date;

public interface ModifiedAuditAware {

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);

    Long getModifiedBy();

    void setModifiedBy(Long modifiedBy);
}
