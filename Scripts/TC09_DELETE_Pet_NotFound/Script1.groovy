import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable
import petstore.PetHelper

def response = WS.sendRequest(findTestObject('API/DELETE_Pet', [petId: GlobalVariable.nonExistentPetId]))

WS.verifyResponseStatusCode(response, 404)
assert response.getResponseText().trim() == '' : 'Body response DELETE not found seharusnya kosong'
