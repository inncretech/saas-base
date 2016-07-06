package com.inncretech.multitenancy.datasource.master.entity;

import javax.persistence.MappedSuperclass;

import com.inncretech.multitenancy.datasource.dto.IdentityAware;

@MappedSuperclass
public abstract class AbstractIdentityAuditData extends AbstractAuditData implements IdentityAware {

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractPersistentObject other = (AbstractPersistentObject) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }
}
