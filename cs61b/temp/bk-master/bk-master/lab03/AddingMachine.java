import java.util.*;

public class AddingMachine {


	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);

		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int input;
		int last = 1;
		int MAXIMUM_NUMBER_OF_INPUTS = 100;
		int[] arr= new int[MAXIMUM_NUMBER_OF_INPUTS];
		int count=0;
		while (true&&(count<MAXIMUM_NUMBER_OF_INPUTS)) {
			input = scanner.nextInt();

				count++;
				arr[count]=input;

			if (input == 0) {
				if (last == 0) {
					System.out.println("total " + total);
					for(int j=0;j<count;j++){
						if(arr[j]!=0)
						System.out.println(arr[j]);
					}
					return;
				}

				System.out.println("subtotal " + subtotal);
				total += subtotal;
				subtotal = 0;
			}
			subtotal += input;
			last = input;


			// TODO Your code here
		}
	}

}
