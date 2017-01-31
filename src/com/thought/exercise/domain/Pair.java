package com.thought.exercise.domain;

public class Pair<A extends Comparable<? super A>, B extends Comparable<? super B>>
		implements Comparable<Pair<A, B>> {

	private  A first;
	private  B second;

	public Pair(A first, B second) {
		this.first = first;
		this.second = second;
	}

	public static <A extends Comparable<? super A>, B extends Comparable<? super B>> Pair<A, B> of(A first, B second) {
		return new Pair<A, B>(first, second);
	}

	@Override
	public int compareTo(Pair<A, B> object) {
		int compareToValue = object == null ? 1 : (this.first).compareTo(object.first);
		return compareToValue == 0 ? (this.second).compareTo(object.second) : compareToValue;
	}

	
	/*
	 * java.util.Objects.hash(...) or even com.google.commons.base.Objects.hashCode(...)
	 */
	@Override
	public int hashCode() {
		return 31 * hashcode(first) + hashcode(second);
	}

	
	private static int hashcode(Object object) {
		return object == null ? 0 : object.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Pair))
			return false;
		if (this == obj)
			return true;
		return this.equal(first, ((Pair<?, ?>) obj).first) && this.equal(second, ((Pair<?, ?>) obj).second);
	}
	

	private boolean equal(Object objectOne, Object object2) {
		return objectOne == object2 || (objectOne != null && objectOne.equals(object2));
	}

	@Override
	public String toString() {
		return "(" + first + ", " + second + ')';
	}

	public A getFirst() {
		return first;
	}

	public B getSecond() {
		return second;
	}
	
	public void setFirst(A newfirst){
		this.first = newfirst;
	}

	public void setSecond(B second) {
		this.second = second;
	}
}