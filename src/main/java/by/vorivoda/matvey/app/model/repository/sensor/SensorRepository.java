package by.vorivoda.matvey.app.model.repository.sensor;

import by.vorivoda.matvey.app.model.entity.sensor.Sensor;
import by.vorivoda.matvey.app.model.repository.IDaoRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SensorRepository implements IDaoRepository<Long, Sensor> {

    private final SessionFactory sessionFactory;

    @Autowired
    public SensorRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Sensor entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    public Sensor getById(Long id) {
        return sessionFactory.getCurrentSession().get(Sensor.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Sensor> getAll() {
        Session current = sessionFactory.getCurrentSession();
        return current.createQuery("from Sensor").list();
    }

    @SuppressWarnings("unchecked")
    public List<Sensor> getAllByCondition(String condition) {
        Session current = sessionFactory.getCurrentSession();
        Query<Sensor> query = current.createQuery("from Sensor " +
                "where name like :cond " +
                "or model like :cond " +
                "or STR(rangeFrom) like :cond " +
                "or STR(rangeTo) like :cond " +
                "or sensorType like :cond " +
                "or unitType like :cond " +
                "or location like :cond " +
                "or description like :cond");
        query.setParameter("cond", "%" + condition + "%");
        return query.list();
    }

    @Override
    public void update(Long id, Sensor sensor) {
        if (!id.equals(sensor.getId())) sensor.setId(id);
        sessionFactory.getCurrentSession().update(sensor);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Sensor.class, id));
    }
}
