package com.inncretech.data.domain;

import java.util.List;

public class Fields {

    private List<String> fields;

    public Fields(List<String> fields) {
        this.fields = fields;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }
}
