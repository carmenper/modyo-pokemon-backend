package com.cemp.modyo.pokemon.exception.handler;

import com.cemp.modyo.pokemon.domain.PokemonErrorResponse;
import com.cemp.modyo.pokemon.exception.ApplicationException;
import com.cemp.modyo.pokemon.exception.DataException;
import com.cemp.modyo.pokemon.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.UnknownHostException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(PokemonErrorResponse pokemonErrorResponse,
                                                       HttpStatus status) {
        return new ResponseEntity<>(pokemonErrorResponse, status);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest request) {
        return buildResponseEntity(new PokemonErrorResponse(HttpStatus.NOT_FOUND.value(),
                ex.getMessage()), status);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public Object handleNotFoundException(NotFoundException ex) {
        return buildResponseEntity(new PokemonErrorResponse(HttpStatus.NOT_FOUND.value(),
                "[" + ex.getResourceId() + "] " + ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<Object> handleApplicationException(ApplicationException ex) {
        return buildResponseEntity(new PokemonErrorResponse(ex.getId(), ex.getDetail()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = DataException.class)
    public ResponseEntity<Object> handleDataException(DataException ex) {
        return buildResponseEntity(new PokemonErrorResponse(ex.getId(), ex.getDetail() + " " + ex.getData()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = WebClientRequestException.class)
    public ResponseEntity<Object> handleWebClientRequestException(WebClientRequestException ex) {
        return buildResponseEntity(new PokemonErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = WebClientResponseException.class)
    public ResponseEntity<Object> handleWebClientResponseException(WebClientResponseException ex) {
        return buildResponseEntity(new PokemonErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UnknownHostException.class)
    public ResponseEntity<Object> handleUnknownHostException(UnknownHostException ex) {
        return buildResponseEntity(new PokemonErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return buildResponseEntity(new PokemonErrorResponse(HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex) {
        return buildResponseEntity(new PokemonErrorResponse(HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}
