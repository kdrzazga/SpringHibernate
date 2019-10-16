package org.kd.main.common.entities;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@DiscriminatorValue("FALSE")
public class ExternalTransfer extends Transfer{

    public ExternalTransfer(Long sourceFundId, Long destFundId, Float units){
        super(sourceFundId, destFundId, units, false);
    }

}
