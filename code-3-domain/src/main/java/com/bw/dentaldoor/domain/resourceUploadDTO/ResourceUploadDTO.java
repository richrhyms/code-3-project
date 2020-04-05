package com.bw.dentaldoor.domain.resourceUploadDTO;

import com.bw.enums.GenericStatusConstant;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class ResourceUploadDTO{
    @NotBlank
    private String name;

    private String code;
}
