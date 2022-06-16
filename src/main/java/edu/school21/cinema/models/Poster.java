package edu.school21.cinema.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="posters")
public class Poster extends BaseEntity{
    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_name_UUID")
    private UUID fileNameUUID;

    @Column(name = "size")
    private Long size;

    @Column(name = "mime")
    private String mime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrator_id", referencedColumnName = "id")
    private Administrator administrator;
}
