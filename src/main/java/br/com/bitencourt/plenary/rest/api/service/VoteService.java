package br.com.bitencourt.plenary.rest.api.service;

import br.com.bitencourt.plenary.rest.api.integration.CpfApiIntegration;
import br.com.bitencourt.plenary.rest.api.model.MeetingModel;
import br.com.bitencourt.plenary.rest.api.model.SessionModel;
import br.com.bitencourt.plenary.rest.api.model.VoteModel;
import br.com.bitencourt.plenary.rest.api.model.dto.VoteResponseDTO;
import br.com.bitencourt.plenary.rest.api.model.exception.ObjectInvalidException;
import br.com.bitencourt.plenary.rest.api.model.exception.ObjectUnauthorizedException;
import br.com.bitencourt.plenary.rest.api.repository.VoteRepository;
import br.com.bitencourt.plenary.rest.api.model.dto.VoteModelDTO;
import br.com.bitencourt.plenary.rest.api.model.exception.ObjectExistsException;
import br.com.bitencourt.plenary.rest.api.model.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteService {
    @Autowired
    private VoteRepository repository;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private CpfApiIntegration cpfApiIntegration;

    public VoteModel createVote(VoteModelDTO voteModelDTO) {

        if (!voteModelDTO.getVote().equals("Sim") & !voteModelDTO.getVote().equals("Não")) {
            throw new ObjectInvalidException("The vote must be only Sim or Não.");
        }

        SessionModel sessionModel = sessionService.findSession(voteModelDTO.getSession_id());

        if (sessionModel == null) {
            throw new ObjectNotFoundException("Session id not found");
        }

        VoteModel voteModel = repository.findVoteByCpfAndSession_id(voteModelDTO.getCpf(), voteModelDTO.getSession_id());

        if (voteModel != null) {
            throw new ObjectExistsException("CPF has already voted in the session");
        }

        LocalDateTime closingDate = sessionModel.getOpeningdate().plusMinutes(sessionModel.getTime());
        int compareValue = closingDate.compareTo(LocalDateTime.now());

        if (compareValue < 0) {
            throw new ObjectUnauthorizedException("Session closed.");
        }

        boolean cpfAbleVote = cpfApiIntegration.getCpfAbleVote(voteModelDTO.getCpf());

        if (cpfAbleVote) {
            voteModel = new VoteModel();
            voteModel.setCpf(voteModelDTO.getCpf());
            voteModel.setVote(voteModelDTO.getVote());
            voteModel.setOpeningdate(LocalDateTime.now());
            voteModel.setSession(sessionModel);

            return repository.save(voteModel);
        }

        throw new ObjectNotFoundException("CPF Unable to vote");
    }

    public VoteResponseDTO getVotesSessionMeeting(Integer meeting_id) {

        MeetingModel meetingModel = meetingService.findMeeting(meeting_id);

        if (meetingModel == null) {
            throw new ObjectNotFoundException("Meeting id not found");
        }

        SessionModel sessionModel = sessionService.findSession(meetingModel.getId());

        if (sessionModel == null) {
            throw new ObjectNotFoundException("Session id not found");
        }

        List<VoteModel> votesModel = repository.findVoteBySession_id(sessionModel.getId());

        VoteResponseDTO voteResponseDTO = new VoteResponseDTO();

        Integer countVoteFavor = Math.toIntExact(votesModel.stream().filter(vote -> vote.getVote().equals("Sim")).count());
        Integer countVoteAgainst = Math.toIntExact(votesModel.stream().filter(vote -> vote.getVote().equals("Não")).count());
        Integer totalVotes = votesModel.size();

        String result;

        if (countVoteFavor > countVoteAgainst) {
            result = "Approved";
        } else if (countVoteAgainst > countVoteFavor) {
            result = "Disapproved";
        } else {
            result = "Undefined";
        }

        voteResponseDTO.setVotesFavor(countVoteFavor);
        voteResponseDTO.setVotesAgainst(countVoteAgainst);
        voteResponseDTO.setResult(result);
        voteResponseDTO.setTotalVotes(totalVotes);

        return voteResponseDTO;
    }
}
