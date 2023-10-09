package tech.leonam.openmarket.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class SupplierResponseDto {

    private long id;
    private String name;
    private String cnpj;
    private String address;
    private String numberPhone;
    private String email;

}
