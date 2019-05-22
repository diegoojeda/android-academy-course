.PHONY: all

start-fakeserver:
	docker-compose up -d
	ssh -o ServerAliveInterval=60 -tt -R apiumacademy.serveo.net:80:localhost:8080 serveo.net

#Just another name to make it shorter
up: start-fakeserver

stop-fakeserver:
	docker-compose stop

remove-fakeserver: stop-fakeserver
	docker-compose rm simfy_dyson

unit-tests:
	./gradlew testRobolectricDebugUnitTest