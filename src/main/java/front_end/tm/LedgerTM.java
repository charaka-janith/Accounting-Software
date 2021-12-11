package front_end.tm;

public class LedgerTM {
    private String date;
    private int number;
    private String description;
    private String credit;
    private String debit;
    private int balance;

    public LedgerTM() {
    }

    public LedgerTM(String date, int number, String description, String credit, String debit, int balance) {
        this.date = date;
        this.number = number;
        this.description = description;
        this.credit = credit;
        this.debit = debit;
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
