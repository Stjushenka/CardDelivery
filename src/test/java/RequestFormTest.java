import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;


public class RequestFormTest {

    public String dateSetUp(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void shouldSendForm() {
        String meetingDay = dateSetUp(3);

        open("http://localhost:9999");
        $(".input__control[placeholder='Город']").setValue("Нижний Новгород");
        $(".input__control[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE, meetingDay);
        $(".input__control[name='name']").setValue("Иванов Иван");
        $(".input__control[name='phone']").setValue("+70000000000");
        $(".checkbox__box").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(appear, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(exactText("Встреча успешно забронирована на " + meetingDay));
    }

    @Test
    public void shouldSendForm1() {

        DateFormat LocalDate = new SimpleDateFormat("dd.MM.yyyy");

        open("http://localhost:9999");
        $(".input__control[placeholder='Город']").setValue("Ка");
        $(byText("Казань")).click();
        $(".icon_name_calendar").click();
        //$$(byName("calendar__arrow_direction_right")).last().click();
        $$("td").find(exactText("31")).click();
        $(".input__control[name='name']").setValue("Иванов Иван");
        $(".input__control[name='phone']").setValue("+78889996633");
        $(".checkbox__box").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(appear, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(exactText("Встреча успешно забронирована на " + "31.03.2022"));
    }


}