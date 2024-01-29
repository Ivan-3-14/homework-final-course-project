package exampl.person;

import exampl.enums.Parameters;
import exampl.dao.PersonDAO;
import exampl.utils.Printer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static exampl.utils.ConstantContainer.*;

public class PersonDAOImpl implements PersonDAO {

    private final Session session;

    public PersonDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public void save(Person person) {
        Transaction transaction = session.beginTransaction();
        String hql = FROM_PERSON + WHERE + STRING_ID + MORE + NULL;
        List<Person> persons = session.createQuery(hql, Person.class).getResultList();

        for (Person p : persons) {
            if (
                    p.getAge().equals(person.getAge()) &&
                    p.getSalary().equals(person.getSalary()) &&
                    p.getPassport().equals(person.getPassport()) &&
                    p.getAddress().equals(person.getAddress()) &&
                    p.getDateOfBirthday().equals(person.getDateOfBirthday()) &&
                    p.getTimeToLunch().equals(person.getTimeToLunch())
            ) {
                Printer.printNotSuccessfulSaveMethodMessage();
                transaction.commit();
                return;
            }
        }
        session.save(person);
        Printer.printSuccessfulSaveMethodMessage();
        transaction.commit();
    }

    @Override
    public List<Person> findByParameter(Parameters parameterName, String mainCondition, Object parameter,
                                        String additionalCondition
    ) {
        List<Person> list = new ArrayList<>();
        Transaction transaction = session.beginTransaction();
        try {
            switch (parameterName) {
                case AGE:
                    list = find(session, parameter, mainCondition, STRING_AGE, additionalCondition);
                    break;
                case SALARY:
                    list = find(session, parameter, mainCondition, STRING_SALARY, additionalCondition);
                    break;
                case PASSPORT:
                    list = find(session, parameter, mainCondition, STRING_PASSPORT, additionalCondition);
                    break;
                case ADDRESS:
                    list = find(session, parameter, mainCondition, STRING_ADDRESS, additionalCondition);
                    break;
                case DATE_OF_BIRTHDAY:
                    ;
                    list = find(session, Date.valueOf(String.valueOf(parameter)), mainCondition, STRING_DATE_OF_BD,
                            additionalCondition
                    );
                    break;
                case TIME_TO_LUNCH:
                    list = find(session, Time.valueOf(String.valueOf(parameter)), mainCondition, STRING_TIME_TO_LUNCH,
                            additionalCondition);
                    break;
                case DATA_TIME_CREATED:
                    list = find(session, Timestamp.valueOf(String.valueOf(parameter)), mainCondition, STRING_DATA_TIME_CREATE,
                            additionalCondition);
                    break;
                case ID:
                    list = find(session, parameter, mainCondition, STRING_ID, additionalCondition);
                    break;
                default:
                    throw new IllegalStateException(ILL_ST_EX + parameter);
            }
        } catch (ClassCastException | IllegalArgumentException e) {
           Printer.printFindByParamExceptionMessage();
        } finally {
            transaction.commit();
        }

        return list;
    }

    private List<Person> find(Session session, Object parameter, String mainCondition, String fieldName,
                              String additionalCondition
    ){
        String hql = FROM_PERSON + WHERE + fieldName + mainCondition + COLON + fieldName + additionalCondition;
        Query<Person> query = session.createQuery(hql, Person.class).setParameter(fieldName, parameter);
        return query.list();
    }

}
