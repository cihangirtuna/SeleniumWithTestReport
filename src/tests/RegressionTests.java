package tests;

import org.testng.annotations.Test;

import global.BaseTest;
import global.Constants;
import pages.EvaluationSessionPage;
import pages.HomePage;


public class RegressionTests extends BaseTest {


    @Test (priority = 1, groups = { "regressions" }, description="RegressionTest - Setup a session")
    public void RegressionTest_Setup_a_session(){
    	//*************PAGE INSTANTIATIONS*************
        HomePage homePage = new HomePage(getDriver(),wait);  
        EvaluationSessionPage evaSessionPage = new EvaluationSessionPage(getDriver(), wait);
        
        //*************PAGE METHODS********************
        //Open Sep-Eva HomePage
        homePage.goToHomePage();
        //Login to Sep-Eva
        homePage.login(Constants.username, Constants.password);
        
        //Open Create New Session Page
        evaSessionPage.openCreateNewSessionPage();
        //Write session name
        evaSessionPage.enterSessionName("SepEvaAutomation_"+dateName);
        //Select session type
        evaSessionPage.selectSessionType("Scientific And Ethics");
        //Click create session button
        evaSessionPage.clickCreateSessionButton();
        
        //*************ASSERTIONS***********************
        evaSessionPage.verifySessionName("SepEvaAutomation_"+dateName);;
        
    }
    
  

}
