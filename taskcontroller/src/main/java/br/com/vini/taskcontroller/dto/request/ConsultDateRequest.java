package br.com.vini.taskcontroller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ConsultDateRequest(

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date
) {
}
