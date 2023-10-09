package tech.leonam.openmarket.model.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ErroListDto {
    private HttpStatus status;
    private List<String> errors;
}
