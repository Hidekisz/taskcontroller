package br.com.vini.taskcontroller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ConsultDateRequest(

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDateTime date
) {
}
