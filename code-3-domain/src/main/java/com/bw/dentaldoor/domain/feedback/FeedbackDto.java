package com.bw.dentaldoor.domain.feedback;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class FeedbackDto {
    private String name;
    private String email;
    private String phoneNumber;
    @NotBlank
    private String comment;
}
