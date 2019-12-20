package org.kd.main.common.entities;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@DiscriminatorValue("TRUE")
public class InternalTransfer extends Transfer {

    public InternalTransfer(Account srcAccount, Account destAccount, Float units){
        super(srcAccount, destAccount, units, true);
    }
}
