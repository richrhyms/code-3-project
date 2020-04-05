package com.bw.dentaldoor.domain.account;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.*;
import java.util.Map;
import java.util.Set;

/*
 * Created by Gibah Joseph on Sep, 2018
 */
public class NewUserDto {

    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    private String PhoneNumber;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    @NotEmpty
    @Size(max = 1)
    private Map<@NotBlank String, @NotEmpty Set<@NotBlank String>> roles;

    public String getUsername() {
        return username;
    }

    public NewUserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public NewUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public NewUserDto setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public NewUserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public NewUserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Map<String, Set<String>> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, Set<String>> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("email", email)
                .append("PhoneNumber", PhoneNumber)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .toString();
    }
}
