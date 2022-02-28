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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import java.text.DateFormat as DateFormat
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import java.util.Calendar as Calendar

'Login to the Website'
WebUI.callTestCase(findTestCase('TC-01-VerifyLogin'), [:], FailureHandling.STOP_ON_FAILURE)

'Maximize the window'
WebUI.maximizeWindow()

'Verify Resources option is present and visible'
WebUI.verifyElementVisible(findTestObject('Resources_Dropdown_OR/Resources_option_location'))

WebUI.verifyElementText(findTestObject('Resources_Dropdown_OR/Resources_option_location'), 'Resources')

'Mouse hover on Resources'
WebUI.mouseOver(findTestObject('Resources_Dropdown_OR/Resources_option_location'))

'Verify option to check is present and visible'
WebDriver driver = DriverFactory.getWebDriver()

optionToBeChecked = driver.findElement(By.xpath(OptionToCheck))

boolean isOptionVisible = optionToBeChecked.isDisplayed()

System.out.println(isOptionVisible)

boolean isthewordPresent = optionToBeChecked.getText().contains(TitleToCheck)

System.out.println(isthewordPresent)

'Click on Option to check'
optionToBeChecked.click()
Thread.sleep(5000)





'To verify the window title'
String actualTitle = WebUI.getWindowTitle()

WebUI.verifyMatch(actualTitle, TitleToCheck, true)

'To check "TitleToCheck"  is present in the search custom header location'
WebUI.verifyElementText(findTestObject('Object Repository/Search_Result_Page_OR/SearchCustomHeader_location'), TitleToCheck)

'Click on "Show all attribute" button'
WebUI.click(findTestObject('Search_Result_Page_OR/ShowAllAttribute_button'))

'To verify whether the search results have "Document type" as "DocumentTypeToCheck" or not'
List<WebElement> thirdRow = driver.findElements(By.xpath('//div[@class = \'coveo-result-row coveo-custom-result-attributes\' ]//div[2]//tr[3]/td[1]'))

List<WebElement> documentColumn = driver.findElements(By.xpath('//div[@class = \'coveo-result-row coveo-custom-result-attributes\' ]//div[2]//tr[3]/td[2]'))

int totalcms = thirdRow.size()

System.out.println(totalcms)

for (int i = 0; i < totalcms; i++) {
	String expectedWord = 'Document type:'

	String actualWord = thirdRow.get(i).getText().toString()

	boolean isdocumentPresent = WebUI.verifyMatch(actualWord, expectedWord, false)

	if (isdocumentPresent == true) {
		String ithdocumentColumn = documentColumn.get(i).getText().toString()

		boolean isitTrue = ithdocumentColumn.contains(DocumentTypeToCheck)

		System.out.println(isitTrue)

		if (isitTrue == true) {
			System.out.println(DocumentTypeToCheck + ' Word is present')
		} else {
			System.out.println(DocumentTypeToCheck + ' Word is not present')
		}
	}
}

Thread.sleep(15000)

'To close attribute table'
WebUI.click(findTestObject('Search_Result_Page_OR/ShowAllAttribute_button'))






'Verify search results are sorted based on modified date'
WebUI.verifyElementVisible(findTestObject('Search_Result_Page_OR/Modified_Date_location'))

WebUI.verifyElementAttributeValue(findTestObject('Search_Result_Page_OR/Modified_Date_location'), 'data-field', '@sflastmodifieddate', 
    10)





'Verify default sorting is based on date'
WebUI.verifyElementVisible(findTestObject('Object Repository/Search_Result_Page_OR/Relevance_button_location'))

WebUI.verifyElementVisible(findTestObject('Object Repository/Search_Result_Page_OR/Date_button_location'))

WebUI.verifyElementAttributeValue(findTestObject('Search_Result_Page_OR/Date_button_location'), 'aria-checked', 'true', 
    10)






'Verify dates are by default sorted in descending order'
List<WebElement> dates = driver.findElements(By.xpath('//*[@id=\'coveo-result-list1\']/div[2]/div/div/div/div/div[1]/div[2]/span/span'))

