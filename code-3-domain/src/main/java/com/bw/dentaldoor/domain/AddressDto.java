package com.bw.dentaldoor.domain;

import com.bw.dentaldoor.constraint.ExistsColumnValue;
import com.bw.dentaldoor.entity.City;
import com.bw.dentaldoor.entity.State;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author Ifesinachi Eze <ieze@byteworks.com.ng>
 */

@Getter
@Setter
public class AddressDto {

    @NotBlank
    private String zipCode;

    @NotBlank
    @ExistsColumnValue(columnName = "code", value = City.class)
    private String city;

    @NotBlank
    private String streetAddress;
    private String houseNumber;

    @Valid
    private GpsCoordinateDto gpsCoordinate;
}