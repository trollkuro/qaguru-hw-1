package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GithubSelenidePageTest {

    @BeforeAll
    public static void browserSetUp() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        open("https://github.com");
        //Configuration.holdBrowserOpen = true;
    }

    @Test
    void codeExampleJUnit5IsPresented() {
        $(".search-input-container").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        $("[data-testid=results-list]").$(".search-title").shouldHave(text("selenide/selenide")).click();
        $("#wiki-tab").click();
        $(".markdown-body").$(byText("Soft assertions")).click();
        //check if JUnit5 is exist
        $$(".markdown-body h4").filterBy(text("JUnit5")).shouldHave(size(1));
        //check first code block of JUnit5
        $$(".markdown-body pre").filterBy(text("@ExtendWith")).shouldHave(size(1));
        //check second code block of JUnit5
        $$(".markdown-body pre").filterBy(text("@RegisterExtension")).shouldHave(size(1));
    }
}
