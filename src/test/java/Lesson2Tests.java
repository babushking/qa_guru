import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class Lesson2Tests {

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
    void fillFormTest() {
        openPage();
        fillForm();
        checkForm();
    }

    void openPage() {
        // Открыть форму
        open("https://demoqa.com/automation-practice-form");
    }

    void fillForm() {
        // Заполнить форму
        $("#firstName").setValue(this.firstName);
        $("#lastName").setValue(this.lastName);
        $("#userEmail").setValue(this.userEmail);
        $("label[for='gender-radio-1']").click();
        $("#userNumber").setValue(this.userNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(this.year);
        $(".react-datepicker__month-select").selectOption(this.month);
        $(".react-datepicker__day--0" + this.day).click();

        $("#subjectsInput").setValue(this.subjects);
        $$("div[id^='react-select-2-option']").find(text(this.subjects)).click();

        $("label[for='hobbies-checkbox-1']").click();
        $("label[for='hobbies-checkbox-2']").click();
        $("label[for='hobbies-checkbox-3']").click();

        $("#currentAddress").setValue(this.currentAddress);

        $("#uploadPicture").uploadFile(new File("src/test/resources/" + filename));

        $(byText("Select State")).scrollTo().click();
        $$("div[id^='react-select-3-option']").find(text("NCR")).click();

        $(byText("Select City")).scrollTo().click();
        $$("div[id^='react-select-4-option']").find(text("Delhi")).click();

        // Отправить форму
        $("#submit").click();
    }



    void checkForm () {
        checkValue("Student Name", this.firstName + " " + this.lastName);
        checkValue("Student Email", this.userEmail);
        checkValue("Gender", this.gender);
        checkValue("Mobile", this.userNumber);
        checkValue("Date of Birth", this.day + " " + this.month + "," + this.year);
        checkValue("Subjects", this.subjects);
        checkValue("Hobbies", String.join(", ", this.hobbies));
        checkValue("Picture", this.filename);
        checkValue("Address", this.currentAddress);
        checkValue("State and City", this.state + " " + this.city);
    }

    void checkValue (String key, String value) {
        $$(".table-responsive td").find(text(key)).sibling(0).shouldHave(exactText(value));
    }
}
