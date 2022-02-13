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
@Table(name = "votes_sessions", uniqueConstraints = {@UniqueConstraint(name = "UniqueVoteAndSession", columnNames = {"cpf", "session_id"})})
public class VoteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;

    @Column(nullable = false, length = 11)
    private String cpf;

    @ManyToOne
    @JoinColumn(nullable = false, name = "session_id", referencedColumnName = "id")
    private SessionModel session;

    @Column(nullable = false)
    private String vote;

    private LocalDateTime openingdate;
}
