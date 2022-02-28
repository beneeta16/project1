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

WebUI.callTestCase(findTestCase('CommonTestCases/TC-OptionsOfResources'), [('OptionToCheck') : RapidAdoptationKitsXPath, ('TitleToCheck') : Title3ToCheck
        , ('DocumentTypeToCheck') : DocumentType3ToCheck], FailureHandling.STOP_ON_FAILURE)

WebUI.switchToWindowIndex(0)

WebDriver driver = DriverFactory.getWebDriver()

driver.findElement(By.xpath('//*[@id=\'second-search-section\']/div[2]/div/div[1]/div[2]/div')).click()

Thread.sleep(3000)

WebUI.callTestCase(findTestCase('CommonTestCases/TC-DynamicNavigationLinks'), [('FacetToCheck') : Facet1toCheck, ('TitleOfTheFacet') : TitleOfFacet1
        , ('LinksOfTheFacet') : LinksOfFacet1, ('LinkToCheck') : Facet1link3, ('expectedsearchedText1') : SearchedText1, ('expectedsearchedText2') : SearchedText2], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('CommonTestCases/TC-DynamicNavigationLinks'), [('FacetToCheck') : Facet2toCheck, ('TitleOfTheFacet') : TitleOfFacet2
        , ('LinksOfTheFacet') : LinksOfFacet2, ('LinkToCheck') : Facet2link3, ('expectedsearchedText1') : SearchedText3, ('expectedsearchedText2') : SearchedText4], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('CommonTestCases/TC-DynamicNavigationLinks'), [('FacetToCheck') : Facet3toCheck, ('TitleOfTheFacet') : TitleOfFacet3
        , ('LinksOfTheFacet') : LinksOfFacet3, ('LinkToCheck') : Facet3link2, ('expectedsearchedText1') : SearchedText5, ('expectedsearchedText2') : SearchedText6], 
    FailureHandling.STOP_ON_FAILURE)

