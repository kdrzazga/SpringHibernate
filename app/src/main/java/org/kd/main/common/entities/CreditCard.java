package org.kd.main.common.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "credit_card")
public class CreditCard extends Card {

    private Float credit_limit;
}
