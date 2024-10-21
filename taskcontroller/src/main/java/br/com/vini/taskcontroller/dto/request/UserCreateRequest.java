package br.com.vini.taskcontroller.dto.request;

import br.com.vini.taskcontroller.enums.RolesEnum;

public record UserCreateRequest(String login, String password, RolesEnum role) {
}
