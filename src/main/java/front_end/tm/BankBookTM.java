package front_end.tm;

public class BankBookTM {
    private String date;
    private int number;
    private int cheque;
    private String description;
    private String credit;
    private String debit;
    private int balance;
    private String ledger;

    public BankBookTM() {
    }

    public BankBookTM(String date, int number, int cheque, String description, String credit, String debit, int balance, String ledger) {
        this.date = date;
        this.number = number;
        this.cheque = cheque;
        this.description = description;
        this.credit = credit;
        this.debit = debit;
        this.balance = balance;
        this.ledger = ledger;
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

    public int getCheque() {
        return cheque;
    }

    public void setCheque(int cheque) {
        this.cheque = cheque;
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

    public String getLedger() {
        return ledger;
    }

    public void setLedger(String ledger) {
        this.ledger = ledger;
    }
}
