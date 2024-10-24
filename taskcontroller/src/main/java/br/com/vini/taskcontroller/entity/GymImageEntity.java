package br.com.vini.taskcontroller.entity;

import br.com.vini.taskcontroller.enums.RolesEnum;
import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDate;

@Document(collection = "images")
@Getter
@Setter
@EqualsAndHashCode(of ="")
@AllArgsConstructor
@NoArgsConstructor
public class GymImageEntity {

    @Id
    public String imageId;

    public String user;

    public LocalDate date;

    public String gridFsId;

    public GymImageEntity(String user,LocalDate localDateTime, String gridFsId) {
        this.user = user;
        this.date = LocalDate.now();
        this.gridFsId = gridFsId;
    }

}
