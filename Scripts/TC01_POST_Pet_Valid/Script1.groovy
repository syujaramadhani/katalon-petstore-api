import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable
import petstore.PetHelper

PetHelper.generateNewPet()
String body = PetHelper.buildPetBody(GlobalVariable.petId, GlobalVariable.petName, GlobalVariable.petStatusAvailable)

def response = WS.sendRequest(findTestObject('API/POST_Pet', [body: body]))

WS.verifyResponseStatusCode(response, 200)
WS.verifyElementPropertyValue(response, 'id', GlobalVariable.petId)
WS.verifyElementPropertyValue(response, 'name', GlobalVariable.petName)
WS.verifyElementPropertyValue(response, 'status', GlobalVariable.petStatusAvailable)
WS.verifyElementPropertyValue(response, 'category.name', GlobalVariable.petCategoryName)

// Cleanup
WS.sendRequest(findTestObject('API/DELETE_Pet', [petId: GlobalVariable.petId]))
