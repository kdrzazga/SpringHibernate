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
public abstract class Transfer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected Long src_customer_id;
    protected Long dest_customer_id;
    protected Float units;
    @Column(insertable = false, updatable = false)
    private Boolean internal;

    public Transfer(long src_customer_id, long dest_customer_id, float units, boolean internal) {
        this.src_customer_id = src_customer_id;
        this.dest_customer_id = dest_customer_id;
        this.units = units;
        this.internal = internal;
    }
}