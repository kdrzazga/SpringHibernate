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

    @Column(name="src_account_id")
    protected Long srcAccountId;

    @Column(name="dest_account_id")
    protected Long destAccountId;
    protected Float units;
    @Column(insertable = false, updatable = false)
    private Boolean internal;

    public Transfer(long srcAccountId, long destAccountId, float units, boolean internal) {
        this.srcAccountId = srcAccountId;
        this.destAccountId = destAccountId;
        this.units = units;
        this.internal = internal;
    }

    @Override
    public String toString() {
        return "id=" + this.id + " from " + this.srcAccountId + " to " + this.destAccountId + " units " + units;
    }
}