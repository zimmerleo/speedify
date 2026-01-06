package de.seniorenheim.speedify.business.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class SchedulingService {

    @Scheduled(cron = "0 0 0 L * *")
    public void monthly() {
        // RV 18.6% aufgeteilt auf AN und AG
        // KV 14.6% aufgeteilt auf AN und AG
        // AV 2.6% aufgeteilt auf AN und AG
        // PV 4.2% aufgeteilt auf AN und AG
        // Lohnsteuer 12% vom Bruttolohn
        // Gehälter
        log.info("Gehälter...");
        // Tank und Maut
        log.info("Tank und Maut...");
    }

    @Scheduled(cron = "0 0 0 10 4,7,10,1 *")
    public void ust() {
        // Umsatzsteuer 19%
        log.info("Umsatzsteuer...");
    }

    @Scheduled(cron = "0 0 0 10 3,6,9,12 *")
    public void est_kst() {
        // Einkommenssteuer 0% bis 11604, 19% bis 17005, 33% bis 66760, 42% bis 277825, 45% ab 277826
        log.info("Einkommenssteuer...");
        // Körperschaftssteuer 15% + 5.5% Soli
        log.info("Körperschaftssteuer...");
    }

    @Scheduled(cron = "0 0 0 15 2,5,8,11 *")
    public void gst() {
        // Gewerbesteuer = 3.5% * Gewerbeertrag * Hebesatz; 3.5% * Gewerbeertrag * 3.8 Anrechung auf ESt
        log.info("Gewerbesteuer...");
    }

    @Scheduled(cron = "0 0 0 1 1 *")
    public void yearly() {
        // UV mindestens 62€, 1.3% vom AG
        log.info("Unfallversicherung...");
        // KFZ-Steuer 1500-2000€ / LKW
        // KFZ-Versicherung 4000 HP + 3000 VK
        // Hauptuntersuchung 150€ / LKW
        log.info("KFZ-Steuer, KFZ-Versicherung und Hauptuntersuchung...");
        // Berufskraftfahrerqualifikation (BKrFQ) - Weiterbildung 100€
        // EU-Gemeinschaftslizenz für den Güterkraftverkehr 100€ + 10€ / Kopie
        log.info("Berufskraftfahrerqualifikation und EU-Gemeinschaftslizenz...");
    }
}
