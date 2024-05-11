package api.englishAPI.controller;


import api.englishAPI.model.User;
import api.englishAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Base64;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GoogleController {

    @Autowired
    private UserService userService;


    @PostMapping("/receber-payload")
    public void receberPayload(@RequestBody String jsonPayload)   {



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



    // Acessar os dados
    System.out.println("Email: " + userInfo.getEmail());
    System.out.println("Given Name: " + userInfo.getGiven_name());
    System.out.println("Family Name: " + userInfo.getFamily_name());
    System.out.println("Picture: " + userInfo.getPicture());


//salvar usuario no banco de dados , se os dados deles ja existe, nao adiciona
//os dados sao creditos, pontos, assinatura, etc
//cri metodo pr sve user



            if(!userService.verifyUser(userInfo.getEmail())){
                // se o email do user não aparecer, salvamos no db

                System.out.println("verify retornou false - não existe esse email no banco ainda");

User user = new User(
        userInfo.getGiven_name()
        , userInfo.getFamily_name(),
        userInfo.getEmail(),
        userInfo.getPicture(),
        100L,
        false,
        false,
        "212121");

              userService.saveNewUser(user);


                // futuro metodo de enviar dados de login pro front

            }else{
                System.out.println("verify retornou true - Existe esse email no banco.");

                 // o verify entregou true, entao existe um cadastro
                 // processeguir com informações de login pro front

                // futuro metodo de enviar dados de login pro front
            }






        } else {
            System.err.println("Não foi possível encontrar o JWT na string de entrada.");
        }

    }


    private static String decodeBase64(String base64String) {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(base64String);
        return new String(decodedBytes);
    }

}
