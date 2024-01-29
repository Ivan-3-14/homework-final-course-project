package exampl.utils;

import exampl.person.Person;

public class ConstantContainer {

    public static final String FROM_PERSON = "From " + Person.class.getSimpleName();
    public static final String WHERE = " where ";
    public static final String ORDER_BY_DTC = " order by date_time_create DESC";
    public static final String NULL_CONDITION = "";


    public static final String MORE  = " > ";
    public static final String LESS  = " < ";
    public static final String EQUALS = " = ";
    public static final String NO_EQUALS = " != ";
    public static final String MORE_OR_EQUALS  = " >= ";
    public static final String LESS_OR_EQUALS  = " <= ";
    public static final String COLON = " :";
    public static final String NULL = " 0";

    public static final String STRING_ID  = "id";
    public static final String STRING_AGE  = "age";
    public static final String STRING_SALARY  = "salary";
    public static final String STRING_PASSPORT  = "passport";
    public static final String STRING_ADDRESS  = "address";
    public static final String STRING_DATE_OF_BD  = "dateOfBirthday";
    public static final String STRING_TIME_TO_LUNCH  = "timeToLunch";
    public static final String STRING_DATA_TIME_CREATE  = "dataTimeCreate";

    public static final String ILL_ST_EX = "Unexpected value: ";

    private ConstantContainer() {}
}
