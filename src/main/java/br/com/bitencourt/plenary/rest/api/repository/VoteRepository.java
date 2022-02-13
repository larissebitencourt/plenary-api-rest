package br.com.bitencourt.plenary.rest.api.repository;

import br.com.bitencourt.plenary.rest.api.model.VoteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<VoteModel, Integer> {

    VoteModel findVoteByCpfAndSession_id(String cpf, Integer session_id);

    List<VoteModel> findVoteBySession_id(Integer session_id);
}
