package br.com.jorgerabellodev.eniacproject.api.models.entities.enums;

public enum CustomerType {

    INDIVIDUAL(1, "Individual Entity"),
    LEGAL(2, "Legal Entity");

    private final Integer code;
    private final String description;

    CustomerType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static CustomerType toEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (CustomerType type : CustomerType.values()) {
            if (code.equals(type.getCode())) {
                return type;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
