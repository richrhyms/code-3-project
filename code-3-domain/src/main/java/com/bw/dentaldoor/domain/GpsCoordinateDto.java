package com.bw.dentaldoor.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GpsCoordinateDto {

    @NotNull
    private Double longitude;
    @NotNull
    private Double latitude;
}
