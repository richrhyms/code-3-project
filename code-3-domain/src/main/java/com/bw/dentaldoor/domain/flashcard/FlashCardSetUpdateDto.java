package com.bw.dentaldoor.domain.flashcard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlashCardSetUpdateDto {
    // flash set info
    private String title;
    private String description;
    private Boolean isPrivate;
    // subjects
    private List<String> subjects;

    @NotNull
    @Size(min = 1)
    public List<String> getSubjects() {
        return subjects;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }
}
