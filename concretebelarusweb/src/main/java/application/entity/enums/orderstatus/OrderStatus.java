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
}
