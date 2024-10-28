package br.com.vini.taskcontroller.services;

import br.com.vini.taskcontroller.dto.request.ConsultDateRequest;
import br.com.vini.taskcontroller.dto.request.CreateDailyFollowUpRequest;
import br.com.vini.taskcontroller.dto.GerarPdfDtoList;
import br.com.vini.taskcontroller.dto.response.PdfFollowUpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
@Slf4j
public class DailyFollowUpService {

    @Autowired
    private ImagemService imagemService;

    @Autowired
    private AuthService authService;

    public GerarPdfDtoList montaDadosPdfToday(LocalDate date) {


        return imagemService.obterImagemPorData(date);
    }

    public GerarPdfDtoList montaDadosPdfDayX(ConsultDateRequest consultDateRequest) {
        LocalDate localDate = consultDateRequest.getDate();
        return imagemService.obterImagemPorData(localDate);
    }

//    public GerarPdfDtoList montaDadosPdfAllDays() {
//        return null;
//    }

    public GerarPdfDtoList uploadImagemDay(CreateDailyFollowUpRequest dailyFollowUpRequest) {

        String nomeUsuario = authService.getUsernameFromToken();
        LocalDate localDateTime = dailyFollowUpRequest.date();
        if(dailyFollowUpRequest.date() == null){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
            localDateTime = LocalDate.parse(LocalDate.now().format(dateTimeFormatter));
        }

        return imagemService.salvarImagem(dailyFollowUpRequest.filePath(),nomeUsuario,localDateTime);
    }
}
