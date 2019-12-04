package org.kd.main.common.entities;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FALSE")
@NoArgsConstructor
public class IndividualAccount extends Account {

    public IndividualAccount(String shortname, String name, Double units, Long bank_id) {
        super(false, shortname, name, units, bank_id);
    }
}
