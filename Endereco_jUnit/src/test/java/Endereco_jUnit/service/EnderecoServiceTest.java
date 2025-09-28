package Endereco_jUnit.service;

import Endereco_jUnit.Enum.EnderecoUF;
import Endereco_jUnit.core.exception.IdNaoEncontradoException;
import Endereco_jUnit.dto.EnderecoRequestDTO;
import Endereco_jUnit.model.Endereco;
import Endereco_jUnit.repository.EnderecoRepository;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import jakarta.validation.*;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    private Endereco validEndereco;
    private EnderecoRequestDTO validEnderecoRequestDTO;
    private static Validator validator;

    //Configuração do validador
    @BeforeAll
    static void setup_Validator()
    {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator=validatorFactory.getValidator();
    }

    //Configuração dos dados do teste
    @BeforeEach
    void setup()
    {
        validEnderecoRequestDTO = new EnderecoRequestDTO();
        validEnderecoRequestDTO.setCidade("Belo Horizonte");
        validEnderecoRequestDTO.setRua("São Paulo");
        validEnderecoRequestDTO.setNumero(166);
        validEnderecoRequestDTO.setEstado(EnderecoUF.MG);

        validEndereco = new Endereco();
        validEndereco.setId(1L);
        validEndereco.setRua(validEnderecoRequestDTO.getRua());
        validEndereco.setCidade(validEnderecoRequestDTO.getCidade());
        validEndereco.setNumero(validEnderecoRequestDTO.getNumero());
        validEndereco.setEstado(validEnderecoRequestDTO.getEstado());
    }

    // --- metodo Add ---
    @Test
    void add_SalvaEntidadeQuandoTodosOsDadosSaoValidos()
    {
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(validEndereco);

        Endereco result = this.enderecoService.add(validEnderecoRequestDTO);
        assertNotNull(result);
        //Verifica se o objeto de chamada e o mesmo do resultado
        assertEquals(validEnderecoRequestDTO.getRua(),result.getRua());
        assertEquals(validEnderecoRequestDTO.getNumero(),result.getNumero());
        assertEquals(validEnderecoRequestDTO.getCidade(),result.getCidade());
        assertEquals(validEnderecoRequestDTO.getEstado(),result.getEstado());

        //Verifica chamada de repositório para persistência de objeto
        verify(enderecoRepository,times(1)).save(any(Endereco.class));
    }

    @Test
    void add_LancaExcecaoQuandoRequisicaoEstaInvalida()
    {
        //Instância de requisção inválida para teste de lançamento de exceção
        EnderecoRequestDTO enderecoRequestDTO = new EnderecoRequestDTO();
        enderecoRequestDTO.setRua("");
        enderecoRequestDTO.setCidade("");
        enderecoRequestDTO.setNumero(0);
        enderecoRequestDTO.setEstado(null);

        //Set para mensagens de validações sem repetições
        Set<ConstraintViolation<EnderecoRequestDTO>> validations = validator.validate(enderecoRequestDTO);
        assertFalse(validations.isEmpty(),"As violações devem ser detectadas");

        //forEach para exibir mensagens de exceções
        validations.forEach(v-> System.out.println(v.getPropertyPath() + " - " +v.getMessage()));
    }

    // ---metodo editById----
    @Test
    void editById() {
        when(enderecoRepository.findById(anyLong())).thenReturn(Optional.of(validEndereco));
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(validEndereco);

        //Trabalham juntos para validação do objeto
        Set<ConstraintViolation<EnderecoRequestDTO>>validations = validator.validate(validEnderecoRequestDTO);
        assertTrue(validations.isEmpty(),"Não deve conter violações");

        Endereco result = this.enderecoService.editById(1L,validEnderecoRequestDTO);

        assertEquals(validEnderecoRequestDTO.getRua(),result.getRua());
        assertEquals(validEnderecoRequestDTO.getNumero(),result.getNumero());
        assertEquals(validEnderecoRequestDTO.getCidade(),result.getCidade());
        assertEquals(validEnderecoRequestDTO.getEstado(),result.getEstado());

        //Verificações de procura de ID e persistência de objeto atualizado
        verify(enderecoRepository,times(1)).findById(1L);
        verify(enderecoRepository,times(1)).save(any(Endereco.class));
    }

    @Test
    void editById_LancarExcecaoQuandoRequisicaoForInvalida() {
        //Instância de requisção inválida para teste de lançamento de exceção
        EnderecoRequestDTO enderecoRequestDTO = new EnderecoRequestDTO();
        enderecoRequestDTO.setRua("");
        enderecoRequestDTO.setCidade("");
        enderecoRequestDTO.setNumero(0);
        enderecoRequestDTO.setEstado(null);

        //Trabalham juntos para validação do objeto
        Set<ConstraintViolation<EnderecoRequestDTO>> validations = validator.validate(enderecoRequestDTO);
        assertFalse(validations.isEmpty(),"As violações devem ser detectadas");

        //forEach para exibir mensagens de exceções
        validations.forEach(v-> System.out.println(v.getPropertyPath() + " - " +v.getMessage()));
    }

    @Test
    void editById_LancarExcecaoQuandoIdNaoEncontrado() {
        when(enderecoRepository.findById(anyLong())).thenReturn(Optional.empty());

        //Checagem de lançamento de exceção para ID não encontrado
        assertThrows(IdNaoEncontradoException.class,()->this.enderecoService.editById(99L,validEnderecoRequestDTO));

        //Verificações de procura de ID
        verify(enderecoRepository,times(1)).findById(99L);
        verify(enderecoRepository,never()).save(any(Endereco.class));
    }

    // ---metodo showAll---
    @Test
    void showAll() {
    }

    // ---metodo showById---
    @Test
    void showById() {
    }

    // ---metodo Delete---

    @Test
    void deleteById() {
    }

    // ---metodo deleteAll---
    @Test
    void deleteAll() {
    }

}