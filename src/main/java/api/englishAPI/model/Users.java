package api.englishAPI.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sobrenome", nullable = false)
    private String sobrenome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "picture", nullable = false)
    private String picture;

    @Column(name = "credits", nullable = false)
    private Long credits;  // ad por creditos, referal, checkin, pre-pgo

    @Column(name = "vip", nullable = false)
    private Boolean vip;  // 0 ads por mensalidade

    @Column(name = "checkin")
    private Boolean checkin;

    @Column(name = "password")
    private String password;



    public Users(String nome,
                String sobrenome,
                String email,
                String picture,
                Long credits,
                Boolean vip,
                Boolean checkin,
                String password) {

    }
    public Users(){

    }
}
