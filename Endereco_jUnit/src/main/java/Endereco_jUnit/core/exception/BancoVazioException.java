package Endereco_jUnit.core.exception;

public class BancoVazioException extends RuntimeException {
    public BancoVazioException(String message) {
        super(message);
    }
    public BancoVazioException() {
        super("Nenhum registro no banco de dados");
    }
}
