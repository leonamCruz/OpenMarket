package tech.leonam.openmarket.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {
    MANAGER("manager"),
    CASHIER("cashier"),
    ADMINISTRATIVE("administrative"),
    COUNTER("counter");

    private final String description;

    public static RoleEnum descriptionToEnum(String description) {
        for (var roleEnum : values()) {
            if (roleEnum.description.equals(description)) {
                return roleEnum;
            }
        }
        throw new IllegalArgumentException();
    }

}
