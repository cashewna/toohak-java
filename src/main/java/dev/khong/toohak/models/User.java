package dev.khong.toohak.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public record User(
        @Id Long authUserId,
        String email,
        String password,
        String nameFirst,
        String nameLast,
        long numSuccessfulLogins,
        long numFailedPasswordsSinceLastLogin) {
    public User(String email, String password, String nameFirst, String nameLast) {
        this(null, email, password, nameFirst, nameLast, 0L, 0L);
    }
}
