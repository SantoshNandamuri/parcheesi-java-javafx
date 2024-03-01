package parcheesi;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class ParcheesiController {

    Game game = new Game(2);

    @FXML
    private GridPane gridPane;

    @FXML
    private Label welcomeLabel, endLabel;

    @FXML
    private Button die_1, die_2, btPlay, btQuit;

    @FXML
    private TextField die_1_Value, die_2_Value;

    private ArrayList<Button> dice = new ArrayList<>();
    private ArrayList<TextField> dieValues = new ArrayList<>();

    private void createDieList() {
        dice.add(die_1);
        dice.add(die_2);
        dieValues.add(die_1_Value);
        dieValues.add(die_2_Value);
    }


    @FXML
    private Circle player_1_Pawn_1, player_1_Pawn_2, player_1_Pawn_3, player_1_Pawn_4,
            player_2_Pawn_1, player_2_Pawn_2, player_2_Pawn_3, player_2_Pawn_4;
    private ArrayList<Circle> player_1_Pawns = new ArrayList<>();
    private ArrayList<Circle> player_2_Pawns = new ArrayList<>();

    private void createPawnList() {
        player_1_Pawns.add(player_1_Pawn_1);
        player_1_Pawns.add(player_1_Pawn_2);
        player_1_Pawns.add(player_1_Pawn_3);
        player_1_Pawns.add(player_1_Pawn_4);
        player_2_Pawns.add(player_2_Pawn_1);
        player_2_Pawns.add(player_2_Pawn_2);
        player_2_Pawns.add(player_2_Pawn_3);
        player_2_Pawns.add(player_2_Pawn_4);
    }


    @FXML
    protected void onPlayButtonClick() {
        welcomeLabel.setVisible(false);
        gridPane.setDisable(false);
        die_1.setDisable(false);
        die_1_Value.setDisable(false);
        btPlay.setDisable(true);
        createPawnList();
        createDieList();
    }

    @FXML
    protected void onQuitButtonClick() {
        Platform.exit();
    }

    @FXML
    protected void disablePawns(int player) {
        switch (player) {
            case 0: for (Circle pawn : player_1_Pawns) {
                pawn.setDisable(true);}
                break;
            case 1: for (Circle pawn : player_2_Pawns) {
                pawn.setDisable(true);}
                break;
        }
    }
    @FXML
    protected void enablePawns(int player) {
        switch (player) {
            case 0: for (Circle pawn : player_1_Pawns) {
                pawn.setDisable(false);}
                break;
            case 1: for (Circle pawn : player_2_Pawns) {
                pawn.setDisable(false);}
                break;
        }
    }

    @FXML
    protected void onDie_1_ButtonClick() {
        int player = 0;
        //int pawn = 0;
        die_2_Value.setText(null);
        die_2_Value.setDisable(true);
        disablePawns(1);
        enablePawns(0);
        int currentValue = game.play(0);
        die_1_Value.setText(String.valueOf(currentValue));
        game.haveActivePawns(player);
        if (game.isMovePossible(player, currentValue)) {
            if (currentValue != 6) {
                die_1.setDisable(true);
                onPawnSelect();
            }
            else {
                onPawnSelect();
            }
        }
        else {
            die_1.setDisable(true);
            die_1_Value.setDisable(true);
            die_2.setDisable(false);
            die_2_Value.setDisable(false);
        }
    }

    @FXML
    protected void onDie_2_ButtonClick() {
        int player = 1;
        //int pawn = 0;
        die_1_Value.setText(null);
        die_1_Value.setDisable(true);
        disablePawns(0);
        enablePawns(1);
        int currentValue = game.play(1);
        die_2_Value.setText(String.valueOf(currentValue));
        game.haveActivePawns(player);
        if (game.isMovePossible(player, currentValue)) {
            if (currentValue != 6) {
                die_2.setDisable(true);
                onPawnSelect();
            }
            else {
                onPawnSelect();
            }
        }
        else {
            die_2.setDisable(true);
            die_2_Value.setDisable(true);
            die_1.setDisable(false);
            die_1_Value.setDisable(false);
        }
    }

    @FXML
    protected void onPawnSelect(){
        gridPane.setOnMouseClicked(mouseEvent -> {
            String targetPawn = mouseEvent.getPickResult().getIntersectedNode().getId();
            Node targetNode = mouseEvent.getPickResult().getIntersectedNode();
            int player = 5;
            int pawn = 5;
            switch (targetPawn) {
                case "player_1_Pawn_1" : player = 0; pawn = 0; break;
                case "player_1_Pawn_2" : player = 0; pawn = 1; break;
                case "player_1_Pawn_3" : player = 0; pawn = 2; break;
                case "player_1_Pawn_4" : player = 0; pawn = 3; break;
                case "player_2_Pawn_1" : player = 1; pawn = 0; break;
                case "player_2_Pawn_2" : player = 1; pawn = 1; break;
                case "player_2_Pawn_3" : player = 1; pawn = 2; break;
                case "player_2_Pawn_4" : player = 1; pawn = 3; break;
            }
            int distance = Integer.parseInt(dieValues.get(player).getText());

            int returnCode = game.move(player, pawn, distance);
            if (returnCode == 1) {
                GridPane.setRowIndex(targetNode, game.getPlayers().get(player).getPawns().get(pawn).getRow());
                GridPane.setColumnIndex(targetNode, game.getPlayers().get(player).getPawns().get(pawn).getColumn());
                if (game.isWin(player)) {
                    //System.out.println("Player " + game.getPlayers().get(player).getColor() + " Win!");
                    endLabel.setText("Player " + game.getPlayers().get(player).getColor() + " Win!");
                    endLabel.setVisible(true);
                    gridPane.setDisable(true);
                }
                else if (distance != 6) {
                    dieValues.get(player).setText(null);
                    dieValues.get(player).setDisable(true);
                    if (player == 0){
                        die_2.setDisable(false);
                        die_2_Value.setDisable(false);
                    }
                    else if (player == 1) {
                        die_1.setDisable(false);
                        die_1_Value.setDisable(false);
                    }
                }
                else {
                    dieValues.get(player).setText(null);
                }
            }

        });

    }

}