package ua.kyrylosoloviov.exchangerateboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.kyrylosoloviov.exchangerateboot.entity.CurrencyData;


import java.util.Set;

public interface CurrencyDataRepository extends JpaRepository<CurrencyData,String> {
    @Query(value = "select cod from CurrencyData")
    public Set<String> getAllCurrencyCodes();

}
