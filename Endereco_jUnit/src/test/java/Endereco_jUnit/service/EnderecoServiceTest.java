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


}