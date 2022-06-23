package com.notekeep.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Document("users")
@Getter
@Setter
@Accessors(chain = true)
public class User {

    @Id
    private String id;

    @Size(max = 25)
    private String firstName;

    @Size(max = 25)
    private String lastName;

    @Email(message = "user.email.incorrect")
    @Size(min = 4, max = 85)
    private String email;

    @Size(min = 4, max = 60)
    private String password;

    private String role;

    public User() {
    }

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User setFirstName(String firstName) {
        this.firstName = StringUtils.isNotEmpty(firstName) ? firstName : "";
        return this;
    }

    public User setLastName(String lastName) {
        this.lastName = StringUtils.isNotEmpty(lastName) ? lastName : "";
        return this;
    }
}
