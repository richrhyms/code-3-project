package com.bw.dentaldoor.domain.review;

import com.bw.dentaldoor.entity.PortalUser;
import com.bw.dentaldoor.enums.ReviewRecordSourceConstant;
import com.bw.enums.GenericStatusConstant;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class ReviewDto {
    @NotBlank
    private String comment;
    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    private int rating;
    private ReviewerDTO reviewer;
    private String entityName;
    private Long entityId;

}
