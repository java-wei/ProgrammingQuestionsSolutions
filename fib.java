/*constant time fib solution*/
public class fib {

	public static int fib(int x)
	{
		int a=1;
		int b=1;
	    for (int i = 2; i < x; i++)
	    {
	        a = a + b;
	       int temp = a;
	       a = b;
	       b= temp;
	    }
	    return b;
	}

	public static void main(String [] args){
		int n = 3;
	
		System.out.println(fib(n));
	}
}
