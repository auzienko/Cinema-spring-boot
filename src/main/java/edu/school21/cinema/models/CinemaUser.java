package edu.school21.cinema.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.school21.cinema.annotations.ValidPassword;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({ "password", "avatar" })
@Table(schema = "cinema", name = "cinema_users")
public class CinemaUser extends BaseEntity {
    @Column(name = "username")
    @NotEmpty(message = "{validation.username.notEmpty}")
    private String username;

    @Column(name = "password")
    @ValidPassword(message = "{validation.username.password}")
    private String password;

    @Column(name = "email")
    @Email(message = "{validation.username.email}")
    private String email;


    @Column(name = "phoneNum")
    @NotEmpty(message = "{validation.phoneNum.notEmpty}")
    private String phoneNum;

    @Column(name = "role")
    private Role role;

    @Column(name = "status")
    private UserStatus status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id", referencedColumnName = "id")
    private Image avatar;
}
