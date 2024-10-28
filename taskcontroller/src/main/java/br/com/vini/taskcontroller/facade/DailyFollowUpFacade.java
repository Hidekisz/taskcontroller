package br.com.vini.taskcontroller.facade;

import br.com.vini.taskcontroller.dto.request.ConsultDateRequest;
import br.com.vini.taskcontroller.dto.request.CreateDailyFollowUpRequest;
import br.com.vini.taskcontroller.dto.GerarPdfDtoList;
import br.com.vini.taskcontroller.services.AuthService;
import br.com.vini.taskcontroller.services.DailyFollowUpService;
import br.com.vini.taskcontroller.services.ImagemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class DailyFollowUpFacade {

    @Autowired
    private DailyFollowUpService dailyFollowUpService;

    @Autowired
    private ImagemService imagemService;

    @Autowired
    private AuthService authService;

    public GerarPdfDtoList gerarPdfByDate(ConsultDateRequest consultDateRequest) {


        return dailyFollowUpService.montaDadosPdfDayX(consultDateRequest);

    }

    public GerarPdfDtoList gerarPdfToday(LocalDate localDateTime) {

        return dailyFollowUpService.montaDadosPdfToday(localDateTime);
    }

//    public PdfFollowUpResponse gerarListAllPdf(){
//
//        GerarPdfDTO gerarPdfDTO = dailyFollowUpService.montaDadosPdfAllDays();
//        return null;
//    }

    public GerarPdfDtoList uploadImagemDay(CreateDailyFollowUpRequest dailyFollowUpRequest){
        return dailyFollowUpService.uploadImagemDay(dailyFollowUpRequest);
    }
}
