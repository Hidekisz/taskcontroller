package br.com.vini.taskcontroller.entity;

import br.com.vini.taskcontroller.enums.RolesEnum;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document(collection = "usuarios")
@Getter
@Setter
@EqualsAndHashCode(of ="userId")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    public String userId;

    public String login;

    public String password;

    public RolesEnum role;

    public UserEntity(String login, String password, RolesEnum role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

//    public UserEntity(UserEntity userEntity) {
//        this.userId = userEntity.getUserId();
//        this.login = userEntity.getLogin();
//        this.role = userEntity.getRole();
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == RolesEnum.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
