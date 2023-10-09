package tech.leonam.openmarket.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandSaveDto {
    @NotNull(message = "Nome não pode ser nulo.")
    private String name;
}
