package com.bw.dentaldoor.domain;

import com.bw.dentaldoor.constraint.ExistsColumnValue;
import com.bw.dentaldoor.entity.State;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Emmanuel Evuazeze <eevuazeze@byteworks.com.ng>
 */
@Getter
@Setter
public class DentalPractitionerStateLicenceDto {

    @NotBlank
    private String nameOnLicence;
    @NotBlank
    private String licenceNumber;
    @PastOrPresent
    private LocalDate dateIssued;
    @FutureOrPresent
    private LocalDate expiryDate;

    @NotBlank
    @ExistsColumnValue(value = State.class, columnName = "code")
    private String stateOfIssuanceCode;
}
