package com.bw.dentaldoor.domain.review;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ReviewUpdateDto {
    @NotBlank
    private String comment;

    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    private int rating;
}
