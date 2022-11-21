before start service you must init a local memcached process run on port 11211 <br />
buld: mvn package <br />
run: java -jar target/joke_api-1.0-SNAPSHOT.jar server <br />
local api url: http://localhost:8080/jokes?keyword={value}
