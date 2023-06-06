package pomTest;

import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.annotations.DataProvider;


public class DashboardTest extends BaseTest {
	
	@DataProvider(name="paymentData")
	public String [][] getData() throws IOException
	{
		//get the data from excel
		String path=".\\Fetch Data.xlsx";
		XLUtility xlutil=new XLUtility(path);
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1);	
				
		String paymentData[][]=new String[totalrows][totalcols];
			
		
		for(int i=1;i<=totalrows;i++) //1
		{
			for(int j=0;j<totalcols;j++) //0
			{
				paymentData[i-1][j]=xlutil.getCellData("Sheet1", i, j);
				//System.out.println(paymentData[i-1][j]);
			}
				
		}
		
		return paymentData;
	}

	@Test(priority = 2)
	public void search() throws InterruptedException {
		
		db.searchMenu();
	}
    
	
	@Test(priority = 3, dataProvider = "paymentData")
	public void takePayment(String acct, String TotalAmtRec, String PayAmt) throws InterruptedException, IOException {
				

		db.payment(acct, TotalAmtRec, PayAmt);
		
	}
}
