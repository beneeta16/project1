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
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import java.awt.Point as Point
import org.openqa.selenium.Point as Point

'Login to the Website'
WebUI.callTestCase(findTestCase('TC-01-VerifyLogin'), [:], FailureHandling.STOP_ON_FAILURE)

'Maximize the window'
WebUI.maximizeWindow()

'Verify Resources option is present and visible'
WebUI.verifyElementVisible(findTestObject('Resources_Dropdown_OR/Resources_option_location'))

WebUI.verifyElementText(findTestObject('Resources_Dropdown_OR/Resources_option_location'), 'Resources')

'Mouse hover on Resources'
WebUI.mouseOver(findTestObject('Resources_Dropdown_OR/Resources_option_location'))

'Verify Product Manuals is present and visible'
WebDriver driver = DriverFactory.getWebDriver()

optionToBeChecked = driver.findElement(By.xpath('//*[@id=\'navbarCollapse\']/ul/li[4]/div/ul/li[4]/a'))

boolean isOptionVisible = optionToBeChecked.isDisplayed()

System.out.println(isOptionVisible)

boolean isthewordPresent = optionToBeChecked.getText().contains('Product Manuals')

System.out.println(isthewordPresent)

'Click on Product Manuals'
optionToBeChecked.click()











'To verify the window title'
String actualTitle = WebUI.getWindowTitle()

WebUI.verifyMatch(actualTitle, 'Product Manuals', true)

'To verify whether "Product Manuals" word is present in Page head or not'
WebUI.verifyElementText(findTestObject('Search_Result_Page_OR/Pagehead_location'), 'Product Manuals')

'To verify breadcrumb is present and visible'
boolean isBreadcrumbPresent = driver.findElement(By.xpath('//ul[@class = \'breadcrumb\']')).isDisplayed()

System.out.println(isBreadcrumbPresent)

boolean isElement1Present = driver.findElement(By.xpath('//ul[@class = \'breadcrumb\']/li[1]')).getText().contains('Support Home')

System.out.println(isElement1Present)

boolean isElement2Present = driver.findElement(By.xpath('//ul[@class = \'breadcrumb\']/li[2]')).getText().contains('Resources')

System.out.println(isElement2Present)

boolean isElement3Present = driver.findElement(By.xpath('//ul[@class = \'breadcrumb\']/li[3]')).getText().contains('Product Manuals')

System.out.println(isElement3Present)

'To verify whether "Release links" are present and visible or not'
WebUI.verifyElementVisible(findTestObject('Object Repository/Search_Result_Page_OR/Product_Manuals_release_links'))









'To verify searchbox is present and visible'
boolean isSearchboxPresent = driver.findElement(By.xpath('//*[@id=\'srch-term1\']')).isDisplayed()

System.out.println(isSearchboxPresent)

'Search innovus in the searchbar'
WebUI.setText(findTestObject('Object Repository/Searchbar_OR/Searchbox_in_product_manual_page'), 'innovus')

'Press Enter'
WebUI.sendKeys(findTestObject('Searchbar_OR/Searchbox_in_product_manual_page'), Keys.chord(Keys.ENTER))

'Wait for page to load'
WebUI.waitForElementVisible(findTestObject('Search_Result_Page_OR/Search_Area'), 5)

'Verify relevant search results are displayed and they are of pub type links'
List<WebElement> highlightedTextinlink = driver.findElements(By.xpath('//div[@class = \'coveo-result-list-container coveo-list-layout-container\']//a[1]'))

for (int i = 0; i < highlightedTextinlink.size(); i++) {
    String titleText = highlightedTextinlink.get(i).getAttribute('title').toString()

    boolean isitem1PresentinLink = titleText.contains('Innovus')

    System.out.println(isitem1PresentinLink)

    if (isitem1PresentinLink == true) {
        break
    }
}

for (int i = 0; i < 25; i++) {
    String hrefText = highlightedTextinlink.get(i).getAttribute('href').toString()

    boolean isTextPresentinLink = hrefText.contains('techpub')

    System.out.println(isTextPresentinLink)
}

