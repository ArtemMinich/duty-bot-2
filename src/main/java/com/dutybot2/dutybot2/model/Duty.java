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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "terka_cadet_id", referencedColumnName = "id")
    private Cadet terkaCadet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cubar_cadet_id", referencedColumnName = "id")
    private Cadet cubarCadet;

}