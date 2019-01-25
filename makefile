
all:
	javac keywordcounter.java
	javac treeNode.java
	javac fibonacciHeap.java

clean:
	$(RM) *.class
	