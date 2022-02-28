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
import java.util.ArrayList as ArrayList

'login to the website'
WebUI.callTestCase(findTestCase('TC-01-VerifyLogin'), [:], FailureHandling.STOP_ON_FAILURE)


//Verification of Dropdown menu

'Verify default selected option is All content'
WebUI.verifyElementText(findTestObject('DropDown_OR/Dropdown_Box_Position'), 'All Content')

'Click on Dropdown'
WebUI.click(findTestObject('DropDown_OR/Dropdown_Box_Position'))

'Verify dropdown has the 7 options'
ArrayList<String> listItem = new ArrayList<String>()

listItem.add('All')

listItem.add('All Content')

listItem.add('Product Manuals')

listItem.add('Articles and AppNotes')

listItem.add('Training Courses')

listItem.add('Community')

listItem.add('Cases')

WebDriver driver = DriverFactory.getWebDriver()

List<WebElement> dropdownItems = driver.findElements(By.xpath('//*[@id=\'standaloneSearchDropdown\']/ul/li'))

for (int i = 0; i < dropdownItems.size(); i++) {
    expecteditemText = listItem.get(i)

    actualitemText = dropdownItems.get(i).getText()

    boolean isitemPresent = WebUI.verifyMatch(actualitemText, expecteditemText, false)

    System.out.println(isitemPresent)

    dropdownItems.get(i).click()

    boolean isoptionSelected = WebUI.verifyElementText(findTestObject('DropDown_OR/Dropdown_Box_Position'), expecteditemText)

    System.out.println(isoptionSelected)

    WebUI.click(findTestObject('DropDown_OR/Dropdown_Box_Position'))
}

WebUI.click(findTestObject('DropDown_OR/Dropdown_Box_Position'))



//Verification of Searchbar

'Verify searchbar is present'
WebUI.verifyElementVisible(findTestObject('Object Repository/Searchbar_OR/Searchbar_position'))

'Verify the placeholder'
boolean isplaceholderPresent = WebUI.verifyElementAttributeValue(findTestObject('Searchbar_OR/Searchbar_position'), 'placeholder', 
    'Start your search here...', 5)

System.out.println(isplaceholderPresent)

'Verify the tooltip'
String expectedTooltip = 'Insert a query. Press enter to send'

String actualTooltip = WebUI.getAttribute(findTestObject('Searchbar_OR/Searchbar_position'), 'title')

boolean istooltipTrue = WebUI.verifyMatch(actualTooltip, expectedTooltip, true)

System.out.println(istooltipTrue)

'Verify the magnifying glass'
WebUI.verifyElementVisible(findTestObject('Object Repository/Searchbar_OR/MagnifyingGlass_Position'))


//Verification of Quick Links section


// Verification of "Installation and Licensing" option
'Scroll to the Quick Link section'
WebUI.scrollToElement(findTestObject('Quick_Link_Section_OR/Quick_link_Section_location'), 5)

'To verify whether "Installation and Licensing" option is present and visible or not'
WebUI.verifyElementVisible(findTestObject('Quick_Link_Section_OR/Installation_and_licensing_location'))

'Click on "Installation and Licensing" option'
WebUI.click(findTestObject('Quick_Link_Section_OR/Installation_and_licensing_location'))

'Wait for page to load'
WebUI.waitForElementVisible(findTestObject('Search_Result_Page_OR/Search_Area'), 5)

'To verify whether the window title contains the word "Installation and Licensing" or not'
String expectedTitle = 'Installation, Licensing and Configuration'

String actualTitle = WebUI.getWindowTitle()

WebUI.verifyMatch(actualTitle, expectedTitle, true)

'To verify "Installation and Licensing" word is present in the search custom header location'
WebUI.verifyElementText(findTestObject('Object Repository/Search_Result_Page_OR/SearchCustomHeader_location'), 'Installation, Licensing and Configuration')

'Click on "Show all attribute" button'
WebUI.click(findTestObject('Search_Result_Page_OR/ShowAllAttribute_button'))

'To verify whether search results have "Product type" as "Installation and Licensing" or "Document type" as "Installation, Licensing and Configuration" or both.'
List<WebElement> firstRow = driver.findElements(By.xpath('//div[@class = \'coveo-result-row coveo-custom-result-attributes\' ]//div[2]//tr/th'))

List<WebElement> productrowColumn = driver.findElements(By.xpath('//div[@class = \'coveo-result-row coveo-custom-result-attributes\' ]//div[2]//tr[1]/td[1]'))

List<WebElement> thirdRow = driver.findElements(By.xpath('//div[@class = \'coveo-result-row coveo-custom-result-attributes\' ]//div[2]//tr[3]/td[1]'))

