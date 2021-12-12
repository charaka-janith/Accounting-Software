package back_end.dto;

public class TrialBalanceDTO {
    private int ledger;
    private String description;
    private int amount;

    public TrialBalanceDTO() {
    }

    public TrialBalanceDTO(int ledger, String description, int amount) {
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
