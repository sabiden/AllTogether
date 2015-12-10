//Shaik Abiden
//APCS1 pd10
//HW41 -- In America, the Driver Sits on the Left
//2015-12-3

public class Rational implements Comparable{

    // Instance Vars
    private int numerator;
    private int denominator;

    public Rational() {
	numerator = 0;
	denominator = 1;
    }

    public Rational(int n, int d) {
	if ( d == 0 ) { // denominator can't be zero
	    System.out.println("Nope.");
	    numerator = 0;
	    denominator = 1;
	}
	else {
	    numerator = n;
	    denominator = d;
	}
    }


    public String toString() { 
	return numerator + " / " + denominator;
    }

    public double floatValue() { 
	return numerator/(double)denominator;
    }

    public void multiply(Rational s) {
	numerator *= s.numerator;
	denominator *= s.denominator;
    }

    public void divide(Rational s) {
	numerator *= s.denominator;
	denominator *= s.numerator;
    }

    public void add(Rational s) {
	if ( denominator == s.denominator ) { // if denominators are equal there is no need for LCD
	    numerator += s.numerator;
	}
	else {
	    numerator *= s.denominator;
	    numerator += s.numerator * denominator;
	    denominator *= s.denominator;
	}
    }

    public void subtract(Rational s) {
	if ( denominator == s.denominator ) {
	    numerator -= s.numerator;
	}
	else {
	    numerator *= s.denominator;
	    numerator -= s.numerator * denominator;
	    denominator *= s.denominator;
	}
    }

    public int gcd(){
	int min;
	int max;
	int stor;
	if ((numerator == 0) || (denominator == 0)){ // if either of them is 0 gcd is automatically 0.
	    return 0;
	}
	else {
	    if ( numerator >= denominator ) {
		max = numerator;
		min = denominator;
	    }
	    else {
		max = denominator;
		min = numerator;
	    }
	    while (min != 0){
	        stor = min;
		min = max % min;
		max = stor;
	    }
	    return max;
	}
    }

    public static int gcds(int n, int d) {
	int min;
	int max;
	int stor;
	if ((n == 0) || (d == 0)){
	    return 0;
	}
	else {
	    if ( n >= d ) {
		max = n;
		min = d;
	    }
	    else {
		max = d;
		min = n;
	    }
	    while (min != 0){
	        stor = min;
		min = max % min;
		max = stor;
	    }
	    return max;
	}
    }


    public void reduce() {
	int g = this.gcd();
	if ( g != 0 ) {
	    numerator /= g;
	    denominator /= g;
	}
    }

    public int compareTo(Object o) {
	if (!(o instanceof Comparable)){
	    throw new ClassCastException("The input isn't Comparable");
	}
	if (!(o instanceof Rational)){
	    if (o instanceof Binary){
		//Creates new Ratonals, using the values of the Binary or Hexadecimal number
		Comparable j = new Rational(((Binary)o).getNum(),1);
		return compareTo(j);
	    }
	    if (o instanceof Hexadecimal){
		Comparable j = new Rational(((Hexadecimal)o).getNum(),1);
		return compareTo(j);
	    }
	}
	if(o == null){
	    throw new NullPointerException("The input is null. Please give it a value");
	}
        this.reduce();
	((Rational)o).reduce();
	//Stimulates the idea of cross-multiplication so that the fractions have the same denominator, and then compares a and b which are the numerator values
	int a = this.numerator * ((Rational)o).denominator;
	int b = this.denominator * ((Rational)o).numerator;
       	if ( a == b ) {
	    return 0;
	}
	else if ( a < b ) {
	    return -1;
	}
	else {
	    return 1;
	}
    }


    public boolean equals(Rational r) {
	if(this == r){
	    return true;
	}
	if(this.compareTo(r) == 0){
	    return true;
	}
	return false;
    }

    public static void main( String[] args ) { // main method

	// testing!
	
	Rational a = new Rational(10,5);
	System.out.println(a);
	System.out.println(a.floatValue());
	System.out.println(a.gcd());
	a.reduce();
	System.out.println(a);

	
	Rational b = new Rational(2,10);
	System.out.println(b);
	System.out.println(b.floatValue());
	System.out.println(b.gcd());
	b.reduce();
	System.out.println(b);


	Rational c = new Rational(9,14);
	System.out.println(c);
	System.out.println(c.floatValue());
	System.out.println(c.gcd());
	c.reduce();
	System.out.println(c);

	
	c.add(b);
	System.out.println(c);
	a.subtract(b);
	System.out.println(a);

	Rational val1 = new Rational(15,30);
	Rational val2 = new Rational(3,6);
	Rational val3 = new Rational(5,6);

	System.out.println(val1.compareTo(val2));
	System.out.println(val1.compareTo(val3));
	System.out.println(val1.equals(val2));
	System.out.println(val1.equals(val3));
	
    }
}
    
    