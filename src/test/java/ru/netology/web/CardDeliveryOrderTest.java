package ru.netology.web;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryOrderTest {

    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Test
    void testingTheCardDeliveryOrderForm() {
        String planningDate = generateDate(4);
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Казань");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[name='name']").setValue("Андрей Иванов");
        $("[name='phone']").setValue("+79179139319");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();

        $("[data-test-id='notification']").should(appear, Duration.ofSeconds(14));


    }

}
