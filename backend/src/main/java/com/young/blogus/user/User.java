package com.young.blogus.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.young.blogus.enumeration.Role;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

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

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String profile;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // it may only be written in deserialization
    private String password;

    private String salt;

    private String about;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String photo;

    private String resetPasswordLink;

    private LocalDateTime joinedAt;

    public void completeSignup() {
        this.joinedAt = LocalDateTime.now();
        this.role = Role.USER;
        this.username = RandomStringUtils.randomAlphanumeric(12);
    }
}
