package br.com.vini.taskcontroller.dto.response;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "pdf")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PdfFollowUpResponse {

    byte[] pdf;

}
