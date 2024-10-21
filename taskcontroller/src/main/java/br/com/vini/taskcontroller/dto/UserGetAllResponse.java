package br.com.vini.taskcontroller.dto;

import br.com.vini.taskcontroller.enums.RolesEnum;
import org.springframework.data.annotation.Id;

public class UserGetAllResponse {

    public String userId;

    public String login;

    public RolesEnum role;
}
