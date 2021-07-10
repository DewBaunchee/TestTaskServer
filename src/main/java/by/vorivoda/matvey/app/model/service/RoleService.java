package by.vorivoda.matvey.app.model.service;

import by.vorivoda.matvey.app.model.entity.user.Role;
import by.vorivoda.matvey.app.model.entity.user.User;
import by.vorivoda.matvey.app.model.repository.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RoleService extends AbstractService<Long, Role> {

    @Transactional
    public Role loadRoleByName(String name) {
        return (Role) ((RoleRepository) repository).getByName(name);
    }

    @Autowired
    public void setRepository(RoleRepository repository) {
        super.repository = repository;
    }
}
