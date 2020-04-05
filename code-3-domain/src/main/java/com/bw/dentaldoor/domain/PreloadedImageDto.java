package com.bw.dentaldoor.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Getter
@Setter
public class PreloadedImageDto {

    @NotBlank
    private String name;

    private Set<String> tags;
}