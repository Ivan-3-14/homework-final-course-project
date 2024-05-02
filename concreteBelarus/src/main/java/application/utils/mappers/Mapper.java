package application.utils.mappers;

public interface Mapper<T, R> {

    R dtoToEntity(T t);

    T entityToDTO(R r);

}
