package Endereco_jUnit.controller;

import Endereco_jUnit.dto.EnderecoRequestDTO;
import Endereco_jUnit.dto.EnderecoResponseDTO;
import Endereco_jUnit.service.EnderecoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
@Tag(name="endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping("/add")
    public ResponseEntity<Object> addNew(@Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.enderecoService.add(enderecoRequestDTO));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> editById(@PathVariable Long id,@Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO)
    {
        return ResponseEntity.ok(this.enderecoService.editById(id,enderecoRequestDTO));
    }

    @GetMapping("/show-All")
    public ResponseEntity<Object> showAll()
    {
        List<EnderecoResponseDTO>enderecoShow=this.enderecoService.showAll();
        return ResponseEntity.status(HttpStatus.OK).body(enderecoShow);
    }
    @GetMapping("/show/{id}")
    public ResponseEntity<Object> showById(@PathVariable Long id)
    {
        EnderecoResponseDTO enderecoShow=this.enderecoService.showById(id);
        return ResponseEntity.status(HttpStatus.OK).body(enderecoShow);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(this.enderecoService.deleteById(id));
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<Object> deleteAll()
    {
        this.enderecoService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
