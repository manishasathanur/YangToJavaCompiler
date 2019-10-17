package YANG2JAVA;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class ObjectGenerator {

	public static void generate(String path,String name)
	{
		//File sourceFile = new File(path+ "/"+name+".java");
	
		
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		    compiler.run(null, null, null,path+ "/"+name+".java");
	}
	
}