List<WebElement> documentColumn = driver.findElements(By.xpath('//div[@class = \'coveo-result-row coveo-custom-result-attributes\' ]//div[2]//tr[3]/td[2]'))

int totalcms = productrowColumn.size()

System.out.println(totalcms)

for (int i = 0; i < totalcms; i++) {
    expectedText = 'Product(s):'

    actualText = firstRow.get(i).getText().toString()

    boolean isproductPresent = WebUI.verifyMatch(actualText, expectedText, false)

    if (isproductPresent == true) {
        ithproductrowColumn = productrowColumn.get(i).getText()

        installationWord = ithproductrowColumn.contains('Installation and Licensing')

        if (installationWord == true) {
            System.out.println('Installation and Licensing word is present')
        } else {
            System.out.println('Installation and Licensing word is not present')
        }
    }
    
    expectedWord = 'Document type:'

    actualWord = thirdRow.get(i).getText().toString()

    boolean isdocumentPresent = WebUI.verifyMatch(actualWord, expectedWord, false)

    if (isdocumentPresent == true) {
        ithdocumentColumn = documentColumn.get(i).getText()

        configurationWord = ithdocumentColumn.contains('Installation, Licensing & Configuration')

        if (configurationWord == true) {
            System.out.println('Installation, Licensing & Configuration word is present')
        } else {
            System.out.println('Installation, Licensing & Configuration word is not present')
        }
    }
}

Thread.sleep(25000)

'Press back button of the browser'
WebUI.back()





// Verification of "Product Manuals" option


'To verify whether "Product Manuals" option is present and visible or not'
WebUI.verifyElementVisible(findTestObject('Object Repository/Quick_Link_Section_OR/Product_Manuals_Location'))

'Click on "Product Manuals" option'
WebUI.click(findTestObject('Object Repository/Quick_Link_Section_OR/Product_Manuals_Location'))

'To verify whether the window title contains the word "Product Manuals" or not'
String expectedTitle1 = 'Product Manuals'

String actualTitle1 = WebUI.getWindowTitle()

WebUI.verifyMatch(actualTitle1, expectedTitle1, true)

'To verify whether "Product Manuals" word is present in Page head or not'
WebUI.verifyElementText(findTestObject('Search_Result_Page_OR/Pagehead_location'), 'Product Manuals')

'To verify whether "Release links" are present and visible or not'
WebUI.verifyElementVisible(findTestObject('Object Repository/Search_Result_Page_OR/Product_Manuals_release_links'))

'Click on the first Release link'
WebUI.click(findTestObject('Object Repository/Search_Result_Page_OR/Product_Manuals_release_links'))

'To verify whether the files are of document type or not'
attributeURL = WebUI.getAttribute(findTestObject('Object Repository/Release_link_pdf_location'), 'href').toString()

System.out.println(attributeURL)

boolean islinkPDF = attributeURL.endsWith('.pdf')

if (islinkPDF == true) {
    System.out.println('Link contains PDF file')
}

Thread.sleep(3000)

'Press back button of the browser'
WebUI.back()

Thread.sleep(2000)

WebUI.back()



// Verification of "Training Courses" option



'Verify Training Courses option is present and visible'
WebUI.verifyElementVisible(findTestObject('Object Repository/Quick_Link_Section_OR/Training_Courses_location'))

'Click on Training Course'
WebUI.click(findTestObject('Object Repository/Quick_Link_Section_OR/Training_Courses_location'))

'Verify window title contains the word Training Courses'
String expectedTitle2 = 'Training Courses'

String actualTitle2 = WebUI.getWindowTitle()

WebUI.verifyMatch(actualTitle2, expectedTitle2, true)

Thread.sleep(5000)

'Press back button of the browser'
WebUI.back()




// Verification of "What's New" option
WebUI.callTestCase(findTestCase('CommonTestCases/TC-QuickLinks'), [('OptionToFind') : Option1ToFind, ('titleToVerify') : title1ToVerify
        , ('DocumentTypeToVerify') : DocumentType1ToVerify], FailureHandling.STOP_ON_FAILURE)



// Verification of "Troubleshooting Information" option
WebUI.callTestCase(findTestCase('CommonTestCases/TC-QuickLinks'), [('OptionToFind') : Option2ToFind, ('titleToVerify') : title2ToVerify
        , ('DocumentTypeToVerify') : DocumentType2ToVerify], FailureHandling.STOP_ON_FAILURE)



// Verification of "Video Library" option
WebUI.callTestCase(findTestCase('CommonTestCases/TC-QuickLinks'), [('OptionToFind') : Option3ToFind, ('titleToVerify') : title3ToVerify
        , ('DocumentTypeToVerify') : DocumentType3ToVerify], FailureHandling.STOP_ON_FAILURE)

