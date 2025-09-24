package Endereco_jUnit.dto;

import Endereco_jUnit.Enum.EnderecoUF;
import Endereco_jUnit.model.Endereco;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequestDTO {

    @NotNull(message = "rua obrigatória") @NotBlank(message = "rua obrigátoria")
    private String rua;

    @NotNull(message = "número obrigatório")
    private Integer numero;

    @NotNull(message = "cidade obrigatória") @NotBlank(message = "cidade obrigátoria")
    private String cidade;

    @NotNull(message = "estado obrigatório") @Enumerated(EnumType.STRING)
    private EnderecoUF estado;

    public Endereco toEndereco()
    {
        Endereco endereco = new Endereco();

        endereco.setRua(this.rua);
        endereco.setNumero(this.numero);
        endereco.setCidade(this.cidade);
        endereco.setEstado(this.estado);

        return endereco;
    }

    public Endereco updateEndereco(Endereco endereco)
    {
        endereco.setRua(this.getRua());
        endereco.setNumero(this.getNumero());
        endereco.setCidade(this.getCidade());
        endereco.setEstado(this.getEstado());

        return endereco;
    }

}
