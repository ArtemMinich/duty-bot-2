package com.dutybot2.dutybot2.model;

import com.dutybot2.dutybot2.util.Caste;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Data
@Entity
@Table(name = "cadet")
public class Cadet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "duty_day_count")
    private int dutyDayCount;

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "staus")
    @Getter(AccessLevel.NONE)
    private boolean status;

    @Column(name = "caste")
    @Enumerated(EnumType.STRING)
    private Caste caste;

    public boolean isFree(){
        return status;
    }
}