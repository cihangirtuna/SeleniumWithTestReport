package tests;

import org.testng.annotations.Test;

import global.BaseTest;
import global.Constants;
import pages.HomePage;


public class LoginTests extends BaseTest {


    @Test (priority = 1, description="Valid Login Scenario with valid username and password.")
    public void ValidLoginTest_ValidUserNameValidPassword ()  {

        //*************PAGE INSTANTIATIONS*************
        HomePage homePage = new HomePage(getDriver(),wait);
     
        //*************PAGE METHODS********************
        //Open Sep-Eva HomePage
        homePage.goToHomePage();

        //Login to Sep-Eva
        homePage.login(Constants.username, Constants.password);

        //*************ASSERTIONS***********************
     	homePage.verifyLogin();
    }
    
    @Test (priority = 1, description="Valid Login Scenario with valid username and unvalid password.")
    public void UnvalidLoginTest_ValidUserNameUnvalidPassword ()  {

        //*************PAGE INSTANTIATIONS*************
        HomePage homePage = new HomePage(getDriver(),wait);
     
        //*************PAGE METHODS********************
        //Open Sep-Eva HomePage
        homePage.goToHomePage();

        //Login to Sep-Eva
        homePage.login(Constants.username, "1234");

        //*************ASSERTIONS***********************
     	homePage.verifyNotLogin();
    }

}
