package application.datalevel.DAO.interfaces;

public interface DAO<T> {

    T create(T obj);

    T update(int id, T obj);

    T read(int id);

    Integer delete(int id);

}