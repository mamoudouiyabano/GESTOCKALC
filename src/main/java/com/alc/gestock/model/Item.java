package com.alc.gestock.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="item")
public class Item  extends AbstractEntity{

    @Column(name="codeitem")
    private String codeItem;

    @Column(name="designation")
    private String designation;

    @Column(name="prixunitaireht")
    private BigDecimal prixUnitaireHt;

    @Column(name="tauxtva")
    private BigDecimal tauxTva;

    @Column(name="prixunitairettc")
    private BigDecimal prixUnitaireTtc;

    @ManyToOne
    @JoinColumn(name="idcategory")
    private Category category;

    @OneToMany(mappedBy = "item")
    private List<LigneCmdEmploye> ligneCmdEmployes;

    @OneToMany(mappedBy = "item")
    private List<MvtStk> mvtStks;

}
