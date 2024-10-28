package br.com.vini.taskcontroller.dto.request;

import br.com.vini.taskcontroller.enums.DayType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
@Getter
public class ConsultDateRequest{

        @Nullable
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date;

        DayType dayType;
}
