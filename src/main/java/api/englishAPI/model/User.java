package api.englishAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sobrenome", nullable = false)
    private String sobrenome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "picture", nullable = false)
    private String picuture;

    @Column(name = "credits", nullable = false)
    private Long credits;  // ad por creditos, referal, checkin, pre-pgo

    @Column(name = "vip", nullable = false)
    private Boolean vip;  // 0 ads por mensalidade

    @Column(name = "checkin", nullable = false)
    private Boolean checkin;

    @Column(name = "ip", nullable = false)
    private String ip;


    public User(String nome,
                String sobrenome,
                String email,
                String picture,
                Long credits,
                Boolean vip,
                Boolean checkin,
                String ip) {

    }
    public User(){

    }
}
