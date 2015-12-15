import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class addOperators {
	//give two part of string do recursive call and get all combinations
	public static void getCombinations(String preAns, String num, List<String> combinations){
		if(num.isEmpty())
			combinations.add(preAns);
		else{
			getCombinations(preAns+num.substring(0,1), num.substring(1),combinations);
			getCombinations(preAns+"+"+num.substring(0,1), num.substring(1),combinations);
			getCombinations(preAns+"-"+num.substring(0,1), num.substring(1),combinations);
			getCombinations(preAns+"*"+num.substring(0,1), num.substring(1),combinations);
		}
	}
	
	public static long compute(String a){
		List<String> numbers = Arrays.asList( a.split("-|\\+"));
		if(numbers.size()==1&&!a.contains("*")) return Long.valueOf(a);
		List<String> operators = new ArrayList<String>();
		for(int i = 0; i < numbers.size();i++){
			List<String> factors = Arrays.asList( numbers.get(i).split("\\*"));
			if(factors.size()==1)
				continue;
			int product = 1;
			for(int j = 0;j<factors.size();j++ )
				product*=Integer.valueOf(factors.get(j));
			numbers.set(i, String.valueOf(product));
		}
		int result = Integer.valueOf(numbers.get(0));
		for(int i = 0; i < a.length(); i++){
			if(a.charAt(i)=='+'||a.charAt(i)=='-')
				operators.add(String.valueOf(a.charAt(i)));
		}
		for(int i = 0; i < numbers.size()-1;i++){
			if(operators.get(i).equals("+"))
				result+=Integer.valueOf(numbers.get(i+1));
			else if(operators.get(i).equals("-"))
				result-=Integer.valueOf(numbers.get(i+1));
		}
		return result;
	}
	public static List<String> addOperators(String num, int target) {
	    List<String> combinations = new ArrayList<String>();
	    List<String> result = new ArrayList<String>();
	    getCombinations(num.substring(0, 1),num.substring(1),combinations);
//	    System.out.println(combinations.size());
//	    for(int i = 0; i <combinations.size();i++)
//	    	System.out.println(combinations.get(i));
	    for(int i = 0; i < combinations.size();i++){
	    	if(compute(combinations.get(i))==target)
	    		result.add(combinations.get(i));
	    }
	    return result;
	}
	public static void main(String[] args){
		List<String> result = addOperators("1349",17);
		//System.out.println(compute("1+2+3"));
		System.out.println(result);
		
	}
}
