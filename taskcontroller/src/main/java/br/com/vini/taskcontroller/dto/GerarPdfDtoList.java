package br.com.vini.taskcontroller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GerarPdfDtoList{

    private List<GerarPdfDTO> gerarPdfDTO;
}
