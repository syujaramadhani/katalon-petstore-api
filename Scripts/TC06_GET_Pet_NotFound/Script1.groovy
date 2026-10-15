import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable
import petstore.PetHelper

def response = WS.sendRequest(findTestObject('API/GET_Pet', [petId: GlobalVariable.nonExistentPetId]))

WS.verifyResponseStatusCode(response, 404)
WS.verifyElementPropertyValue(response, 'code', 1)
WS.verifyElementPropertyValue(response, 'type', 'error')
WS.verifyElementPropertyValue(response, 'message', 'Pet not found')
