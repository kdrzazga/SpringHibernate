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
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    protected Account account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bankId", referencedColumnName = "id")
    protected Bank bank;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    protected Currency currency;

    @Column(name = "is_active")
    protected Boolean active;

    protected Float balance;
}
