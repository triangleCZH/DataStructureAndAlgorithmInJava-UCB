/**
 * Created by cs61bl-bk on 7/18/16.
 */

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *  A simple mapping from string names to string values backed by an array.
 *  Supports only A-Z for the first character of the key name. Values can be
 *  any valid string.
 *
 *  @author You
 */
public class HashMap<K,V> implements Map61BL<K,V> { // FIXME: 7/18/16  I need to know for such a linkedlist what is the initial values
    public Entry<K,V>[] map;

    public int capacity = 16;

    public int elements = 0;

    public float loadFactor = 0.7f;

    public void clear() {
        for (int i = 0;i < capacity;i++) {
            map[i] = null;
        }
        elements = 0;
    }

    /** Create a new hash map with default parameters. */
    public HashMap() {
        //this.map = (Entry<K,V>[]) new Entry[capacity];
        this.map = (Entry<K,V>[]) new Entry[capacity];
    }

    /** Create a new hash map with an array of size INITIALCAPACITY. */
    public HashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.map = (Entry<K,V>[]) new Entry[capacity];
    }

    /** Create a new hash map with INITIALCAPACITY and LOADFACTOR. */
    public HashMap(int initialCapacity, float loadFactor) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.map = (Entry<K,V>[]) new Entry[capacity];
    }

    /** Return the capacity of this hash table's internal array. */
    public int capacity() {
        return this.capacity;
    }


    public int size() {
        return elements;
    }

    /**
     * Returns true if the map contains the KEY.
     */
    public boolean containsKey(K key) {
        /**return this.map[key.charAt(0) - 'A']._key.equals(key) && isValidName(key);
         * this is for the first exercise*/
            Entry temp = this.map[(key.hashCode() & 0x7FFFFFFF) % capacity];
            while (temp != null) {
                if (temp._key.equals(key)) {
                    return true;
                }
                temp = temp._next;
            }
        return false;
    }

    /**
     * Returns the value for the specified KEY.
     */
    public V get(K key) throws IllegalArgumentException {
            Entry<K,V> temp = this.map[(key.hashCode() & 0x7FFFFFFF) % capacity];
            while (temp != null) {
                if (temp._key.equals(key)) {
                    return temp._value;
                }
                temp = temp._next;
            }
        return null;
    }


    /**
     * Put a (KEY, VALUE) pair into this map.
     */
    public void put(K key, V value) {
        if (key != null && value != null) {
            //System.out.println(elements);
            //System.out.println(capacity);
            //System.out.println(loadFactor);
            double loadCompare = (elements + 1) * 1.0 / capacity;
            //System.out.println(loadCompare);
            //System.out.println(loadFactor);
            if (loadCompare > loadFactor) {
                //System.out.println("testing");
                map = resize();
            }
            Entry<K,V> temp = this.map[(key.hashCode() & 0x7FFFFFFF) % capacity];
            if (temp == null) {
                this.map[(key.hashCode() & 0x7FFFFFFF) % capacity] = new Entry(key, value, null);
                //System.out.println(map[(key.hashCode() & 0x7FFFFFFF) % capacity]._key);
            } else {
                if (temp._key == key) {
                    temp._value = value;
                    return;
                } else {
                    while (temp._next != null) {
                        temp = temp._next;
                        if (temp._key == key) {
                            temp._value = value;
                            return;
                        }
                    }
                    temp._next = new Entry(key, value, null);
                }
                }
            elements++;
        }
    }

    public Entry<K,V>[] resize() {
        capacity *= 2;
        Entry<K,V>[] temp = new Entry[capacity];
        for (int i = 0; i < capacity / 2; i++) { // this loop for every array pointer
            Entry temp1 = map[i];
            while (temp1 != null) { // this loop for every linkedlist
                int hashCode = (temp1._key.hashCode() & 0x7FFFFFFF) % capacity;
                Entry temp2 = temp[hashCode];
                while (temp2 != null) { // this loop for entering the end of the new linkedlist
                    temp2 = temp2._next;
                }
                temp2 = new Entry(temp1._key, temp1._value, null);
                temp1 = temp1._next;
            }
        }
        return temp;
    }

    /**
     * Remove a single entry, KEY, from this table and returns true if successful.
     */
    @Override
    public boolean remove(K key, V value) {
        if (!containsKey(key)) {
            return false;
        }
        Entry temp = this.map[(key.hashCode() & 0x7FFFFFFF) % capacity];
        if (temp._key.equals(key)) {
            return temp._value.equals(value);
        } else {
            while (temp._next != null) {
                temp = temp._next;
                if (temp._key.equals(key)) {
                    return temp._value.equals(value);
                }
            }

        }
        return false;
    }

    public V remove(K key) {
        int hashCode = (key.hashCode() & 0x7FFFFFFF) % capacity;
        Entry<K,V> temp = this.map[hashCode];
        if (temp._key.equals(key) && remove(key,temp._value)) {
                V new1 = temp._value;
            this.map[hashCode] = null;
            elements--;
            System.out.print("lala");
                return new1;
        } else {
            while (temp._next != null) {
                if (temp._next._key.equals(key) && remove(key, temp._next._value)) {
                    V new1 = temp._next._value;
                    temp._next = temp._next._next;
                    elements--;

                    return new1;
                }
                temp = temp._next;
            }

        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new MapIterator();
    }

    public class MapIterator implements Iterator { // just empty
        int nextIndexToReturn;
        int currentCode = 0;
        int currentNum = 0;
        Entry<K,V> temp;
        public MapIterator() {
            nextIndexToReturn = 0;
            temp = null;
            currentCode = 0;
            currentNum = 0;

        }

        public K next() {

            while (map[currentCode] == null) {
                    currentCode++;
                }
                temp = map[currentCode];
            System.out.println(currentCode +"before finding the temp");
                for (int i = 0; i < currentNum; i++) {
                    temp = temp._next;
                }
            if (temp == null) {
                currentCode++;
                while (map[currentCode] == null) {
                    currentCode++;
                }
                System.out.println(currentCode+"if temp is null. then~");
            }
                if (temp._next == null) {
                    currentCode++;
                    while (map[currentCode] == null) {
                            currentCode++;

                        }
                    System.out.println(currentCode+"this is where we reach");
                        currentNum = 0;
                        nextIndexToReturn++;
                        return map[currentCode]._key;
                }
                currentNum++;
                nextIndexToReturn++;
                return temp._next._key;
        }

        //@Override
        public boolean hasNext(){
            return nextIndexToReturn < elements;
        }

    }

    /**
     * A wrapper class for holding each (KEY, VALUE) pair.
     */
    public static class Entry<K,V> {

        /**
         * The key used for lookup.
         */
        private K _key;
        /**
         * The associated value.
         */
        private V _value;

        private Entry<K,V> _next;

        /**
         * Create a new (KEY, VALUE) pair.
         */
        public Entry(K key, V value, Entry<K,V> next) {
            _key = key;
            _value = value;
            _next = next;
        }

        /**
         * Returns true if this key matches with the OTHER's key.
         */
        public boolean keyEquals(Entry other) {
            return _key.equals(other._key);
        }

        /**
         * Returns true if both the KEY and the VALUE match.
         */
        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry &&
                    _key.equals(((Entry) other)._key) &&
                    _value.equals(((Entry) other)._value));
        }

    }

    /**
     * Returns true if the given KEY is a valid name that starts with A-Z.
     */
    /*private static boolean isValidName(K key) {
        return 'A' <= key.charAt(0) && key.charAt(0) <= 'Z';
    }*/
}

