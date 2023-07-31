package ua.kyrylosoloviov.exchangerateboot.controllers.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kyrylosoloviov.exchangerateboot.dto.CurrencyDataDTO;
import ua.kyrylosoloviov.exchangerateboot.entity.CurrencyData;
import ua.kyrylosoloviov.exchangerateboot.service.CurrencyDataService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
    private final CurrencyDataService currencyDataService;

    public CurrencyController(CurrencyDataService currencyDataService) {
        this.currencyDataService = currencyDataService;
    }

    @GetMapping("/{cod}")
    public CurrencyDataDTO getCurrency(@PathVariable String cod) {
        return currencyDataService.getCurrencyDataByCode(cod);
    }

    @GetMapping("/")
    public List<CurrencyDataDTO> getAllCurrency() {
        return currencyDataService.getAllCurrencyData();
    }
}
