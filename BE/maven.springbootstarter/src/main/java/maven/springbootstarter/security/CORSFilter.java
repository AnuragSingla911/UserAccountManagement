package maven.springbootstarter.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class CORSFilter extends OncePerRequestFilter {

    private final Logger LOG = LoggerFactory.getLogger(CORSFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        LOG.info("Adding CORS Headers ........................" + req.getMethod());

        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
        res.addHeader("Access-Control-Expose-Headers", "xsrf-token");


        res.setHeader("Access-Control-Allow-Credentials","true");

        if(req != null && req.getCookies() != null) {
            for (Cookie c : req.getCookies()) {
                res.addCookie(c);
            }
        }
        chain.doFilter(req, res);
    }
}