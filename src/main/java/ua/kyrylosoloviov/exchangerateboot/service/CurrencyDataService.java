package ua.kyrylosoloviov.exchangerateboot.service;


import ua.kyrylosoloviov.exchangerateboot.dto.CurrencyDataDTO;
import ua.kyrylosoloviov.exchangerateboot.entity.CurrencyData;

import java.util.List;
import java.util.Set;

public interface CurrencyDataService {
    public Set<String> getAllCurrencyCode();

    public CurrencyDataDTO getCurrencyDataByCode(String code);

    public List<CurrencyDataDTO> getAllCurrencyData();

}
