package fpt.se1601.giveandget.config;

import fpt.se1601.giveandget.reponsitory.entity.User;
import fpt.se1601.giveandget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        try {
            List<User> roleUserUsers = userService.getUsersHaveRole("USER");
            List<User> roleAdminUsers = userService.getUsersHaveRole("ADMIN");
            List<User> roleModUsers = userService.getUsersHaveRole("MOD");
            for(User user : roleUserUsers)
                auth.inMemoryAuthentication().withUser(user.getEmail()).password(user.getPassword()).roles("USER");
            for(User user : roleAdminUsers)
                auth.inMemoryAuthentication().withUser(user.getEmail()).password(user.getPassword()).roles("ADMIN");
            for(User user : roleModUsers)
                auth.inMemoryAuthentication().withUser(user.getEmail()).password(user.getPassword()).roles("MOD");
            auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        // Các trang không yêu cầu login
        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();

        // Trang /userInfo yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
        // Nếu chưa login, nó sẽ redirect tới trang /login.
        http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('USER', 'ADMIN')");

        // Trang chỉ dành cho ADMIN
        http.authorizeRequests().antMatchers("/admin").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/books/{\\d+}/abc").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/books/**").hasRole("ADMIN");
        // Khi người dùng đã login, với vai trò XX.
        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        // Ngoại lệ AccessDeniedException sẽ ném ra.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Cấu hình cho Login Form.
        http.authorizeRequests().and().formLogin()//
                // Submit URL của trang login
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/userInfo")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
    }
}
