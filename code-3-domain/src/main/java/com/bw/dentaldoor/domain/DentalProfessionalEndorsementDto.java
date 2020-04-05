package com.bw.dentaldoor.domain;

import com.bw.dentaldoor.constraint.ExistsColumnValue;
import com.bw.dentaldoor.entity.DentalProfessional;
import com.bw.dentaldoor.entity.Specialization;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Jabir Minjibir <jminjibir@byteworks.com.ng>
 */
@Data
public class DentalProfessionalEndorsementDto {

    private String comment;

    @NotBlank
    @ExistsColumnValue(value = Specialization.class, columnName = "code")
    private String specializationCode;

    @NotNull
    @ExistsColumnValue(value = DentalProfessional.class)
    private Long dentalProfessionalId;
}
