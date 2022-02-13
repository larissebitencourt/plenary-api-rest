package br.com.bitencourt.plenary.rest.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteResponseDTO {

    private Integer votesFavor;

    private Integer votesAgainst;

    private Integer totalVotes;

    private String result;

}
