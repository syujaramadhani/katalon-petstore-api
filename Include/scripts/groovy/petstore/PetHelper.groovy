package petstore

import groovy.json.JsonOutput
import internal.GlobalVariable

class PetHelper {

	/** Buat petId (timestamp + random) dan petName baru, simpan ke Global Variable. */
	static void generateNewPet() {
		GlobalVariable.petId = System.currentTimeMillis() * 1000 + new Random().nextInt(1000)
		GlobalVariable.petName = "pet_" + GlobalVariable.petId
	}

	/** Susun body JSON pet dari id, nama, dan status. */
	static String buildPetBody(long id, String name, String status) {
		Map pet = [
			id       : id,
			category : [id: 1, name: GlobalVariable.petCategoryName],
			name     : name,
			photoUrls: [GlobalVariable.petPhotoUrl],
			tags     : [[id: 1, name: 'auto']],
			status   : status
		]
		return JsonOutput.toJson(pet)
	}
}
