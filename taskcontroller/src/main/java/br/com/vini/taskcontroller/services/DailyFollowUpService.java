package br.com.vini.taskcontroller.services;

import br.com.vini.taskcontroller.dto.request.ConsultDateRequest;
import br.com.vini.taskcontroller.dto.request.CreateDailyFollowUpRequest;
import br.com.vini.taskcontroller.dto.response.PdfFollowUpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class DailyFollowUpService {

    @Autowired
    private ImagemService imagemService;

    @Autowired
    private AuthService authService;

    public PdfFollowUpResponse gerarPdfToday(LocalDate date) {

        PdfFollowUpResponse pdfFollowUpResponse = new PdfFollowUpResponse();
//        byte[] pdf = chamadaServiceGeracaoPdf;
        byte[] pdf = new byte[1];
        pdfFollowUpResponse.setPdf(pdf);
        return pdfFollowUpResponse;
    }

    public PdfFollowUpResponse gerarPdfDayX(ConsultDateRequest consultDateRequest) {
//        Consulta banco de dados com consultDateRequest.date();
//        retorna json com lugar da imagem e data
//        criar service para gerar o pdf em array byte[]
//        usar json como datasource da criação do pdf jasper

        PdfFollowUpResponse pdfFollowUpResponse = new PdfFollowUpResponse();
//        byte[] pdf = chamadaServiceGeracaoPdf;
        byte[] pdf = new byte[1];
        pdfFollowUpResponse.setPdf(pdf);
        return pdfFollowUpResponse;
    }

    public PdfFollowUpResponse gerarListaPdf() {
//        Consulta banco de dados para trazer uma lista de todos os acompanhamentos
//        retorna json com lugar da imagem no sistema (temporário) e data
//        criar service para gerar o pdf em array byte[]
//        usar json como datasource da criação do pdf jasper

        PdfFollowUpResponse pdfFollowUpResponse = new PdfFollowUpResponse();
//        byte[] pdf = chamadaServiceGeracaoPdf;
        byte[] pdf = new byte[1];
        pdfFollowUpResponse.setPdf(pdf);
        return pdfFollowUpResponse;
    }

    public String uploadImagemDay(CreateDailyFollowUpRequest dailyFollowUpRequest) {
//        Consulta banco de dados para trazer uma lista de todos os acompanhamentos
//        retorna json com lugar da imagem no sistema (temporário) e data
//        criar service para gerar o pdf em array byte[]
//        usar json como datasource da criação do pdf jasper
        String nomeUsuario = authService.getUsernameFromToken();
        LocalDate localDateTime = dailyFollowUpRequest.date();
        if(dailyFollowUpRequest.date() == null){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
            localDateTime = LocalDate.parse(LocalDate.now().format(dateTimeFormatter));
        }
        String idImagem = imagemService.salvarImagem(dailyFollowUpRequest.filePath(),nomeUsuario,localDateTime);

//       PdfFollowUpResponse pdfFollowUpResponse = new PdfFollowUpResponse();
//       byte[] pdf = chamadaServiceGeracaoPdf;
//       byte[] pdf = new byte[1];
//       pdfFollowUpResponse.setPdf(pdf);

        return "Tudo certo, id da imagem: " + idImagem;
    }
}
