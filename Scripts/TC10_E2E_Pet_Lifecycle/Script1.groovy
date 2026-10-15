import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable
import petstore.PetHelper

// 1. Create pet
PetHelper.generateNewPet()
long petId = GlobalVariable.petId
String petName = GlobalVariable.petName
String createBody = PetHelper.buildPetBody(petId, petName, GlobalVariable.petStatusAvailable)

def createResp = WS.sendRequest(findTestObject('API/POST_Pet', [body: createBody]))
WS.verifyResponseStatusCode(createResp, 200)
WS.verifyElementPropertyValue(createResp, 'id', petId)
WS.verifyElementPropertyValue(createResp, 'name', petName)

// 2. Get pet -> data sesuai hasil create
def getResp1 = WS.sendRequest(findTestObject('API/GET_Pet', [petId: petId]))
WS.verifyResponseStatusCode(getResp1, 200)
WS.verifyElementPropertyValue(getResp1, 'id', petId)
WS.verifyElementPropertyValue(getResp1, 'name', petName)
WS.verifyElementPropertyValue(getResp1, 'status', GlobalVariable.petStatusAvailable)

// 3. Update pet (nama + status)
String updatedName = petName + '_updated'
String updateBody = PetHelper.buildPetBody(petId, updatedName, GlobalVariable.petStatusSold)
def updateResp = WS.sendRequest(findTestObject('API/PUT_Pet', [body: updateBody]))
WS.verifyResponseStatusCode(updateResp, 200)
WS.verifyElementPropertyValue(updateResp, 'name', updatedName)
WS.verifyElementPropertyValue(updateResp, 'status', GlobalVariable.petStatusSold)

// 4. Get pet lagi -> verifikasi perubahan tersimpan
def getResp2 = WS.sendRequest(findTestObject('API/GET_Pet', [petId: petId]))
WS.verifyResponseStatusCode(getResp2, 200)
WS.verifyElementPropertyValue(getResp2, 'id', petId)
WS.verifyElementPropertyValue(getResp2, 'name', updatedName)
WS.verifyElementPropertyValue(getResp2, 'status', GlobalVariable.petStatusSold)

// 5. Delete pet
def deleteResp = WS.sendRequest(findTestObject('API/DELETE_Pet', [petId: petId]))
WS.verifyResponseStatusCode(deleteResp, 200)
WS.verifyElementPropertyValue(deleteResp, 'message', petId.toString())

// 6. Get pet setelah delete -> harus 404
def getResp3 = WS.sendRequest(findTestObject('API/GET_Pet', [petId: petId]))
WS.verifyResponseStatusCode(getResp3, 404)
WS.verifyElementPropertyValue(getResp3, 'message', 'Pet not found')
