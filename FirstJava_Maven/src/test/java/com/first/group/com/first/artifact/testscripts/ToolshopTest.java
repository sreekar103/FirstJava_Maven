package com.first.group.com.first.artifact.testscripts;

import com.first.group.com.first.artifact.pages.HomePage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class ToolshopTest extends TestBase {

    public void verifySignInButton() {
        HomePage homePage = new HomePage(getDriver());
        homePage.get();
        assertThat(homePage.isSignInButtonDisplayed()).isTrue();
    }

    public void verifySignInButton_failOnPurpose() {
        HomePage homePage = new HomePage(getDriver());
        homePage.get();
        assertThat(homePage.isSignInButtonDisplayed()).isFalse();
    }
}
