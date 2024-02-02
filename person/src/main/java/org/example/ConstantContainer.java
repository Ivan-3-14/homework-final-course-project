package org.example;

public class ConstantContainer {

    public static final String CREATE_PERSON_TABLE = "create table people2.person("
            + "id INT PRIMARY KEY AUTO_INCREMENT, "
            + "age INT, salary DECIMAL, "
            + "passport CHAR(10), "
            + "address VARCHAR(200), "
            + "date_of_birthday DATE,"
            + "date_time_create TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
            + "time_to_lunch TIME, "
            + "letter TEXT)";
    public static final String DROP_PEOPLE_TABLE = "drop table people2.person";

    public static final int AGE_FOR_FILTER = 21;
    public static final String DATABASE_NAME = "people2";
    public static final String TABLE_NAME = "person";
    public static final String INSERT_PERSON_PREPARE_STATEMENT =
            "insert into person(age, salary, passport, address, date_of_birthday, date_time_create, time_to_lunch, letter)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String NAME_OF_TABLE_FOR_PRINT_META = "TABLE_NAME";
    public static final String NAME_OF_COLUMNS_FOR_PRINT_META = "COLUMN_NAME";
    public static final String TYPE_OF_COLUMNS_FOR_PRINT_META = "TYPE_NAME";
    public static final String SIZE_OF_COLUMNS_FOR_PRINT_META = "COLUMN_SIZE";

    public static final String SIZE = ", size: ";
    public static final String NAME = "), name: ";
    public static final String TYPE = ", type: ";

    public static final String  SELECT_ALL_FROM = "select * from ";
    public static final String  WHERE = " where ";
    public static final String AGE  = "age";
    public static final String ORDER_BY  = " order by ";
    public static final String D_T_C  = "date_time_create";

    public static final String MORE  = " > ";

    private ConstantContainer() {}
}
