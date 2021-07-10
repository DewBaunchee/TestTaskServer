package by.vorivoda.matvey.app.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    public TokenAuthenticationFilter() {
        super("/sensors/**");
        setAuthenticationSuccessHandler((request, response, authentication) -> {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                request.getRequestDispatcher(request.getServletPath() +
                        (request.getPathInfo() == null ? "" : request.getPathInfo())).forward(request, response);
        });
        setAuthenticationFailureHandler((request, response, authenticationException) -> {
            response.getOutputStream().print(authenticationException.getMessage());
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null) {
            token = request.getParameter("token");
        } else {
            if (token.indexOf("Bearer") > 0) {
                token = null;
            } else {
                token = token.substring("Bearer".length()).trim();
            }
        }

        if (token == null) {
            Authentication authentication = new TokenAuthentication(null);
            authentication.setAuthenticated(false);
            return authentication;
        }

        TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
        return getAuthenticationManager().authenticate(tokenAuthentication);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }
}