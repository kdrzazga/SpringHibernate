package org.kd.main.common.entities;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@DiscriminatorValue("TRUE")
public class InternalTransfer extends Transfer {

    public InternalTransfer(Long sourceFundId, Long destFundId, Float units){
        super(sourceFundId, destFundId, units, true);
    }
}
