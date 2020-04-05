package com.bw.dentaldoor.domain;

public class NameAndValueDto {
    private String name;
    private String value;

    public NameAndValueDto() {
    }

    public NameAndValueDto(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
