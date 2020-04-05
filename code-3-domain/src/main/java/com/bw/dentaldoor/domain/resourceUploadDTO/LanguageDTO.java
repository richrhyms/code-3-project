package com.bw.dentaldoor.domain.resourceUploadDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LanguageDTO{
    @NotBlank
    private String name;
    @NotBlank
    private String code;
}
