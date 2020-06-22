package proj3;  // Gradescope needs this.
/**
 *  SEQUENCE CLASS (5/14/2020)
 *  * An abstract data structure that is a collection of strings. The sequence can have a "current element" that helps user manipulate the sequence
 *  * @author Quoc (Jordan) An
 *  * @version 2
 *  * I affirm that I have carried out the attached academic endeavors with full academic honesty, in
 *  * accordance with the Union College Honor Code and the course syllabus.
 */

/**
 * CLASS INVARIANT:
 * 1. The capacity of the sequence is stored in the instance variable capacity
 * 2. If there is a current element, then it lies in contents.search(currentIndex); if there is no current element, then currentIndex equals this.size() (i.e the length of contents).
 */

public class Sequence
{
	private LinkedList contents;
	private int capacity;
	private int currentIndex;
	
    /**
     * Creates a new sequence with initial capacity 10.
     */
    public Sequence() {
    	contents = new LinkedList();
    	capacity = 10;
        currentIndex = this.size();
    }
    

    /**
     * Creates a new sequence.
     * 
     * @param initialCapacity the initial capacity of the sequence.
     */
    public Sequence(int initialCapacity){
        contents = new LinkedList();
        capacity = initialCapacity;
        currentIndex = this.size();
    }
    

    /**
     * Adds a string to the sequence in the location before the
     * current element. If the sequence has no current element, the
     * string is added to the beginning of the sequence.
     *
     * The added element becomes the current element.
     *
     * If the sequences's capacity has been reached, the sequence will
     * expand to twice its current capacity plus 1.
     *
     * @param value the string to add.
     */
    public void addBefore(String value)
    {
        if (!isCurrent()){
            currentIndex = 0;
        }
        if (this.size() == capacity) {
            this.ensureCapacity(capacity * 2 + 1);
        }
        if (this.size() == 0){
            contents.insertAtHead(value);
        }
        else {
            contents.insertBeforeIndex(currentIndex, value);
        }
    }
    
    
    /**
     * Adds a string to the sequence in the location after the current
     * element. If the sequence has no current element, the string is
     * added to the end of the sequence.
     *
     * The added element becomes the current element.
     *
     * If the sequences's capacity has been reached, the sequence will
     * expand to twice its current capacity plus 1.
     *
     * @param value the string to add.
     */
    public void addAfter(String value)
    {

        if (!isCurrent()){
            currentIndex = this.size() - 1;
        }

        if (this.size() == this.getCapacity()){
            this.ensureCapacity(capacity*2 + 1);
        }

        if (this.size() == 0){
            contents.insertAtTail(value);
            currentIndex = this.size() - 1;
        }
        else {
            contents.insertAfterIndex(currentIndex, value);
            currentIndex++;
        }
//        System.out.println("currentI: "+ currentIndex);
    }

    
    /**
     * @return true if and only if the sequence has a current element.
     */
    public boolean isCurrent()
    {
        return currentIndex != this.size();
    }
    
    
    /**
     * @return the capacity of the sequence.
     */
    public int getCapacity()
    {
        return capacity;
    }

    
    /**
     * @return the element at the current location in the sequence, or
     * null if there is no current element.
     */
    public String getCurrent()
    {
//        System.out.println(currentIndex);
        if (isCurrent()) {
            return contents.search(currentIndex);
        }
        else{
            return null;
        }
    }
    
    
    /**
     * Increase the sequence's capacity to be
     * at least minCapacity.  Does nothing
     * if current capacity is already >= minCapacity.
     *
     * @param minCapacity the minimum capacity that the sequence
     * should now have.
     */
    public void ensureCapacity(int minCapacity)
    {
        if (this.getCapacity() < minCapacity){
            capacity = minCapacity;
        }
    }

    
    /**
     * Places the contents of another sequence at the end of this sequence.
     *
     * If adding all elements of the other sequence would exceed the
     * capacity of this sequence, the capacity is changed to make (just enough) room for
     * all of the elements to be added.
     * 
     * Postcondition: NO SIDE EFFECTS!  the other sequence should be left
     * unchanged.  The current element of both sequences should remain
     * where they are. (When this method ends, the current element
     * should refer to the same element that it did at the time this method
     * started.)
     *
     * @param another the sequence whose contents should be added.
     */
    public void addAll(Sequence another)
    {
        if(!this.isCurrent()){
            currentIndex = this.size() + another.size();
        }
        ensureCapacity(this.size() + another.size());
        LinkedList copyAnotherLL = another.contents.copy();
        this.contents.addLinkedListToTail(copyAnotherLL);

    }

    
    /**
     * Move forward in the sequence so that the current element is now
     * the next element in the sequence.
     *
     * If the current element was already the end of the sequence,
     * then advancing causes there to be no current element.
     *
     * If there is no current element to begin with, do nothing.
     */
    public void advance()
    {
        if (currentIndex > this.size() - 1) {
            currentIndex = this.size();
        } else {
            currentIndex++;
        }
    }

    
    /**
     * Make a copy of this sequence.  Subsequence changes to the copy
     * do not affect the current sequence, and vice versa.
     * 
     * Postcondition: NO SIDE EFFECTS!  This sequence's current
     * element should remain unchanged.  The clone's current
     * element will correspond to the same place as in the original.
     *
     * @return the copy of this sequence.
     */
    public Sequence clone()
    {
        Sequence ans = new Sequence();
        LinkedList temp = this.contents.copy();
        ans.contents = temp;
        ans.currentIndex = this.currentIndex;
        ans.capacity = this.capacity;
        return ans;
    }
   
    
    /**
     * Remove the current element from this sequence.  The following
     * element, if there was one, becomes the current element.  If
     * there was no following element (current was at the end of the
     * sequence), the sequence now has no current element.
     *
     * If there is no current element, does nothing.
     */
    public void removeCurrent()
    {
        if(isCurrent()){
            contents.removeAtIndex(currentIndex);
        }

    }

    
    /**
     * @return the number of elements stored in the sequence.
     */
    public int size()
    {
        return this.contents.getLength();
    }

    
    /**
     * Sets the current element to the start of the sequence.  If the
     * sequence is empty, the sequence has no current element.
     */
    public void start()
    {
        if (!isEmpty()) {
            currentIndex = 0;
        }
    }

    
    /**
     * Reduce the current capacity to its actual size, so that it has
     * capacity to store only the elements currently stored.
     */
    public void trimToSize()
    {
        if (this.getCapacity() != this.size()){
            capacity = this.size();
        }
    }
    
    
    /**
     * Produce a string representation of this sequence.  The current
     * location is indicated by a >.  For example, a sequence with "A"
     * followed by "B", where "B" is the current element, and the
     * capacity is 5, would print as:
     * 
     *    {A, >B} (capacity = 5)
     * 
     * The string you create should be formatted like the above example,
     * with a comma following each element, no comma following the
     * last element, and all on a single line.  An empty sequence
     * should give back "{}" followed by its capacity.
     * 
     * @return a string representation of this sequence.
     */
    public String toString() 
    {
        String ans = "{";
        for (int i = 0; i < this.size(); i++){
            if (i == currentIndex && i == 0){
                ans = ans + ">" + contents.search(i);
            }
            else if(i == currentIndex) {
                ans = ans + ", >" + contents.search(i);

            }
            else if (i == 0){
                ans += contents.search(i);
            }
            else{
                ans = ans + ", " + contents.search(i);
            }
        }

        ans = ans + "}" + " (capacity = " + this.getCapacity() + ")";
        return ans;
    }
    
    /**
     * Checks whether another sequence is equal to this one.  To be
     * considered equal, the other sequence must have the same size
     * as this sequence, have the same elements, in the same
     * order, and with the same element marked
     * current.  The capacity can differ.
     * 
     * Postcondition: NO SIDE EFFECTS!  this sequence and the
     * other sequence should remain unchanged, including the
     * current element.
     * 
     * @param other the other Sequence with which to compare
     * @return true iff the other sequence is equal to this one.
     */
    public boolean equals(Sequence other) 
    {
        return this.currentIndex == other.currentIndex && this.contents.equals(other.contents);
    }
    
    
    /**
     * 
     * @return true if Sequence empty, else false
     */
    public boolean isEmpty()
    {
        return this.size() == 0;
    }
    
    
    /**
     *  empty the sequence.  There should be no current element.
     */
    public void clear()
    {
        contents.clear();
        currentIndex = this.size();
    }

}