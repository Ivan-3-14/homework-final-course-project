package application.entity.enums.mobilityvalue;

public enum MobilityValue {

    P1("П1", 1),
    P2("П2", 2),
    P3("П3", 3),
    P4("П4", 4),
    P5("П5", 5);

    private final String valueOfMobility;
    private final int intValueOfMobility;

    MobilityValue(String valueOfMobility, int intValueOfMobility) {
        this.valueOfMobility = valueOfMobility;
        this.intValueOfMobility = intValueOfMobility;
    }

    public static MobilityValue getMobilityFromIntValue(int intValue) {
        MobilityValue result = null;
        switch (intValue) {
            case 1:
                result = P1;
                break;
            case 2:
                result = P2;
                break;
            case 3:
                result = P3;
                break;
            case 4:
                result = P4;
                break;
            case 5:
                result = P5;
                break;
            default:
        }
        return result;
    }

    public int getIntValueOfMobility() {
        return intValueOfMobility;
    }

    public String getValueOfMobility() {
        return valueOfMobility;
    }
}
