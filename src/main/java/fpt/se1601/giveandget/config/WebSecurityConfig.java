//package fpt.se1601.giveandget.config;
//
//import fpt.se1601.giveandget.reponsitory.entity.User;
//import fpt.se1601.giveandget.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.util.List;
//
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    UserService userService;
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        try {
//            List<User> roleUserUsers = userService.getUsersHaveRole("USER");
//            List<User> roleAdminUsers = userService.getUsersHaveRole("ADMIN");
//            List<User> roleModUsers = userService.getUsersHaveRole("MOD");
//            for(User user : roleUserUsers)
//                auth.inMemoryAuthentication().withUser(user.getPhone()).password(user.getPassword()).roles("USER");
//            for(User user : roleAdminUsers)
//                auth.inMemoryAuthentication().withUser(user.getPhone()).password(user.getPassword()).roles("ADMIN");
//            for(User user : roleModUsers)
//                auth.inMemoryAuthentication().withUser(user.getPhone()).password(user.getPassword()).roles("MOD");
//            auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http
//                .authorizeRequests()
//                .antMatchers("/register").permitAll()
//                .antMatchers("/").hasRole("ADMIN")
//                .antMatchers("/admin").hasRole("ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/j_spring_security_check")
//                .usernameParameter("phone")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/")
//                .failureUrl("/login?error")
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/403");
//    }
//
//}
