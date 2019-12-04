package org.kd.main.common.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "credit_card")
public class CreditCard extends Card {

    private Float credit_limit;
}
