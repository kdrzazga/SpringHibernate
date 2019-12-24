package org.kd.main.common.entities;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@DiscriminatorValue("FALSE")
public class ExternalTransfer extends Transfer{

    public ExternalTransfer(Account sourceFund, Account destAccount, Double units){
        super(sourceFund, destAccount, units, false);
    }

}
