# katalon-petstore-api

Automation API untuk https://petstore.swagger.io/v2 pakai Katalon Studio. Endpoint yang dites: POST /pet, PUT /pet, GET /pet/{petId}, DELETE /pet/{petId}.

## Cara menjalankan

1. Buka project di Katalon Studio 11.x
2. Buka Test Suites > TS_Petstore_API
3. Klik Run

Report keluar di folder Reports.

Base URL dan data lain ada di Profiles/default.glbl. petId dan nama pet dibuat otomatis tiap run.

## Test Suite

TS_Petstore_API, isinya semua test case di bawah.

## Test coverage

- TC01 POST /pet valid
- TC02 POST /pet body rusak (400)
- TC03 PUT /pet valid
- TC04 PUT /pet body rusak (400)
- TC05 GET /pet/{petId} valid
- TC06 GET /pet/{petId} id tidak ada (404)
- TC07 GET /pet/{petId} id bukan angka (404)
- TC08 DELETE /pet/{petId} valid
- TC09 DELETE /pet/{petId} id tidak ada (404)
- TC10 E2E: create, get, update, get, delete, get (404)

Setiap test case cek status code dan isi response.

## Catatan

Tidak ada password atau token di repo ini. apiKey di default.glbl cuma key demo publik Petstore.
