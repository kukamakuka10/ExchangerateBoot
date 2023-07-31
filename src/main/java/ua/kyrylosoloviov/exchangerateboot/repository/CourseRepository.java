package ua.kyrylosoloviov.exchangerateboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.kyrylosoloviov.exchangerateboot.entity.Course;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    public Course findByCurrencyFromCodAndCurrencyToCodAndDateLastUpdateUTC(String fromCod, String toCod, LocalDate date);
    public Course findFirstByCurrencyFromCodAndCurrencyToCodOrderByDateLastUpdateUTC(String currencyFromCod, String currencyToCod);
}
