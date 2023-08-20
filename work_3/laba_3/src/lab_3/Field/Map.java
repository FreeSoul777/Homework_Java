package lab_3.Field;

import java.util.ArrayList;

import static lab_3.Logic.Creation.rnd;

public class Map {

    public static int SIZE_X = 0;
    public static int SIZE_Y = 0;

    private ArrayList<Cell> cells = new ArrayList<>();

    public Map() {
        SIZE_Y = rnd.nextInt(10) + 1;
        SIZE_X = rnd.nextInt(10) + 1;
        createMap();
    }

    public void createMap() {
        for(int i = 0; i < SIZE_X; i++) {
            for(int j = 0; j < SIZE_Y; j++) {
                cells.add(new Cell(i, j, '*'));
            }
        }
    }

    public int findCell(Position pos) {
        for(int i = 0; i < cells.size(); ++i) {
            Position position = cells.get(i).getPosition();
            if(position.getX() == pos.getX() && position.getY() == pos.getY()) {
                return i;
            }
        }
        return -1;
    }

    public Cell get(int i) {
        return cells.get(i);
    }

    public Cell assignACell(char symbol) {
        int i = rnd.nextInt(cells.size());
        cells.get(i).setSymbol(symbol);
        return cells.get(i);
    }

    public void show() {
        System.out.println("-----------------------------------------------------------------------------");
        int i = 0;
        while(i < cells.size()) {
            for (int j = 0; j < SIZE_Y; j++, i++) {
                System.out.print(cells.get(i).getSymbol() + " ");
            }
            System.out.println();
        }
        System.out.print("-----------------------------------------------------------------------------");
        System.out.println("\n\n");
    }
}
