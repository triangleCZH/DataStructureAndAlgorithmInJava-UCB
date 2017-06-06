/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {
        private Account parentAccount;
	private int balance;

	/** Initialize an account with the given BALANCE. */
	public Account(int balance) {
		this.balance = balance;
                this.parentAccount=null;
	}

        public Account(int balance , Account parentAccount){
                this.balance = balance;
                this.parentAccount=parentAccount;
        }
	/** Return the number of dollars in the account. */
	public int getBalance() {
		return this.balance;
	}

	/** Deposits AMOUNT into the current account. */
	public void deposit(int amount) {
		if (amount < 0) {
			System.out.println("Cannot deposit negative amount.");
		} else {
			this.balance = this.balance + amount;
		}
	}

	/** Subtract AMOUNT from the account if possible. If subtracting AMOUNT
	 *	would leave a negative balance, print an error message and leave the
	 *	balance unchanged.
	 */
	public boolean withdraw(int amount) {
		if (amount < 0) {
			System.out.println("Cannot withdraw negative amount.");
                         return false;
		} 
		else if (this.balance >= amount) {
                        this.balance-=amount;
                        System.out.println("Insufficient funds");
		         return true;
		} 
		else {if (parentAccount==null){
                        System.out.println("Insufficient funds");
                        return false;}
                        else
                    {if(this.balance+parentAccount.balance>=amount)
			            {parentAccount.balance-=amount-this.balance;
                        this.balance=0;
                        return true;}
			             else {
                         System.out.println("Insufficient funds");
                         return false;
                         }}
               
		}
	}

	/** Merge account OTHER into this account by removing all money from OTHER
	 *	and depositing it into this account.
     */
    
    public void merge(Account otherAccount) {
          this.balance+=otherAccount.balance ;
          otherAccount.balance=0; 
    }
    public static void main(String[] args)
{   Account p = new Account(500);
    Account a = new Account(100,p);
    a.withdraw(500);
    System.out.println(a.balance + "+" + p.balance);
    
}

}
