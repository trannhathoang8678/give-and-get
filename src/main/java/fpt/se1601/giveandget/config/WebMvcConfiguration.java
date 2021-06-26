package fpt.se1601.giveandget.config;

import fpt.se1601.giveandget.interceptor.GatewayInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    GatewayInterceptor gatewayInterceptor;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(gatewayInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }

}
