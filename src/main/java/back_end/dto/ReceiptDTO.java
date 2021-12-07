package back_end.dto;

import java.time.LocalDate;

public class ReceiptDTO {
    private int number;
    private LocalDate date;
    private String description;
    private int amount;

    public ReceiptDTO() {
    }

    public ReceiptDTO(int number, LocalDate date, String description, int amount) {
        this.number = number;
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
