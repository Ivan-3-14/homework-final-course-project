package application.entity.enums.frostresistance;

public enum FrostResistance {
    F50(50),
    F100(100),
    F150(150),
    F200(200),
    F300(300);

    private final int valueFR;

    FrostResistance(int valueFR) {
        this.valueFR = valueFR;
    }

}
