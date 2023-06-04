package entities.enums;

public enum Naipes {
    OURO(1),
    COPAS(2),
    PAUS(3),
    ESPADAS(4);

    private final Integer value;

    Naipes(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
