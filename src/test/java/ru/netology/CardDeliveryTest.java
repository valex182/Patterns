package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class CardDeliveryTest {

    @Test
    public void shouldSendForm() {
        RegistrationByCardInfo registrationByCardInfo = DataGenerator.Registration.generateInfo("ru");
        String firstAppoinedDay = DataGenerator.Registration.generateDate(4);
        String secondAppointedDay = DataGenerator.Registration.generateDate(7);

        //Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue(registrationByCardInfo.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").doubleClick().sendKeys(firstAppoinedDay);
        $("[data-test-id='name'] input").setValue(registrationByCardInfo.getName());
        $("[data-test-id='phone'] input").setValue(registrationByCardInfo.getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + firstAppoinedDay), Duration.ofSeconds(15));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").doubleClick().sendKeys(secondAppointedDay);
        $$("button").find(exactText("Запланировать")).click();
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible, Duration.ofSeconds(15));
        $$("button").find(exactText("Перепланировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + secondAppointedDay), Duration.ofSeconds(15));

    }
}
