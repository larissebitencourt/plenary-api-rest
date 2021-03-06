package br.com.bitencourt.plenary.rest.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionModelDTO {

    @NotNull
    @PositiveOrZero
    private Integer meeting_id;

    @Positive
    private Integer time = 1;
}
