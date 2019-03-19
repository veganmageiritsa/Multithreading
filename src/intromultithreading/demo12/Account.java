package intromultithreading.demo12;

public class Account {

    private int balance=10000;

    public void deposit(int amount){
        balance+=amount;
    }

    public void withdraw(int amount){
        balance-=amount;
    }

    public static void tranfer(Account a1, Account a2, int amount){
        a1.withdraw(amount);
        a2.deposit(amount);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
