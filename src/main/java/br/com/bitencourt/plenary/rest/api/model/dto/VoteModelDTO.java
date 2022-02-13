package br.com.bitencourt.plenary.rest.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteModelDTO {

    @NotNull
    @Length(max = 11)
    private String cpf;

    @NotNull
    @Positive
    private Integer session_id;

    @NotNull
    @NotBlank
    private String vote;
}