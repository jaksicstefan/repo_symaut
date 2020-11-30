To develop the ARV library we have used:

Eclipse IDE for Java Developers, 
version 2020-09

The code has certain dependencies:

JAVA JDK SE 8 .
ANTLR 4.7
JAVA-SMT , revision` "1.0.1"

it is necessary to use Ivy dependency manager to resolve the dependencies for JAVA-SMT and to download the binaries for the solvers.


------------------------

How to start the library:

1. Create a trace to monitor in appropriate format and put it in /trace/YOUR_TRACE_NAME.txt. We support collumn format, separated by spaces. It is important that every column has a specified name (first line of the file).
2. Create an XML configuration and save it as /XMLcfg/config.xml. Here it is also important to specify the domain range (maximum value of all the variables in the trace)
3. Specify a formula to monitor and save it in a file. You can can set the path to a custom file. Here we are using /src/parser/toparse.
4. you can run src/MainTestSwMonitors.java, or you can create your own test in the similar fashion.
