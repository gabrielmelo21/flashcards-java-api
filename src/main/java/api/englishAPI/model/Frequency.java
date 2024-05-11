package api.englishAPI.model;


import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "frequency")
@Data
public class Frequency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dataDia", nullable = false)
    private String dataDia;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "numeroDoDia", nullable = false)
    private Long numeroDoDia;



}
