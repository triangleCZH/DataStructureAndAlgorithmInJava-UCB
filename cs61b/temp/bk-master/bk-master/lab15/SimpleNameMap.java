/**
 *  A simple mapping from string names to string values backed by an array.
 *  Supports only A-Z for the first character of the key name. Values can be
 *  any valid string.
 *
 *  @author You
 */
public class SimpleNameMap { // FIXME: 7/18/16  I need to know for such a linkedlist what is the initial values
    public  Entry[] map;

    public int array_size = 10;

    public int elements;

    public final double loadFactor = 0.7;

    public SimpleNameMap() {
       this.map = new Entry[array_size];
        elements = 0;
    }

    /** Returns true if the map contains the KEY. */
    boolean containsKey(String key) {
        /**return this.map[key.charAt(0) - 'A']._key.equals(key) && isValidName(key);
         * this is for the first exercise*/
        if (isValidName(key)) {
            Entry temp = this.map[(key.hashCode() & 0x7FFFFFFF) % array_size];
            while (temp != null) {
                if (temp._key.equals(key)) {
                    return true;
                }
                temp = temp._next;
            }
        }
        return false;
    }

    /** Returns the value for the specified KEY. */
    String get(String key) throws IllegalArgumentException {
        if (isValidName(key)) {
            Entry temp = this.map[(key.hashCode() & 0x7FFFFFFF) % array_size];
            while (temp != null) {
                if (temp._key.equals(key)) {
                    return temp._value;
                }
                temp = temp._next;
            }
        } else {
            throw new IllegalArgumentException("not found");
        }
        return null;
    }


    /** Put a (KEY, VALUE) pair into this map. */
    void put(String key, String value) {
        if (key != null && value != null) {
            if (elements / array_size == loadFactor) {
                map = resize();
            }
            if (containsKey(key)) {
                return;
            }
            Entry temp = this.map[(key.hashCode() & 0x7FFFFFFF) % array_size];
            if (temp == null) {
                temp = new Entry(key,value,null);
            } else {
                while(temp._next != null) {
                    temp = temp._next;
                }
                temp._next = new Entry(key,value,null);
            }
            elements++;
        }
    }

    public Entry[] resize() {
        array_size *= 2;
        Entry[] temp = new Entry[array_size];
        for (int i = 0;i < array_size / 2;i++) { // this loop for every array pointer
            Entry temp1 = map[i];
            while (temp1 != null) { // this loop for every linkedlist
                int hashCode = (temp1._key.hashCode() & 0x7FFFFFFF) % array_size;
                   Entry temp2 = temp[hashCode];
                    while (temp2 != null) { // this loop for entering the end of the new linkedlist
                        temp2 = temp2._next;
                    }
                    temp2 = new Entry(temp1._key,temp1._value,null);
                temp1 = temp1._next;
            }
        }
        return temp;
    }

    /** Remove a single entry, KEY, from this table and returns true if successful. */
    boolean remove(String key) { // FIXME: 7/18/16  I think this should be boolean, not String
        if (!containsKey(key)) {
            return false;
        }
        Entry temp = this.map[(key.hashCode() & 0x7FFFFFFF) % array_size];
        if (temp._key.equals(key)) {
            temp = null;
        } else {
            while(temp._next._key.equals(key)) {
                temp = temp._next;
            }
            temp._next = temp._next._next;
        }
        return true;
    }

    /** A wrapper class for holding each (KEY, VALUE) pair. */
    public static class Entry {

        /** The key used for lookup. */
        private String _key;
        /** The associated value. */
        private String _value;

        private Entry _next;

        /** Create a new (KEY, VALUE) pair. */
        public Entry(String key, String value, Entry next) {
            _key = key;
            _value = value;
            _next = next;
        }

        /** Returns true if this key matches with the OTHER's key. */
        public boolean keyEquals(Entry other) {
            return _key.equals(other._key);
        }

        /** Returns true if both the KEY and the VALUE match. */
        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry &&
                    _key.equals(((Entry) other)._key) &&
                    _value.equals(((Entry) other)._value));
        }

    }

    /** Returns true if the given KEY is a valid name that starts with A-Z. */
    private static boolean isValidName(String key) {
        return 'A' <= key.charAt(0) && key.charAt(0) <= 'Z';
    }

}