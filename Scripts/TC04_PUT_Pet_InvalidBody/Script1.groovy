import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable
import petstore.PetHelper

def response = WS.sendRequest(findTestObject('API/PUT_Pet', [body: GlobalVariable.invalidBody]))

WS.verifyResponseStatusCode(response, 400)
WS.verifyElementPropertyValue(response, 'code', 400)
WS.verifyElementPropertyValue(response, 'message', 'bad input')