Thread.sleep(5000)

WebUI.back()

WebUI.waitForElementVisible(findTestObject('Object Repository/Search_Result_Page_OR/Product_Manuals_release_links'), 5)









'Click on any Release link'
WebUI.click(findTestObject('Object Repository/Search_Result_Page_OR/Product_Manuals_release_links'))

'To verify breadcrumb is present and visible'
boolean isBreadcrumb2Present = driver.findElement(By.xpath('//ul[@class = \'breadcrumb\']')).isDisplayed()

System.out.println(isBreadcrumbPresent)

boolean isTerm1Present = driver.findElement(By.xpath('//ul[@class = \'breadcrumb\']/li[1]')).getText().contains('Support Home')

System.out.println(isTerm1Present)

boolean isTerm2Present = driver.findElement(By.xpath('//ul[@class = \'breadcrumb\']/li[2]')).getText().contains('Resources')

System.out.println(isTerm2Present)

boolean isTerm3Present = driver.findElement(By.xpath('//ul[@class = \'breadcrumb\']/li[3]')).getText().contains('Product Manuals')

System.out.println(isTerm3Present)


'Verify the searchbox and its placeholder'
WebUI.verifyElementVisible(findTestObject('Object Repository/Searchbar_OR/Searchbox_in_releaseLinks_Page'))

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Searchbar_OR/Searchbox_in_releaseLinks_Page'), 'placeholder', 
    'Search manuals for IC 6.1.8', 5)


'Verify PDF icon is present in every search result link'
List<WebElement> links = driver.findElements(By.xpath('//*[@id=\'coveo-result-list1\']//a[1]'))

List<WebElement> pdfIcons = driver.findElements(By.xpath('//i[@class = \'fa fa-file-pdf-o\']'))

for (int i = 0; i < pdfIcons.size(); i++) {
    boolean isPdfIconVisible = pdfIcons.get(i).isDisplayed()

    System.out.println(isPdfIconVisible)
}

Thread.sleep(20000)


'Verify Clicking on a link opens the page on a new tab'
String titleOfLink = links.get(0).getAttribute('title').toString()

System.out.println(titleOfLink)

links.get(0).click()

WebUI.switchToWindowIndex(1)

Thread.sleep(10000)

String windowTitle = WebUI.getWindowTitle()

System.out.println(windowTitle)

boolean isTitlePresent = windowTitle.contains(titleOfLink)

System.out.println(isTitlePresent)

WebUI.closeWindowIndex(1)

WebUI.switchToWindowIndex(0)

Thread.sleep(5000)



'Verify clicking on any PDF icon present on the links opens the page on a new tab'
ArrayList<String> initialTabs = new ArrayList<String>(driver.getWindowHandles())

System.out.println('Initial No. of tabs: ' + initialTabs.size())

pdfIcons.get(0).click()

Thread.sleep(5000)

ArrayList<String> currentTabs = new ArrayList<String>(driver.getWindowHandles())

System.out.println('Now No. of tabs are: ' + currentTabs.size())

WebUI.closeWindowIndex(1)

WebUI.switchToWindowIndex(0)

Thread.sleep(5000)



'Verify only relevant search results are displayed'
driver.findElement(By.xpath("//div[@aria-label = 'Quick View']")).click();
// identify modal header and obtain text
WebElement header = driver.findElement(By.xpath("//h1/div/div[2]/a"));
System.out.println("Modal Dialog text: " + header.getText());

driver.switchTo().frame(11)
Thread.sleep(3000)

WebElement searchedText = driver.findElement(By.xpath("//h5/center"));
boolean isTextPresent = searchedText.getText().contains("Product Version IC6.1.8")
System.out.println(isTextPresent);
Thread.sleep(5000)

driver.switchTo().defaultContent();

driver.findElement(By.xpath("/html/body/div[19]/div/header/span")).click()











