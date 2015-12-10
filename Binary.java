//Shaik Abiden
//APCS1 pd10
//HW40 -- This or That
//2015 - 12 - 06

//skeleton file for class Binary

public class Binary implements Comparable{

    private int _decNum;
    private String _binNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _binNum to "0"
      =====================================*/
    public Binary() { 
	//Sets to the post cond values
	_decNum = 0;
	_binNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _binNum to equiv string of bits
      =====================================*/
    public Binary( int n ) {
	_decNum = n;
	_binNum = decToBin(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _binNum to input, _decNum to decimal equiv
      =====================================*/
    public Binary( String s ) {
	//Uses the function to set the decNum to the decimal version of the inputed binary
	_decNum = binToDec(s);
	_binNum = s;
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
	//Returns the binary 1 and 0's or Binary value of the input
	return _binNum;
    }


    /*=====================================
      String decToBin(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToBin(0) -> "0"
      decToBin(1) -> "1"
      decToBin(2) -> "10"
      decToBin(3) -> "11"
      decToBin(14) -> "1110"
      =====================================*/
    public static String decToBin( int n ) {
	//Continously gets the remainder and quotient of n divided by 2, making the remainder the values for the places and the quotient the next value to be divided to determine the next value
	String binVal = "";
	while (n > 0){
	    binVal = n % 2 + binVal;
	    n = n / 2;
	}
	return binVal;
    }


    /*=====================================
      String decToBinR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToBinR(0) -> "0"
      decToBinR(1) -> "1"
      decToBinR(2) -> "10"
      decToBinR(3) -> "11"
      decToBinR(14) -> "1110"
      =====================================*/
    public static String decToBinR( int n ) { 
	//Does the same as above, but instead recalls this fucntion with the quotient, adding the remainder to the running total, eventually stopping when n equals 0
	if(n == 0){
	    return "";
	}
	else{
	    return "" + decToBinR(n / 2) + (n % 2);
	}
    }


    /*=====================================
      String binToDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      binToDec("0") -> 0
      binToDec("1") -> 1
      binToDec("10") -> 2
      binToDec("11") -> 3
      binToDec("1110") -> 14
      =====================================*/
    public static int binToDec( String s ) {
	//First this gets the last value in the binary string, checks it, muliplies it by 2 to the counter that keep track of the place value and adds it to a running total, there is also a bunch of parsing and type casting to turn everything to ints
	int cntr = 0;
	int total = 0;
	while (s.length() >= 1){
	    total = total + ((Integer.parseInt(s.substring(s.length() - 1))) * (int)(Math.pow(2,cntr)));
	    cntr++;
	    s = s.substring(0,s.length() - 1);
	    if (s.length() == 1){
		total = total + ((Integer.parseInt(s.substring(s.length() - 1))) * (int)(Math.pow(2,cntr)));
		s = "";
	    }
	}
	return total;
    }


    /*=====================================
      String binToDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      binToDecR("0") -> 0
      binToDecR("1") -> 1
      binToDecR("10") -> 2
      binToDecR("11") -> 3
      binToDecR("1110") -> 14
      =====================================*/
    public static int binToDecR( String s ) { 
	//This starts with the first value in binary and multiplies it by 2 to the length of the string minus one, which is the value of place the string is in. It then calls on the function again with the first value cut off, adding all the values to a running total. There is also parsing and type casting here.
	if (s.length() == 1){
	    return Integer.parseInt(s) * (int)(Math.pow(2,0));
	}
	else{
	    return (Integer.parseInt(s.substring(0,1)) * (int)(Math.pow(2,s.length() - 1))) + binToDecR(s.substring(1));
	}
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Binary
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/
    public boolean equals( Object other ) { 
	//This returns true if they are the same alias
	if (this == other){
	    return true;
	}
	//This returns true if they have the same binNum, or binary value
	if (this.toString().equals(other.toString())){
	    return true;
	}
	//If the above fails, then they are clearly not equal
	else{
	    return false;
	}
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Binary objects is greater
      pre:  other is instance of class Binary
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
	if (!(other instanceof Comparable)){
	    throw new ClassCastException("The input isn't Comparable");
	}
	if(other == null){
	    throw new NullPointerException("The input is null. Please give it a value");
	}
	if (!(other instanceof Binary)){
	    //Executes these functions, which find better means to compare, when other is a Hexadecimal or Binary
	    if (other instanceof Hexadecimal){
		Comparable j = new Binary(((Hexadecimal)other).getNum());
		return compareTo(j);
	    }
	    if (other instanceof Rational){
		if (_decNum == ((Rational)other).floatValue()){
		    return 0;
		}
		if (_decNum < ((Rational)other).floatValue()){
		    return -1;
		}
		if (_decNum > ((Rational)other).floatValue()){
		    return 1;
		}
	    }
	}
	//First uses equals to compare, returns 0 if true
	if (this.equals(other)){
	    return 0;
	}
	//First type casts, then it compares the decimal values of the two instances of class binary, returing -1 if this is less than and 1 if it is greaterthan other.
	if (this._decNum < ((Binary)other)._decNum){
	    return -1;
	}
	if (this._decNum > ((Binary)other)._decNum){
	    return 1;
	}
	//All else fails, return 99
	return 99;
    }
    
    public int getNum(){
	return _decNum;
    }


    //main method for testing
    public static void main( String[] args ) {
	

	System.out.println();
	System.out.println( "Testing ..." );

	Binary b1 = new Binary(5);
	Binary b2 = new Binary(5);
	Binary b3 = b1;
	Binary b4 = new Binary(7);

	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );       

	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	
	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false


	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos

    }//end main()

} //end class
