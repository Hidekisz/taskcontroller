package br.com.vini.taskcontroller.facade;

import br.com.vini.taskcontroller.dto.request.ConsultDateRequest;
import br.com.vini.taskcontroller.dto.response.PdfFollowUpResponse;
import br.com.vini.taskcontroller.enums.DayType;
import br.com.vini.taskcontroller.services.DailyFollowUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class DailyFollowUpFacade {

    @Autowired
    private DailyFollowUpService dailyFollowUpService;

    public PdfFollowUpResponse gerarPdfDaily(ConsultDateRequest consultDateRequest) {

        return dailyFollowUpService.gerarPdfToday(consultDateRequest.date());

    }

    public PdfFollowUpResponse gerarPdfToday(LocalDateTime localDateTime) {

        return dailyFollowUpService.gerarPdfToday(localDateTime);
    }

    public PdfFollowUpResponse gerarListAllPdf(){
        return dailyFollowUpService.gerarListaPdf();
    }
}
