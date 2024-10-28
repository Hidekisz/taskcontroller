package br.com.vini.taskcontroller.controller;

import br.com.vini.taskcontroller.dto.GerarPdfDTO;
import br.com.vini.taskcontroller.dto.GerarPdfDtoList;
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

//    //    Get followUp todos os dias returns pdf all days
//    @GetMapping()
//    public ResponseEntity<PdfFollowUpResponse> getAllGymFollowUp(){
//        PdfFollowUpResponse pdfFollowUpResponse = dailyFollowUpFacade.gerarListAllPdf();
//        return ResponseEntity.ok(pdfFollowUpResponse);
//    }

    //    Get followUp do dia returns pdf day.now
    @GetMapping("/day")
    public ResponseEntity<GerarPdfDtoList> getGymFollowUpByDate(@RequestBody ConsultDateRequest consultDateRequest){
        GerarPdfDtoList pdfFollowUpResponse = dailyFollowUpFacade.gerarPdfByDate(consultDateRequest);
        return ResponseEntity.ok(pdfFollowUpResponse);
    }

    //    Post followUp daily post and returns pdf day.now
    @PostMapping("/insert")
    public ResponseEntity<GerarPdfDtoList> insertFollowUp(@RequestBody CreateDailyFollowUpRequest daily){
        GerarPdfDtoList result = dailyFollowUpFacade.uploadImagemDay(daily);
        return ResponseEntity.ok(result);
    }

//    //    Delete acompanhamento day x returns message ok if exists
//    @DeleteMapping("/delete/{date}")
//    public ResponseEntity<String> deleteFollowUpByDay(@PathVariable("date") LocalDate date){
//        return ResponseEntity.ok().build();
//    }
}
