import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable
import petstore.PetHelper

def response = WS.sendRequest(findTestObject('API/GET_Pet', [petId: GlobalVariable.invalidPetId]))

WS.verifyResponseStatusCode(response, 404)
WS.verifyElementPropertyValue(response, 'code', 404)
WS.verifyElementPropertyValue(response, 'message', 'java.lang.NumberFormatException: For input string: "' + GlobalVariable.invalidPetId + '"')
