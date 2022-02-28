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



WebDriver driver = DriverFactory.getWebDriver()

optionToBeFound = driver.findElement(By.xpath(OptionToFind))

boolean isOptionVisible = optionToBeFound.isDisplayed()

System.out.println(isOptionVisible)


'Click on Option to find'
optionToBeFound.click()

'Wait for page to load'
WebUI.waitForElementVisible(findTestObject('Search_Result_Page_OR/Search_Area'), 5)

'To verify the window title'
String actualTitle = WebUI.getWindowTitle()

WebUI.verifyMatch(actualTitle, titleToVerify, true)

'To check "titleToVerify"  is present in the search custom header location'
WebUI.verifyElementText(findTestObject('Object Repository/Search_Result_Page_OR/SearchCustomHeader_location'), titleToVerify)

'Click on "Show all attribute" button'
WebUI.click(findTestObject('Search_Result_Page_OR/ShowAllAttribute_button'))

'To verify whether the search results have "Document type" as "DocumentTypeToVerify" or not'
List<WebElement> thirdRow = driver.findElements(By.xpath('//div[@class = \'coveo-result-row coveo-custom-result-attributes\' ]//div[2]//tr[3]/td[1]'))

List<WebElement> documentColumn = driver.findElements(By.xpath('//div[@class = \'coveo-result-row coveo-custom-result-attributes\' ]//div[2]//tr[3]/td[2]'))

int totalcms = thirdRow.size()

System.out.println(totalcms)

for (int i = 0; i < totalcms; i++) {
	String expectedWord = 'Document type:'

	String actualWord = thirdRow.get(i).getText().toString()

	boolean isdocumentPresent = WebUI.verifyMatch(actualWord, expectedWord, false)

	if (isdocumentPresent == true) {
		String ithdocumentColumn = documentColumn.get(i).getText()

		boolean isitTrue = ithdocumentColumn.contains(DocumentTypeToVerify)

		System.out.println(isitTrue)

		if (isitTrue == true) {
			System.out.println(DocumentTypeToVerify + ' Word is present')
		} else {
			System.out.println(DocumentTypeToVerify + ' Word is not present')
		}
	}
}

Thread.sleep(15000)

'To close attribute table'
WebUI.click(findTestObject('Search_Result_Page_OR/ShowAllAttribute_button'))

WebUI.back()