package com.dutybot2.dutybot2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "duty")
public class Duty {

    @Id
    @Column(name = "date", nullable = false)
    private Date dutyDate;

    @Column(name = "terka_cadet_id")
    @OneToOne()
    private Integer terkaCadetId;

    @Column(name = "cubar_cadet_id")
    private Integer cubarCadetId;

}