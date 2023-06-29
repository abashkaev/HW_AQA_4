
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    public String getFutureDay(int dayAgo) {
        LocalDate currentDate = LocalDate.now();
        LocalDate days = currentDate.plusDays(dayAgo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dayAgoStr = days.format(formatter);
        return dayAgoStr;
    }

    @Test
    void manualInputTestForm () {

      String fourDaysAgoStr = getFutureDay(4);

       open("http://localhost:9999/");
       $("[data-test-id='city'] input").setValue("Казань");
       $(".calendar-input .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
       $(".calendar-input .input__control").setValue(fourDaysAgoStr);
       $("[data-test-id='name'] input").setValue("Иванов Иван");
       $("[data-test-id='phone'] input").setValue("+79876543210");
       $("[data-test-id='agreement']").click();
       $(".button .button__text").click();
       $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + fourDaysAgoStr), Duration.ofSeconds(15)).shouldBe(Condition.visible);
       $(withText(fourDaysAgoStr)).shouldBe(visible);
    }
}
