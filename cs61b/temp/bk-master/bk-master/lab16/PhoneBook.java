import java.util.*;

public class PhoneBook {
    // TODO Add any instance variables necessary
    HashMap<Person,PhoneNumber> hm;
    private int num = 0; // how many people in the book

    /*
     * Adds a person with this name to the phone book and associates 
     * with the given PhoneNumber.
     */
    public void addEntry(Person personToAdd, PhoneNumber numberToAdd){
    	// TODO Add your own code
        hm.put(personToAdd,numberToAdd);
        num++;
    }

    public PhoneBook() {
        hm = new HashMap<>();
    }

    /*
     * Access an entry in the phone book. 
     */
    public PhoneNumber getNumber(Person personToLookup){
    	// TODO Add your own code
        if (hm.containsKey(personToLookup)) {
            return hm.get(personToLookup);
        }
    	return null;
    }

    /*public void main(String[] args) {
        PhoneBook a = new PhoneBook();
        Person person1 = new Person("Sally");
        PhoneNumber num1 = new PhoneNumber("5105551234");
        a.addEntry(person1,num1);
        System.out.print(a.getNumber(person1));
        PhoneNumber num2 = new PhoneNumber("5105551233");
        a.addEntry(person1,num2);
        System.out.print(a.getNumber(person1));
    }

    public class Person {

        private String myName;

        public Person(String name) {
            this.myName = name;
        }

        // return a String representation of the Person object
        public String toString() {
            return myName;
        }

        // Change the name of the person
        public void changeName(String newName) {
            this.myName = newName;
        }

        // TODO add additional methods

    }

    public class PhoneNumber {

        private String number;

        /*
         * Constructor takes a String that represents a phone number. The String
         * should contain numbers only and should be of length 9.
         
        public PhoneNumber(String num) {
            if (num.length() != 10) {
                throw new IllegalArgumentException("phone number was not 10 numbers");
            }
            this.number = num;
        }

        public void changeNumber(String num){
            if (num.length() != 10) {
                throw new IllegalArgumentException("phone number was not 10 numbers");
            }
            this.number = num;
        }

        @Override
        public String toString() {
            return number;
        }

        // TODO Add additional methods?

    }*/


}
