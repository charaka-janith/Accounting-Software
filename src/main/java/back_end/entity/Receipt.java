package back_end.entity;

public class Receipt {
    private int number;
    private String date;
    private String description;
    private int amount;

    public Receipt() {
    }

    public Receipt(int number, String date, String description, int amount) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
