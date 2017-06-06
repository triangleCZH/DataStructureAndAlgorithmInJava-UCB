//import java.util.Scanner;

public class SieveOfEratosthenes {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("You need to enter an argument!");
		}else{
		//System.out.println(upperBound);
		/*try {int upperBound= Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			e.printStackTrace();*/

		int upperBound = Integer.parseInt(args[0]);
		if ( upperBound>=2)
		{boolean[] isNotPrime = new boolean[upperBound + 1];
			//for(int i=0;i<upperBound;i++)
			//isNotPrime[i]=false;
			for (int i = 0; i < upperBound; i++) {
				if (!isNotPrime[i])
					if (i == 0) {
						isNotPrime[i] = true;
					}//THIS DATA HAS BEEN CORRUPTED; REPLACE IT!
					else {
						int count = 2;
						while (count * (i + 1) < upperBound) {
							isNotPrime[count * (i + 1) - 1] = true;
							//System.out.println(count * (i + 1) + "is not prime");
							count++;
						}
					}
			}

			for (int i = 0; i < upperBound-1; i++) {
				if (!isNotPrime[i]) {
					System.out.print((i + 1) + " is a prime number.\n");
				}}
	    }

}}
}
