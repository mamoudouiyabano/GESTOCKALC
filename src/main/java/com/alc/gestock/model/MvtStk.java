package com.alc.gestock.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="mvtstk")
public class MvtStk extends AbstractEntity{

    @Column(name = "datemvt")
    private Instant dateMvt;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name=  "typemvt")
    private TypeMvtStk typeMvt;

    @Column(name=  "sourcemvt")
    private SourceMvtStk sourceMvtStk;

    @ManyToOne
    @JoinColumn(name = "iditem")
    private Item item;
}
