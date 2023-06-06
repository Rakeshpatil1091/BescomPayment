package pomPage;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;


public class DashboardPage {
	
WebDriver driver;
Actions act;
WebDriverWait wait;
	
	@FindBy(xpath="//input[@id='inputSearch|input']") 
	private WebElement searchMenu;
	
	@FindBy(xpath="//frame[@title='Main Frame']") 
	private WebElement MainFrame;
	
	@FindBy(xpath="//iframe[@id='tabPage']") 
	private WebElement TabPageFrame;
	
	@FindBy(xpath="//iframe[@id='zoneMapFrame_1']") 
	private WebElement ZoneMapFrame;
	
	@FindBy(xpath="//input[@id='totalAmount']") 
	private WebElement TotalAmountReceived;
	
	@FindBy(xpath="//input[@id='acctId1_0']") 
	private WebElement PayorAccountID;
	
	@FindBy(xpath="//input[@id='paymentAmount1_0']") 
	private WebElement PaymentAmount;
	
	@FindBy(xpath="//input[@id='save']") 
	private WebElement SaveButton;
	
	@FindBy(xpath="//input[@id='IM_REFRESH']") 
	private WebElement Refresh;
	
	
	
    public DashboardPage(WebDriver driver) {
    	
    	this.driver = driver;
    	PageFactory.initElements(driver, this);
    }
    
    public void searchMenu() throws InterruptedException {
    	
    	driver.switchTo().frame(MainFrame);
    	
    	searchMenu.sendKeys("Unified Payment Screen");
    	
    	Thread.sleep(1500);
    	
         act = new Actions(driver);
    	
    	act.sendKeys(Keys.ENTER).perform();
  
    }
    

    public void payment( String acct, String TotalAmtRec, String PayAmt) throws InterruptedException, IOException {
    	
    	driver.switchTo().frame(TabPageFrame);
    	driver.switchTo().frame(ZoneMapFrame);
   
        TotalAmountReceived.clear();
        Thread.sleep(1500);
    	TotalAmountReceived.sendKeys(TotalAmtRec);
        
         driver.switchTo().parentFrame();

         JavascriptExecutor js = (JavascriptExecutor)driver;
			
 		js.executeScript("window.scrollBy(0, 400)");
        
 		driver.switchTo().frame(ZoneMapFrame);
 		
 		act = new Actions(driver);

 		PayorAccountID.clear();
 		Thread.sleep(1500);
 		PayorAccountID.sendKeys(acct);
 		act.sendKeys(Keys.TAB).perform();
 		
 		Thread.sleep(2000);

 		PaymentAmount.clear();
 		Thread.sleep(1500);

 		PaymentAmount.sendKeys(PayAmt);
 		
 		SaveButton.click();
 		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
 		wait.until(ExpectedConditions.alertIsPresent());
 		
 		String text1=driver.switchTo().alert().getText();
 		System.out.println(text1);
 		driver.switchTo().alert().accept();

 		wait.until(ExpectedConditions.alertIsPresent());
 		
 		String text2=driver.switchTo().alert().getText();
 		//System.out.println(text2);
 		Reporter.log(text2, true);
 		driver.switchTo().alert().accept();
 		
 		Thread.sleep(1000);
 		
 		driver.switchTo().defaultContent();
 		driver.switchTo().frame(MainFrame);
 
 		Refresh.click();
 		Thread.sleep(2000);
    }
}
