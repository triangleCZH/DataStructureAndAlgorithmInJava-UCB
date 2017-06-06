equality,comparison and java

equality and comparison{
	== means compare by value;

	Comparable:comapers the object with the specified object for order,return zero negative
	positive for equal to less than greater than the specified object respectively

	it is strongly recommended to have (x,compareTo(u)==0)==(x.equals(u));

	map(f,a):map is a higher order function since it takes function as an argument

}

lecture note:
to compare two different classes in the same superclass animal dog and wolf,
do  animal implements Comparable<animal>;


interface Function<T,R>{
	R apply (T 0)
}
take in T and return R;