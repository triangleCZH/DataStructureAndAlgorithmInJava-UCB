public class DLList {
    DLNode sentinel;
    int size;

    public class DLNode {
        Object item;
        DLNode prev, next;

        public DLNode(Object item, DLNode prev, DLNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * Construct a new DLList with a sentinel that points to itself.
     */
    public DLList() {
        sentinel = new DLNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     * Insert into the end of this list
     * @param o Object to insert
     */
    public void insertBack(Object o) {
        DLNode n = new DLNode(o, sentinel.prev, sentinel);
        n.next.prev = n;
        n.prev.next = n;
        size++;
    }


    /**
     * Get the value at position pos. If the position does not exist, return null (the item of
     * the sentinel).
     * @param position to get from
     * @return the Object at the position in the list.
     */
    public Object get(int position) {
        DLNode curr = sentinel.next;
        while (position > 0 && curr != sentinel) {
            curr = curr.next;
            position--;
        }
        return curr.item;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("DLList(");
        DLNode curr = sentinel.next;
        while (curr != sentinel) {
            s.append(curr.item.toString());
            if (curr.next != sentinel) s.append(", ");
            curr = curr.next;
        }
        s.append(')');
        return s.toString();
    }

    /* Fill these in! */

    /**
     * Insert a new node into the DLList.
     * @param o Object to insert
     * @param position position to insert into. If position exceeds the size of the list, insert into
     *            the end of the list.
     */
    public void insert(Object o, int position) {
<<<<<<< HEAD
        DLNode temp1 = sentinel.next;

        if (position >= this.size) {
            this.insertBack(o);

        }
        else {
            while (--position != 0) {
                temp1 = temp1.next;
            }

            DLNode temp2 = temp1.next;
            DLNode n = new DLNode(o, sentinel.prev, sentinel);
            n.next = temp2;
            temp2.prev = n;
            temp1.next = n;
            n.prev = temp1;
            this.size++;
        }

    }


=======
        // fill me in
    }

>>>>>>> c537325ea17b0e1bb98884ce2ea3a802b49b2a0b
    /**
     * Insert into the front of this list. You should can do this with a single call to insert().
     * @param o Object to insert
     */
    public void insertFront(Object o) {
<<<<<<< HEAD
        insert(o,0);
=======
        // fill me in
>>>>>>> c537325ea17b0e1bb98884ce2ea3a802b49b2a0b
    }

    /**
     * Remove all copies of Object o in this list
     * @param o Object to remove
     */
    public void remove(Object o) {
<<<<<<< HEAD
        DLNode curr = sentinel.next;
        while (curr != sentinel.prev) {
            if (curr.next.item == o){
                this.size--;
                DLNode del=curr.next;
                DLNode after=del.next;
                curr.next=after;
                after.prev=curr;
                del.next=null;
                del.prev=null;

            }
            curr = curr.next;

        }
=======
        // fill me in
>>>>>>> c537325ea17b0e1bb98884ce2ea3a802b49b2a0b
    }

    /**
     * Remove a DLNode from this list. Does not error-check to make sure that the node actually
     * belongs to this list.
     * @param n DLNode to remove
     */
    public void remove(DLNode n) {
<<<<<<< HEAD
        DLNode temp1 = sentinel.next;
        int pos = this.size-1;
        while(temp1 !=sentinel && temp1 != n ) {
            temp1 = temp1.next;
        }

        if(temp1 == n) {
            this.size--;
            DLNode after = n.next;
            DLNode prev= n.prev;
            prev.next = after;
            after.prev = prev;
            n.prev = null;
            n.next = null;
        }

        }



=======
        // fill me in
    }
>>>>>>> c537325ea17b0e1bb98884ce2ea3a802b49b2a0b


    /**
     * Duplicate each node in this linked list destructively.
     */
    public void doubleInPlace() {
<<<<<<< HEAD
        int pos=0;
        DLNode temp1 = sentinel.next;

        while(temp1 !=sentinel){
            this.insert(temp1.item,pos+1);
            temp1=temp1.next.next;
            pos += 2;
        }
=======
        // fill me in
>>>>>>> c537325ea17b0e1bb98884ce2ea3a802b49b2a0b
    }

    /**
     * Reverse the order of this list destructively.
     */
    public void reverse() {
<<<<<<< HEAD
        DLNode curr = sentinel.next;

        DLNode after ;
        while(curr !=sentinel){
            after=curr.next;
            curr.next=curr.prev;
            curr.prev=after;
            curr=curr.prev;
        }
=======
        // fill me in
>>>>>>> c537325ea17b0e1bb98884ce2ea3a802b49b2a0b
    }

    public static void main(String[] args) {
        // you can add some quick tests here if you would like
    }
}
