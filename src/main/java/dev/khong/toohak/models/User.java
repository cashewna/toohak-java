package dev.khong.toohak.models;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private long authUserId;
    private String email;
    private String password;
    private String nameFirst;
    private String nameLast;
    private long numSuccessfulLogins;
    private long numFailedPasswordsSinceLastLogin;
}
