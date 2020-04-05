package com.bw.dentaldoor.domain.account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;
import java.util.Set;

public class UpdateUserDto {

    private String PhoneNumber;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    @NotEmpty
    @Size(max = 1)
    private Map<@NotBlank String, @NotEmpty Set<@NotBlank String>> roles;

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public UpdateUserDto setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UpdateUserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UpdateUserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Map<String, Set<String>> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, Set<String>> roles) {
        this.roles = roles;
    }
}
