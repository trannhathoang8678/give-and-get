package fpt.se1601.giveandget.interceptor;

import fpt.se1601.giveandget.reponsitory.entity.ApiEntity;
import fpt.se1601.giveandget.service.TokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class GatewayInterceptor implements HandlerInterceptor {
    private static Logger logger = LogManager.getLogger(GatewayInterceptor.class);
    @Autowired
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            return verifyRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    private boolean verifyRequest(HttpServletRequest request) {
        String httpMethod = request.getMethod();
        String servletPath = request.getServletPath();
        String accessToken = request.getHeader(GatewayConstant.AUTHORIZATION_HEADER);
        String role = null;
        if (accessToken != null)
            System.out.println(accessToken);
        else
            System.out.print ("There is no access token ");
        if (accessToken != null)
            role = tokenService.getTokenRole(accessToken);
        if(role!=null)
        System.out.println(servletPath + "\n" + role);
        if (role != null && role.equals("ADMIN"))
            return true;
        ApiEntity apiEntity = getMatchingAPI(httpMethod, servletPath);
        if (apiEntity != null) {
            if (accessToken == null) {
                logger.error("Authorization field in header is null or empty");
                return false;
            }
            if (verifyRole(apiEntity.getRole(), role)) {
                logger.info("Request validated. Start forward request to controller");
                return true;
            } else {
                logger.info("User don't have permission to access");
                return false;
            }
        }
        logger.info("Request validated. Start forward request to controller");
        return true;
    }

    private boolean verifyRole(String pathRole, String userRole) {
        String roles[] = pathRole.split("&");
        for (String role : roles)
            if (role.equals(userRole)) {
                System.out.println(role + '\n' + userRole);
                return true;
            }
        return false;
    }

    private ApiEntity getMatchingAPI(String httpMethod, String path) {
        AntPathMatcher matcher = new AntPathMatcher();
        for (ApiEntity apiEntity : GatewayConstant.apiEntities) {
            if (matcher.match(apiEntity.getPattern(), path) && httpMethod.equals(apiEntity.getHttpMethod())) {
                logger.info("Found api matched");
                return apiEntity;
            }
        }
        return null;
    }

}
