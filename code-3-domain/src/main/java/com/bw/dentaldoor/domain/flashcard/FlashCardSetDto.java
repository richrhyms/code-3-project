package com.bw.dentaldoor.domain.flashcard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlashCardSetDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private Boolean isPrivate;

    @NotNull
    private List<String> subjects;

    @NotNull
    @NotEmpty
    private List<FlashCardDto> flashCards;

    @NotNull
    @Size(min = 1)
    public List<String> getSubjects() {
        return subjects;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }
}
