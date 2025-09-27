package Endereco_jUnit.service;

import Endereco_jUnit.core.exception.BancoVazioException;
import Endereco_jUnit.core.exception.IdNaoEncontradoException;
import Endereco_jUnit.dto.EnderecoRequestDTO;
import Endereco_jUnit.dto.EnderecoResponseDTO;
import Endereco_jUnit.model.Endereco;
import Endereco_jUnit.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;


    public void EnderecoExistenteAndNumInvalido(EnderecoRequestDTO enderecoRequestDTO)
    {
        Boolean jaExiste = this.enderecoRepository.existsByRuaAndNumeroAndEstadoAndCidade(
                enderecoRequestDTO.getRua(), enderecoRequestDTO.getNumero(),enderecoRequestDTO.getEstado(),enderecoRequestDTO.getCidade()
        );

        if(jaExiste)
        {
            throw new IllegalArgumentException("Endereço já cadastrado");
        }
    }

    public Endereco add(EnderecoRequestDTO enderecoRequestDTO)
    {
        EnderecoExistenteAndNumInvalido(enderecoRequestDTO);

        Endereco endereco = enderecoRequestDTO.toEndereco();
        return this.enderecoRepository.save(endereco);
    }

    public Endereco editById(Long id, EnderecoRequestDTO enderecoRequestDTO)
    {
        Endereco enderecoID=this.enderecoRepository.findById(id).orElseThrow(IdNaoEncontradoException::new);

        EnderecoExistenteAndNumInvalido(enderecoRequestDTO);

        enderecoRequestDTO.updateEndereco(enderecoID);
        return this.enderecoRepository.save(enderecoID);
    }


    public List<EnderecoResponseDTO> showAll()
    {
        List<Endereco>enderecoShowAll=this.enderecoRepository.findAll();
        if(enderecoShowAll.isEmpty())
        {
            throw new IdNaoEncontradoException();
        }

        return enderecoShowAll.stream().map(EnderecoResponseDTO::fromEndereco).toList();
    }

    public EnderecoResponseDTO showById(Long id)
    {
        Endereco enderecoID=this.enderecoRepository.findById(id).orElseThrow(IdNaoEncontradoException::new);
        return EnderecoResponseDTO.fromEndereco(enderecoID);
    }

    public Boolean deleteById(Long id)
    {
        Endereco enderecoID=this.enderecoRepository.findById(id).orElseThrow(IdNaoEncontradoException::new);
        this.enderecoRepository.delete(enderecoID);
        return true;
    }

    public void deleteAll()
    {
        if(enderecoRepository.findAll().isEmpty())
        {
            throw new BancoVazioException();
        }

        this.enderecoRepository.deleteAll();
    }
    
}
