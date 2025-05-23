package br.com.vini.taskcontroller.dto.request;

import br.com.vini.taskcontroller.enums.RolesEnum;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    public String userId;

    public String login;

    public String password;

    public RolesEnum role;

}
