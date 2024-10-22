package br.com.vini.taskcontroller.services;

import br.com.vini.taskcontroller.dto.request.ConsultDateRequest;
import br.com.vini.taskcontroller.dto.response.PdfFollowUpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class DailyFollowUpService {
    public PdfFollowUpResponse gerarPdfToday(LocalDateTime date) {
//        Consulta banco de dados com date
//        retorna json com lugar da imagem e data





        PdfFollowUpResponse pdfFollowUpResponse = new PdfFollowUpResponse();
        byte[] pdf = new byte[1];
        pdfFollowUpResponse.setPdf(pdf);
        return pdfFollowUpResponse;
    }

    public PdfFollowUpResponse gerarPdfDayX(ConsultDateRequest consultDateRequest) {
        //Consulta banco de dados com consultDateRequest.date()
        //     retorna json com lugar da imagem e data
        PdfFollowUpResponse pdfFollowUpResponse = new PdfFollowUpResponse();
        byte[] pdf = new byte[1];
        pdfFollowUpResponse.setPdf(pdf);
        return pdfFollowUpResponse;
    }

    public PdfFollowUpResponse gerarListaPdf() {

        PdfFollowUpResponse pdfFollowUpResponse = new PdfFollowUpResponse();
        byte[] pdf = new byte[1];
        pdfFollowUpResponse.setPdf(pdf);
        return pdfFollowUpResponse;
    }
}