for (i = 0; i < (dates.size() - 1); i++) {
    String sDate1 = dates.get(i).getText().toString()

    SimpleDateFormat formatter1 = new SimpleDateFormat('(dd MMM yyyy)')

    Date date1 = formatter1.parse(sDate1)

    int j = i + 1

    String sDate2 = dates.get(j).getText().toString()

    Date date2 = formatter1.parse(sDate2)

    if (date1.compareTo(date2) >= 0) {
        System.out.println('date 1 is greater than or equal to date 2')
    } else {
        System.out.println('date 1 is less than date 2')
    }
}

Thread.sleep(15000)





'Click on date arrow up and verify dates are now sorted in ascending order'
WebUI.click(findTestObject('Object Repository/Search_Result_Page_OR/Date_button_location'))

List<WebElement> dates1 = driver.findElements(By.xpath('//*[@id=\'coveo-result-list1\']/div[2]/div/div/div/div/div[1]/div[2]/span/span'))

for (i = 0; i < (dates1.size() - 1); i++) {
    String aDate1 = dates1.get(i).getText().toString()

    SimpleDateFormat formatter2 = new SimpleDateFormat('(dd MMM yyyy)')

    Date adate1 = formatter2.parse(aDate1)

    int j = i + 1

    String aDate2 = dates1.get(j).getText().toString()

    Date adate2 = formatter2.parse(aDate2)

    if (adate1.compareTo(adate2) <= 0) {
        System.out.println('date 1 is less than or equal to date 2')
    } else {
        System.out.println('date 1 is greater than date 2')
    }
}

Thread.sleep(15000)

WebUI.click(findTestObject('Object Repository/Search_Result_Page_OR/Date_button_location'))






'Verify searchbox is present and search the word Cadence'
WebUI.verifyElementVisible(findTestObject('Search_Result_Page_OR/Searchbox_in_search_result_page'))

WebUI.setText(findTestObject('Search_Result_Page_OR/Searchbox_in_search_result_page'), 'cadence')

WebUI.sendKeys(findTestObject('Search_Result_Page_OR/Searchbox_in_search_result_page'), Keys.chord(Keys.ENTER))

'Wait for page to load'
WebUI.waitForElementVisible(findTestObject('Search_Result_Page_OR/Search_Area'), 5)

'Click on show all attribute button and verify the text'
WebUI.click(findTestObject('Search_Result_Page_OR/ShowAllAttribute_button'))

WebUI.verifyElementText(findTestObject('Search_Result_Page_OR/ShowAllAttribute_button'), 'Hide all attributes')

WebUI.verifyElementVisible(findTestObject('Search_Result_Page_OR/Attribute_Table_location'), FailureHandling.STOP_ON_FAILURE)

'Verify whether the search results have product, Article number and Document type in the attribute table or not'
List<WebElement> firstRow = driver.findElements(By.xpath('//div[@class = \'coveo-result-row coveo-custom-result-attributes\']//div[2]//tr[1]/th'))

List<WebElement> secondRow = driver.findElements(By.xpath('//div[@class = \'coveo-result-row coveo-custom-result-attributes\' ]//div[2]//tr[2]/td[1]'))

List<WebElement> thirdRow1 = driver.findElements(By.xpath('//div[@class = \'coveo-result-row coveo-custom-result-attributes\' ]//div[2]//tr[3]/td[1]'))

List<WebElement> documentColumn1 = driver.findElements(By.xpath('//div[@class = \'coveo-result-row coveo-custom-result-attributes\' ]//div[2]//tr[3]/td[2]'))

int totalcms1 = firstRow.size()

System.out.println(totalcms1)

for (int i = 0; i < totalcms1; i++) {
    expectedWord1 = 'Product(s):'

    actualWord1 = firstRow.get(i).getText().toString()

    isdocumentPresent1 = WebUI.verifyMatch(actualWord1, expectedWord1, false)
}

int totalcms2 = secondRow.size()

System.out.println(totalcms2)

for (int i = 0; i < totalcms2; i++) {
    expectedWord2 = 'Article Number:'

    actualWord2 = secondRow.get(i).getText().toString()

    isdocumentPresent2 = WebUI.verifyMatch(actualWord2, expectedWord2, false)
}

