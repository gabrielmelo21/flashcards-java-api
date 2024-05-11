package api.englishAPI.service;

import api.englishAPI.model.User;
import api.englishAPI.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void saveNewUser(User userDTO) {

        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userRepository.save(user);


    }

    public boolean verifyUser(String email){
        return userRepository.existsByEmail(email);
    }

    public List<User> listAllUsers(){
         return userRepository.findAll();
    }

    public void showUserData() {

    }


}
