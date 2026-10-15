import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable
import petstore.PetHelper
// Arrange: buat pet baru dengan data dinamis
PetHelper.generateNewPet()
String createBody = PetHelper.buildPetBody(GlobalVariable.petId, GlobalVariable.petName, GlobalVariable.petStatusAvailable)
WS.sendRequest(findTestObject('API/POST_Pet', [body: createBody]))

// Act: ubah nama dan status
String newName = GlobalVariable.petName + '_updated'
String updateBody = PetHelper.buildPetBody(GlobalVariable.petId, newName, GlobalVariable.petStatusSold)
def response = WS.sendRequest(findTestObject('API/PUT_Pet', [body: updateBody]))

WS.verifyResponseStatusCode(response, 200)
WS.verifyElementPropertyValue(response, 'id', GlobalVariable.petId)
WS.verifyElementPropertyValue(response, 'name', newName)
WS.verifyElementPropertyValue(response, 'status', GlobalVariable.petStatusSold)

// Cleanup
WS.sendRequest(findTestObject('API/DELETE_Pet', [petId: GlobalVariable.petId]))
