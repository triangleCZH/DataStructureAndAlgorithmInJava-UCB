public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }

    @Override
    public Date nextDate() {
        int yy = super.year();
        int mm = super.month();
        int dd = super.dayOfMonth();
        if ((mm >= 1) && (mm <=12)) {
            if (dd == 30) {
                mm++;
                dd = 1;
                System.out.println("it is now thirty");
            } else {
                dd++;
                System.out.println("normal day");
            }
        } else {
            if (dd < 5) {
                dd++;
                System.out.println("it is 13 some");
            } else {
                yy++;
                mm = 1;
                dd = 1;
                System.out.println("it should be 1 1next");
            }
        }
        return new FrenchRevolutionaryDate(yy,mm,dd);
    }

}
