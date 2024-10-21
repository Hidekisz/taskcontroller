package br.com.vini.taskcontroller.controller;

import br.com.vini.taskcontroller.config.security.TokenService;
import br.com.vini.taskcontroller.dto.AuthenticationDTO;
import br.com.vini.taskcontroller.dto.UserGetAllResponse;
import br.com.vini.taskcontroller.dto.request.UserCreateRequest;
import br.com.vini.taskcontroller.dto.response.LoginResponse;
import br.com.vini.taskcontroller.dto.response.UserCreateResponse;
import br.com.vini.taskcontroller.entity.UserEntity;
import br.com.vini.taskcontroller.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<UserGetAllResponse>> getAllUsers(){
        List<UserGetAllResponse> userEntities = userRepository.findAllNoPassword();
        return ResponseEntity.ok(userEntities);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") String userId) {
//        UserEntity userEntity = userRepository.findByUserId(userId);
//        return ResponseEntity.ok(new UserEntity(userEntity));
//    }

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

//    @PutMapping("/update/{id}")
//    public ResponseEntity<UserUpdateResponse> updateUserById(@RequestBody UserUpdateRequest userUpdateRequest, @PathVariable String userId){
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUserId(userId);
//        userEntity.setRole(userUpdateRequest.getRole() != null ? );
//        userEntity.setLogin(userUpdateRequest.getLogin());
//        userEntity.set
//        userRepository.save()
//
//        return ResponseEntity.ok().build();
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") String userId) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            userRepository.deleteById(userId);
            String message = String.format("Usuário de id %s e login %s foi apagado com sucesso!", userEntity.getUserId(), userEntity.getLogin());
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
    }

}
