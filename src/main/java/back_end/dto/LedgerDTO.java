package back_end.dto;

import java.time.LocalDate;

public class LedgerDTO {
    private LocalDate date;
    private int number;
    private String description;
    private int amount;
    private String table;

    public LedgerDTO() {
    }

    public LedgerDTO(LocalDate date, int number, String description, int amount, String table) {
        this.date = date;
        this.number = number;
        this.description = description;
        this.amount = amount;
        this.table = table;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
