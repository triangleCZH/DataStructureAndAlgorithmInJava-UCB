public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        int rtnValue = 0;
        for (int m = 0; m < month() - 1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth();
    }

    @Override
    public GregorianDate nextDate() {
        int yy = super.year();
        int mm = super.month();
        int dd = super.dayOfMonth();
        if ((mm == 12) && (dd == monthLengths[mm - 1])) {
            yy++;
            dd = 1;
            mm = 1;
        } else if (dd < monthLengths[mm - 1]) {
            dd++;
        } else {
            dd = 1;
            mm++;
        }
        return new GregorianDate(yy,mm,dd);
    }

}
