package ua.kyrylosoloviov.exchangerateboot.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ua.kyrylosoloviov.exchangerateboot.dto.CourseDTO;
import ua.kyrylosoloviov.exchangerateboot.dto.imports.ExchangeRateApiResDTO;
import ua.kyrylosoloviov.exchangerateboot.entity.Course;
import ua.kyrylosoloviov.exchangerateboot.entity.CurrencyData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Mapper
public interface CoursesMapper {
    CoursesMapper INSTANCE = Mappers.getMapper(CoursesMapper.class);

    default List<Course> mapToEntity(ExchangeRateApiResDTO exchangeRateApiResDTO, Collection<String> toCod) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        List<Course> courseList = new LinkedList<>();
        for (String c : toCod) {
            courseList.add(
                    Course.builder()
                            .currencyFrom(CurrencyData.builder().cod(exchangeRateApiResDTO.getBaseCode()).build())
                            .currencyTo(CurrencyData.builder().cod(c).build())
                            .rate(exchangeRateApiResDTO.getConversionRates().get(c))
                            .dateLastUpdateUTC(LocalDateTime.parse(exchangeRateApiResDTO.getTimeLastUpdateUtc(), formatter).toLocalDate())
                            .dateNextUpdateUTC(LocalDateTime.parse(exchangeRateApiResDTO.getTimeNextUpdateUtc(), formatter).toLocalDate())
                            .build());
        }
        return courseList;
    }

    @Mapping(source = "currencyFrom.cod", target = "fromCurrencyCod")
    @Mapping(source = "currencyTo.cod", target = "toCurrencyCod")
    CourseDTO mapToDTO(Course course);

    List<CourseDTO> mapToDTOlist(List<Course> courseList);
}
