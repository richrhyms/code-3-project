package com.bw.dentaldoor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageListDto {

    @NotNull
    @Size(min = 1)
    private Set<@NotBlank String> codes;
}
