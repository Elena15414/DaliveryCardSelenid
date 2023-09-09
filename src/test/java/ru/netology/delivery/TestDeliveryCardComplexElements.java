package ru.netology.delivery;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TestDeliveryCardComplexElements {
    String currentDate;

    private String Date(int addDays, String pattern) {

        if (generateDate(7, "dd.MM.yyyy").equals(generateDate(7, "dd.MM.yyyy"))) {
            currentDate = generateDate(7, "dd.MM.yyyy");
            $("[data-test-id='date'] input").setValue(currentDate);
            $("[data-test-id='date'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        }
        if (generateDate(7, "dd.MM.yyyy").equals(generateDate(14, "dd.MM.yyyy"))) {
            return currentDate;
        }
        return null;
    }

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public String testDelivery() {

        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Мос");
        $$(".menu-item__control").findBy(text("Москва")).click();
        String currentDate = generateDate(7, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id='date'] input").getClass(Date);

        return currentDate;
    }
}
