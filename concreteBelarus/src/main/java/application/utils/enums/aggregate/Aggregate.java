package application.utils.enums.aggregate;

public enum Aggregate {

    CRUSHED_STONE("Щебень"),
    GRAVEL("Гравий");

    private final String valueOfAggregate;

    Aggregate(String valueOfAggregate) {
        this.valueOfAggregate = valueOfAggregate;
    }

    public String getValueOfAggregate() {
        return valueOfAggregate;
    }

    public static Aggregate getAggregateByValue(String value) {
        Aggregate result;
        switch (value) {
            case "CRUSHED_STONE":
                result = CRUSHED_STONE;
                break;
            case "GRAVEL":
                result = GRAVEL;
                break;
            default:
                result = null;
        }
        return result;
    }
}
