package fpt.se1601.giveandget.service;

import fpt.se1601.giveandget.reponsitory.UserRepository;
import fpt.se1601.giveandget.reponsitory.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public List<User> getUsersHaveRole(String role) {
        try {
            return userRepository.findByRole(role);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

