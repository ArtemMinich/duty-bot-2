package com.dutybot2.dutybot2.model;

import com.dutybot2.dutybot2.util.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cadet_user")
public class CadetUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false,unique = true)
    @NotEmpty(message = "Введіть логін")
    @Size(min = 2, max = 50, message = "Логін повинен бути від 2 до 50 символів")
    private String username;

    @Column(name = "password", nullable = false)
    @Size(min = 8, max = 100, message = "Пароль має бути розміром від 8 да 100 символів")
    @NotEmpty(message = "Введіть пароль")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

}