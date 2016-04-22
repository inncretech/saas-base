package com.inncretech.data.domain;

import java.util.List;

public class Filter {

    private List<FilterField> filterFields;

    public List<FilterField> getFilterFields() {
        return filterFields;
    }

    public void setFilterFields(List<FilterField> filterFields) {
        this.filterFields = filterFields;
    }

}
