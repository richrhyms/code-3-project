package com.bw.dentaldoor.domain;

import com.bw.dentaldoor.constraint.ExistsColumnValue;
import com.bw.dentaldoor.entity.DentalOffice;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * @author Jabir Minjibir <jminjibir@byteworks.com.ng>
 */

@Getter
@Setter
public class CouponDto {

    @NotBlank
    private String code;

    @NotNull
    @FutureOrPresent
    private Date startDate;

    @NotNull
    @FutureOrPresent
    private Date endDate;

    @NotNull
    private Integer maxUsageCount;

    private String description;
    private String termsOfUse;
    private Set<Long> preloadedImages;
}
