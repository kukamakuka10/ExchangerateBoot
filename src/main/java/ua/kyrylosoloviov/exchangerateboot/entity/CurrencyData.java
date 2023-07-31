package ua.kyrylosoloviov.exchangerateboot.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "currency_data")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CurrencyData {
    @Id
    @Column(name = "currency_cod")
    private String cod;
    @Column(name = "currency_name")
    private String name;

}
