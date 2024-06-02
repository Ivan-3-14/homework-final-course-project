package application.entity.enums.roles;

public enum Roles {
    ADMIN("администратор"),
    MANAGER("менеджер"),
    USER("зарегистрирован"),
    SUPERUSER("не зарегистрирован");

    Roles(String role) {
        this.role = role;
    }

    private final String role;

    public String getStringRole() {
        return role;
    }
}
