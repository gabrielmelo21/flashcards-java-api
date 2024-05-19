package api.englishAPI.service;


import api.englishAPI.dto.UserDTO;
import api.englishAPI.model.Users;
import api.englishAPI.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public boolean saveNewUser(UserDTO userDTO) {
        Users user = new Users();
        BeanUtils.copyProperties(userDTO, user);

        // ENCRYPTAR A SENHA AQUI

        if (!verifyUser(user.getEmail())){
            userRepository.save(user);
            return true;
        }else{
            return false;
        }



    }

    public boolean verifyUser(String email){
        // Verifica se o email é válido
        if(email == null || email.isEmpty()) {
            System.out.println("Email inválido ou vázio");
            return false;
        }

        return userRepository.existsByEmail(email);
    }

    public List<Users> listAllUsers(){
         return userRepository.findAll();
    }

    public Users showUserData(UUID id) {
        Optional<Users> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public UUID getUserIdByEmail(String email) {
        Users user = userRepository.findByEmail(email);
        if (user != null) {
            return user.getId();
        }
        return null;
    }




    public void deleteAllUsers() {
        userRepository.deleteAll();
    }


}
