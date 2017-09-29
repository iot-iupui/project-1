all:
	pi4j --compile ToggleLightTest.java

run:
	sudo pi4j --run ToggleLightTest

clean:
	rm *.class