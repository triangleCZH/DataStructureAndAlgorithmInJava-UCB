/** A data structure to represent a Linked List of Integers.
 * Each IntList represents one node in the overall Linked List.
 * Encapsulated version.
 */
public class IntList {
    /** The head of the list is the first node in the list. If the list is empty, head is null **/
    private IntListNode head;
    private int size;

    /** IntListNode is a nested class. It can be instantiated when associated with an instance of
     *  IntList.
     *  **/
    public class IntListNode {
        int item;
        IntListNode next;

        public IntListNode(int item, IntListNode next) {
            this.item = item;
            this.next = next;
        }
        public int getItem(){
            return this.item;
        }
    }

    public int getSize() {
        return size;
    }

    public IntList() {}

    public IntList(int[] initial) {
        for (int i = initial.length - 1; i >= 0; i--) {
            head = new IntListNode(initial[i], head);
        }
        size = initial.length;
    }

    /**
     * Get the value at position pos. If the position does not exist, throw an
     * IndexOutOfBoundsException.
     * @param position to get from
     * @return the int at the position in the list.
     */
    public int get(int position) {
        if (position >= size) throw new IndexOutOfBoundsException("Position larger than size of list.");
        IntListNode curr = head;
        while (position > 0) {
            curr = curr.next;
            position--;
        }
        return curr.item;
    }

    

    /* Fill in below! */

    /**
     * Insert a new node into the IntList.
     * @param x value to insert
     * @param position position to insert into. If position exceeds the size of the list, insert into
     *            the end of the list.
     */
    public void insert(int x, int position) {
       IntListNode temp = head;
        if(position >= size){
            if(temp == null){
                head = new IntListNode(x,null);
                size++;
            }else{
                while(temp.next != null){
                    temp = temp.next;
                }
                temp.next = new IntListNode(x,null);
                size++;
            }
        }else{
            for(int i=0;i<position - 1; i++){
                temp = temp.next;
            }
            IntListNode temp2 = temp.next;
            temp.next = new IntListNode(x,temp2);
            size++;
        }
    }
    /*IntListNode temp = head;
        if(position == 0 && temp.next != null)
        {IntListNode temp1 = new IntListNode(x,null);
            temp1.next = temp.next;
            temp.next = temp1;
        this.size++;}
        else if(temp.next == null ){
            IntListNode temp1 = new IntListNode(x,null);
            temp.next = temp1;
            this.size++;
        }
        else insert(x,position-1);}*/
    public static IntListNode mergeNode(IntListNode a,IntListNode b){
        if(a == null&&b == null)
            return null;
        else if(a == null)
            return b;
        else if(b == null)
            return a;
        else{
            if(a.getItem() < b.getItem()){
                a.next = mergeNode(a.next,b);
                return a;
            }else{
                b.next = mergeNode(a,b.next);
                return b;
            }
        }
    }
    /*public IntList copy(IntList l1){//
        IntList one = new IntList();
        if(l1 == null)
            return null;
        else{
            one.size = l1.size;
            int n = l1.get(0);
            IntListNode temp = l1.head;// goes with next for l1
            IntListNode head1 = new IntListNode(temp.item,null);//new head
            IntListNode head2 = head1; // go with next for one
            while(temp.next != null){
                temp = temp.next;
                //IntListNode temp1 = new IntListNode(temp.item,null);
                head2.next.item = temp.item; //= temp1;
                head2.next.next = null;
                head2 = head2.next;
            }
            one.head = head1;
            one.size = l1.size;
            return one;
        }
        
    }*/
    /*if(l1 == null)
            return null;
        else {IntList one = new IntList();
            l1.head.item= one.head.item;
            for(int i=0;i< l1.size-1;i++){
                one.head.insert(l1.get(i+1));
            }

            return one;}*/

    public void add(int item) { // TODO: 6/27/16

        IntListNode temp = this.head;
        for(int i=0;i< this.size-1;i++){
            temp = temp.next;
        }
        IntListNode temp1 = new IntListNode(item,null);
        temp.next = temp1;
        this.size++;
        // YOUR CODE HERE
    }
    /**
     * Merge two sorted IntLists a and b into one sorted IntList containing all of their elements.
     * @return a new IntList without modifying either parameter
     */
    public static IntList merge(IntList a, IntList b) {
       IntListNode tempa = a.head;
        IntListNode tempb = b.head;
        if(mergeNode(tempa,tempb)==tempa){
            a.size = a.getSize() + b.getSize();
            return a;
        }else{
            b.size = b.getSize() + a.getSize();
            return b;
        }

    }

    /* if(a.head == null||b.head != null)
       return copy(b);
        else if(b.head == null||a.head != null)
        return copy(a);
        else if(a ==null && b == null)
            return null;
        else {
            if(a.item >= b.item)
                IntList temp = new IntList(b.item,merge(a,b.next));
            else
               IntList temp = new IntList(a.item,merge(a.next,b));
               return temp;
        }*/
    /* if(a ==null && b == null)
            return null;
        else {
            IntList temp = new IntList();
            while (!(a == null && b == null)) {

                if (a == null){
                    temp = (a,b.next,temp);
                    else if(b == null)
                        temp = (a.next,b,temp);
                    }
                }

            }
        }
    public static IntList mergeSub(IntList a, IntList b, IntList c){
        if(a == null||b != null)
        {  IntList temp = new IntList(b.item,null);
            c.next = temp;
          return c;}
        else if(b == null||a != null)
        { IntList temp = new IntList(a.item,null);
           c.next = temp;
        return c;}
        else if(a ==null && b == null)
            return c;
            else {
            int small;
            if(a.item > b.item)
                small = b.item;
            else small = a.item;
            IntList temp = new IntList(small,null);
            c.next = temp;
            return c;
        }
    }*/

    /**
     * Reverse the current list recursively, using a helper method.
     */
    public void reverse() {
        head = reverseNode(head);
    }
    public IntListNode reverseNode(IntListNode a){
        if(a == null||a.next == null)
            return a;
        else{
            IntListNode remain = reverseNode(a.next);
            a.next.next = a;
            a.next = null;
            return remain;// FIXME: 6/29/16  this is hard to udnerstand, need some more time to figure it out
        }
    }

    /* if(head != null)
        { IntListNode temp = head;
        for(int i=0;i<size-1;i++){
            temp.item = get(size-1-i);
            temp = temp.next;
        }
        temp.item = get(0);
        }
        // Fill me in! */

    /**
     * Remove the node at position from this list.
     * @param position int representing the index of the node to remove. If greater than the size
     *                 of this list, throw an IndexOutOfBoundsException.
     */
    public void remove(int position) {
        if (position >= size) throw new IndexOutOfBoundsException();
        IntListNode curr = head;
        if(position == 0)
        {head = head.next;
            curr = null;}
        else if(position == size-1){
            while (position > 1) {
                curr = curr.next;
                position--;
            }
            curr.next = null;
        }
        else {
        while (position > 1) {
            curr = curr.next;
            position--;
        }
        IntListNode curr2 = curr;
            curr2 = curr2.next;
            curr.next = curr2.next;
            curr2 = null;
        }
        this.size--;
        // fill me in
    }
}
