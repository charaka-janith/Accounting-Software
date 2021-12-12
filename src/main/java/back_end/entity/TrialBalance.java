package back_end.entity;

public class TrialBalance {
    private int ledger;
    private String description;
    private int amount;

    public TrialBalance() {
    }

    public TrialBalance(int ledger, String description, int amount) {
        this.ledger = ledger;
        this.description = description;
        this.amount = amount;
    }

    public int getLedger() {
        return ledger;
    }

    public void setLedger(int ledger) {
        this.ledger = ledger;
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
}
