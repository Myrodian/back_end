package br.ifmg.produto1_2026.resources.exception;

import br.ifmg.produto1_2026.service.exception.ErroNoBancoDeDados;
import br.ifmg.produto1_2026.service.exception.RegistroNaoEncontrado;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<standartError> entityNotFound(RegistroNaoEncontrado e , HttpServletRequest req){

        standartError error = new standartError();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setError("Recurso não encontrado");
        error.setTimestamp(Instant.now());
        error.setPath(req.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }
    @ExceptionHandler(ErroNoBancoDeDados.class)
    public ResponseEntity<standartError> databaseIntegrity(ErroNoBancoDeDados e , HttpServletRequest req){
        standartError error = new standartError();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        error.setError("Erro de integridade no banco de dados");
        error.setTimestamp(Instant.now());
        error.setPath(req.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }
}
