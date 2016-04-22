package com.inncretech.data.domain;

public class FieldGroup {

    private String fieldGroup;

    public FieldGroup(String fieldGroup) {
        this.fieldGroup = fieldGroup;
    }

    public String getFieldGroup() {
        return fieldGroup;
    }

    public void setFieldGroup(String fieldGroup) {
        this.fieldGroup = fieldGroup;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FieldGroup [fieldGroup=").append(fieldGroup).append("]");
        return builder.toString();
    }
}
