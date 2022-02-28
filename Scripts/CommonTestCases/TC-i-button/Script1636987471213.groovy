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

int currentResultPerPage = WebUI.findWebElement(findTestObject('Object Repository/Search_Result_Page_OR/Selected_result_number')).getText().toInteger()
System.out.println(currentResultPerPage)

'Click on the i button of 1st link'
WebUI.click(findTestObject('i_button_OR/1st_i_button_location'))

'To verify whether the 1st attribute table is visible or not'
WebUI.verifyElementVisible(findTestObject('i_button_OR/1st_attribute_table_location'))

'To verify that the remaining attribute tables are not visible'
WebDriver driver = DriverFactory.getWebDriver()

boolean expectedValue = false

for (int i = 1; i <= (currentResultPerPage-1); i++) {
	boolean actualValue = driver.findElements(By.xpath('//div[@class = \'coveo-result-row coveo-custom-result-attributes\' ]//div[2]')).get(i).isDisplayed()

	System.out.println(actualValue)

	int b = Boolean.compare(expectedValue, actualValue)

	if (b == 0) {
		System.out.println('Both values are equal')
	}
}