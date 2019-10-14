GET http://localhost:8080/rate/EUR or USD

HEADER api-key = 25334e280bdae49780d12b1db9e17b24 Content-Type = application/json

spring.datasource.url= jdbc:postgresql://${DB_HOST:localhost}:5432/${DB_NAME:rate} spring.datasource.username=postgres #spring.datasource.password= (password not used)
