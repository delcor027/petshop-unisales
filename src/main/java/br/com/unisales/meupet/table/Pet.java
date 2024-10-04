package br.com.unisales.meupet.table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    @ManyToOne(targetEntity = Proprietario.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proprietario")
    private Proprietario proprietario;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "data_nascimento", nullable = false, length = 10)
    private String dataNascimento;

    @Column(name = "sexo", nullable = false, length = 1)
    private String sexo;

    @Column(name = "especie", nullable = false, length = 20)
    private String especie;

    @Column(name = "raca", nullable = false, length = 20)
    private String raca;
}
