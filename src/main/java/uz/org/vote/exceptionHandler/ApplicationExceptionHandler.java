package uz.org.vote.exceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.org.vote.message.ResponseMessage;

@ControllerAdvice
public class ApplicationExceptionHandler{
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, InvalidFormatException.class})
    public ResponseEntity<?> methodValidException(Exception e){
        ResponseMessage resMessage = new ResponseMessage(400, e.getMessage(), null);
        return new ResponseEntity<>(resMessage, HttpStatus.OK);
    }
}