package tech.leonam.openmarket.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.leonam.openmarket.model.dto.LoginDto;
import tech.leonam.openmarket.model.dto.LoginDtoResponseWithToken;
import tech.leonam.openmarket.model.entity.LoginEntity;
import tech.leonam.openmarket.repository.RespositoryLogin;

@Service
public class LoginService implements UserDetailsService {

    private final RespositoryLogin respositoryLogin;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public LoginService(RespositoryLogin respositoryLogin, TokenService tokenService, @Lazy AuthenticationManager authenticationManager) {
        this.respositoryLogin = respositoryLogin;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        return respositoryLogin.findByCpf(cpf);
    }

    public LoginDtoResponseWithToken login(LoginDto login) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(login.getCpf(), login.getPassword());
        var auth = authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((LoginEntity) auth.getPrincipal());

        return new LoginDtoResponseWithToken(login.getCpf(), token);
    }

}
