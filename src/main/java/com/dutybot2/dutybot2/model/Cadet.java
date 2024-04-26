package com.dutybot2.dutybot2.model;

import com.dutybot2.dutybot2.util.CadetStatus;
import com.dutybot2.dutybot2.util.Caste;
import com.dutybot2.dutybot2.util.Role;
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
    @Enumerated(EnumType.STRING)
    private CadetStatus status;

    @Column(name = "caste")
    @Enumerated(EnumType.STRING)
    private Caste caste;

    public boolean isFree(){
        return status==CadetStatus.FREE;
    }

    public String getCasteInUkrainian(){
        if(caste.equals(Caste.HOBO)){
            return "на базі";
        }
        return "київлянин";
    }

    public String getAllInformation(){
        return String.format("%-3d%-9s%-4d",id,lastName,dutyDayCount);
    }

    public void incrementDutyDayCount(){
        dutyDayCount++;
    }

    public void decrementDutyDayCount(){
        dutyDayCount = Math.max(dutyDayCount-1,0);
    }
}