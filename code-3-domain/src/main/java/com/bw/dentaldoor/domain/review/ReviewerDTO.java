package com.bw.dentaldoor.domain.review;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ReviewerDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    private String phoneNumber;

}
