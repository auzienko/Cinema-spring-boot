package edu.school21.cinema.models;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(schema = "cinema", name="images")
public class Image extends BaseEntity{
    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_name_UUID", columnDefinition = "BINARY(16)", length = 16 )
    @Type(type="uuid-char")
    private UUID fileNameUUID;

    @Column(name = "type")
    private ImageType type;

    @Column(name = "size")
    private Long size;

    @Column(name = "mime")
    private String mime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrator_id", referencedColumnName = "id")
    private CinemaUser administrator;


}
