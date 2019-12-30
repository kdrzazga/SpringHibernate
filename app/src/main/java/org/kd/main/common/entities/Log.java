package org.kd.main.common.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.sql.Timestamp;
import java.util.logging.Logger;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Log implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Timestamp created;

    @Column(name = "file_path")
    private String filepath;

    public Log(Timestamp created, String file_path) {
        this.created = created;
        this.filepath = file_path;
    }

    public boolean addMessage(String message) {
        try {
            var writer = new FileWriter(new File(this.filepath), true);
            writer.write(message + System.lineSeparator());
            writer.close();
            return true;
        } catch (IOException e) {
            Logger.getLogger(Log.class.getSimpleName()).warning("Problem writing to file " + this.filepath);
            Logger.getLogger(Log.class.getSimpleName()).warning(e.getMessage());

            return false;
        }
    }

}
