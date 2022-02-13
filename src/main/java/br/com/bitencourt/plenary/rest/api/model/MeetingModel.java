package br.com.bitencourt.plenary.rest.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "meetings")
public class MeetingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;

    //@NotNull()  @NotBlank() @Length(max = 50)
    @Column(nullable = false, length = 50, unique = true)
    private String name;

    //@NotNull()  @NotBlank() @Length(max = 150)
    @Column(nullable = false, length = 150)
    private String description;

}
