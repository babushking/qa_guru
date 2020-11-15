import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class Lesson2 {

    private String firstName = "Anton";
    private String lastName = "Babushkin";
    private String userEmail = "babos87@mail.ru";

    private String gender = "Male";

    private String userNumber = "9443456677";

    private String year = "1987";
    private String month = "October";
    private String day = "24";

    private String subjects = "Computer Science";

    private String[] hobbies = {"Sports", "Reading", "Music"};

    private String filename = "avatar.jpg";

    private String currentAddress = "Moscow";
    private String state = "NCR";
    private String city = "Delhi";

    @Test
    void testForm() {
        submit();
        check();
    }

    void submit () {
        // Открыть форму
        open("https://demoqa.com/automation-practice-form");

        // Заполнить форму
        $(byId("firstName")).setValue(this.firstName);
        $(byId("lastName")).setValue("Babushkin");
        $(byId("userEmail")).setValue("babos87@mail.ru");
        $("label[for=\"gender-radio-1\"]").click(); // Mail
        $(byId("userNumber")).setValue("9443456677");


        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1987");
        $(".react-datepicker__month-select").selectOption("October");
        $(".react-datepicker__day--0" + "24").click();

        $(byId("subjectsInput")).setValue("Computer Science");
        $$("div[id^=\"react-select-2-option\"]").find(text("Computer Science")).click();

        $("label[for=\"hobbies-checkbox-1\"]").click();
        $("label[for=\"hobbies-checkbox-2\"]").click();
        $("label[for=\"hobbies-checkbox-3\"]").click();

        $("#currentAddress").setValue("Moscow");

        $("#uploadPicture").uploadFile(new File("src/resources/avatar.jpg"));

        $(byText("Select State")).scrollTo().click();
        $$("div[id^=\"react-select-3-option\"]").find(text("NCR")).click();

        $(byText("Select City")).scrollTo().click();
        $$("div[id^=\"react-select-4-option\"]").find(text("Delhi")).click();

        $("#submit").click();
    }

    void check () {
        checkItemEqual("Student Name", this.firstName + " " + this.lastName);
        checkItemEqual("Student Email", this.userEmail);
        checkItemEqual("Gender", this.gender);
        checkItemEqual("Mobile", this.userNumber);
        checkItemEqual("Date of Birth", this.day + " " + this.month + "," + this.year);
        checkItemEqual("Subjects", this.subjects);
        checkItemEqual("Hobbies", String.join(", ", this.hobbies));
        checkItemEqual("Picture", this.filename);
        checkItemEqual("Address", this.currentAddress);
        checkItemEqual("State and City", this.state + " " + this.city);
    }

    void checkItemEqual (String key, String value) {
        $$(".table-responsive td").find(text(key)).sibling(0).shouldHave(exactText(value));
    }

    void checkItemContain (String key, String value) {
        $$(".table-responsive td").find(text(key)).sibling(0).shouldBe(exactText(value));
    }
}
