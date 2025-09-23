package Endereco_jUnit.controller;

import Endereco_jUnit.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereço")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;
}
