
// MODIFIED RATIONAL METHOD

package info.martinoconnell;

import java.util.Scanner;

public class RationalMethod {

	private String pipeNumber;			// e.g. "Pipe 1.2"
	private double pipeLength; 			// m
	private double pipeGradient;		// 1 in x
	private double assumedDia;			// m
	private double pipeFullVel;			// Litres per second, taken from Wallingford tables
	private double Qf;					// Litres per second, taken from Wallingford tables or calculated from Qf method below
	private double pipeRoughness;		// mm	
	private double pipeUpstream;

	private int returnPeriod;			// Years
	private double timeOfEntry;			// mins, see p249 of book
	private double timeOfFlow;			// mins
	private double timeToConcentration;	// mins
	private double Cv;					// Volumetric runoff co-efficient (0.75 - 0.95 for concrete and asphalt) or
										// Cv = PR / PIMP (** TO BE CALCULATED IN ADVANCED, NOT ASSUMED - p110 OF BOOK **)
	private double rainfallIntensity;	// mm/hr, read from local data, see Baldonnel sample table or Example 11.3, p255 of book
	private double impervArea;			// m2
	private double Qp;					// Calculated discharge



	public double getPipeUpstream() {
		return pipeUpstream;
	}

	public void setPipeUpstream(double pipeUpstream) {
		this.pipeUpstream = pipeUpstream;
	}

	public String getPipeNumber() {
		return pipeNumber;
	}

	public void setPipeNumber(String pipeNumber) {
		this.pipeNumber = pipeNumber;
	}

	public double getPipeLength() {
		return pipeLength;
	}

	public void setPipeLength(double pipeLength) {
		this.pipeLength = pipeLength;
	}

	public double getPipeGradient() {
		return pipeGradient;
	}

	public void setPipeGradient(double pipeGradient) {
		this.pipeGradient = pipeGradient;
	}

	public double getAssumedDia() {
		return assumedDia;
	}

	public void setAssumedDia(double assumedDia) {
		this.assumedDia = assumedDia;
	}

	public double getPipeFullVel() {
		return pipeFullVel;
	}

	public void setPipeFullVel(double pipeFullVel) {
		this.pipeFullVel = pipeFullVel;
	}

	public double getQf() {
		return Qf;
	}

	public void setQf(double qf) {
		Qf = qf;
	}

	public double getPipeRoughness() {
		return pipeRoughness;
	}

	public void setPipeRoughness(double pipeRoughness) {
		this.pipeRoughness = pipeRoughness;
	}

	public int getReturnPeriod() {
		return returnPeriod;
	}

	public void setReturnPeriod(int returnPeriod) {
		this.returnPeriod = returnPeriod;
	}

	public double getTimeOfEntry() {
		return timeOfEntry;
	}

	public void setTimeOfEntry(double timeOfEntry) {
		this.timeOfEntry = timeOfEntry;
	}

	public double getTimeOfFlow() {
		return timeOfFlow;
	}

	public void setTimeOfFlow(double timeOfFlow) {
		this.timeOfFlow = timeOfFlow;
	}

	public double getTimeToConcentration() {
		return timeToConcentration;
	}

	public void setTimeToConcentration(double timeToConcentration) {
		this.timeToConcentration = timeToConcentration;
	}

	public double getCv() {
		return Cv;
	}

	public void setCv(double cv) {
		Cv = cv;
	}

	public double getRainfallIntensity() {
		return rainfallIntensity;
	}

	public void setRainfallIntensity(double rainfallIntensity) {
		this.rainfallIntensity = rainfallIntensity;
	}

	public double getImpervArea() {
		return impervArea;
	}

	public void setImpervArea(double impervArea) {
		this.impervArea = impervArea;
	}

	public double getQp() {
		return Qp;
	}

	public void setQp(double qp) {
		Qp = qp;
	}

	RationalMethod(){

		System.out.println("\nEnter return period (years): ");
		Scanner input1 = new Scanner(System.in);
		returnPeriod = input1.nextInt();

		System.out.println("Enter pipe roughness (mm): ");
		Scanner input2 = new Scanner(System.in);
		this.pipeRoughness = input2.nextDouble();

		System.out.println("Enter time of entry (mins): ");
		Scanner input3 = new Scanner(System.in);
		timeOfEntry = input3.nextDouble();

		System.out.println("Enter volumetric run-off coefficient: ");
		Scanner input4 = new Scanner(System.in);
		Cv = input4.nextDouble();

		System.out.println("Enter pipe length (m): ");
		Scanner input5 = new Scanner(System.in);
		this.pipeLength = input5.nextDouble();

		System.out.println("Enter pipe gradient (1 in 'x'): ");
		Scanner input6 = new Scanner(System.in);
		this.pipeGradient = input6.nextDouble();

		System.out.println("Enter total contributing impervious area (ha): ");
		Scanner input7 = new Scanner(System.in);
		impervArea = input7.nextDouble();

		System.out.println("Enter pipe diameter (m): ");
		Scanner input8 = new Scanner(System.in);
		this.assumedDia = input8.nextDouble();

		System.out.println("Enter pipe-full velocity from Wallignford tables (m/s): ");
		Scanner input9 = new Scanner(System.in);
		this.pipeFullVel = input9.nextDouble();

		System.out.println("Enter the sum of the upstream time of flow (mins): ");		// As this programme is only valid for single pipe analysis
		Scanner input10 = new Scanner(System.in);									// to undertake multi-pipe analysis requires entering the
		this.pipeUpstream = input10.nextDouble();									// sum of the 'Time of Flow' values for upstream pipes


		System.out.println("\nTime of flow is: " 
				+ (this.timeOfFlow(this.getPipeLength() , this.getPipeFullVel())	// Displays the 'Time of Flow' for the pipe in question 
						- pipeUpstream));											// only and not all contributing pipes. Hence '-piprUpstream'
		System.out.println("Time to concentration is: " 
				+ this.timeToConcentration() + "\n");

		System.out.println("Enter rainfall intensity obtained from local data (mm/hr): ");
		Scanner input11 = new Scanner(System.in);
		rainfallIntensity = input11.nextDouble();

	}

	public double Qp() {											// Method to calculate the actual pipe discharge 

		Qp = 3.61*Cv*rainfallIntensity*impervArea;

		return Qp;
	}

	public double timeOfFlow(double length, double velocity) {		// Method to calculate the time of flow including any upstream pipes

		timeOfFlow = (length / velocity)/60 + pipeUpstream;			// Divided by 60 to ensure value returned is in minutes

		return timeOfFlow;

	}

	public double timeToConcentration(){							// Method to calculate the 'Time to Concentration'

		timeToConcentration = timeOfEntry + timeOfFlow;

		return timeToConcentration;
	}

	public double Qf() {											// Method to calculate max discharge based on value 'pipeFullVel'
		// read from Wallingford tables
		Qf = 1000*Math.PI*Math.pow(assumedDia, 2.0)*pipeFullVel/4.0;

		return Qf;

	}

}
