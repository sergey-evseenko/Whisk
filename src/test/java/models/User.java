package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    String email;
    String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