'Verify Alphabet section is present and visible'
WebUI.verifyElementVisible(findTestObject('Object Repository/Search_Result_Page_OR/Alphabet_location'))


'Verify 26 options in the name of (A - Z) are present '
List<WebElement> alphabets = driver.findElements(By.xpath("//ul[@class = 'coveo-custom-glossary-content']/li/a"))
String c = "A";
for(int i=0; i<26;i++) {
while(c <= "Z") {
	String text = alphabets.get(i).getText().toString()
	boolean isLetterPresent = WebUI.verifyMatch(text, c, false)
	System.out.println(isLetterPresent);
	
	c++;
	break;
}
}


'Verify search results are alphabetically sorted'
for(int i=0; i<links.size(); i++) 
{
	
	for(int j=i+1; j<links.size(); i++) 
	{
		String firstText = links.get(i).getText()
		String secondText = links.get(j).getText()
		System.out.println(firstText);
		System.out.println(secondText);

		char firstLetterOf1stSentenece = firstText.charAt(0);
		char firstLetterOf2ndSentence = secondText.charAt(0);
		System.out.println(firstLetterOf1stSentenece);
		System.out.println(firstLetterOf2ndSentence);

		if(firstLetterOf1stSentenece.compareTo(firstLetterOf2ndSentence)<=0) 
		{
		System.out.println("First text is less than or equals to the second text");
	
		}
		break;
	}
}

Thread.sleep(135000)


'Verify clicking on a letter takes the user to that particular section'
JavascriptExecutor j = ((driver) as JavascriptExecutor)

Long v = ((j.executeScript('return window.pageYOffset;')) as Long)

System.out.println('Scroll position after launch: ' + v)

idValue = driver.findElement(By.xpath('//li[@class = \'letter available\'][7]//a')).getText()

driver.findElement(By.xpath('//li[@class = \'letter available\'][7]//a')).click()

Thread.sleep(2000)

// get current scroll position with Javascript Executor
JavascriptExecutor js = ((driver) as JavascriptExecutor)

int scrollLocation = ((js.executeScript('return window.pageYOffset;')) as int)

System.out.println('Scroll position after scrolling upto an element: ' + scrollLocation)

WebElement pBlock = driver.findElement(By.id(idValue))

//Used points class to get x and y coordinates of element.
Point classname = pBlock.getLocation()

int ycordi = classname.getY()

System.out.println(('Element\'s Position from top ' + ycordi) + ' pixels.')

boolean isLocationSame = WebUI.verifyEqual(scrollLocation, ycordi)

System.out.println(isLocationSame)










'Verify the dynamic navigation links'
WebUI.callTestCase(findTestCase('CommonTestCases/TC-DynamicNavigationLinks'), [('FacetToCheck') : Facet_1_To_Check, ('TitleOfTheFacet') : Title_Of_The_Facet_1
        , ('LinksOfTheFacet') : Links_Of_The_Facet_1, ('LinkToCheck') : Facet_1_Link_4, ('expectedsearchedText1') : Searched_Text_1
        , ('expectedsearchedText2') : Searched_Text_2], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('CommonTestCases/TC-DynamicNavigationLinks'), [('FacetToCheck') : Facet_2_To_Check, ('TitleOfTheFacet') : Title_Of_The_Facet_2
        , ('LinksOfTheFacet') : Links_Of_The_Facet_2, ('LinkToCheck') : Facet_2_Link_4, ('expectedsearchedText1') : Searched_Text_3
        , ('expectedsearchedText2') : Searched_Text_4], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('CommonTestCases/TC-DynamicNavigationLinks'), [('FacetToCheck') : Facet_3_To_Check, ('TitleOfTheFacet') : Title_Of_The_Facet_3
        , ('LinksOfTheFacet') : Links_Of_The_Facet_3, ('LinkToCheck') : Facet_3_Link_1, ('expectedsearchedText1') : Searched_Text_5
        , ('expectedsearchedText2') : Searched_Text_6], FailureHandling.STOP_ON_FAILURE)

