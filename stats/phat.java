import java.util.Scanner;


public class phat {
	public static double p;
	public static int n;

	public static void main(String[] args){
		enterP();
		boolean n=normality();

		boolean s=SRS();
		boolean i=independence();
		boolean t=tenN();

		if(s&&i&&t&&n){
			System.out.println("ASSUMPTIONS MET D00D");
			calculateInt();		
		}
		else{
			System.out.println("You need to fix assumptions.");
		}
	}
	public static void enterP(){
		System.out.println("Enter your sample size?");
		Scanner in = new Scanner(System.in);
		n=in.nextInt();
		System.out.println("Enter your p hat (as a proportion, like .5 instead of 50%)");
		p=in.nextFloat();
	}
	public static boolean SRS() {
		System.out.println("Did you pick an SRS?");
		String r = "";
		Scanner in = new Scanner(System.in);
		r = in.nextLine();
		if (r.equals("yes")) {
			return true;
		} else {
			System.out.println("You need an SRS.");
			return false;
		}
	}

	public static boolean independence() {
		System.out.println("Are the observations independent?");
		String r = "";
		Scanner in = new Scanner(System.in);
		r = in.nextLine();
		if (r.equals("yes")) {
			return true;
		} else {
			System.out.println("You need independence.");
			return false;
		}
	}

	public static boolean tenN(){
		Scanner in = new Scanner(System.in);
		System.out.println("What's your population size?");
		int p=in.nextInt();
		if(p>=10*n){
			return true;
		}
		else{
			System.out.println("You need a population more than 10 times sample size.");
			return false;
		}
	}
	public static boolean normality(){
		if(n*p>=10&&n*(1-p)>=10){
			return true;
		}
		else{
			System.out.println("You need to have the number of failures and number of successes in your sample to be at least 10.");
			return false;
		}
	}
	public static void calculateInt(){
		Scanner in = new Scanner(System.in);
		System.out.println("What is your confidence level? (i.e. 95%)");
		String confLevel=in.nextLine();
		System.out.println("What is the z* value corresponding to your confidence level?");
		double zcrit=in.nextFloat();
		double marginError= Math.pow(p*(1.0-p)/n, .5);
		System.out.println("Your "+confLevel+" confidence interval is ("+(p-marginError)+", "+(p+marginError)+").");
		System.out.println("This means that we are "+confLevel+" confident that the true population proportion in this context is between "
				+(p-marginError)+" and "+(p+marginError)+".");
	}


}
