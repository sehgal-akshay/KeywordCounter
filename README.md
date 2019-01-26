Advanced Data Structure Project Report
Advanced Data Structures (COP 5536)
2018
Fibonacci Heap Implementation Keyword Counter
Akshay Sehgal 
UFID: 1416-7988 
akshay.sehgal@ufl.edu


PROJECT DESCRIPTION
The goal of the project is to implement a system that can count the most popular keywords in the search engine. The input file is fed into the system containing the list of keywords and the output file will contain “n” most popular keywords. The value of n will also be provided as a number in the input file.
The project uses the following data structure.
1. Max Fibonacci heap: Use to keep track of the frequencies of the keywords.
2. Hash table(Hash Map in java) : Key for the hash table is keyword and value is pointer to the corresponding node in the Fibonacci heap.
The project is implemented in JAVA language. The HashMap is the built in data structure used. The Fibonacci Heap is implemented without the use of any internal data structure. Fibonacci heap is required because it has better theoretical bounds for increase key operation.

   
How to Run the Program?
1. You can remotely access the server using ssh
username@thunder.cise.ufl.edu
2. For running the Keyword Counter application
3. Unzip the file to extract content.
4. Enter “make” without quotes.
5. Enter java “keywordcounter filepath/input_file_name.txt” without quotes.
6. Run “make clean” without quotes to clean .class files.
   

STRUCTURE OF THE PROGRAM
The program consists of 3 classes.
1) keywordcounter - The main class that reads the input file and performs the insert and writes operation based on the values read.
2) treeNode – This class is used to instantiate the object of node in memory which has following properties.
• Objects of treeNode which acts as pointers in the tree - left, right, child, parent
• degree of the node
• childCut value of the node for the operation of cascading cut
• data field holds the value of the keyword
• count field holds the value of number of times the keyword has been searched
• getData() function returns the data field
3) fibonacciHeap – This class is used to instantiate the methods and functions of the Heap class. All the operations pertaining to Fibonacci Heap are defined in this class.
The basic workflow of the program is the keywordcounter.class takes input file and reads it to create a hash map with different values of the keyword, it also performs insert node operations on an instance of the fibonacciHeap.class by creating new nodes using treeNode.class. As soon as it reads a digit in the input file, it performs remove max operation and writes it in the output_file.txt, after which it reinserts the node back into the hash map.

RESULTS
The code was run for 1 million inputs and the output time required was found to be 1753 ms. It was observed that the time taken for execution varies each time.
This is because there are many nodes with same values when remove max was called.

CONCLUSION
The objective is achieved with the implementation of the Fibonacci Heap to hold all the keywords and there count i.e. number of times the keyword has been searched. The in-built data structure Hash map is used to hold the keyword value and the corresponding tree node in the heap. The program is run and tested for all the possible cases and the results are obtained as per the best of the knowledge.
Assumptions:
1. The program stops as soon as it encounters the “stop” as the input string from the input file.
2. The keywords are not case sensitive i.e. the whole word should match with considering the
case sensitiveness.
3. If the number of write values is greater than the available nodes in the heap. The program
shall print all the available nodes.
4. Rest everything is implemented with reference to the problem statement provided.

REFERENCES:
[1] Fibonacci heap. (n.d.). Retrieved November 18, 2016, from
https://en.wikipedia.org/wiki/Fibonacciheap
[2] Introduction to Algorithms: http://staff.ustc.edu.cn/~csli/graduate/algorithms/book6/chap21.htm
   
For any further details or clarification, please contact Akshay Sehgal. Email: akshay.sehgal@ufl.edu

For more inforamtion, please refer to the report attached with the source code.
