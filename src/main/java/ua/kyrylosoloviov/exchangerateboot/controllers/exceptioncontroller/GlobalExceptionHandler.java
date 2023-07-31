package ua.kyrylosoloviov.exchangerateboot.controllers.exceptioncontroller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.kyrylosoloviov.exchangerateboot.dto.exception.CourseIncorrectData;


import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CourseIncorrectData handleDateTimeParseException(DateTimeParseException exception) {
        return new CourseIncorrectData("Incorrect date format. Use the format 'YYYY-MM-DD'");
    }
}
