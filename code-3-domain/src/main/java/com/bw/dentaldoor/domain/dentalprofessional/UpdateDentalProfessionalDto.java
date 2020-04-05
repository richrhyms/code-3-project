package com.bw.dentaldoor.domain.dentalprofessional;

import com.bw.dentaldoor.domain.AddressDto;
import com.bw.dentaldoor.domain.DentalPractitionerStateLicenceDto;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Emmanuel Evuazeze <eevuazeze@byteworks.com.ng>
 */
@Data
public class UpdateDentalProfessionalDto {

    @Valid
    private DentalPractitionerStateLicenceDto stateLicence;

    @Size(min = 1)
    private List<String> specializations;

    @Size(min = 1)
    private List<String> languages;

    private String website;

    @Valid
    private AddressDto address;
}
