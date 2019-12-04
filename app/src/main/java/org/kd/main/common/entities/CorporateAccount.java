package org.kd.main.common.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TRUE")

public class CorporateAccount extends Account {

    public CorporateAccount(){
        super();
    }

    public CorporateAccount(String shortname, String name, Double units, Long bank_id) {
        super(true, shortname, name, units, bank_id);
    }
}
