package br.com.vini.taskcontroller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.nio.file.Path;
import java.time.LocalDateTime;

public record CreateDailyFollowUpRequest(

        Path filePath,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDateTime date
) {
}
