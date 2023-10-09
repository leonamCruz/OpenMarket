package tech.leonam.openmarket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class BrandResponseDto {
    private long id;
    private String name;
}
