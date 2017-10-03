all:
	pi4j --compile interval/IntervalLoopDriver.java

run:
	sudo pi4j --run interval/IntervalLoopDriver

clean:
	rm *.class
