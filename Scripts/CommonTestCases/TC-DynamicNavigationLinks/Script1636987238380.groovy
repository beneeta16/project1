import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory



//Verify dynamic navigation link and its features


'Verify different DN links are present and each DN link contains different options'
WebDriver driver = DriverFactory.getWebDriver()

facetToBeChecked = driver.findElement(By.xpath(FacetToCheck))
boolean isFacetVisible = facetToBeChecked.isDisplayed()
System.out.println(isFacetVisible)
boolean isMatchTrue = WebUI.verifyMatch(TitleOfTheFacet, facetToBeChecked.getAttribute("data-title"), false)
System.out.println(isMatchTrue)

boolean isLinksVisible = driver.findElement(By.xpath(LinksOfTheFacet)).isDisplayed()
System.out.println(isLinksVisible)

if(TitleOfTheFacet =='All Products') {
titleText1 = driver.findElement(By.xpath("//div[@data-title = 'All Products']/ul/li[1]")).getAttribute("data-value")
titleText2 = driver.findElement(By.xpath("//div[@data-title = 'All Products']/ul/li[2]")).getAttribute("data-value")
driver.findElement(By.xpath("//div[@data-title = 'All Products']/ul/li[1]")).click()
Thread.sleep(2000)

driver.findElement(By.xpath("//div[@data-title = 'All Products']/ul/li[2]")).click()
Thread.sleep(3000)

boolean isOption1Present= WebUI.verifyElementText(findTestObject('Object Repository/Search_Result_Page_OR/Platform_Value_location1'), titleText1)
System.out.println(isOption1Present)
boolean isOption2Present = WebUI.verifyElementText(findTestObject('Object Repository/Search_Result_Page_OR/Platform_Value_location2'), titleText2)
System.out.println(isOption2Present)
driver.findElement(By.xpath("//div[@data-title = 'All Products']/ul/li[1]")).click()
Thread.sleep(2000)

driver.findElement(By.xpath("//div[@data-title = 'All Products']/ul/li[2]")).click()
Thread.sleep(4000)

WebUI.verifyElementNotPresent(findTestObject('Object Repository/Search_Result_Page_OR/Coveo_breadcrumb_item_area'), 5)
}


actualTitle = driver.findElement(By.xpath(LinkToCheck)).getAttribute("data-value")
System.out.println(actualTitle)





'Click on any DN link and verify relevant search results are being shown'
driver.findElement(By.xpath(LinkToCheck)).click()
WebUI.verifyElementVisible(findTestObject('Object Repository/Search_Result_Page_OR/Coveo_breadcrumb_item_area'))
WebUI.verifyElementText(findTestObject('Object Repository/Search_Result_Page_OR/Coveo_breadcrumb_item_area'), TitleOfTheFacet+":")
WebUI.verifyElementVisible(findTestObject('Object Repository/Search_Result_Page_OR/Selected_Platform_value_location'))
boolean istitleTrue = WebUI.verifyElementText(findTestObject('Object Repository/Search_Result_Page_OR/Selected_Platform_value_location'), actualTitle)
System.out.println(istitleTrue)


List<WebElement> highlightedTextinlink = driver.findElements(By.xpath("//div[@class = 'coveo-result-list-container coveo-list-layout-container']//a"))
for (int i = 0; i < highlightedTextinlink.size(); i++) {
	
	boolean isitem1PresentinLink = highlightedTextinlink.get(i).getText().contains(expectedsearchedText1)
	boolean isitem2PresentinLink = highlightedTextinlink.get(i).getText().contains(expectedsearchedText2)
	
	
	System.out.println(isitem1PresentinLink)
	System.out.println(isitem2PresentinLink)
	
	
	if(isitem1PresentinLink == true)
	{
		break;
	}
	else if(isitem2PresentinLink == true) {
		break;
	}
	
}
WebUI.click(findTestObject('Object Repository/Search_Result_Page_OR/Clear_option'))






