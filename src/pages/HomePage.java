package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import global.BasePage;
import global.Constants;

public class HomePage extends BasePage {
	
    private By userName;
    private By passWord;
    private By submitButton;
    private By ecButton;
    private By userInfo;
    private By nextButton;
    private By errorInfo;
    private By errorInfoCloud;
    private By evaluationsTab;

    public HomePage (WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        userName = By.id("username");
        passWord = By.id("password");
        submitButton = By.name("_submit");
        nextButton = By.name("whoamiSubmit");
        ecButton = By.xpath("//a[@title='European Commission']");
        userInfo = By.className("user-info");
        errorInfo = By.className("srvErrors");
        errorInfoCloud = By.className("error");
        evaluationsTab = By.xpath("//a[normalize-space(text())='Evaluations']");
    }

    //Go to Homepage
    public void goToHomePage(){
        driver.get(Constants.URL);
        //driver.navigate().to(baseURL)
    }
    
    //Login 
    public void login(String user, String pwd){
    	ifExistsThenClick(ecButton); // cloud has this button
    	write(userName, user);
    	ifExistsThenClick(nextButton); // cloud doesn't have this button
    	write(passWord, pwd);
    	clickBy(submitButton); 
    }
    
    //Verify login
    public void verifyLogin(){
    	Assert.assertTrue(isElementExists(userInfo), "verifyLogin()");
    }
    
    //Verify not login
    public void verifyNotLogin(){
    	Assert.assertTrue(isElementExists(errorInfo) || isElementExists(errorInfoCloud) , "verifyNotLogin()");		
    }
    
    //Go to Evaluations Tab
    public void goToEvaluationsTab(){
    	clickBy(evaluationsTab);
    }
}
