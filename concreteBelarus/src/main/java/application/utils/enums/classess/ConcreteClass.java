package application.utils.enums.classess;

public enum ConcreteClass {

    C8_10("C8/10"),
    C12_15("C12/15"),
    C16_20("C16/20"),
    C18_22("C18/22,5"),
    C20_25("C20/25"),
    C25_30("C25/30"),
    C27_32("C27/32"),
    C30_37("C30/37");

    private final String concreteClassName;

    ConcreteClass(String concreteClassName) {
        this.concreteClassName = concreteClassName;
    }

    public String getConcreteClassName() {
        return concreteClassName;
    }

}
