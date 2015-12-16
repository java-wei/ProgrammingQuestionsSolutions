import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//integer cannot start with 0;
//care overflow since input is string


public class addOperatorsDP {
	//give two part of string do recursive call and get all combinations
	public static void getCombinations(String resultStr,String preAns, String num, List<String> combinations, long target){
		//System.out.println(resultStr+" "+preAns+" "+target);
		if(num.isEmpty()){
			if(preAns.contains("*")){
				List<String> factors = Arrays.asList( preAns.substring(1).split("\\*"));
				if((factors.get(0).charAt(0)=='0'&&factors.get(0).length()>1)||(factors.get(1).charAt(0)=='0'&&factors.get(1).length()>1))
					return;
				preAns = preAns.substring(0,1)+String.valueOf(Integer.valueOf(factors.get(0))*Integer.valueOf(factors.get(1)));
			}
			if(preAns.charAt(0)=='+'){
				if(target==Long.valueOf(preAns.substring(1)))
					combinations.add(resultStr);		
			}
			else if(preAns.charAt(0)=='-'){
				if(target+Long.valueOf(preAns.substring(1))==0)
					combinations.add(resultStr);
			}
		}
		else{
			if(preAns.charAt(1)!='0')
				getCombinations(resultStr+num.substring(0,1),preAns+num.substring(0,1), num.substring(1),combinations, target);
			if(preAns.contains("*")){
				List<String> factors = Arrays.asList( preAns.substring(1).split("\\*"));
				if((factors.get(0).charAt(0)=='0'&&factors.get(0).length()>1)||(factors.get(1).charAt(0)=='0'&&factors.get(1).length()>1))
					return;
				preAns = preAns.substring(0,1)+String.valueOf(Integer.valueOf(factors.get(0))*Integer.valueOf(factors.get(1)));
			}
			if(preAns.charAt(0)=='+'){
				getCombinations(resultStr+"+"+num.substring(0,1),"+"+num.substring(0,1), num.substring(1),combinations,  target-Integer.valueOf(preAns.substring(1)));
				getCombinations(resultStr+"-"+num.substring(0,1),"-"+num.substring(0,1), num.substring(1),combinations,  target-Integer.valueOf(preAns.substring(1)));
				getCombinations(resultStr+"*"+num.substring(0,1),preAns+"*"+num.substring(0,1), num.substring(1),combinations,  target);
			}
			else if(preAns.charAt(0)=='-'){
				getCombinations(resultStr+"+"+num.substring(0,1),"+"+num.substring(0,1), num.substring(1),combinations,  target+Integer.valueOf(preAns.substring(1)));
				getCombinations(resultStr+"-"+num.substring(0,1),"-"+num.substring(0,1), num.substring(1),combinations,  target+Integer.valueOf(preAns.substring(1)));
				getCombinations(resultStr+"*"+num.substring(0,1),preAns+"*"+num.substring(0,1), num.substring(1),combinations,  target);
			}
		}
		
	}
	
	public static List<String> addOperators(String num, long target) {
	    List<String> combinations = new ArrayList<String>();
	    if(num.length()!=0){ 
	    	if(!(Long.valueOf(num)<target||(Long.valueOf(num)<=-target&&target<0&&(!num.substring(0,1).equals("0")))))
	    		getCombinations(num.substring(0,1),"+"+num.substring(0, 1),num.substring(1),combinations, target);
	    }
	    return combinations;
	}

	public static void main(String[] args) throws Exception{
		List<String> result = addOperators("1000000009",9);
		PrintWriter writer = new PrintWriter("C:/Users/chezhang/workspace/leetcode/src/a.txt", "UTF-8");
		writer.println(result);
		writer.close();

		

		
	}
}
