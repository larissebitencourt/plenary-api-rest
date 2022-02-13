package br.com.bitencourt.plenary.rest.api.service;

import br.com.bitencourt.plenary.rest.api.model.MeetingModel;
import br.com.bitencourt.plenary.rest.api.model.SessionModel;
import br.com.bitencourt.plenary.rest.api.model.dto.SessionModelDTO;
import br.com.bitencourt.plenary.rest.api.model.exception.ObjectExistsException;
import br.com.bitencourt.plenary.rest.api.model.exception.ObjectNotFoundException;
import br.com.bitencourt.plenary.rest.api.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private SessionRepository repository;

    @Autowired
    private MeetingService meetingService;

    public SessionModel createSession(SessionModelDTO sessionModelDTO) {

        MeetingModel meetingModel = meetingService.findMeeting(sessionModelDTO.getMeeting_id());

        if (meetingModel == null) {
            throw new ObjectNotFoundException("Meeting id not found");
        }

        SessionModel sessionModel = repository.findSessionByMeeting_id(sessionModelDTO.getMeeting_id());

        if (sessionModel != null) {
            throw new ObjectExistsException("The session is already open");
        }

        sessionModel = new SessionModel();
        sessionModel.setTime(sessionModelDTO.getTime());
        sessionModel.setMeeting(meetingModel);
        sessionModel.setOpeningdate(LocalDateTime.now());

        return repository.save(sessionModel);
    }

    public SessionModel findSession(Integer id) {

        Optional<SessionModel> sessionModel = repository.findById(id);

        if (!sessionModel.isPresent()) {
            return null;
        }

        return sessionModel.get();
    }

    public SessionModel findSessionByMeeting(Integer id) {
        return repository.findSessionByMeeting_id(id);
    }
}
