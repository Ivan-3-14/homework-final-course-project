package application.dao.daoClasses;

import application.dao.daoInterfaces.AddressDAO;
import application.dto.address.Address;

import javax.persistence.EntityManager;

@SuppressWarnings("checkstyle:WhitespaceAround")
public class AddressDAOImpl extends DAOImpl<Address> implements AddressDAO {

    public AddressDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Address> getEntityClass() {
        return Address.class;
    }

    @Override
    public void create(Address address) {
        super.create(address);
    }

    @Override
    public Address read(int id) {
        return super.read(id);
    }

    @Override
    public Address update(int id, Address address) {
        return super.update(id, address);
    }

    @Override
    public Integer delete(int id) {
        return super.delete(id);
    }
}
