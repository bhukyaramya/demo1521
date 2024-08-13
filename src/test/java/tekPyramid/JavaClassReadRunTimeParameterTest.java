package tekPyramid;

public class JavaClassReadRunTimeParameterTest {

	public static void main(String[] args) {
		
		System.out.println(args.length);
		//traditional for loop
		for(String var : args) {
			System.out.println(var);
		}
	}

}
