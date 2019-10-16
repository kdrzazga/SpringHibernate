package org.kd.main.common.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class Card implements Serializable {

    @Id
    protected Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    protected Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    protected Bank bank;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    protected Currency currency;

    protected Float balance;
}