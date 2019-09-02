package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import global.BasePage;

public class EvaluationSessionPage extends BasePage {
	
	private By createNewSessionLink;
	private By sessionNameTextArea;
	private By sessionTypeSelect;
	private By createSessionButton;
	private By headerSessionName;
	
    public EvaluationSessionPage (WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        createNewSessionLink = By.xpath("//a[normalize-space(text())='Create new evaluation session']");
        sessionNameTextArea = By.id("callIdentifier");
        sessionTypeSelect = By.id("typeId");
        createSessionButton = By.name("action confirm");
        headerSessionName = By.xpath("//div[contains(@class, 'session-name')]//h2");
    }

    //Go to Create new evaluation session page
    public void openCreateNewSessionPage(){
    	clickBy(createNewSessionLink);
    }
    
    //Enter session name on create new session page
    public void enterSessionName(String sessionName) {
    	write(sessionNameTextArea, sessionName);
    }
    
    //Select session type on create new session page
    public void selectSessionType(String sessionType) {
    	selectByText(sessionTypeSelect, sessionType);
    }
    
    //Click create session on create new session page
    public void clickCreateSessionButton() {
    	clickBy(createSessionButton);
    }
    
    //Get session name
    public String getSessionName() {
    	return getText(headerSessionName);
    }
    
    //Verify session name
    public void verifySessionName(String sName) {
    	Assert.assertTrue(isTextEquals(headerSessionName,sName), "verifySessionName(String sName)");
    }
    
 
}
