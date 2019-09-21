package info.martinoconnell;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		ArrayList<RationalMethod> res = new ArrayList<RationalMethod>();	// Creates a new results ArrayList of RationalMethod objects
		String proceed = "y";												// Ensure the while loop is carried out at least once
		int i = 0;															// Ensures the ArrayList location will match the calculations currently being undertaken

		while(proceed.equals("y") || proceed.equals('Y')) {					// The while loop that will add RationalMethod object to the ArrayList

			res.add(new RationalMethod());									// This method actually adds new RationalMethod objects to the ArrayList

			System.out.println("\nQp = " + (res.get(i).Qp() + " l/s"));		// Prints out Qp for the given ArrayList location 'i'
			System.out.println("Qf = " + (res.get(i).Qf() + " l/s\n"));		// Prints out Qf for the given ArrayList location 'i'

			if(res.get(i).Qp() < res.get(i).Qf()) {							// Determines whether Qp < Qf for each object in location 'i'

				System.out.println("Pipe capacity is OK\n"
						+ "Continue calculations? 'y' or 'n': ");
				Scanner proceedInput = new Scanner(System.in);				
				proceed = proceedInput.nextLine();							// Updates the condition of the while loop

				i++;														/* Updates the ArrayList location 'i' and ensures that each 
																			of the calculations being undertaken are specific to that 
																			new location */
				if(proceed.equals("n") || proceed.equals("N")){
					System.out.println("\nProgramme terminated...");
				}
				

			} else {

				System.out.println("Pipe capacity insufficient as Qp > Qf. Try again? 'y' or 'n': ");		// Updates the condition of the while loop
				Scanner proceedInput = new Scanner(System.in);
				proceed = proceedInput.nextLine();

				i++;														/* Updates the ArrayList location 'i' and ensures that each 
																			of the calculations being undertaken are specific to that 
																			new location */
				if(proceed.equals("n") || proceed.equals("N")){
					System.out.println("\nProgramme terminated...");
				}
			}

		}

	}
	
}
