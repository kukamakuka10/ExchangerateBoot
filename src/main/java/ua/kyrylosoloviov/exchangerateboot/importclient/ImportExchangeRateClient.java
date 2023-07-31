package ua.kyrylosoloviov.exchangerateboot.importclient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;
import ua.kyrylosoloviov.exchangerateboot.dto.imports.ExchangeRateApiResDTO;
import ua.kyrylosoloviov.exchangerateboot.mappers.CoursesMapper;
import ua.kyrylosoloviov.exchangerateboot.entity.Course;
import ua.kyrylosoloviov.exchangerateboot.service.CourseService;
import ua.kyrylosoloviov.exchangerateboot.service.CurrencyDataService;


import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@Component
@ConfigurationProperties(prefix = "import-service.exchanger")
@Getter
@Setter
public class ImportExchangeRateClient {
    private final RestTemplate restTemplate;
    private final CurrencyDataService currencyDataService;
    private final CourseService courseService;
    private static final String LATEST_URL = "latest";
    private static final String HEADER_KEY = "apikey";
    private String url;
    private String key;
    private HttpHeaders headers = new HttpHeaders();


    @Autowired
    public ImportExchangeRateClient(RestTemplate restTemplate, CurrencyDataService currencyDataService, CourseService courseService) {
        this.restTemplate = restTemplate;
        this.currencyDataService = currencyDataService;
        this.courseService = courseService;
    }

    @PostConstruct
    void init() {
        headers.add(HttpHeaders.USER_AGENT, "Application");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        url = String.format("%s/%s/%s/", getUrl(), key, LATEST_URL);
    }

    @Scheduled(cron = "0 1 1 * * *")
    public void updateOrCreateExchangeRate() {
        Set<String> actualCurrency = currencyDataService.getAllCurrencyCode();
        List<Course> courses = new LinkedList<>();
        HttpEntity<Object> response = new HttpEntity<>(headers);
        for (String base : actualCurrency) {
            String urlBase = url.concat(base);
            ExchangeRateApiResDTO exchangeRateApiResDTO = restTemplate.exchange(urlBase, HttpMethod.GET, response, ExchangeRateApiResDTO.class).getBody();
            courses.addAll(CoursesMapper.INSTANCE.mapToEntity(exchangeRateApiResDTO, actualCurrency));
        }
        courseService.saveAllCourses(courses);

    }
}
