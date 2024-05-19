package api.englishAPI.controller;


import api.englishAPI.dto.UserDTO;
import api.englishAPI.model.Users;
import api.englishAPI.service.UserService;
import api.englishAPI.utils.GenerateJWT;
import api.englishAPI.utils.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.Base64;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LoginController {

    @Autowired
    private UserService userService;

     /**  LOGIN VIA GOOGLE **/
    @PostMapping("/googleLogin")
    public ResponseEntity<Object> googleLogin(@RequestBody String jsonPayload)   {
        // Encontra a posição do "credential="
        int startIndex = jsonPayload.indexOf("credential=");

        if (startIndex != -1) {
            // Extrai o JWT após "credential="
            String jwt = jsonPayload.substring(startIndex + "credential=".length());

            String[] jwtParts = jwt.split("\\.");
            // Decodificando o payload (parte do JWT que contém os dados)
            String payload =  decodeBase64(jwtParts[1]);

            // Criar um objeto Gson
            Gson gson = new GsonBuilder().create();

            // Desserializar o JSON para a classe UserInfo
            UserInfo userInfo = gson.fromJson(payload, UserInfo.class);



            /**
             *    Vamos criar o sistema de login completo, com criar conta + login via senha e email
             *    Ao fazer login, vamos enviar um JWT com apenas o UUID do usuario ou EMAIL
             *    isso para o frontend identificar o usuario e solicitar a API java os dados do usuario
             *    como foto, nome, email, creditos, vip, chekin etc....
             *
             */


           // Verifica se o user existe, senao criamos o cadastro
            if(!userService.verifyUser(userInfo.getEmail())){
             UserDTO user = new UserDTO(userInfo.getGiven_name(),userInfo.getFamily_name(), userInfo.getEmail(), userInfo.getPicture(), 25L, false, false, "");
             userService.saveNewUser(user);
            }else{
                System.out.println("User ja existe no banco de dados");
            }


            // Pega o UUID do user e cria um JWT e envia para o FrontEnd
            UUID id = userService.getUserIdByEmail(userInfo.getEmail());
            System.out.println("UUID encontrado foi " + id);
            String token = GenerateJWT.generateToken(id);
            String redirectUrl = "http://localhost:4200/videos?jwt=" + token;
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirectUrl)).build();




        } else {
            System.err.println("Não foi possível encontrar o JWT na string de entrada.");
        }

        return null;
    }



    /** LOGIN VIA SITE **/
    @PostMapping("/basicLogin")
    public ResponseEntity<Object> basicLogin(@RequestBody String jsonPayload)   {



        if(!userService.verifyUser()){

        }

    }




    private static String decodeBase64(String base64String) {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(base64String);
        return new String(decodedBytes);
    }



}
