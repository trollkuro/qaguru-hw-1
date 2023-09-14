package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
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
        $(".wiki-more-pages-link button").click();
        $$("[data-filterable-for=wiki-pages-filter] li").findBy(text("SoftAssertions")).$("a").click();
        //check if JUnit5 is exist
        $$(".markdown-body h4").filterBy(text("JUnit5")).shouldHave(size(1));
        //check first code block of JUnit5
        $(".markdown-body").shouldHave(text(
                "@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));
        //check second code block of JUnit5
        $(".markdown-body").shouldHave(text(
                "class Tests {\n" +
                "  @RegisterExtension\n" +
                "  static SoftAssertsExtension softAsserts = new SoftAssertsExtension();" +
                "\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));
    }
}
