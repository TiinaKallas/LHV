import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SampleMonthlyInstalment {

    public WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        open("https://www.lhv.ee/et/liising#kalkulaator");

        $(".btn-dark").click();
        $("#kalkulaator").scrollTo();
        $("#kalkulaator").shouldBe(visible);
    }

    @Test
    public void checkLeasingCalculatorElements() {

        //Check calculator field elements
        $("#kalkulaator").should(exist);
        $("#kalkulaator").shouldBe(visible);
        $("#kalkulaator").shouldHave(text("Arvuta kuumakse"));

        $("[href='#monthly-payment']").should(exist);
        $("[href='#monthly-payment']").shouldBe(visible);
        $("[href='#monthly-payment']").shouldHave(text("Näidiskuumakse"));
        $("[href='#monthly-payment']").parent().shouldHave(cssClass("active"));

        $("[href='#max-payment']").should(exist);
        $("[href='#max-payment']").shouldBe(visible);
        $("[href='#max-payment']").shouldHave(text("Maksimaalne kuumakse"));
        $("[href='#max-payment']").parent().shouldNotHave(cssClass("active"));

        $("[for='account_type']").should(exist);
        $("[for='account_type']").shouldBe(visible);
        $("[for='account_type']").shouldHave(text("Soovin liisingut"));
        $("[for='account_type-0']").shouldBe(visible);
        $("[for='account_type-0']").shouldHave(text("eraisikuna"));
        $("[id='account_type-0']").shouldHave(type("radio"));
        $("[id='account_type-0']").shouldBe(checked);

        $("[for='account_type-1']").should(exist);
        $("[for='account_type-1']").shouldBe(visible);
        $("[for='account_type-1']").shouldHave(text("juriidilise isikuna"));
        $("[id='account_type-1']").shouldHave(type("radio"));
        $("[id='account_type-1']").shouldNotBe(checked);

        $("[for='lease_type']").should(exist);
        $("[for='lease_type']").shouldBe(visible);
        $("[for='lease_type']").shouldHave(text("Liisingu tüüp"));

        $("[for='kap_rent']").should(exist);
        $("[for='kap_rent']").shouldBe(visible);
        $("[for='kap_rent']").shouldHave(text("kapitalirent"));
        $("[id='kap_rent']").shouldHave(type("radio"));
        $("[id='kap_rent']").shouldBe(checked);

        $("[for='kas_rent']").should(exist);
        $("[for='kas_rent']").shouldBe(visible);
        $("[for='kas_rent']").shouldHave(text("kasutusrent"));
        $("[id='kas_rent']").shouldHave(type("radio"));
        $("[id='kas_rent']").shouldNotBe(checked);

        $("[for='price']").should(exist);
        $("[for='price']").shouldBe(visible);
        $("[for='price']").shouldHave(text("Sõiduki hind"));
        $("#price").shouldHave(value("15 000"));
        $(".form-group:nth-child(3) .input-group-addon").should(exist);
        $(".form-group:nth-child(3) .input-group-addon").shouldBe(visible);
        $(".form-group:nth-child(3) .input-group-addon").shouldHave(text("€"));

        $(".form-group:nth-child(4) > .col-sm-6 label").shouldBe(exist);
        $(".form-group:nth-child(4) > .col-sm-6 label").shouldBe(visible);
        $("[for='vat_included']").shouldHave(text("Hind sisaldab käibemaksu"));
        $("[id='vat_included']").should(exist);
        $("[id='vat_included']").shouldHave(type("checkbox"));
        $("[id='vat_included']").shouldBe(checked);

        $("[for='account_type-1']").click();
        $("[for='vat_scheduling']").should(exist);
        $("[for='vat_scheduling']").shouldBe(visible);
        $("[for='vat_scheduling']").shouldHave(text("Käibemaksu tasumine"));
        $("#vat_scheduling.form-control").should(exist);
        $("#vat_scheduling.form-control").shouldBe(visible);
        $("#vat_scheduling.form-control").shouldHave(text("Koos sissemaksega"));

        $("[for='account_type-0']").click();
        $("[for='initial_percentage']").should(exist);
        $("[for='initial_percentage']").shouldBe(visible);
        $("[for='initial_percentage']").shouldHave(text("Sissemakse"));
        $("#initial_percentage").shouldHave(value("10"));
        $(".form-group:nth-child(6) .input-group-addon").should(exist);
        $(".form-group:nth-child(6) .input-group-addon").shouldBe(visible);
        $(".form-group:nth-child(6) .input-group-addon").shouldHave(text("%"));

        $("#initial").should(exist);
        $("#initial").shouldBe(visible);
        $("#initial").shouldHave(value("1500"));
        $(".form-group:nth-child(7) .input-group-addon").should(exist);
        $(".form-group:nth-child(7) .input-group-addon").shouldBe(visible);
        $(".form-group:nth-child(7) .input-group-addon").shouldHave(text("€"));

        $("[for='period']").should(exist);
        $("[for='period']").shouldBe(visible);
        $("[for='period']").shouldHave(text("Liisingu periood"));
        $(".dropdown-toggle").should(exist);
        $(".dropdown-toggle").shouldBe(visible);
        $(".col-xs-6:nth-child(1) .form-control").shouldHave(value("72"));
        $(".col-xs-6:nth-child(1) .input-group-addon").should(exist);
        $(".col-xs-6:nth-child(1) .input-group-addon").shouldBe(visible);
        $(".col-xs-6:nth-child(1) .input-group-addon").shouldHave(text("aastat"));

        $(".col-xs-6:nth-child(2) .form-control").should(exist);
        $(".col-xs-6:nth-child(2) .form-control").shouldBe(visible);
        $(".col-xs-6:nth-child(2) .form-control").shouldHave(value("0"));
        $(".col-xs-6:nth-child(2) .input-group-addon").should(exist);
        $(".col-xs-6:nth-child(2) .input-group-addon").shouldBe(visible);
        $(".col-xs-6:nth-child(2) .input-group-addon").shouldHave(text("kuud"));

        $("[for='interest_rate']").should(exist);
        $("[for='interest_rate']").shouldBe(visible);
        $("[for='interest_rate']").shouldHave(text("Intress"));
        $("#interest_rate").shouldHave(value("6"));
        $(".form-group:nth-child(10) .input-group-addon").should(exist);
        $(".form-group:nth-child(10) .input-group-addon").shouldBe(visible);
        $(".form-group:nth-child(10) .input-group-addon").shouldHave(text("%"));

        $("[for='reminder_percentage']").should(exist);
        $("[for='reminder_percentage']").shouldBe(visible);
        $("[for='reminder_percentage']").shouldHave(text("Jääkväärtus"));
        $("#reminder_percentage").shouldHave(value("10"));
        $(".form-group:nth-child(11) .input-group-addon").should(exist);
        $(".form-group:nth-child(11) .input-group-addon").shouldBe(visible);
        $(".form-group:nth-child(11) .input-group-addon").shouldHave(text("%"));

        $("#reminder").should(exist);
        $("#reminder").shouldBe(visible);
        $("#reminder").shouldHave(value("1500"));
        $(".form-group:nth-child(12) .input-group-addon").should(exist);
        $(".form-group:nth-child(12) .input-group-addon").shouldBe(visible);
        $(".form-group:nth-child(12) .input-group-addon").shouldHave(text("€"));

        $("[id='monthly-payment']").shouldHave(text("Tulemus on ligikaudne ja võib erineda sulle pakutavatest tingimustest"));

        //Check calculator results field elements
        $("#monthly-payment").should(exist);
        $("#monthly-payment").shouldBe(visible);
        $("#monthly-payment").shouldHave(text("Kuumakse"));

        $(".payment").should(exist);
        $(".payment").shouldBe(visible);

        $("#monthly-payment .btn").should(exist);
        $("#monthly-payment .btn").shouldBe(visible);
        $("#monthly-payment .btn").shouldHave(text("Taotle liisingut"));

        $(".arrow > span").should(exist);
        $(".arrow > span").shouldBe(visible);
        $(".arrow > span").shouldHave(text("Maksegraafik"));
    }

    @Test
    public void privatePersonFinancialLeaseMinRequirements() {

        //Fill in calculator fields
        $("[for='account_type-0']").click();
        $("[for='kap_rent']").click();
        $("#price").setValue("7500");
        $("#initial_percentage").setValue("10");
        $(".dropdown-toggle").click();
        $(".col-xs-6:nth-child(1) .form-control").selectOption("0");
        $(".col-xs-6:nth-child(2) .form-control").selectOption("6");
        $("#reminder_percentage").setValue("10");

        //Verify calculated values
        $("#initial").shouldHave(value("750"));
        $("#reminder").shouldHave(value("750"));
        $(".payment").shouldHave(text("1021.32"));
    }

    @Test
    public void privatePersonFinancialLeaseMinPriceWithoutVatMaxPeriod() {

        //Fill in calculator fields
        $("[for='account_type-0']").click();
        $("[for='kap_rent']").click();
        $("#price").setValue("7500");
        $("[for='vat_included']").click();
        $("#initial_percentage").setValue("10");
        $(".dropdown-toggle").click();
        $(".col-xs-6:nth-child(1) .form-control").selectOption("7");
        $("#reminder_percentage").setValue("0");

        //Verify calculated values
        $("#initial").shouldHave(value("750"));
        $("#reminder").shouldHave(value("0"));
        $(".payment").shouldHave(text("98.60"));
    }

    @Test
    public void legalPersonFinancialLeaseWithVatPaymentInThirdMonth() {

        //Fill in calculator fields
        $("[for='account_type-1']").click();
        $("[for='kap_rent']").click();
        $("#price").setValue("50000");
        $("#vat_scheduling.form-control").click();
        $("#vat_scheduling").selectOption("Tasumine kolmandal kuul");
        $("#initial").setValue("10000");
        $(".dropdown-toggle").click();
        $(".col-xs-6:nth-child(1) .form-control").selectOption("6");
        $(".col-xs-6:nth-child(2) .form-control").selectOption("1");
        $("#reminder_percentage").setValue("15");

        //Verify calculated values
        $("#initial_percentage").shouldHave(value("20"));
        $("#reminder").shouldHave(value("7500"));
        $(".payment").shouldHave(text("456.87"));
    }

    @Test
    public void legalPersonOperationalLeaseWithoutVat() {

        //Fill in calculator fields
        $("[for='account_type-1']").click();
        $("[for='kas_rent']").click();
        $("#price").setValue("25000");
        $("[for='vat_included']").click();
        $("#initial_percentage").setValue("20");
        $(".dropdown-toggle").click();
        $(".col-xs-6:nth-child(1) .form-control").selectOption("4");
        $("#reminder").setValue("3000");

        //Verify calculated values
        $("#initial").shouldHave(value("5000"));
        $("#reminder_percentage").shouldHave(value("12"));
        $(".payment").shouldHave(text("484.79"));
    }

    @Test
    public void legalPersonOperationalLeaseWithVatMaxPeriod() {

        //Fill in calculator fields
        $("[for='account_type-1']").click();
        $("[for='kas_rent']").click();
        $("#price").setValue("25000");
        $("[id='vat_included']").shouldBe(checked);
        $("#initial_percentage").setValue("20");
        $(".dropdown-toggle").click();
        $(".col-xs-6:nth-child(1) .form-control").selectOption("6");
        $(".col-xs-6:nth-child(2) .form-control").shouldNotBe(visible);
        $("#reminder").setValue("3000");

        //Verify calculated values
        $("#initial").shouldHave(value("5000"));
        $("#reminder_percentage").shouldHave(value("12"));
        $(".payment").shouldHave(text("286.28"));
    }

    @Test
    public void privatePersonOperationalLease() {

        //Fill in calculator fields
        $("[for='account_type-0']").click();
        $("[for='kas_rent']").click();
        $("#price").setValue("75000");
        $("#initial").setValue("10000");
        $(".dropdown-toggle").click();
        $(".col-xs-6:nth-child(1) .form-control").selectOption("5");
        $(".col-xs-6:nth-child(2) .form-control").selectOption("3");
        $("#reminder_percentage").setValue("10");

        //Verify calculated values
        $("#initial_percentage").shouldHave(value("13.33"));
        $("#reminder").shouldHave(value("7500"));
        $(".payment").shouldHave(text("1070.90"));
    }

    @Test
    public void invalidInput() {

        //Fill in invalid input
        $("#price").setValue("invalid");
        $("#initial").setValue("-1000");
        $(".dropdown-toggle").click();
        $(".col-xs-6:nth-child(1) .form-control").selectOption("3");
        $("#reminder").setValue("abc");

        //Verify that the result section is not visible
        $(".payment").shouldHave(text("0.00"));
    }

    @Test
    public void changeToLegalPersonDefaultCalculatorSetting() {

        //Fill in calculator fields
        $("[id='account_type-0']").shouldBe(checked);
        $("[id='kap_rent']").shouldBe(checked);
        $("#price").shouldHave(value("15 000"));
        $("[id='vat_included']").shouldBe(checked);
        $("#initial_percentage").shouldHave(value("10"));
        $("#initial").shouldHave(value("1500"));
        $(".col-xs-6:nth-child(1) .form-control").shouldHave(value("72"));
        $(".col-xs-6:nth-child(2) .form-control").shouldHave(value("0"));
        $("#interest_rate").shouldHave(value("6"));
        $("#reminder_percentage").shouldHave(value("10"));
        $("#reminder").shouldHave(value("1500"));

        //Verify the calculated monthly payment
        $(".payment").shouldHave(text("206.37"));

        //Change to legal person
        $(".form-group:nth-child(1) .radio:nth-child(2)").click();

        //Verify the updated monthly payment
        $(".payment").shouldHave(text("169.08"));
    }

    @Test
    public void changeToOperationalLeaseDefaultCalculatorSetting() {

        //Fill in calculator fields
        $("[id='account_type-0']").shouldBe(checked);
        $("[id='kap_rent']").shouldBe(checked);
        $("#price").shouldHave(value("15 000"));
        $("[id='vat_included']").shouldBe(checked);
        $("#initial_percentage").shouldHave(value("10"));
        $("#initial").shouldHave(value("1500"));
        $(".col-xs-6:nth-child(1) .form-control").shouldHave(value("72"));
        $(".col-xs-6:nth-child(2) .form-control").shouldHave(value("0"));
        $("#interest_rate").shouldHave(value("6"));
        $("#reminder_percentage").shouldHave(value("10"));
        $("#reminder").shouldHave(value("1500"));

        //Verify the calculated monthly payment
        $(".payment").shouldHave(text("206.37"));

        //Change to operational lease
        $(".form-group:nth-child(2) .radio:nth-child(2) > label").click();
        //$(".kas_rent").click();

        //Verify the updated monthly payment
        $(".payment").shouldHave(text("199.50"));
    }

    @Test
    public void changeLeasePeriodDefaultCalculatorSetting() {

        //Fill in calculator fields
        $("[id='account_type-0']").shouldBe(checked);
        $("[id='kap_rent']").shouldBe(checked);
        $("#price").shouldHave(value("15 000"));
        $("[id='vat_included']").shouldBe(checked);
        $("#initial_percentage").shouldHave(value("10"));
        $("#initial").shouldHave(value("1500"));
        $(".col-xs-6:nth-child(1) .form-control").shouldHave(value("72"));
        $(".col-xs-6:nth-child(2) .form-control").shouldHave(value("0"));
        $("#interest_rate").shouldHave(value("6"));
        $("#reminder_percentage").shouldHave(value("10"));
        $("#reminder").shouldHave(value("1500"));

        //Verify the calculated monthly payment
        $(".payment").shouldHave(text("206.37"));

        //Change the lease period to 5 years
        $(".dropdown-toggle").click();
        $(".col-xs-6:nth-child(1) .form-control").selectOption("3");
        $(".col-xs-6:nth-child(2) .form-control").selectOption("7");

        //Verify the updated monthly payment
        $(".payment").shouldHave(text("318.33"));
    }

    @Test
    public void changeInterestRateDefaultCalculatorSettings() {
        $("[id='account_type-0']").shouldBe(checked);
        $("[id='kap_rent']").shouldBe(checked);
        $("#price").shouldHave(value("15 000"));
        $("[id='vat_included']").shouldBe(checked);
        $("#initial_percentage").shouldHave(value("10"));
        $("#initial").shouldHave(value("1500"));
        $(".col-xs-6:nth-child(1) .form-control").shouldHave(value("72"));
        $(".col-xs-6:nth-child(2) .form-control").shouldHave(value("0"));
        $("#interest_rate").shouldHave(value("6"));
        $("#reminder_percentage").shouldHave(value("10"));
        $("#reminder").shouldHave(value("1500"));

        //Verify the calculated monthly payment
        $(".payment").shouldHave(text("206.37"));

        //Change the interest rate to 5%
        $("#interest_rate").setValue("4.83");

        //Verify the updated monthly payment
        $(".payment").shouldHave(text("198.35"));
    }

    @AfterEach
    public void cleanup() {
        WebDriverRunner.closeWebDriver();
    }
}

