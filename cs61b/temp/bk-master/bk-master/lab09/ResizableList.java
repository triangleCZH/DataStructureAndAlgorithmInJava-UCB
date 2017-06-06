/**
 * Created by USER on 2016/7/8.
 */
public class ResizableList extends FixedSizeList{

    public ResizableList () {
        super (1);
    }

    @Override
    public void add(int k) {
        if(count == values.length) {
            expand(values);
        }
        super.add(k);
    }

    @Override
    public void add(int i, int k) {
        if (count == values.length) {
            expand(values);
        }
        super.add(i,k);
    }

    public void expand(int[] value) {
        int[] value1 = new int[2*count];
        for(int i =0;i < count;i++) {
            value1[i] = value[i];
        }
        this.values = value1;
    }
}
