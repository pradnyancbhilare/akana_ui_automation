package com.perforce.akana.qa;

import org.openqa.selenium.Dimension;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.perforce.akana.qa.utils.Sleeper;

public abstract class APageObject {
	
    protected String windowHandle = null;
    protected String callersWindowHandle = null;
    protected final Logger logger = LogManager.getLogger(getClass());
    
    public APageObject() {
        windowHandle = getWindowHandle();
    }
    
    public APageObject(String url) {
        logger.info(String.format("Opening url: %s", url));
        Connection.getWebDriver().get(url);
        windowHandle = getWindowHandle();
    }
    
    public String getTitle() {
        String title = Connection.getWebDriver().getTitle();
        logger.trace(String.format("Retrieved title: '%s'", title));
        return title;
    }
    
    public String getURL() {
        String url = Connection.getWebDriver().getCurrentUrl();
        logger.trace(String.format("Retrieved URL: '%s'", url));
        return url;
    }
    
    public String getWindowHandle() {
        windowHandle = Connection.getWebDriver().getWindowHandle();
        logger.trace(String.format("Retrieved handle: '%s'", windowHandle));
        return windowHandle;
    }
    
    public void switchToNewWindow() {
        logger.info("Switching to new window");
        Sleeper.seconds(3, "Letting the window switch happen");
        WebDriver wd = Connection.getWebDriver();
        Set<String> handles = wd.getWindowHandles();
        String[] handleArray = handles.toArray(new String[handles.size()]);
        wd.switchTo().window(handleArray[handles.size() - 1]);
        Sleeper.seconds(2, "Letting the window switch stabilize");
        // Screenshot can be taken
    }
    
    public APageObject reload() {
        logger.info("Reloading " + this.getClass().getName());
        WebDriver wd = Connection.getWebDriver();
        wd.navigate().refresh();
        Sleeper.seconds(10, "Wait for 10 seconds to refresh and load the web page.");
        return this;
    }
    
    /**
     * Sets focus to the page
     * 
     * @return
     */
    public APageObject getFocus() {
        logger.info("Obtaining focus for " + this.getClass().getSimpleName());
        WebDriver wd = Connection.getWebDriver();
        wd.switchTo().window(windowHandle);
        return this;
    }
    
    /**
     * Closes the current page and return to the caller page if known
     */
    public void close() {
        logger.info("Closing " + this.getClass().getSimpleName());
        WebDriver wd = Connection.getWebDriver();
        wd.close();
        if (callersWindowHandle != null) {
            wd.switchTo().window(callersWindowHandle);
        }
    }
    
    /**
     * Re set browser size
     */
    public void setBrowserSize(int width, int height) {
        logger.info("Re set browser size");
        WebDriver wd = Connection.getWebDriver();
        wd.manage().window().setSize(new Dimension(width, height));
    }

}
