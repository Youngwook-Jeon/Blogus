package com.young.blogus.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Size(min = 2, max = 32)
    @Column(unique = true)
    private String name;

    @Column(unique = true)
    @Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    private String profile;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // it may only be written in deserialization
    private String password;

    private String salt;

    private String about;

    private Integer role = 0;

    private String photo;

    private String resetPasswordLink;

    private LocalDateTime joinedAt;

}
