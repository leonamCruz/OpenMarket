package tech.leonam.openmarket.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tech.leonam.openmarket.service.LoginService;
import tech.leonam.openmarket.service.TokenService;

import java.io.IOException;
import java.util.Objects;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    private final LoginService loginService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            var login = tokenService.validationToken(token);
            var user = loginService.loadUserByUsername(login);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, Objects.requireNonNull(user).getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        return request.getHeader("Authorization") == null ? null : request.getHeader("Authorization").replace("Bearer ", "");
    }
}

