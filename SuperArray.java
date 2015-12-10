//Team SNL- Shaik Abiden and Ling Cheng
//APCS1 pd10
//HW42 -- Array of Titanium
//2015 - 12 - 05

/*****************************
 * SKELETON for
 * class SuperArray --  A wrapper class for an array. 
 * Maintains functionality:
 *  access value at index
 *  overwrite value at index
 *  report number of meaningful items
 * Adds functionality to std Java array:
 *  resizability
 *  ability to print meaningfully
 *  add item (at end)
 *  insert item
 *  remove item (while maintaining "left-justification")
 *****************************/

public class SuperArray{
 
    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;

		
    //~~~~~METHODS~~~~~
    //default constructor  initializes 10-item array
    public SuperArray() { 
    	_data = new Comparable[10];
    	_lastPos = -1; //flag to indicate no lastpos yet
    	_size = 0;	
    }

		
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() { 
    	String foo = "[";
    	for( int i = 0; i < _size; i++ ) {
    		foo += _data[i] + ",";
    	}
		//shave off trailing comma
		if ( foo.length() > 1 )
	    	foo = foo.substring( 0, foo.length()-1 );
		foo += "]";
		return foo;
    }

		
    //double capacity of this SuperArray
    private void expand() { 
		Comparable[] temp = new Comparable[ _data.length * 2 ];
		for( int i = 0; i < _data.length; i++ )
	    	temp[i] = _data[i];
		_data = temp;
    }

		
    //accessor -- return value at specified index
    public Comparable get( int index ) {
    	return _data[index]; }

		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public Comparable set( int index, Comparable newVal ) {
	Comparable temp= _data[index];
	_data[index]= newVal;
	return temp;
    }


    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( Comparable newVal ) {
    	//if _data is full, expand the array
    	if (_size == _data.length){
	    	expand();
    	}
    	
	set(_lastPos + 1, newVal);
    	//increases _lastPos and _size
    	_lastPos++;
    	_size++;
    }


    //inserts an item at index
    //shifts existing elements to the right
    public void add( int index, Comparable newVal ) { 
    	//checks if there is a meaningful value at index
    	if (index> _lastPos){
	    System.out.println("No meaningful int at index");
    	} else {
	    //If _data is full, increase length of _data
	    if (_size == _data.length) {
		expand();
	    }		
	    //copies everything before index from _data to temp 
	    for (int i = _lastPos; i >= index; i--) {
		_data[i+1]= _data[i];
	    }
	    _data[index]= newVal;
	    _size++;
	    _lastPos++;
	}//end else
	   
    }//end add
    

    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove( int index ) {
	if (index > _lastPos)
	    System.out.println("No meaningful value at index");
	else {for (int i= index; i < _lastPos; i++){
		_data[i]= _data[i+1];
	    }
	    _size--;
	    _lastPos--;
	}
    }


    //return number of meaningful items in _data
    public int size() { return _size;}
    
    //Iterates through the array and stops at the first index with the same value as the input, returning the index
    public int linSearch(Comparable c){
	for (int i = 0; i < _size;i++){
	    if (c.compareTo(_data[i]) == 0){
		return i;
	    }
	}
	return -1;
    }
    
    //Checks to make sure every value after a value at an index is greater than itself, returns true is everything is in order, false otehrwise
    public boolean isSorted(){
	for (int i = 0; i < (_size - 2); i++){
	    if (_data[i].compareTo(_data[i + 1]) > 0){
		return false;
	    }
	}
	return true;
    }

    public static void main( String[] args ) {
	//Test Cases
	SuperArray Boss = new SuperArray();
	System.out.println(Boss);
	Comparable a = new Rational(5,10);
	Boss.add(a);
	System.out.println(Boss);
	Comparable b = new Binary(37);
	Comparable c = new Hexadecimal(5);
	Comparable d = new Hexadecimal(6);
	Comparable e = new Hexadecimal(9);
	Comparable f = new Rational(12,2);
	Comparable g = new Rational(24,4);
	Comparable h = new Binary(6);
	Boss.add(b);
	Boss.add(c);
	Boss.add(d);
	Boss.add(e);
	Boss.add(f);
	Boss.add(g);
	Boss.add(h);
	System.out.println(Boss);
	Comparable s1 = new Binary(6);
	Comparable s2 = new Hexadecimal(6);
	Comparable s3 = new Rational(6,1);
	Comparable s4 = new Binary(7);
	Comparable s5 = new Hexadecimal(7);
	Comparable s6 = new Rational(7,1);
	System.out.println(Boss.linSearch(s1));
	System.out.println(Boss.linSearch(s2));
	System.out.println(Boss.linSearch(s3));
	System.out.println(Boss.linSearch(s4));
	System.out.println(Boss.linSearch(s5));
	System.out.println(Boss.linSearch(s6));
	System.out.println(Boss.isSorted());
	SuperArray Boss2 = new SuperArray();
	Comparable a1 = new Binary(1);
	Comparable a2 = new Hexadecimal(2);
	Comparable a3 = new Rational(3,1);
	Comparable a4 = new Binary(4);
	Comparable a5 = new Hexadecimal(5);
	Comparable a6 = new Rational(6,1);
	Boss2.add(a1);
	Boss2.add(a2);
	Boss2.add(a3);
	Boss2.add(a4);
	Boss2.add(a5);
	Boss2.add(a6);
	System.out.println(Boss2.isSorted());

	//In order to check and call from ListInt, the var type is ListInt instead of SuperArray, esuring that only ListInt's functions are called.

	//This should and does return an error b/c the var type of test2 is Listint and should have this function
	
	//test2.expand();

    }//end main
		
}//end class
