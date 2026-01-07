package de.seniorenheim.speedify.business.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Getter
@Setter
@Component
public class FinanceValues {

    // KFZ-Steuer 1500-2000€ / LKW
    // KFZ-Versicherung 4000 HP + 3000 VK / LKW
    // Hauptuntersuchung 150€ / LKW
    // Umsatzsteuer 19%
    // Körperschaftssteuer 15%
    // Gewerbesteuer = 3.5% * Gewerbeertrag * Hebesatz; 3.5% * Gewerbeertrag * 3.8 Anrechung auf ESt aber nicht auf KSt

    private BigDecimal KFZ_STEUER =  BigDecimal.valueOf(1500);
    private BigDecimal KFZ_HAFTPFLICHT = BigDecimal.valueOf(4000);
    private BigDecimal KFZ_VOLLKASKO = BigDecimal.valueOf(3000);
    private BigDecimal HAUPTUNTERSUCHUNG = BigDecimal.valueOf(150);

    private BigDecimal UMSATZSTEUER = BigDecimal.valueOf(0.19);
    private BigDecimal KÖRPERSCHAFTSSTEUER = BigDecimal.valueOf(0.15);
}