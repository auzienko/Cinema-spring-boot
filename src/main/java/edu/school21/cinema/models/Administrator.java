package edu.school21.cinema.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="administrators")
@JsonIgnoreType
public class Administrator extends BaseEntity{
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
}
