package studs;

import dbal.repository.IRepository;
import dbal.specification.Specifiable;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public class AbstractRepoBookStud implements IRepository {
    @Override
    public Class getDomainClass() {
        return null;
    }

    @Override
    public Session openSession() {
        return null;
    }

    @Override
    public void delete(Serializable serializable) {

    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public void delete(Iterable entities) {

    }

    @Override
    public Object findOne(Serializable serializable) {
        return null;
    }

    @Override
    public Object findOne(Specifiable spec) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public List findAll(Specifiable spec) {
        return null;
    }

    @Override
    public List save(Iterable entities) {
        return null;
    }

    @Override
    public Object save(Object entity) {
        return null;
    }
}
