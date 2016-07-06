package com.inncretech.multitenancy.datasource.dto;

import java.util.Date;

public interface CreationAuditAware {

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    void setCreatedBy(Long createdBy);

    Long getCreatedBy();
}
