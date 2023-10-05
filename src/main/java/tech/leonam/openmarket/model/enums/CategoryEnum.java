package tech.leonam.openmarket.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tech.leonam.openmarket.exception.CategoryEnumException;

@Getter
@AllArgsConstructor
public enum CategoryEnum {

    GROCERY(1, "Mercearia"),
    HORTI_FRUTI(2, "Hortifrúti"),
    MEAT(3, "Carnes"),
    DAIRY(4, "Laticínios"),
    FROZEN_FOOD(5, "Congelados"),
    BEVERAGES(6, "Bebidas"),
    SNACKS(7, "Lanches"),
    CLEANING_SUPPLIES(8, "Produtos de Limpeza"),
    PERSONAL_CARE(9, "Cuidados Pessoais"),
    HOME_AND_KITCHEN(10, "Casa e Cozinha"),
    ELECTRONICS(11, "Eletrônicos"),
    CLOTHING(12, "Vestuário"),
    SPORTS_AND_OUTDOORS(13, "Esportes e Ar Livre"),
    TOYS(14, "Brinquedos"),
    OTHER(15, "Outros");

    private final int code;
    private final String description;

    public static String codeToDescription(int code) throws CategoryEnumException {
        for (var category : CategoryEnum.values()) {
            if (category.getCode() == code) {
                return category.getDescription();
            }
        }

        throw new CategoryEnumException("Code not found");
    }

    public static int descriptionToCode(String description) throws CategoryEnumException {
        for (var category : CategoryEnum.values()) {
            if (category.getDescription().equals(description)) {
                return category.getCode();
            }
        }

        throw new CategoryEnumException("Description not found");
    }
}
