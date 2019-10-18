# YangToJavaCompiler
We write a CFG for YANG Language.
module, body statements, grouping statements etc.
The SyntaxChecker.jj file stores various data used for internal purposes of the Java Compiler.
Lexical Analysis
-Token.java
-Error - TokenMgrError.java
Syntax Analysis
- SyntaxChecker.java
- ParseException.java

Converter.java
takes the parsed .yang file as input and converts it to its equivalent .java file.

Compiler.java
I/O for the program and the main function


