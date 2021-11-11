package back_end.entity;

public class Colors {
    private String color;
    private String colorCode;

    public Colors() {
    }

    public Colors(String color, String colorCode) {
        this.color = color;
        this.colorCode = colorCode;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
}
