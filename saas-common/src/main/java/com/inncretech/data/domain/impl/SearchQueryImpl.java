package com.inncretech.data.domain.impl;

import java.util.List;

import com.inncretech.data.domain.FieldFilter;
import com.inncretech.data.domain.SearchQuery;

public class SearchQueryImpl implements SearchQuery {

    private String fieldGroup;

    private String filters;

    private String fields;

    private List<String> fieldList;

    private List<FieldFilter> fieldFilters;

    @Override
    public String getFieldGroup() {
        return fieldGroup;
    }

    public void setFieldGroup(String fieldGroup) {
        this.fieldGroup = fieldGroup;
    }

    @Override
    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    @Override
    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public List<String> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<String> fieldList) {
        this.fieldList = fieldList;
    }

    public List<FieldFilter> getFieldFilters() {
        return fieldFilters;
    }

    public void setFieldFilters(List<FieldFilter> fieldFilters) {
        this.fieldFilters = fieldFilters;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SearchQueryImpl [fieldGroup=").append(fieldGroup).append(", filter=").append(filters).append(", fields=").append(fields)
                .append(", fieldList=").append(fieldList).append(", filterFields=").append(fieldFilters).append("]");
        return builder.toString();
    }
}
