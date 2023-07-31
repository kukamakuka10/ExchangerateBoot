package ua.kyrylosoloviov.exchangerateboot.controllers.restcontroller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.kyrylosoloviov.exchangerateboot.dto.CourseDTO;
import ua.kyrylosoloviov.exchangerateboot.importclient.ImportExchangeRateClient;
import ua.kyrylosoloviov.exchangerateboot.service.CourseService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final ImportExchangeRateClient importExchangeRateClient;

    private final CourseService courseService;

    public CourseController(ImportExchangeRateClient importExchangeRateClient, CourseService courseService) {
        this.importExchangeRateClient = importExchangeRateClient;
        this.courseService = courseService;
    }

    @GetMapping("/{fromCurrencyCod}/{toCurrencyCod}")
    public CourseDTO getCourseLatest(@PathVariable @Valid @Pattern(regexp = "^[A-Z]{3}$") String fromCurrencyCod,
                                     @PathVariable @Valid @Pattern(regexp = "^[A-Z]{3}$") String toCurrencyCod) {

        return courseService.getCourseLatest(fromCurrencyCod, toCurrencyCod);
    }

    @GetMapping("/{id}")
    public CourseDTO getCourseByID(@PathVariable long id) {
        return courseService.getCourseByID(id);
    }

    @GetMapping("/")
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{fromCurrencyCod}/{toCurrencyCod}/{dateCourse}")
    public CourseDTO getCourseHistoryDay(@PathVariable @Pattern(regexp = "^[A-Z]{3}$") String fromCurrencyCod,
                                         @PathVariable @Pattern(regexp = "^[A-Z]{3}$") String toCurrencyCod,
                                         @PathVariable String dateCourse) {
        LocalDate parsedDate = LocalDate.parse(dateCourse);
        return courseService.getCourseByCurrencyCodeAndDay(fromCurrencyCod, toCurrencyCod, parsedDate);
    }

}
