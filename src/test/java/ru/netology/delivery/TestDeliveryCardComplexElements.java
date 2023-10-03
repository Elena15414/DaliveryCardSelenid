package ru.netology.delivery;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.conditions.datetime.DateConditions.date;

public class TestDeliveryCardComplexElements {
    String currentDate;
    int addDays = 7;


    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

//    private String dateNow(String pattern) {
//        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
//    }

    @Test
    public void testDeliveryCity() {

        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Мос");
        $$(".menu-item__control").findBy(text("Москва")).click();
        String currentDate = generateDate(7, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id='date'] input").setValue(currentDate);
        $("[data-test-id='name'] input").setValue("Морозова Анна");
        $("[data-test-id='phone'] input").setValue("+79856325874");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно забронирована на " + currentDate));
    }

    @Test
    public void testDelivery7Days() {

        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Мос");
        $$(".menu-item__control").findBy(text("Москва")).click();
        $("[data-test-id=date] [value]").click();
        LocalDate dateDefault = LocalDate.now().plusDays(1);
        LocalDate dateOfMeeting = LocalDate.now().plusDays(7);
        String stringToSearch = dateOfMeeting.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String dayToSearch = String.valueOf(dateOfMeeting.getDayOfMonth());
        if (dateOfMeeting.getMonthValue() > dateDefault.getMonthValue() | dateOfMeeting.getYear() > dateDefault.getYear()) {
            $(".calendar__arrow_direction_right[data-step='1']").click();
        }
        $$("td.calendar__day").find(exactText(dayToSearch)).click();
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+78956321475");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно забронирована на " + stringToSearch));

    }




//    public boolean matches(String query) {
//        if (generateDate(7, "dd.MM.yyyy").equals(dateNow("dd.MM.yyyy"));
//            return true;
//        }
//        return false;
//
//
//    private String Date(int addDays, String pattern) {
//
//        if (generateDate(7, "dd.MM.yyyy").equals(dateNow("dd.MM.yyyy")) {
//            currentDate = generateDate(7, "dd.MM.yyyy");
//            $("[data-test-id='date'] input").setValue(currentDate);
//            $("[data-test-id='date'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
//        }
//        if (generateDate(7, "dd.MM.yyyy").equals(generateDate(14, "dd.MM.yyyy"))) {
//            return currentDate;
//        }
//        return null;

//        if (generateDate(7, "dd.MM.yyyy").equals(generateDate(7, "dd.MM.yyyy"))) {
//            currentDate = generateDate(7, "dd.MM.yyyy");
//            $("[data-test-id='date'] input").setValue(currentDate);
//            $("[data-test-id='date'] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
//        }
//        if (generateDate(7, "dd.MM.yyyy").equals(generateDate(14, "dd.MM.yyyy"))) {
//            return currentDate;
//        }
//        return null;


}
