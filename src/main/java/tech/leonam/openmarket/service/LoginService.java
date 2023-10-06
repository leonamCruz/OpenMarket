package tech.leonam.openmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.leonam.openmarket.exception.UserOrPasswordException;
import tech.leonam.openmarket.model.dto.LoginDto;
import tech.leonam.openmarket.model.dto.LoginDtoResponse;
import tech.leonam.openmarket.model.entity.LoginEntity;
import tech.leonam.openmarket.repository.RespositoryLogin;

@Service
public class LoginService {
    @Autowired
    private RespositoryLogin respositoryLogin;

    public LoginDtoResponse login(LoginDto login) throws UserOrPasswordException {
        var user = userExists(login);

        if (!user.getPassword().equals(login.getPassword())) throw new UserOrPasswordException("User or Password incorrect");

        var loginResponse = new LoginDtoResponse();

        loginResponse.setCpf(user.getCpf());
        loginResponse.setCategory(user.getCategory());

        return loginResponse;
    }

    public LoginEntity userExists(LoginDto login) throws UserOrPasswordException {
        return respositoryLogin.findById(login.getCpf()).orElseThrow(() -> new UserOrPasswordException("User or Password incorrect"));
    }

}
