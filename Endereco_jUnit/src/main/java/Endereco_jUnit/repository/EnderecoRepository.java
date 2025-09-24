package Endereco_jUnit.repository;

import Endereco_jUnit.Enum.EnderecoUF;
import Endereco_jUnit.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {

    boolean existsByRuaAndNumeroAndEstadoAndCidade(String rua, Integer numero, EnderecoUF enderecoUF, String cidade);
}
