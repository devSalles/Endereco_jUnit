package Endereco_jUnit.dto;

import Endereco_jUnit.Enum.EnderecoUF;
import Endereco_jUnit.model.Endereco;

public record EnderecoResponseDTO(
        Long id,
        String rua,
        Integer numero,
        String cidade,
        EnderecoUF estado
) {
    //Metodo para exibição de dados
    public static EnderecoResponseDTO fromEndereco(Endereco endereco)
    {
        return new EnderecoResponseDTO(endereco.getId(), endereco.getRua(), endereco.getNumero(), endereco.getCidade(),endereco.getEstado());
    }
}
