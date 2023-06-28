
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void manualInputTestForm () {

        LocalDate currentDate = LocalDate.now();
        LocalDate fourDaysAgo = currentDate.plusDays(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String fourDaysAgoStr = fourDaysAgo.format(formatter);

       open("http://localhost:9999/");
       $("[data-test-id='city'] input").setValue("Казань");
       $(".calendar-input .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
       $(".calendar-input .input__control").setValue(fourDaysAgoStr);
       $("[data-test-id='name'] input").setValue("Иванов Иван");
       $("[data-test-id='phone'] input").setValue("+79876543210");
       $("[data-test-id='agreement']").click();
       $(".button .button__text").click();
       $(withText( "Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
       $(withText(fourDaysAgoStr)).shouldBe(visible);
    }
}
