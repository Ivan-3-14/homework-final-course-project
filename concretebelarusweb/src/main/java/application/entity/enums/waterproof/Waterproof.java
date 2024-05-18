package application.entity.enums.waterproof;

public enum  Waterproof {
    W2(2),
    W4(4),
    W6(6),
    W8(8),
    W10(10),
    W12(12);

    private final int valueWP;

    Waterproof(int valueWP) {
        this.valueWP = valueWP;
    }

    public int getValueWP() {
        return valueWP;
    }
}
