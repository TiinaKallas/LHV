import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MaximumMonthlyInstalment {
    public WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Kallati1\\IdeaProjects\\LHV\\src\\test\\resources\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        open("https://www.lhv.ee/et/liising#kalkulaator");

        $(".btn-dark").click();
        $("#kalkulaator").scrollTo();
        $("#kalkulaator").shouldBe(visible);
    }

    @Test
    public void maximumMonthlyInstallmentCalculatorElements() {

        //Check calculator field elements
        $("[href='#max-payment']").should(exist);
        $("[href='#max-payment']").shouldBe(visible);
        $("[href='#max-payment']").shouldHave(text("Maksimaalne kuumakse"));
        $("[href='#max-payment']").click();
        $("[href='#max-payment']").parent().shouldHave(cssClass("active"));

        $("[for='ownership']").should(exist);
        $("[for='ownership']").should(visible);
        $("[for='ownership']").shouldHave(text("Liisingut soovin taotleda"));

        $("[for='ownership-0']").should(exist);
        $("[for='ownership-0']").shouldBe(visible);
        $("#max-payment .radio:nth-child(1)").shouldHave(text("üksi"));
        $("[id='ownership-0']").shouldHave(type("radio"));
        $("[id='ownership-0']").shouldBe(checked);

        $("[for='ownership-1']").should(exist);
        $("[for='ownership-1']").shouldBe(visible);
        $("#max-payment .radio:nth-child(2)").shouldHave(text("koos kaastaotlejaga"));
        $("[id='ownership-1']").shouldHave(type("radio"));
        $("[id='ownership-1']").shouldNotBe(checked);

        $("[for='marital-status']").should(exist);
        $("[for='marital-status']").shouldBe(visible);
        $("[for='marital-status']").shouldHave(text("Perekonnaseis"));
        $("[id='marital-status-married']").shouldHave(type("checkbox"));
        $("[id='marital-status-married']").shouldBe(checked);

        $("[for='dependent-persons']").should(exist);
        $("[for='dependent-persons']").shouldBe(visible);
        $("[for='dependent-persons']").shouldHave(text("Ülalpeetavate arv"));
        $("#dependent-persons.form-control").should(exist);
        $("#dependent-persons.form-control").shouldBe(visible);
        $("#dependent-persons.form-control").shouldHave(text("1"));

        $("[for='monthly-income']").should(exist);
        $("[for='monthly-income']").shouldBe(visible);
        $("[for='monthly-income']").shouldHave(text("Netosissetulek"));

        $("#monthly-income.form-control").should(exist);
        $("#monthly-income.form-control").shouldBe(visible);
        $("#monthly-income.form-control").shouldHave(value("900"));
        $("#monthly-income.form-control").hover();
        $(new By.ByClassName("tooltip-inner")).shouldHave(text("Pangakontole laekuv regulaarne sissetulek sh palk, omanditulu, pension, sotsiaaltoetused, abiraha, stipendium"));

        $("#max-payment .radio:nth-child(2)").hover();
        $("#max-payment .radio:nth-child(2)").click();
        $("#max-payment > .row > .small").shouldBe(visible);
        $("#max-payment > .row > .small").shouldHave(text("Maksimaalse kuumakse arvutamiseks on netosissetulek liiga väike."));
        $(".col-xs-12:nth-child(2) .col-xs-11").should(exist);
        $(".col-xs-12:nth-child(2) .col-xs-11").shouldBe(visible);
        $(".col-xs-12:nth-child(2) .col-xs-11").shouldHave(text("Põhitaotleja"));

        $("#guarantor-fieldset .col-xs-11").should(exist);
        $("#guarantor-fieldset .col-xs-11").shouldBe(visible);
        $("#guarantor-fieldset .col-xs-11").shouldHave(text("Kaastaotleja"));
        $("[for='guarantor-marital-status']").should(exist);
        $("[for='guarantor-marital-status']").shouldBe(visible);
        $("[for='guarantor-marital-status']").shouldHave(text("Perekonnaseis"));
        $("[for='guarantor-marital-status']").shouldNotBe(checked);

        $("[for='guarantor-dependent-persons']").should(exist);
        $("[for='guarantor-dependent-persons']").shouldBe(visible);
        $("[for='guarantor-dependent-persons']").shouldHave(text("Ülalpeetavate arv"));
        $("#guarantor-dependent-persons.form-control").should(exist);
        $("#guarantor-dependent-persons.form-control").shouldBe(visible);
        $("#guarantor-dependent-persons.form-control").shouldHave(text("0"));

        $("[for='guarantor-monthly-income']").should(exist);
        $("[for='guarantor-monthly-income']").shouldBe(visible);
        $("[for='guarantor-monthly-income']").shouldHave(text("Netosissetulek"));

        $("#guarantor-monthly-income.form-control").should(exist);
        $("#guarantor-monthly-income.form-control").shouldBe(visible);
        $("#guarantor-monthly-income.form-control").shouldHave(value("0"));

        $("#max-payment p").shouldHave(text("Tulemus on ligikaudne ja võib erineda sulle pakutavatest tingimustest"));

        $("[for='ownership-0']").click();

        //Check calculator results field elements
        $("#max-payment .col-xs-12 > .small").should(exist);
        $("#max-payment .col-xs-12 > .small").shouldBe(visible);
        $("#max-payment .col-xs-12 > .small").shouldHave(text("Kuumakse"));

        $("#max-payment .payment").should(exist);
        $("#max-payment .payment").shouldBe(visible);

        $("#max-payment .btn").should(exist);
        $("#max-payment .btn").shouldBe(visible);
        $("#max-payment .btn").shouldHave(text("Taotle siin"));
    }

    @Test
    public void maxMonthlyAloneSingleNoKids() {

        //Fill in calculator fields
        $("[href='#max-payment']").click();
        $("#max-payment .radio:nth-child(1)").shouldHave(text("üksi"));
        $(".col-xs-12:nth-child(2) .col-xs-12 label").click();
        $("[id='dependent-persons']").selectOption("0");
        $("#monthly-income.form-control").setValue("1300");

        //Verify calculated values
        $("#max-payment .payment").shouldHave(text("594.79"));
    }

    @Test
    public void maxMonthlyAloneMarriedOneKid() {

        //Fill in calculator fields
        $("[href='#max-payment']").click();
        $("#max-payment .radio:nth-child(1)").shouldHave(text("üksi"));
        $("[id='marital-status-married']").shouldBe(checked);
        $("#dependent-persons.form-control").shouldHave(text("1"));
        $("#monthly-income.form-control").setValue("1500");

        //Verify calculated values
        $("#max-payment .payment").shouldHave(text("659.71"));
    }

    @Test
    public void maxMonthlyWithCoApplicantMarriedThreeKids() {

        //Fill in calculator fields
        $("[href='#max-payment']").click();
        $("#max-payment .radio:nth-child(2)").click();
        $("[id='marital-status-married']").shouldBe(checked);
        $("[id='dependent-persons']").selectOption("3");
        $("#monthly-income.form-control").setValue("1800");
        $("#guarantor-dependent-persons.form-control").selectOption("1");
        $("#guarantor-monthly-income.form-control").setValue("900");

        //Verify calculated values
        $("#max-payment .payment").shouldHave(text("1139.14"));
    }

    @AfterEach
    public void cleanup() {
        WebDriverRunner.closeWebDriver();
    }
}
