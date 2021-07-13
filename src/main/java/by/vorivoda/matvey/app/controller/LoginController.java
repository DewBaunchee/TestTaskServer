package by.vorivoda.matvey.app.controller;

import by.vorivoda.matvey.app.security.jwt.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class LoginController {

    private final TokenService tokenService;

    @Autowired
    public LoginController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping
    public String availableCheck() {
        return "Available.";
    }

    @PostMapping(path = "/login")
    public String authenticate(@RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String authentication) {
        if (authentication == null)
            throw new AuthenticationCredentialsNotFoundException("Specify username and password.");

        authentication = authentication.trim();
        if (authentication.indexOf("Basic") > 0)
            throw new AuthenticationCredentialsNotFoundException("Invalid credential type.");

        authentication = new String(Base64.getDecoder().decode(authentication.substring("Basic".length()).trim()));
        String username = authentication.substring(0, authentication.indexOf(":"));
        String password = authentication.substring(authentication.indexOf(":") + 1);
        return "{ \"token\": \"" + tokenService.generateToken(username, password) + "\"," +
                "\"canModify\": " + canModify(username) + " }";
    }

    public Boolean canModify(String username) {
        return tokenService.getUserService().loadUserByUsername(username)
                .getAuthorities().stream()
                .anyMatch(value -> value.getAuthority().equals("ROLE_ADMINISTRATOR"));
    }
}
