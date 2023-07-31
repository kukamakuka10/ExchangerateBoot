package ua.kyrylosoloviov.exchangerateboot.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.kyrylosoloviov.exchangerateboot.dto.CurrencyDataDTO;
import ua.kyrylosoloviov.exchangerateboot.entity.CurrencyData;

import java.util.List;
import java.util.Set;

@Mapper
public interface CurrencyDataMapper {
    CurrencyDataMapper INSTANCE = Mappers.getMapper(CurrencyDataMapper.class);

    CurrencyData mapToEntity(CurrencyDataDTO currencyDataDTO);

    CurrencyDataDTO mapToDTO(CurrencyData currencyData);

    List<CurrencyData> mapToEntityList(List<CurrencyDataDTO> currencyDataDTOList);

    List<CurrencyDataDTO> mapToDtoList(List<CurrencyData> currencyDataList);
}
