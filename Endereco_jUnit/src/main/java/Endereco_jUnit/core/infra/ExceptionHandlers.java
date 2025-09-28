package Endereco_jUnit.core.infra;

import Endereco_jUnit.core.exception.IdNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Responsável por tratamento de exceções
@ControllerAdvice
public class ExceptionHandlers {

    //Exceção requisição inválida
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageRestError> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex)
    {
        String errorMessage=ex.getBindingResult().getFieldErrors().stream().map(Error->Error.getDefaultMessage()).findFirst().orElse("Erro de validação");
        MessageRestError messageRestError = new MessageRestError(HttpStatus.BAD_REQUEST,errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageRestError);
    }

    //Exceção para requisições inválidas globais
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageRestError> IllegalArgumentExceptionHandler(IllegalArgumentException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageRestError);
    }

    //Exceção para ID não encontrado
    @ExceptionHandler(IdNaoEncontradoException.class)
    public ResponseEntity<MessageRestError> IdNaoEncontradoExceptionHandler(IdNaoEncontradoException ex)
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageRestError);
    }

    //Exceções Globais
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageRestError> GlobalExceptionHandler()
    {
        MessageRestError messageRestError = new MessageRestError(HttpStatus.INTERNAL_SERVER_ERROR,"Erro interno, tente novamente mais tarde");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageRestError);
    }
}
