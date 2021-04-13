package com.young.blogus.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

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

    private String password;

    private String salt;

    private String about;

    private Integer role = 0;

    private String photo;

    private String resetPasswordLink;

    private LocalDateTime joinedAt;

}
