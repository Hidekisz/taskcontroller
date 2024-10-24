package br.com.vini.taskcontroller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.nio.file.Path;
import java.time.LocalDate;

public record CreateDailyFollowUpRequest(

        String filePath,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date
) {
}
