package by.vorivoda.matvey.app.model.repository.user;

import by.vorivoda.matvey.app.model.entity.user.User;
import by.vorivoda.matvey.app.model.repository.IDaoRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements IDaoRepository<Long, User> {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public User getById(Long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        Session current = sessionFactory.getCurrentSession();
        return (List<User>) current.createQuery("from User").list();
    }

    @Override
    public void update(Long id, User user) {
        if (!id.equals(user.getId())) user.setId(id);
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(User.class, id));
    }

    @SuppressWarnings("unchecked")
    public UserDetails getByUsername(String username) throws UsernameNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        List<UserDetails> users = session.createQuery("from User where username = '" + username + "'").list();

        if (users.size() > 0) return users.get(0);
        return null;
    }
}
