import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import java.awt.Desktop

import java.io.File

import java.io.IOException

import java.nio.file.*



public class FileOperations {

	// Custom keyword for opening file - provide file path as a parameter

	@Keyword

	def Open_File(String FilePath) {

		File file = new File(FilePath)

		//first check if Desktop is supported by Platform or not

		if(!Desktop.isDesktopSupported()){

			System.out.println("Desktop is not supported")

			return

		}

		Desktop desktop = Desktop.getDesktop()

		if(file.exists()) desktop.open(file)

		System.out.println("File Opened Sucessfully \r")


	}

}
