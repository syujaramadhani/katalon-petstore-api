import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable
import petstore.PetHelper
// Arrange: buat pet baru dengan data dinamis
PetHelper.generateNewPet()
String createBody = PetHelper.buildPetBody(GlobalVariable.petId, GlobalVariable.petName, GlobalVariable.petStatusAvailable)
WS.sendRequest(findTestObject('API/POST_Pet', [body: createBody]))

def response = WS.sendRequest(findTestObject('API/DELETE_Pet', [petId: GlobalVariable.petId]))

WS.verifyResponseStatusCode(response, 200)
WS.verifyElementPropertyValue(response, 'code', 200)
WS.verifyElementPropertyValue(response, 'message', GlobalVariable.petId.toString())
