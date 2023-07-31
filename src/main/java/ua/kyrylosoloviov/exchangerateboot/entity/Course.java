package ua.kyrylosoloviov.exchangerateboot.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "course")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "time_last_update_utc")
    private LocalDate dateLastUpdateUTC;
    @Column(name = "time_next_update_utc")
    private LocalDate dateNextUpdateUTC;
    @Column(name = "rate")
    private BigDecimal rate;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "currency_from")
    private CurrencyData currencyFrom;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "currency_to")
    private CurrencyData currencyTo;
}