package back_end.dto;

public class ColorDTO {
    private String color;
    private String code;

    public ColorDTO() {
    }

    public ColorDTO(String color, String code) {
        this.color = color;
        this.code = code;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
