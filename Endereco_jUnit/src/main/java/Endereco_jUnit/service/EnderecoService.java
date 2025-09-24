package Endereco_jUnit.service;

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

    public Endereco add(EnderecoRequestDTO enderecoRequestDTO)
    {
        Endereco endereco = enderecoRequestDTO.toEndereco();
        return this.enderecoRepository.save(endereco);
    }

    public Endereco editById(Long id, EnderecoRequestDTO enderecoRequestDTO)
    {
        Endereco enderecoID=this.enderecoRepository.findById(id).orElseThrow(IdNaoEncontradoException::new);
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
            throw new IdNaoEncontradoException();
        }

        this.enderecoRepository.deleteAll();
    }
    
}
