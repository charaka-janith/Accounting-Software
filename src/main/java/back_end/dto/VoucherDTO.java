package back_end.dto;

import java.time.LocalDate;

public class VoucherDTO {
    private int number;
    private int ledger;
    private LocalDate date;
    private String description;
    private int amount;

    public VoucherDTO() {}

    public VoucherDTO(int number, int ledger, LocalDate date, String description, int amount) {
        this.number = number;
        this.ledger = ledger;
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getLedger() {
        return ledger;
    }

    public void setLedger(int ledger) {
        this.ledger = ledger;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
