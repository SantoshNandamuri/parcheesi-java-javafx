package parcheesi;

import java.util.ArrayList;
//import javafx.scene.paint.Color;

public class Player {

    /* **************************************************************** */
    /* Class Scope Variables                                            */
    /* **************************************************************** */
    private String color;
    private String status = "offline";
    private String turn = "off";
    private ArrayList<Pawn> pawns = new ArrayList<>();
    private ArrayList<Pawn> activePawns = new ArrayList<>();
    private int numberOfActivePawns = 0;

    /* **************************************************************** */
    /* Constructors                                                     */
    /* **************************************************************** */

    public Player() {

    }

    /* **************************************************************** */
    /* Getters                                                          */
    /* **************************************************************** */
    public String getColor() {
        return color;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Pawn> getPawns() {
        return pawns;
    }

    public String getTurn() {
        return turn;
    }

    public ArrayList<Pawn> getActivePawns() {
        return activePawns;
    }

    public int getNumberOfActivePawns() {
        return numberOfActivePawns;
    }

    /* **************************************************************** */
    /* Setters                                                          */
    /* **************************************************************** */
    public void setColor(String color) {
        this.color = color;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPawns(int numberOfPawns) {
        int i;
        for (i=0; i<numberOfPawns; i++) {
            Pawn pawn = new Pawn();
            pawn.setColor(this.getColor());
            pawn.setPosition(-6);
            this.pawns.add(pawn);
        }
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public void setNumberOfActivePawns(int numberOfActivePawns) {
        this.numberOfActivePawns = numberOfActivePawns;
    }
}
