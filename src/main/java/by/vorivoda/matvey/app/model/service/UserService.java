package by.vorivoda.matvey.app.model.service;

import by.vorivoda.matvey.app.model.entity.user.User;
import by.vorivoda.matvey.app.model.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService extends AbstractService<Long, User> implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    @Transactional
    public boolean add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return super.add(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) ((UserRepository) repository).getByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("User '" + username + "' not found.");

        return user;
    }

    @Autowired
    public void setRepository(UserRepository repository) {
        super.repository = repository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
