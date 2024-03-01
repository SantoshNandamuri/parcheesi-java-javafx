package parcheesi;

public class Pawn {

    /* **************************************************************** */
    /* Class Scope Variables                                            */
    /* **************************************************************** */
    private String color;
    private int row;
    private int column;
    private int position;
    private int distanceToEnd;

    /* **************************************************************** */
    /* Constructors                                                     */
    /* **************************************************************** */
    public Pawn() {

    }

    /* **************************************************************** */
    /* Getters                                                          */
    /* **************************************************************** */

    public String getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPosition() {
        return position;
    }

    public int getDistanceToEnd() {
        return distanceToEnd;
    }

    /* **************************************************************** */
    /* Setters                                                          */
    /* **************************************************************** */
    public void setColor(String color) {
        this.color = color;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setDistanceToEnd(int distanceToEnd) {
        this.distanceToEnd = distanceToEnd;
    }
}