int totalcms3 = thirdRow1.size()

System.out.println(totalcms3)

for (int i = 0; i < totalcms3; i++) {
    expectedWord3 = 'Document type:'

    actualWord3 = thirdRow1.get(i).getText().toString()

    boolean isdocumentPresent3 = WebUI.verifyMatch(actualWord3, expectedWord3, false)

    if (isdocumentPresent3 == true) {
        String ithdocumentColumn1 = documentColumn1.get(i).getText().toString()
		System.out.println(ithdocumentColumn1)
		
        boolean configurationWord1 = ithdocumentColumn1.contains(DocumentTypeToCheck)

        if (configurationWord1 == true) {
            System.out.println(DocumentTypeToCheck + ' word is present')
        } 
		else {
            System.out.println(DocumentTypeToCheck + ' word is not present')
        }
    }
}

Thread.sleep(20000)

'Close attribute table and verify it is not visible'
WebUI.click(findTestObject('Search_Result_Page_OR/ShowAllAttribute_button'))

WebUI.verifyElementText(findTestObject('Search_Result_Page_OR/ShowAllAttribute_button'), 'Show all attributes')

WebUI.verifyElementNotVisible(findTestObject('Search_Result_Page_OR/Attribute_Table_location'), FailureHandling.STOP_ON_FAILURE)








WebUI.callTestCase(findTestCase('CommonTestCases/TC-i-button'), [:], FailureHandling.STOP_ON_FAILURE)

'Verify whether the 1st search result has the attributes (products, article number and document type) or not'
boolean product = driver.findElement(By.xpath('//div[@class = \'coveo-result-row coveo-custom-result-attributes\']//div[2]//tr[1]/th')).isDisplayed()

boolean articleNumber = driver.findElement(By.xpath('//div[@class = \'coveo-result-row coveo-custom-result-attributes\' ]//div[2]//tr[2]/td[1]')).isDisplayed()

boolean documentType = driver.findElement(By.xpath('//div[@class = \'coveo-result-row coveo-custom-result-attributes\' ]//div[2]//tr[3]/td[1]')).isDisplayed()

System.out.println(product)

System.out.println(articleNumber)

System.out.println(documentType)

Thread.sleep(6000)
WebUI.click(findTestObject('i_button_OR/1st_i_button_location'))






'Click on the first search result and verify it is opening on a new tab'
String linkTitle = driver.findElement(By.xpath("//div[@class = 'coveo-list-layout CoveoResult'][2]//div[2]/a")).getText().toString()
System.out.println(linkTitle)

driver.findElement(By.xpath("//div[@class = 'coveo-list-layout CoveoResult'][2]//div[2]/a")).click()


WebUI.switchToWindowIndex(1)

String windowTitle = WebUI.getWindowTitle()
System.out.println(windowTitle)

System.out.println(windowTitle.equals(linkTitle))

'Verify whether the first search result name is present in the pagehead location'
actualpageheadText = WebUI.getText(findTestObject('Object Repository/Search_Result_Page_OR/Pagehead_location'))

System.out.println(actualpageheadText.equals(linkTitle))

'Click on the i button and verify whether the document type is Application Notes or not'
WebUI.click(findTestObject('Object Repository/i_button_OR/i_button_on_cadence_search_result_page'))

String actualWord4 = driver.findElement(By.xpath('//table[@class="drop-table"]/tbody/tr[3]/td[1]')).getText()

expectedWord4 = 'Document Type:'

boolean isdocumentPresent4 = WebUI.verifyMatch(actualWord4, expectedWord4, true)

if (isdocumentPresent4 == true) {
    String actualWord5 = driver.findElement(By.xpath("//table[@class='drop-table']/tbody/tr[3]/td[2]")).getText()


    boolean isdocumentPresent5 = actualWord5.contains(DocumentTypeToCheck)

    if (isdocumentPresent5 == true) {
        System.out.println('Document type is '+ DocumentTypeToCheck)
    }
	else  {
		System.out.println('Document type is not '+ DocumentTypeToCheck)
		
	}
}

WebUI.closeWindowIndex(1)
