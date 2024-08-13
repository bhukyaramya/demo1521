package tekPyramid;

public class GenerateAlphaNumericRandomdata {

	public static void main(String[] args) {
	//i specified upperlimit,i want to generate 20 numbers of alphanumeric	data
		int n=20;
		
	//choose a Character random from this String
	//generate 20 length of alphanumerical data u have to all type of character
	String AlphaNumericString="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
	
	//create StringBuffer size of AlphaNumericString
	StringBuilder sb = new StringBuilder(n);
	
	for(int i=0; i < n; i++) {
		
		//generate a random number between 0 to AlphaNumericString variable length
		int index = (int) (AlphaNumericString.length()*Math.random());
		
		// add Character one by one in end of sb
		//iam to append with stringbuilder.in banking appln we use this ANRD
		sb.append(AlphaNumericString.charAt(index));
		
	}
	
	System.out.println(sb);
	}

}
