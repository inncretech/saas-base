package com.inncretech.multitenancy.datasource.master.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.inncretech.multitenancy.datasource.dto.CreationAuditAware;
import com.inncretech.multitenancy.datasource.dto.ModifiedAuditAware;

@MappedSuperclass
public abstract class AbstractAuditData implements ModifiedAuditAware, CreationAuditAware, Serializable {

    /**
     * version would only exist, if update sql operation is supported and that is why audit columns exists
     */
    @Version
    private Integer version;

    @Column(name = "modified_by")
    private Long modifiedBy;

    @Column(name = "created_by")
    private Long createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getVersion() {
        return version;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @PreUpdate
    public void onUpdate() {
        this.setUpdatedDate(new Date());
    }

    @PrePersist
    public void onPersist() {
        this.setCreatedDate(new Date());
        onUpdate();
    }
}
