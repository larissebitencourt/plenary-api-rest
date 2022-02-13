package br.com.bitencourt.plenary.rest.api.repository;

import br.com.bitencourt.plenary.rest.api.model.SessionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<SessionModel, Integer> {
    SessionModel findSessionByMeeting_id(Integer id);
}
