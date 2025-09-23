package Endereco_jUnit.model;

import Endereco_jUnit.Enum.EnderecoUF;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb__endereco")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) @NotNull @NotBlank
    private String rua;

    @Column(nullable = false) @NotNull
    private Integer numero;

    @Column(nullable = false) @NotNull @NotBlank
    private String cidade;

    @Column(nullable = false) @Enumerated(EnumType.STRING) @NotNull
    private EnderecoUF estado;
}
