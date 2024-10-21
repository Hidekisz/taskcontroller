package br.com.vini.taskcontroller.controller;

import br.com.vini.taskcontroller.config.security.TokenService;
import br.com.vini.taskcontroller.dto.AuthenticationDTO;
import br.com.vini.taskcontroller.dto.request.UserCreateRequest;
import br.com.vini.taskcontroller.dto.request.UserUpdateRequest;
import br.com.vini.taskcontroller.dto.response.LoginResponse;
import br.com.vini.taskcontroller.dto.response.UserCreateResponse;
import br.com.vini.taskcontroller.dto.response.UserUpdateResponse;
import br.com.vini.taskcontroller.entity.UserEntity;
import br.com.vini.taskcontroller.repositories.UserRepository;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public String getAllUsers(){
        return "oi";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Integer userId) {
        return "oi";
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@Valid @RequestBody AuthenticationDTO userCreateRequest){
        var usernamePassword = new UsernamePasswordAuthenticationToken(userCreateRequest.login(),userCreateRequest.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserEntity)auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/registerUser")
    public ResponseEntity<UserCreateResponse> registerUser(@Valid @RequestBody UserCreateRequest data){
        if(this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserEntity newUser = new UserEntity(data.login(),encryptedPassword,data.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public UserUpdateResponse updateUserById(UserUpdateRequest userUpdateRequest){
        UserUpdateResponse userUpdaTeResponse;
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Integer userId){
        return "oi";

    }}
