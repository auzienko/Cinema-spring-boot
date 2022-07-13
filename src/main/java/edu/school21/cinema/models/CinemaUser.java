package edu.school21.cinema.models;

import edu.school21.cinema.annotations.ValidPassword;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(schema = "cinema", name = "cinema_users")
public class CinemaUser extends BaseEntity {
    @Column(name = "username")
    @NotEmpty(message = "{validation.username.notEmpty}")
    private String username;

    @Column(name = "password")
    @ValidPassword(message = "{validation.username.password}")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private Role role;

    @Column(name = "status")
    private UserStatus status;
}
