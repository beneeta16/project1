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
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

//To verify search by clicking on magnifying glass


'Login to the Website'
WebUI.callTestCase(findTestCase('TC-01-VerifyLogin'), [:], FailureHandling.STOP_ON_FAILURE)

'Search product manuals in the searchbar'
WebUI.setText(findTestObject('Searchbar_OR/Searchbar_position'), 'product manuals')

'Click on the magnifying glass'
WebUI.click(findTestObject('Searchbar_OR/MagnifyingGlass_Position'))

'Wait for page to load'
WebUI.waitForElementVisible(findTestObject('Search_Result_Page_OR/Search_Area'), 5)

'Verify window title'
String expectedTitle = 'Search Results'

String actualTitle = WebUI.getWindowTitle()

WebUI.verifyMatch(actualTitle, expectedTitle, true)

'Press the back button of the browser'
WebUI.back()







//To verify search by pressing Enter


'Search product manuals in the searchbar'
WebUI.setText(findTestObject('Searchbar_OR/Searchbar_position'), 'product manuals')

'Press Enter'
WebUI.sendKeys(findTestObject('Searchbar_OR/Searchbar_position'), Keys.chord(Keys.ENTER))

'Wait for page to load'
WebUI.waitForElementVisible(findTestObject('Search_Result_Page_OR/Search_Area'), 5)

'Verify window title'
String expectedTitle1 = 'Search Results'
String actualTitle1 = WebUI.getWindowTitle()
WebUI.verifyMatch(actualTitle1, expectedTitle1, true)

'Verify searched text is present in the summary section'
WebUI.verifyElementText(findTestObject('Search_Result_Page_OR/Searched_Text_in_Summary_section'), 'product manuals')

'Verify search results are relevant to the searched text'
WebDriver driver = DriverFactory.getWebDriver()

List<WebElement> highlightedTexts = driver.findElements(By.xpath("//div[@class = 'coveo-result-row coveo-custom-result-excerpt']//span[@class = 'coveo-highlight']"))

for (int i = 0; i < highlightedTexts.size(); i++) {
	expectedsearchedText1 = "product"
	expectedsearchedText2 = "manual"
	boolean isitem1Present = highlightedTexts.get(i).getText().contains(expectedsearchedText1)
	boolean isitem2Present = highlightedTexts.get(i).getText().contains(expectedsearchedText2)
	System.out.println(isitem1Present)
	System.out.println(isitem2Present)
	
	if(isitem1Present == true)
	{
		break;
	}
	else if(isitem2Present == true) {
		break;
	}
}







//Verification of "Results per page section


'Verify Results per page section is present and visible'
WebUI.verifyElementVisible(findTestObject('Search_Result_Page_OR/Results_Per_Page_location'), FailureHandling.STOP_ON_FAILURE)

'Verify four options will be present - 10.25,50,100'
resultNumber = ['10', '25', '50', '100']
List<WebElement> actualNumber = driver.findElements(By.xpath("//ul[@class ='coveo-results-per-page-list']/li"))

for (int i = 0; i <= 3; i++) {
	boolean isnumberSame = WebUI.verifyMatch(actualNumber.get(i).getText(), resultNumber[i], false)
	System.out.println(isnumberSame);
}

'Verify default results per page is 25'
WebUI.verifyElementAttributeValue(findTestObject('Search_Result_Page_OR/Result_Number/Default_number_location'), 'aria-checked',
	'true', 10)

'Click on 10 results per page'
actualNumber.get(0).click()

'Wait for page to load'
WebUI.waitForElementVisible(findTestObject('Search_Result_Page_OR/Search_Area'), 5)

'Verify 10 results will be shown per page'
WebUI.verifyElementText(findTestObject('Search_Result_Page_OR/Result_summary_section'), '10')

int expectedresultNumber = 10

List numberofLink = WebUI.findWebElements(findTestObject('Object Repository/Search_Result_Page_OR/LinkLocation_on_Search_Result_Page'),
	30)

int actualresultNumber = numberofLink.size()

WebUI.verifyEqual(actualresultNumber, expectedresultNumber)







//Verification of Pagination location

'Verify if total number of results is greater than results per page then more pages will be present'
totalResult = WebUI.getText(findTestObject('Object Repository/Search_Result_Page_OR/Total_number_of_results'))

resultperPage = WebUI.getText(findTestObject('Object Repository/Search_Result_Page_OR/Result_summary_section'))


if (totalResult > resultperPage) {
	WebUI.verifyElementVisible(findTestObject('Object Repository/Search_Result_Page_OR/Pagination_location'))
	
} else {
	println('Only One page is there')
}

'Verify 1st page will be the default selected page'
WebUI.verifyElementAttributeValue(findTestObject('Search_Result_Page_OR/Default_Selected_Page'), 'aria-pressed', 'true',
	10)

'Click on page 2'
WebUI.click(findTestObject('Search_Result_Page_OR/Page_2_location'))

'Wait for page to load'
WebUI.waitForElementVisible(findTestObject('Search_Result_Page_OR/Search_Area'), 5)

'Verify if results per page is 10 then second page should start from 11th result'
defaultpageNumber = WebUI.findWebElement(findTestObject('Object Repository/Search_Result_Page_OR/Selected_result_number')).getText()

int defaultNumber = defaultpageNumber.toInteger()

expected_search_result_Number = (defaultNumber+1).toString()

actual_search_result_Number = WebUI.getText(findTestObject('Object Repository/Search_Result_Page_OR/Starting_search_result_number'))

WebUI.verifyMatch(actual_search_result_Number, expected_search_result_Number, false)





