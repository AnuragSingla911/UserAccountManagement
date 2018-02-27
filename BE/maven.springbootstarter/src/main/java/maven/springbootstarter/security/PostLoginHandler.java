package maven.springbootstarter.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PostLoginHandler extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationEntryPoint{





    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
             {

       

        System.out.println("on auth success :"+response.getStatus());
        clearAuthenticationAttributes(request);

        response.setStatus(200);


    }



    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        System.out.println("unauthorized");
        httpServletResponse.addHeader("Access-Control-Allow-Credentials","true");
        httpServletResponse.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized" );
    }
}
