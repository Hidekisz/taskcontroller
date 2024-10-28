package br.com.vini.taskcontroller.dto;

import br.com.vini.taskcontroller.entity.GymImageEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GerarPdfDTO{

        public String imageId;

        public String user;

        public LocalDate date;

        public String image;

    public GerarPdfDTO(GymImageEntity gymImageEntity){
        this.imageId = gymImageEntity.getImageId();
        this.user = gymImageEntity.getUser();
        this.date = gymImageEntity.getDate();
        this.image = gymImageEntity.getGridFsId();
    }
}
