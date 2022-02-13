package br.com.bitencourt.plenary.rest.api.service;

import br.com.bitencourt.plenary.rest.api.model.MeetingModel;
import br.com.bitencourt.plenary.rest.api.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeetingService {

    @Autowired
    private MeetingRepository repository;

    public MeetingModel findMeeting(Integer id) {

        Optional<MeetingModel> meetingModel = repository.findById(id);

        if (!meetingModel.isPresent()) {
            return null;
        }

        return meetingModel.get();
    }

    public MeetingModel createMeeting(MeetingModel meetingModel) {
        return repository.save(meetingModel);
    }
}
