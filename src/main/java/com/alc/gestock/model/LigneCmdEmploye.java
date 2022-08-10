package com.alc.gestock.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="lignecmdemploye")
public class LigneCmdEmploye extends AbstractEntity{

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "prixUnitaire")
    private BigDecimal prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "iditem")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "idcmdemploye")
    private CommandeEmploye commandeEmploye;
}
