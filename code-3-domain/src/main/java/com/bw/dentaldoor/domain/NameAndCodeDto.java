package com.bw.dentaldoor.domain;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public class NameAndCodeDto {

    private String name;
    private String code;

    public NameAndCodeDto(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public NameAndCodeDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
