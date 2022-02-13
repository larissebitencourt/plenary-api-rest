package br.com.bitencourt.plenary.rest.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sessions_meetings")
public class SessionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "meeting_id", referencedColumnName = "id", nullable = false, unique = true)
    private MeetingModel meeting;

    private Integer time = 1;

    private LocalDateTime openingdate;

}
