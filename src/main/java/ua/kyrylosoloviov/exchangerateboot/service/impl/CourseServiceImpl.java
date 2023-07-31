package ua.kyrylosoloviov.exchangerateboot.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kyrylosoloviov.exchangerateboot.dto.CourseDTO;
import ua.kyrylosoloviov.exchangerateboot.mappers.CoursesMapper;
import ua.kyrylosoloviov.exchangerateboot.entity.Course;
import ua.kyrylosoloviov.exchangerateboot.repository.CourseRepository;
import ua.kyrylosoloviov.exchangerateboot.service.CourseService;


import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseDTO getCourseByCurrencyCodeAndDay(String fromCurrency, String toCurrency, LocalDate dateCourse) {
        Course course = courseRepository.findByCurrencyFromCodAndCurrencyToCodAndDateLastUpdateUTC(fromCurrency.toUpperCase(), toCurrency.toUpperCase(), dateCourse);
        return CoursesMapper.INSTANCE.mapToDTO(course);
    }

    @Override
    public void saveAllCourses(List<Course> courses) {
        courseRepository.saveAll(courses);
    }

    @Override
    public CourseDTO getCourseByID(long id) {
        return CoursesMapper.INSTANCE.mapToDTO(courseRepository.findById(id).get());
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return CoursesMapper.INSTANCE.mapToDTOlist(courseRepository.findAll());
    }
    @Override
    public CourseDTO getCourseLatest(String fromCod,String toCod){
        return CoursesMapper.INSTANCE.mapToDTO(courseRepository.findFirstByCurrencyFromCodAndCurrencyToCodOrderByDateLastUpdateUTC(fromCod,toCod));
    }

}
