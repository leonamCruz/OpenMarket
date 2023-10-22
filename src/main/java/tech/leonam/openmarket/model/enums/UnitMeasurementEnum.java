package tech.leonam.openmarket.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UnitMeasurementEnum {
    UNIT(0,"Unidade(s)"),
    DOZEN(1,"Dúzia"),
    KG(2,"Quilo(s)"),
    BARS(3, "Barra(s)");

    private final int code;
    private final String description;

    public static UnitMeasurementEnum descriptionToEnum(String description) {
        for (var unitMeasurementEnum : values()) {
            if (unitMeasurementEnum.description.equals(description)) {
                return unitMeasurementEnum;
            }
        }
        throw new IllegalArgumentException();
    }

}
