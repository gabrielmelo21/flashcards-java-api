package api.englishAPI.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "words")
@Data
public class Words {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "english", nullable = false)
    private String english;

    @Column(name = "portugues", nullable = false)
    private String portugues;

    @Column(name = "explain", length = 500)
    private String explain;


    @Column(name = "memorizeTimes")
    private Long memorizeTimes = 0L;


}
