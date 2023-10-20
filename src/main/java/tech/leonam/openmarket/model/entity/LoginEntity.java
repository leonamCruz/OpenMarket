package tech.leonam.openmarket.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tech.leonam.openmarket.model.enums.RoleEnum;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LoginEntity implements UserDetails {

    @Id
    private String cpf;
    private String password;
    private RoleEnum role;
    private LocalDateTime localDateTime;
    public static final SimpleGrantedAuthority ROLE_MANAGER = new SimpleGrantedAuthority("ROLE_MANAGER");
    public static final SimpleGrantedAuthority ROLE_ADMINISTRATIVE = new SimpleGrantedAuthority("ROLE_ADMINISTRATIVE");
    public static final SimpleGrantedAuthority ROLE_CASHIER = new SimpleGrantedAuthority("ROLE_CASHIER");

    public LoginEntity(String cpf, String password, RoleEnum role) {
        this.cpf = cpf;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return switch (this.role){
            case MANAGER -> List.of(ROLE_MANAGER,ROLE_ADMINISTRATIVE,ROLE_CASHIER);
            case ADMINISTRATIVE -> List.of(ROLE_ADMINISTRATIVE,ROLE_CASHIER);
            case CASHIER -> List.of(ROLE_CASHIER);
        };

    }

    @Override
    public String getUsername() {
        return cpf;
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
