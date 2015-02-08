import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class main {
	public static ArrayList<Double> d = new ArrayList<Double>();

	public static void main(String[] args) {
		enterData();

		//System.out.println(d.size());
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

	public static void enterData(){
		//ENTERING DATA INTO THE PUBLIC ARRARYLIST d
		double g=0;
		System.out.println("Enter your data! Enter 666 when done!");
		Scanner in = new Scanner(System.in);
		double data=0;
		boolean go=true;
		while(go){
			data=in.nextFloat();
			if(data==666){
				break;
			}
			else{
				d.add(data);
			}
		} 
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
		int n=d.size();
		//System.out.println(n);
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
		//CALCULATING SKEWNESS
		double sum=0;
		for(int i=0;i<d.size();i++){
			sum=sum+(double) d.get(i);
		}
		double denom=0;
		double numer=0;
		for(int i=0;i<d.size();i++){
			denom+=Math.pow((double)d.get(i)-sum/d.size(),3);
			numer+=Math.pow((double)d.get(i)-sum/d.size(),2);
		}
		//System.out.println(denom);
		//System.out.println(numer);
		double coeff=1.0/d.size();
		double skew=(coeff*denom)/Math.pow(coeff*numer, 1.5);
		//System.out.println(skew);

		//SEEING IF NORMALITY CAN BE ASSUMED BASED ON SKEWNESS AND SAMPLE SIZE
		if(d.size()<15){
			System.out.println("You need a sample size that's at least 15!");
			return false;
		}
		if(d.size()>=40){
			return true;
		}
		if(skew<=.5&&skew>=-.5&&d.size()>15){
			return true;
		}
		else{
			System.out.println("Based on your sample's skewness, you need a sample size that's at least 40");
		}
		return false;
	}
	public static void calculateInt(){
		double sum=0;
		for(int i=0;i<d.size();i++){
			sum=sum+(double) d.get(i);
		}
		double ave=sum/d.size();
		double stdevpart=0;
		for(int i=0;i<d.size();i++){
			stdevpart+=Math.pow((double)d.get(i)-ave,2);
		}
		double stdev=Math.pow((1.0/(d.size()-1))*stdevpart,.5);
		//System.out.println(stdev);

		Scanner in = new Scanner(System.in);
		System.out.println("What is your confidence level? (i.e. 95%)");
		String confLevel=in.nextLine();
		System.out.println("What is the t* value corresponding to your df and confidence level?");
		//http://3.bp.blogspot.com/_C6375WoyYP0/THVqQkJRqbI/AAAAAAAAASU/ML7g-OPPgug/s1600/Student-t-table.png
		double tcrit=in.nextFloat();
		double marginError=tcrit*stdev/Math.pow(d.size(),.5);
		System.out.println("Your "+confLevel+" confidence interval is ("+(ave-marginError)+", "+(ave+marginError)+").");
		System.out.println("This means that we are "+confLevel+" confident that the true population mean in this context is between "
				+(ave-marginError)+" and "+(ave+marginError)+".");
	}

}
