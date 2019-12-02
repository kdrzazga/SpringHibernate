package org.kd.main.common.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Log implements Serializable {

    @Id
    private Long id;

    private Timestamp created;
    private String file_path;

    public Log(Timestamp created, String file_path) {
        this.created = created;
        this.file_path = file_path;
    }
}
