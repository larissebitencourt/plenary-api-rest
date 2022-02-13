package br.com.bitencourt.plenary.rest.api.repository;

import br.com.bitencourt.plenary.rest.api.model.MeetingModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<MeetingModel, Integer> {

}
