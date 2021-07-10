package by.vorivoda.matvey.app.security.jwt;

import by.vorivoda.matvey.app.model.entity.user.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    @Value("${app.jwt.secret}")
    private String secret;
    @Value("${app.jwt.token.lifetime.minutes}")
    private Integer tokenLifetimeInMinutes;

    private UserDetailsService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserService(UserDetailsService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String generateToken(String username, String password) {

        if (username == null || password == null)
            return null;

        User user = (User) userService.loadUserByUsername(username);
        Map<String, Object> tokenData = new HashMap<>();
        if (passwordEncoder.matches(password, user.getPassword())) {

            tokenData.put("clientType", "user");
            tokenData.put("userID", user.getId());
            tokenData.put("username", user.getUsername());
            tokenData.put("token_create_date", new Date().getTime());

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, tokenLifetimeInMinutes);
            tokenData.put("token_expiration_date", calendar.getTime());

            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(calendar.getTime());
            jwtBuilder.setClaims(tokenData);

            return jwtBuilder.signWith(SignatureAlgorithm.HS512, secret).compact();
        } else {
            throw new AuthenticationServiceException("Wrong password.");
        }
    }

    public String getSecret() {
        return secret;
    }
}
