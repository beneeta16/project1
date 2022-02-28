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

WebUI.callTestCase(findTestCase('CommonTestCases/TC-OptionsOfResources'), [('OptionToCheck') : ErrorMessageXpath, ('TitleToCheck') : Title2ToCheck
        , ('DocumentTypeToCheck') : DocumentType2ToCheck], FailureHandling.STOP_ON_FAILURE)

WebUI.switchToWindowIndex(0)

WebDriver driver = DriverFactory.getWebDriver()

driver.findElement(By.xpath('//*[@id=\'second-search-section\']/div[2]/div/div[1]/div[2]/div')).click()

Thread.sleep(3000)

WebUI.callTestCase(findTestCase('CommonTestCases/TC-DynamicNavigationLinks'), [('FacetToCheck') : Facet_1ToCheck, ('TitleOfTheFacet') : TitleOfTheFacet1
        , ('LinksOfTheFacet') : LinksOfTheFacet1, ('LinkToCheck') : Facet1Link_4, ('expectedsearchedText1') : Text_1, ('expectedsearchedText2') : Text_2], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('CommonTestCases/TC-DynamicNavigationLinks'), [('FacetToCheck') : Facet_2ToCheck, ('TitleOfTheFacet') : TitleOfTheFacet2
        , ('LinksOfTheFacet') : LinksOfTheFacet2, ('LinkToCheck') : Facet2Link_1, ('expectedsearchedText1') : Text_3, ('expectedsearchedText2') : Text_4], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('CommonTestCases/TC-DynamicNavigationLinks'), [('FacetToCheck') : Facet_3ToCheck, ('TitleOfTheFacet') : TitleOfTheFacet3
        , ('LinksOfTheFacet') : LinksOfTheFacet3, ('LinkToCheck') : Facet3Link_2, ('expectedsearchedText1') : Text_5, ('expectedsearchedText2') : Text_6], 
    FailureHandling.STOP_ON_FAILURE)

