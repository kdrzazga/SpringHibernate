package org.kd.main.common.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "transfer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "internal",
        discriminatorType = DiscriminatorType.STRING)
public class Transfer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "src_account_id")
    protected Account srcAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dest_account_id")
    protected Account destAccount;

    protected Float units;
    @Column(insertable = false, updatable = false)
    private Boolean internal;

    public Transfer(Account srcAccountId, Account destAccountId, float units, boolean internal) {
        this.srcAccount = srcAccountId;
        this.destAccount = destAccountId;
        this.units = units;
        this.internal = internal;
    }

    @Override
    public String toString() {
        return "id=" + this.id + " from " + this.srcAccount + " to " + this.destAccount + " units " + units;
    }
}