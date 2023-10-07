package tech.leonam.openmarket.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {
    MANAGER(0,"manager"),
    CASHIER(1,"cashier"),
    ADMINISTRATIVE(2,"administrative"),
    COUNTER(3,"counter");

    private final int code;
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
