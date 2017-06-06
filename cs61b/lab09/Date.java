public abstract class Date {

    public abstract int dayOfYear();
    private int dayOfMonth;
    private int month;
    private int year;

    public Date(int year, int month, int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
    }

    public int dayOfMonth() {
        return dayOfMonth;
    }

    public int month() {
        return month;
    }

    public int year() {
        return year;
    }

    /*public int setdayOfMonth(int n) {
        return n;
    }

    public int setmonth(int n) {
        return n;
    }

    public int setyear(int n) {
        return n;
    }*/



    public String toString() {
        return "" + dayOfMonth + "/" + month + "/" + year;
    }

    public abstract Date nextDate();

    /*public boolean leapYear(int year) {
        if(year % 100 == 0 ) {
            if(year % 400 == 0) {
                return true;
            }
        } else {
            if(year % 4 == 0) {
                return true;
            }
        }
        return false;
    }*/

}

