package parcheesi;

import java.util.ArrayList;
public class Game {

    private final String[] colors = {"RED", "BLUE", "YELLOW", "GREEN"};
    private final int numberOfPawns = 4;
    private final int[] p1_PawnPathRow = {13, 12, 11, 10, 9, 8, 8, 8, 8, 8, 8, 7, 6, 6, 6, 6, 6, 6, 5, 4, 3, 2, 1, 0, 0, 0, 1, 2, 3,
            4, 5, 6, 6, 6, 6, 6, 6, 7, 8, 8, 8, 8, 8, 8, 9, 10, 11, 12, 13, 14, 14, 13, 12, 11, 10, 9, 8};
    private final int[] p1_PawnPathColumn = {6, 6, 6, 6, 6, 5, 4, 3, 2, 1, 0, 0, 0, 1, 2, 3, 4, 5, 6, 6, 6, 6, 6, 6, 7, 8, 8, 8, 8,
            8, 8, 9, 10, 11, 12, 13, 14, 14, 14, 13, 12, 11, 10, 9, 8, 8, 8, 8, 8, 8, 7, 7, 7, 7, 7, 7, 7};
    private final int[] p2_PawnPathRow = {1, 2, 3, 4, 5, 6, 6, 6, 6, 6, 6, 7, 8, 8, 8, 8, 8, 8, 9, 10, 11, 12, 13, 14, 14, 14, 13,
            12, 11, 10, 9, 8, 8, 8, 8, 8, 8, 7, 6, 6, 6, 6, 6, 6, 5, 4, 3, 2, 1, 0, 0, 1, 2, 3, 4, 5, 6};
    private final int[] p2_PawnPathColumn = {8, 8, 8, 8, 8, 9, 10, 11, 12, 13, 14, 14, 14, 13, 12, 11, 10, 9, 8, 8, 8, 8, 8, 8, 7,
            6, 6, 6, 6, 6, 6, 5, 4, 3, 2, 1, 0, 0, 0, 1, 2, 3, 4, 5, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7};
    private final int[] p1_InitialRows = {10, 11, 12, 13};
    private final int[] p1_InitialColumns = {4, 4, 4, 4};
    private final int[] p2_InitialRows = {1, 2, 3, 4};
    private final int[] p2_InitialColumns = {10, 10, 10, 10};
    private final int numberOfPlayers = 4;
    private String status = "not over";
    private ArrayList<Player> players = new ArrayList<>();

    private ArrayList<NSidedDie> dice = new ArrayList<>();

    private ArrayList<int[]> rowPaths = new ArrayList<>();
    private ArrayList<int[]> columnPaths = new ArrayList<>();
    private ArrayList<int[]> initialRows = new ArrayList<>();
    private ArrayList<int[]> initialColumns = new ArrayList<>();


    public Game() {
        this.create(numberOfPlayers);
    }

    public Game(int numberOfPlayers) {
        this.create(numberOfPlayers);
    }
    private void create(int numberOfPlayers) {
        for (int i=0; i<numberOfPlayers; i++) {
            Player player = new Player();
            NSidedDie die = new NSidedDie();
            players.add(player);
            players.get(i).setColor(colors[i]);
            players.get(i).setPawns(numberOfPawns);
            players.get(i).setStatus("online");
            players.get(i).setStatus("off");
            dice.add(die);
        }
        rowPaths.add(p1_PawnPathRow);
        rowPaths.add(p2_PawnPathRow);
        columnPaths.add(p1_PawnPathColumn);
        columnPaths.add(p2_PawnPathColumn);
        initialRows.add(p1_InitialRows);
        initialRows.add(p2_InitialRows);
        initialColumns.add(p1_InitialColumns);
        initialColumns.add(p2_InitialColumns);
        for (Player player : players) {
            for (Pawn pawn : player.getPawns()) {
                pawn.setRow(initialRows.get(players.indexOf(player))[player.getPawns().indexOf(pawn)]);
                pawn.setColumn(initialColumns.get(players.indexOf(player))[player.getPawns().indexOf(pawn)]);
            }
        }

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<NSidedDie> getDice() {
        return dice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int play(int player) {
        dice.get(player).roll();

        return dice.get(player).getCurrentValue();
    }

    public void haveActivePawns(int player) {
        ArrayList<Pawn> pawns = players.get(player).getPawns();
        int[] pathRow = rowPaths.get(player);
        int[] pathColumn = columnPaths.get(player);
        players.get(player).getActivePawns().clear();
        int numberOfActivePawns = 0;
        for (int i = 0; i < pathRow.length-1; i++) {
            for (Pawn pawn : pawns) {
                if (pawn.getRow() == pathRow[i] && pawn.getColumn() == pathColumn[i]) {
                    pawn.setPosition(i);
                    pawn.setDistanceToEnd(pathRow.length-1-i);
                    players.get(player).getActivePawns().add(pawn);
                    numberOfActivePawns++;
                }
            }
        }
        players.get(player).setNumberOfActivePawns(numberOfActivePawns);
    }

    public int move(int player, int pawn, int distance) {
        if (0>player || player>1) {
            return 0;
        }
        Pawn mPawn = players.get(player).getPawns().get(pawn);
        if (1<=distance && distance<=6) {
            if (mPawn.getPosition() != -6) {
                mPawn.setPosition(mPawn.getPosition()+distance);
                mPawn.setRow(rowPaths.get(player)[mPawn.getPosition()]);
                mPawn.setColumn(columnPaths.get(player)[mPawn.getPosition()]);
                return 1;
            }
            else if (distance == 6) {
                mPawn.setPosition(mPawn.getPosition()+distance);
                mPawn.setRow(rowPaths.get(player)[mPawn.getPosition()]);
                mPawn.setColumn(columnPaths.get(player)[mPawn.getPosition()]);
                return 1;
            }
            else {
                mPawn.setPosition(-6);
                mPawn.setRow(mPawn.getRow());
                mPawn.setColumn(mPawn.getColumn());
                return 0;
            }
        }
        else {
            mPawn.setRow(mPawn.getRow());
            mPawn.setColumn(mPawn.getColumn());
            return 0;
        }

    }

    public boolean isWin(int player) {
        ArrayList<Pawn> pawns = players.get(player).getPawns();
        int numberOfReachedPawns = 0;
        for (Pawn pawn : pawns) {
            if (pawn.getPosition() == p1_PawnPathRow.length-1) {
                numberOfReachedPawns++;
            }
        }
        return numberOfReachedPawns == numberOfPawns;
    }

    public int getActivePawn(int player) {
        ArrayList<Pawn> pawns = players.get(player).getPawns();
        int[] pathRow = rowPaths.get(player);
        int[] pathColumn = columnPaths.get(player);
        int pawnPosition = 0;
        int activePawn = 0;
        for (int i = 0; i < pathRow.length-1; i++) {
            for (Pawn pawn : pawns) {
                if (pawn.getRow() == pathRow[i] && pawn.getColumn() == pathColumn[i]) {
                    pawnPosition = i;
                    activePawn = pawns.indexOf(pawn);

                }
            }
        }
        //return pawnPosition;
        return activePawn;
    }

    public boolean isMovePossible(int player, int distance) {
        int movablePawns = 0;
        if (players.get(player).getNumberOfActivePawns() == 0) {
            if (distance == 6) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            for (Pawn pawn : players.get(player).getActivePawns()) {
                if (pawn.getDistanceToEnd() >= distance) {
                    movablePawns++;
                }
            }
            return movablePawns > 0;
        }
    }

}
