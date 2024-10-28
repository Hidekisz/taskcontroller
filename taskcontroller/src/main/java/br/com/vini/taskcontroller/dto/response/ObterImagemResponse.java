package br.com.vini.taskcontroller.dto.response;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class ObterImagemResponse {

    public String imageId;

    public String user;

    public LocalDate date;

    public byte[] gridFsId;
}
