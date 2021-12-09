package back_end.dto;

public class LedgerDTO {
    private int id;
    private String name;

    public LedgerDTO() {
    }

    public LedgerDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
