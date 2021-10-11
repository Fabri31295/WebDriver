import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestPlan {

    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Login successfully")
    public static void loginSuccessfully() {
        driver.get(Utils.BASE_URL);
        LoginForm loginform = new LoginForm(driver);
        loginform.enterUsername();
        loginform.enterPassword();
        loginform.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.getTitle(),"PRODUCTS");
    }

    @Test(testName = "Add one item to cart")
    public static void verifyItemAdded() {
        driver.get(Utils.BASE_URL);
        LoginForm loginform = new LoginForm(driver);
        loginform.enterUsername();
        loginform.enterPassword();
        loginform.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCartBackpack();
        Assert.assertEquals(productsPage.getCardBadge(),"1");
    }

    @Test(testName = "Desloguearse correctamente")
    public static void desloguearseCorrectamente() {
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);
        loginForm.enterUsername();
        loginForm.enterPassword();
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.open_collapsible_menu();
        productsPage.logout();
    }

    @Test(testName = "Verificar carrito")
    public static void verificarCarrito() {
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);
        loginForm.enterUsername();
        loginForm.enterPassword();
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCartBackpack();
        productsPage.showCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getTitleItem(),"Sauce Labs Backpack");
    }


    @AfterSuite
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
        //driver.close();
    }

}
