package br.com.jorgerabellodev.eniacproject.api.models.entities.enums;

public enum PaymentStatus {

    PENDING(1, "Pending"),
    GONE(2, "Gone"),
    CANCELED(3, "Canceled");

    private final Integer code;
    private final String description;

    PaymentStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static PaymentStatus toEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (PaymentStatus state : PaymentStatus.values()) {
            if (code.equals(state.getCode())) {
                return state;
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
