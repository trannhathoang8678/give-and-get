package fpt.se1601.giveandget.service;

import fpt.se1601.giveandget.reponsitory.UserRepository;
import fpt.se1601.giveandget.reponsitory.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService  {
    @Autowired
    UserRepository userRepository;
    public List<User> getUsersHaveRole(String role){
        try{
        return userRepository.findByRole(role);
    }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRepository.findOneByPhone(phone);
        if (user == null) {
            System.out.println(phone.length());
            System.out.println("User not found! " + phone);
            throw new UsernameNotFoundException("User has" + phone + " was not found in the database");
        }
        System.out.println("Found User: " + user);

        List<String> roles = Arrays.stream(user.getRole().split(",")).collect(Collectors.toList());

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roles != null) {
            for (String role : roles) {
                grantList.add(new SimpleGrantedAuthority(role));
            }
        }

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getPhone(),
                user.getPassword(), grantList);
        return userDetails;
    }
}

