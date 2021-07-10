package by.vorivoda.matvey.app.config;

import by.vorivoda.matvey.app.model.entity.user.Role;
import by.vorivoda.matvey.app.model.entity.user.User;
import by.vorivoda.matvey.app.model.service.RoleService;
import by.vorivoda.matvey.app.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class InitializeUsers {

    private static final String role_viewer = "ROLE_VIEWER";
    private static final String role_admin = "ROLE_ADMINISTRATOR";

    @Autowired
    public InitializeUsers(UserService userService, RoleService roleService) {
        Role viewer = new Role(role_viewer);
        Role administrator = new Role(role_admin);

        if(roleService.loadRoleByName(role_viewer) == null) roleService.add(viewer);
        if(roleService.loadRoleByName(role_admin) == null) roleService.add(administrator);

        try {
            userService.loadUserByUsername("admin");
        } catch (Exception ignored) {
            User admin = new User("admin", "admin");
            admin.setRoles(Set.of(viewer, administrator));
            userService.add(admin);
        }

        try {
            userService.loadUserByUsername("user");
        } catch (Exception ignored) {
            User user = new User("user", "user");
            user.setRoles(Set.of(viewer));
            userService.add(user);
        }
    }
}
