public class FixedSizeList implements SimpleList {

    // instance variables
    protected int[] values;   // list elements
    int count;                // number of array cells used by list

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public FixedSizeList(int capacity) {
        values = new int[capacity];
        //System.out.println("make new list with capacity"+capacity);
        //this.count = 0;
    }

    public FixedSizeList(){//fixme

    }

    // This method should return the number of items 
    // contained in values
    public int size() {
        //System.out.println("current size is" + count);
        return count;
    }

    // This method should return true if the list
    // is empty, otherwise return false
    public boolean isEmpty() {
        return count == 0;
    }

    // Add the argument to the list by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int k) {
        if (count >= values.length)//fixme
            throw new ListException("wrong input");
        values[count] = k;
        count++;
        //System.out.println("add " + k + "to the list and now the count is" + count);
    }

    // This method removes k from the list, if it is present.
    // If k appears multiple times, it removes the first occurence of k
    public void remove(int k) {
        int i;
        for (i = 0;i < count ;i++) {
            if (values[i] == k) {
                for (int j = 0;j < count - 1;j++) {
                    values[j] = values[j + 1];
                }
                values[count - 1] = 0;//fixme
                count--;
                break;

            }

        }

    }

    // This method returns whether or not the collection contains k
    public boolean contains(int k) {
        for (int i = 0;i < count;i++) {
            if (values[i] == k) {
                return true;
                //System.out.println(k+"is contained in the list");
            }
        }
        return false;
    }

    // Returns the integer stored at the i-th index in the List
    @Override
    public int get(int i) {
        if ( i < 0)  {
            throw new ListException("i-th is more than we have");
        }
        //System.out.println("get the value of "+i+"-th");
        if (i >= count)
            return 0;
        else //fixme
        return values[i];
    }

    // Insert k into the sequence at position i,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. count < values.length
    // Also, i is between 0 and count, inclusive.
    @Override
    public void add(int i, int k) {
        for (int j = count;j > i;j--) {
            values[j] = values[j - 1];
        }
        values[i] = k;
        count++;
        //System.out.println("add"+k+"into position"+i);
    }


    // Removes the integer in the i-th position in the list,
    // note now this is different from the one-argument remove
    @Override
    public void removeIndex(int i) {
        if (i >= count || i < 0) {
            throw new ListException("this i-th is out of bound");
        }
        for(int j = i;j < count - 1;j++) {
            values[j] = values[j + 1];
        }
        values[count] = 0;
        count--;
        //System.out.println(i+"-th is removed");
    }




}