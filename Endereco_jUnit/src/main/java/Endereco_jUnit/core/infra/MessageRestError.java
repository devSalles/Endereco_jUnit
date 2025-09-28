package Endereco_jUnit.core.infra;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

//Classe responsável por mostrar mensagem de exceção formatada
@Getter
@Setter
@NoArgsConstructor
public class MessageRestError {

        private HttpStatus status;
        private String message;
        private LocalDateTime timeStamp;

        public MessageRestError(HttpStatus status,String message)
        {
            this.status=status;
            this.message=message;
            this.timeStamp=LocalDateTime.now();
        }

}
