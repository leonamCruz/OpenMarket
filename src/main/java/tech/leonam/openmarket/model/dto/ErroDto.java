package tech.leonam.openmarket.model.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErroDto {
    private HttpStatus status;
    private String message;
}
