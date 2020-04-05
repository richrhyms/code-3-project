package com.bw.dentaldoor.domain.flashcard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlashCardDto {
    @NotBlank
    private String question;

    @NotBlank
    private String answer;

    private String hint;

    public Optional<String> getHint() {
        return Optional.ofNullable(hint);
    }
}
