package co.edu.unicauca.administracionDocumental_ms.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public abstract class File {
    private String name;
    private String path;
    private Date dateUpload;

}
