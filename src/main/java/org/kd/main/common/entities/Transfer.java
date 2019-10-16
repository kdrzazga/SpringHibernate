package org.kd.main.common.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bytecode.constant.LongConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Transfer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long src_customer_id;
    private Long dest_customer_id;
    private Float units;
    private Boolean internal;

    public Transfer(long src_customer_id, long dest_customer_id, float units, boolean internal) {
        this.src_customer_id = src_customer_id;
        this.dest_customer_id = dest_customer_id;
        this.units = units;
        this.internal = internal;
    }
}