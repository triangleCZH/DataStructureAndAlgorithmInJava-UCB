public class JugContents {

    public int jugs[];	// contents of the three jugs.
    
    public JugContents (int amt0, int amt1, int amt2) {
        jugs = new int [3];
        jugs[0] = amt0;
        jugs[1] = amt1;
        jugs[2] = amt2;
    }
    
    public JugContents (JugContents b) {
        jugs = new int [3];
        jugs[0] = b.jugs[0];
        jugs[1] = b.jugs[1];
        jugs[2] = b.jugs[2];
    }

    public JugContents () {
        jugs = null;
    }
    
    public String toString() {
        return "Configuration = (" + jugs[0] + "," 
            + jugs[1] + "," + jugs[2] + ")";
    }
    
    // YOUR CODE HERE

    /*public JugContents JugTest(int amt1, int amt2) {
        int[] b = new int [5];
        for (int i = 0;i < 3;i++) {
            b[i] = this.jugs[i];
        }
        b[3] = amt1;
        b[4] = amt2;
        JugContents c = new JugContents();
        c.jugs = b;
        return c;
    }*/

    @Override
    public int hashCode() {
        return 100 * jugs[2] + 10 * jugs[1] + jugs[0];
    }

    @Override
    public boolean equals(Object obj) {
        JugContents a = (JugContents)obj;
        return a.jugs[0] == jugs[0] && a.jugs[1] == jugs[1] && a.jugs[2] == jugs[2];
    }
}
