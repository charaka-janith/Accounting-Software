package front_end.tm;

public class TrialBalanceTM {
    private String ledger;
    private String description;
    private String credit;
    private String debit;

    public TrialBalanceTM() {
    }

    public TrialBalanceTM(String ledger, String description, String credit, String debit) {
        this.ledger = ledger;
        this.description = description;
        this.credit = credit;
        this.debit = debit;
    }

    public String getLedger() {
        return ledger;
    }

    public void setLedger(String ledger) {
        this.ledger = ledger;
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
}
