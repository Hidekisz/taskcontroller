package br.com.vini.taskcontroller.controller;

import br.com.vini.taskcontroller.dto.request.ConsultDateRequest;
import br.com.vini.taskcontroller.dto.request.CreateDailyFollowUpRequest;
import br.com.vini.taskcontroller.dto.response.PdfFollowUpResponse;
import br.com.vini.taskcontroller.facade.DailyFollowUpFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("api/followup/gym")
public class DailyController {

    @Autowired
    private DailyFollowUpFacade dailyFollowUpFacade;
    
    /*
    *  Get followUp returns pdf all days
    *  Get followUp dayly returns pdf day.now
    *  Get followUp day x returns pdf day x
    *  Post followUp daily post and returns pdf day.now
    *  Delete acompanhamento day x returns message ok if exists
    *  Get openai asks (later)
    */

    //    Get followUp todos os dias returns pdf all days
    @GetMapping()
    public ResponseEntity<PdfFollowUpResponse> getAllGymFollowUp(){
        PdfFollowUpResponse pdfFollowUpResponse = dailyFollowUpFacade.gerarListAllPdf();
        return ResponseEntity.ok(pdfFollowUpResponse);
    }

    //    Get followUp do dia returns pdf day.now
    @GetMapping("/day")
    public ResponseEntity<PdfFollowUpResponse> getGymFollowUpByDate(@RequestBody ConsultDateRequest consultDateRequest){
        PdfFollowUpResponse pdfFollowUpResponse = dailyFollowUpFacade.gerarPdfDaily(consultDateRequest);
        return ResponseEntity.ok(pdfFollowUpResponse);
    }

    @GetMapping("/day/today")
    public ResponseEntity<PdfFollowUpResponse> getGymFollowUpToday(){

        LocalDate localDateTime = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        localDateTime.format(dateTimeFormatter);

        PdfFollowUpResponse pdfFollowUpResponse = dailyFollowUpFacade.gerarPdfToday(localDateTime);
        return ResponseEntity.ok(pdfFollowUpResponse);

    }

    //    Post followUp daily post and returns pdf day.now
    @PostMapping("/insert")
    public ResponseEntity<String> insertFollowUp(@RequestBody CreateDailyFollowUpRequest daily){
        String result = dailyFollowUpFacade.uploadImagemDay(daily);
        return ResponseEntity.ok(result);
    }

    //    Delete acompanhamento day x returns message ok if exists
    @DeleteMapping("/delete/{date}")
    public ResponseEntity<String> deleteFollowUpByDay(@PathVariable("date") Date date){
        return ResponseEntity.ok().build();
    }
}
