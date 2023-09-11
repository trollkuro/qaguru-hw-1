import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemoqaFormTests {

    private String firstName = "Angelina";
    private String lastName = "Jolie";
    private String userEmail = "AngelinaJolie@test.com";
    private String sex = "Female";
    private String userNumber = "9991110000";
    private String day = "04";
    private String month = "June";
    private String year = "1975";
    private String subject = "Maths";
    private String hobby = "Reading";
    private String filePath = "src/test/resources/tiger.jpg";
    private String fileName = "tiger.jpg";
    private String currentAddress = "USA";
    private String state = "Haryana";
    private String city = "Panipat";

    @BeforeAll
    public static void browserSetUp(){
        Configuration.pageLoadStrategy = "eager";
        open("https://demoqa.com/automation-practice-form");
        //Configuration.holdBrowserOpen = true;
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }

    @Test
    void allFieldsAreFilled(){
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText(sex)).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-dropdown-container").$(byText(month)).click();
        $(".react-datepicker__year-select").$(byText(year)).click();
        $(".react-datepicker__day--004").click();
        $("#subjectsInput").setValue(subject).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $(".form-control-file").uploadFile(new File(filePath));
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        $("[id=submit]").click();

        //Checking filled data
        $(".modal-content").shouldHave(
                text(firstName + " " + lastName),
                text(userEmail),
                text(sex),
                text(userNumber),
                text(day + " " + month + "," + year),
                text(subject),
                text(hobby),
                text(fileName),
                text(currentAddress),
                text(state + " " + city)
        );
    }
}
