package ru.netology.Delivery;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TestDeliveryCardComplexElements {

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void testDelivery() {

        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Мос");
        $$(".menu-item__control").findBy(text("Москва")).click();
        String currentDate = generateDate(7, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue(currentDate);
        $("[data-test-id='date'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);

    }
}
