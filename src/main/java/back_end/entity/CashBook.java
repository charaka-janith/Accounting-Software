package back_end.entity;

public class CashBook {
    private String date;
    private int number;
    private String description;
    private int amount;
    private String ledger;
    private String table;

    public CashBook() {
    }

    public CashBook(String date, int number, String description, int amount, String ledger, String table) {
        this.date = date;
        this.number = number;
        this.description = description;
        this.amount = amount;
        this.ledger = ledger;
        this.table = table;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getLedger() {
        return ledger;
    }

    public void setLedger(String ledger) {
        this.ledger = ledger;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
