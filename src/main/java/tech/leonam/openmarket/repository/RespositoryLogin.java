package tech.leonam.openmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import tech.leonam.openmarket.model.entity.LoginEntity;

@Repository
public interface RespositoryLogin extends JpaRepository<LoginEntity,String> {
    UserDetails findByCpf(String cpf);
}
