package seleniumactions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumActions {
	/** The driver to launch to browser. */
	public RemoteWebDriver driver;

	public WebDriverWait wait;
	
	public JavascriptExecutor js;
	
	public Actions actionBuilder;


	public void launchBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			
			FirefoxOptions options = new FirefoxOptions();
			//options.setProfile(new FirefoxProfile());
			options.addPreference("dom.webnotifications.enabled", false);

			driver = new FirefoxDriver(options);
		}else if(browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driver = new InternetExplorerDriver(options);
		}
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		actionBuilder = new Actions(driver);
		js = driver;
	}
	
	
	public void launchWebApplication(String url) {
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	
	
}
