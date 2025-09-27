package Endereco_jUnit.service;

import Endereco_jUnit.Enum.EnderecoUF;
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

    @BeforeAll
    static void setup_Validator()
    {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator=validatorFactory.getValidator();
    }

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

    @Test
    void add_SalvaEntidadeQuandoTodosOsDadosSaoValidos() {
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(validEndereco);

        Endereco result = this.enderecoService.add(validEnderecoRequestDTO);
        assertNotNull(result);
        assertEquals(validEnderecoRequestDTO.getRua(),result.getRua());
        assertEquals(validEnderecoRequestDTO.getNumero(),result.getNumero());
        assertEquals(validEnderecoRequestDTO.getCidade(),result.getCidade());
        assertEquals(validEnderecoRequestDTO.getEstado(),result.getEstado());

        verify(enderecoRepository,times(1)).save(any(Endereco.class));
    }

    @Test
    void add_LancaExcecaoQuandoRequisicaoEstaInvalida()
    {
        EnderecoRequestDTO enderecoRequestDTO = new EnderecoRequestDTO();
        enderecoRequestDTO.setRua("");
        enderecoRequestDTO.setCidade("");
        enderecoRequestDTO.setNumero(0);
        enderecoRequestDTO.setEstado(null);

        Set<ConstraintViolation<EnderecoRequestDTO>> validations = validator.validate(enderecoRequestDTO);

        assertFalse(validations.isEmpty());
        validations.forEach(v-> System.out.println(v.getPropertyPath() + " - " +v.getMessage()));
    }

    @Test
    void editById() {
    }

    @Test
    void showAll() {
    }

    @Test
    void showById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteAll() {
    }

}