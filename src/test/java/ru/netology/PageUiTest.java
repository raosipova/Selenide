package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;



public class PageUiTest {
    private String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldBeSuccessFullyCompleted() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Краснодар");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input ").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Елена Пирогова");
        $("[data-test-id='phone']  input").setValue("+79883339918");
        $("[data-test-id='agreement']").click();
        $("[button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    }

    // 2. Отправка заявки с указанием фамилии через дефис;
    @Test
    public void mustBeASuccessfulDoubleSurname() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Краснодар");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input ").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Елена Пирогова-Смирнова");
        $("[data-test-id='phone']  input").setValue("+79883339918");
        $("[data-test-id='agreement']").click();
        $("[button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    }

    // 3. Отправка заявки с указанием города через дефис;
    @Test
    public void thereMustBeASuccessfulHyphenatedCity() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Ростов-на-Дону");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input ").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Елена Пирогова");
        $("[data-test-id='phone']  input").setValue("+79883339918");
        $("[data-test-id='agreement']").click();
        $("[button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    }
}