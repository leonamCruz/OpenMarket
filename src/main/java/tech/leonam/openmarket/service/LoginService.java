package tech.leonam.openmarket.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.leonam.openmarket.model.dto.LoginDto;
import tech.leonam.openmarket.model.dto.LoginResponseDto;
import tech.leonam.openmarket.model.entity.LoginEntity;
import tech.leonam.openmarket.repository.RespositoryLogin;

@Service
@AllArgsConstructor
public class LoginService implements UserDetailsService {

    private final RespositoryLogin respositoryLogin;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        return respositoryLogin.findByCpf(cpf);
    }

    public LoginResponseDto login(LoginDto login) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(login.getCpf(), login.getPassword());
        var auth = authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((LoginEntity) auth.getPrincipal());

        var response = modelMapper.map(login, LoginResponseDto.class);
        response.setToken(token);

        return response;
    }

}
