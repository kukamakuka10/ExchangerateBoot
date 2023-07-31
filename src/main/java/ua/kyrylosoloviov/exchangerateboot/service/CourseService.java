package ua.kyrylosoloviov.exchangerateboot.service;


import ua.kyrylosoloviov.exchangerateboot.dto.CourseDTO;
import ua.kyrylosoloviov.exchangerateboot.entity.Course;

import java.time.LocalDate;
import java.util.List;

public interface CourseService {
    public CourseDTO getCourseByCurrencyCodeAndDay(String fromCurrency, String toCurrency, LocalDate dateCourse);

    public CourseDTO getCourseByID(long id);

    public void saveAllCourses(List<Course> exchangeRateInfo);

    public List<CourseDTO> getAllCourses();
    public CourseDTO getCourseLatest(String fromCod,String toCod);
}
