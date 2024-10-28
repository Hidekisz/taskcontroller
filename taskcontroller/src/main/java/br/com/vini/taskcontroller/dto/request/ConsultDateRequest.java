package br.com.vini.taskcontroller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class ConsultDateRequest{
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date;
}
