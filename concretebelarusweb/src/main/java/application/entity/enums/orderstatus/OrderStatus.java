package application.entity.enums.orderstatus;

public enum OrderStatus {

    NEW("новый"),
    IN_WORK("в работе"),
    CLOSED("закрыт");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static String getOrderStatusFromStringStatus(String search) {
        String result = null;
        switch (search) {
            case "новый":
                result = "NEW";
                break;
            case "в работе":
                result = "IN_WORK";
                break;
            case "закрыт":
                result = "CLOSED";
                break;
            default:
        }
        return result;
    }
}
