package by.vorivoda.matvey.app.model.repository.user;

import by.vorivoda.matvey.app.model.entity.user.Role;
import by.vorivoda.matvey.app.model.repository.IDaoRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository implements IDaoRepository<Long, Role> {

    private final SessionFactory sessionFactory;

    @Autowired
    public RoleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Role role) {
        sessionFactory.getCurrentSession().persist(role);
    }

    @Override
    public Role getById(Long id) {
        return sessionFactory.getCurrentSession().get(Role.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getAll() {
        Session current = sessionFactory.getCurrentSession();
        return (List<Role>) current.createQuery("from Role").list();
    }

    @Override
    public void update(Long id, Role role) {
        if (!id.equals(role.getId())) role.setId(id);
        sessionFactory.getCurrentSession().update(role);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Role.class, id));
    }

    @SuppressWarnings("unchecked")
    public Role getByName(String name) throws UsernameNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        List<Role> roles = session.createQuery("from Role where name = '" + name + "'").list();

        if (roles.size() > 0) return roles.get(0);
        return null;
    }
}