//Verification of Previous and next button


'Verify previous button is present and visible'
WebUI.click(findTestObject('Object Repository/Search_Result_Page_OR/Page_1_location'))
WebUI.verifyElementNotPresent(findTestObject('Search_Result_Page_OR/Previous_button_location'), 5)
WebUI.click(findTestObject('Search_Result_Page_OR/Page_2_location'))
WebUI.verifyElementVisible(findTestObject('Search_Result_Page_OR/Previous_button_location'), FailureHandling.STOP_ON_FAILURE)

'Verify Next button is present and visible'
WebUI.verifyElementVisible(findTestObject('Search_Result_Page_OR/Next_button_location'), FailureHandling.STOP_ON_FAILURE)

'Verify clicking on Previous button takes the user back to the previous stage'
WebUI.verifyElementAttributeValue(findTestObject('Search_Result_Page_OR/Page_2_location'), 'aria-pressed', 'true', 10)

currentpageNumber = WebUI.getText(findTestObject('Object Repository/Search_Result_Page_OR/Page_2_location'))

int convert_current_page_number = currentpageNumber.toInteger() - 1

expected_current_page_number = convert_current_page_number.toString()

WebUI.click(findTestObject('Search_Result_Page_OR/Previous_button_location'))

actual_current_page_number = WebUI.findWebElement(findTestObject('Object Repository/Search_Result_Page_OR/Active_page_number')).getText()

WebUI.verifyMatch(actual_current_page_number, expected_current_page_number, false)


'Verify clicking on the Next button directs the user to the Next stage'
WebUI.verifyElementAttributeValue(findTestObject('Search_Result_Page_OR/Page_1_location'), 'aria-pressed', 'true', 10)

currentpageNumber1 = WebUI.getText(findTestObject('Object Repository/Search_Result_Page_OR/Page_1_location'))

int convert_current_page_number1 = currentpageNumber1.toInteger() + 1

expected_current_page_number1 = convert_current_page_number1.toString()

WebUI.click(findTestObject('Search_Result_Page_OR/Next_button_location'))

actual_current_page_number1 = WebUI.findWebElement(findTestObject('Object Repository/Search_Result_Page_OR/Active_page_number')).getText()

boolean isMatchTrue = WebUI.verifyMatch(actual_current_page_number1, expected_current_page_number1, false)

System.out.println(isMatchTrue)






//Verify "Show all attribute" button and its features

'Verify show all attribute button is present and visible'
WebUI.verifyElementVisible(findTestObject('Search_Result_Page_OR/ShowAllAttribute_button'), FailureHandling.STOP_ON_FAILURE)

'Click on show all attribute button and verify text'
WebUI.click(findTestObject('Search_Result_Page_OR/ShowAllAttribute_button'))

WebUI.verifyElementText(findTestObject('Search_Result_Page_OR/ShowAllAttribute_button'), 'Hide all attributes')

'Verify attribute tables are present and visible'
WebUI.verifyElementVisible(findTestObject('Search_Result_Page_OR/Attribute_Table_location'), FailureHandling.STOP_ON_FAILURE)

'Verify Product attribute is present in the attribute table'
WebUI.verifyElementAttributeValue(findTestObject('Search_Result_Page_OR/Product_attribute_location'), 'data-caption', 'Product(s):',
	20)

'Close show all attribute button and verify text'
WebUI.click(findTestObject('Search_Result_Page_OR/ShowAllAttribute_button'))

WebUI.verifyElementText(findTestObject('Search_Result_Page_OR/ShowAllAttribute_button'), 'Show all attributes')

'Verify attribute tables are not visible'
WebUI.verifyElementNotVisible(findTestObject('Search_Result_Page_OR/Attribute_Table_location'), FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('CommonTestCases/TC-i-button'), [:], FailureHandling.STOP_ON_FAILURE)


'To get the title of the 1st URL'
title = WebUI.getAttribute(findTestObject('Object Repository/Search_Result_Page_OR/1st_URL_location'), 'title')

'To get the 1st URL'
url = WebUI.getAttribute(findTestObject('Object Repository/Search_Result_Page_OR/1st_URL_location'), 'href')

'Click on the "Copy Title/URL" option present on the 1st attribute table and validate the text'

WebUI.verifyElementText(findTestObject('Object Repository/Search_Result_Page_OR/1st_Copy_URL_link_location'), "Copy Title/URL")

WebUI.click(findTestObject('Object Repository/Search_Result_Page_OR/1st_Copy_URL_link_location'))

WebUI.verifyElementText(findTestObject('Object Repository/Search_Result_Page_OR/1st_Copy_URL_link_location'), "Copied!")


'Get the data from the clipboard and print'

String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);

System.out.println(data)


'To verify whether the copied text contains "Title" and "URL" of the 1st link or not'

boolean istitlePresent = data.contains(title)

System.out.println(istitlePresent)

boolean isurlPresent = data.contains(url)

System.out.println(istitlePresent)





//Verify clicking on any search result opens the page on a new tab

'Verify opening of new tab on click'
linkTitle = WebUI.getText(findTestObject('Object Repository/Search_Result_Page_OR/Search_Area'))

WebUI.click(findTestObject('Search_Result_Page_OR/Search_Area'))

WebUI.switchToWindowIndex(1)

String windowTitle = WebUI.getWindowTitle()

boolean isTitleSame = linkTitle.contains(windowTitle)
//WebUI.verifyMatch(windowTitle, linkTitle, true)

System.out.println(isTitleSame)

WebUI.closeBrowser()
