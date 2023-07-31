package ua.kyrylosoloviov.exchangerateboot.service.impl;

import org.springframework.stereotype.Service;
import ua.kyrylosoloviov.exchangerateboot.dto.CurrencyDataDTO;
import ua.kyrylosoloviov.exchangerateboot.mappers.CurrencyDataMapper;
import ua.kyrylosoloviov.exchangerateboot.repository.CurrencyDataRepository;
import ua.kyrylosoloviov.exchangerateboot.service.CurrencyDataService;


import java.util.List;
import java.util.Set;

@Service
public class CurrencyDataServiceImpl implements CurrencyDataService {
    private final CurrencyDataRepository currencyDataRepository;

    public CurrencyDataServiceImpl(CurrencyDataRepository currencyDataRepository) {
        this.currencyDataRepository = currencyDataRepository;
    }

    @Override
    public Set<String> getAllCurrencyCode() {
        return currencyDataRepository.getAllCurrencyCodes();
    }

    @Override
    public CurrencyDataDTO getCurrencyDataByCode(String code) {
        return CurrencyDataMapper.INSTANCE.mapToDTO(currencyDataRepository.findById(code).get());
    }

    @Override
    public List<CurrencyDataDTO> getAllCurrencyData() {
        return CurrencyDataMapper.INSTANCE.mapToDtoList(currencyDataRepository.findAll());
    }
}
