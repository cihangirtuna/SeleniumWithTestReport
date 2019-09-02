package global;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	public WebDriver driver;
	public WebDriverWait wait;

	public BasePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void waitVisibility(By by) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitPageLoad() {
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	public WebElement findElement(By by) {
		return driver.findElement(by);
	}
	
	public List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	public void clickBy(By by) {
		waitPageLoad();
		waitVisibility(by);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
		element.click();
	}

	public void write(By by, String text) {
		waitPageLoad();
		waitVisibility(by);
		findElement(by).sendKeys(text);
	}

	public String getText(By by) {
		waitPageLoad();
		waitVisibility(by);
		return findElement(by).getText();
	}

	public void selectByValue(By by, String text) {
		waitPageLoad();
		Select select = new Select(findElement(by));
		select.selectByValue(text);
	}

	public void selectByText(By by, String text) {
		waitPageLoad();
		Select select = new Select(findElement(by));
		select.selectByVisibleText(text);
	}

	public Boolean isTextEquals(By by, String expectedText) {
		waitPageLoad();
		String text = getText(by);
		if (text.equalsIgnoreCase(expectedText)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isTextContains(By by, String expectedText) {
		waitPageLoad();
		String text = getText(by);
		if (text.contains(expectedText)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isElementExists(By by) {
		waitPageLoad();
			if (findElements(by).size() != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public  void ifExistsThenClick(By by) {		
		if (isElementExists(by)) {
			findElement(by).click();
		}
	}
	

	public Boolean checkUrlContains(String expectedText) {
		waitPageLoad();
		String url = driver.getCurrentUrl();
		if (url.contains(expectedText)) {
			return true;
		} else {
			return false;
		}
	}

}
