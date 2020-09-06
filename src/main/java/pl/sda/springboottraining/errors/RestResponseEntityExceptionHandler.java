package pl.sda.springboottraining.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(value = {IllegalStateException.class, RuntimeException.class})
//    protected ResponseEntity<Object> handleConflict(
//            RuntimeException ex, WebRequest request) {
//
//        ErrorEntity errorEntity = new ErrorEntity();
//        errorEntity.setError("Wystąpił błąd");
//
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(errorEntity);
//    }

    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }


}
