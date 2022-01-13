import java.util.ArrayList;

class Grid extends ArrayList {
    int length, width;
    Cell currentCell;
    Character ch;
    private static Grid instance = null;
    private Grid(int length,int width){
        this.length = length;
        this.width = width;
    }
    public static Grid getInstance(){
        if (instance == null) {
            instance = new Grid(5, 6);
        }
        return instance;
    }
    //method to generate a fixed map
    static ArrayList<ArrayList<Cell>> mapGenerator(int length,int width){
        int k;
        //the map is an array list of array lists of cells
        ArrayList<ArrayList<Cell>> map = new ArrayList<ArrayList<Cell>>();
        for(int i = 0; i < length; i++) {
            ArrayList<Cell> line = new ArrayList<Cell>();
            for (int j = 0; j < width; j++) {
                k = 0;
                if(i == 0 && j == 0) {
                    line.add(new Cell(i, j, "EMPTY", 1));
                }
                else {
                    if(i == 0 && j != 0){
                        line.add(new Cell(i, j, "ENEMY", 2));
                        k = 1;
                    }
                    if(i == j && i != 0 && j != width -1) {
                        line.add(new Cell(i, j, "SHOP", 2));
                        k = 1;
                    }
                    if(i == length - 2 && j == width -2){
                        line.add(new Cell(i, j, "ENEMY", 2));
                        k = 1;
                    }
                    if(i == length - 1 && j == width -1){
                        line.add(new Cell(i, j, "FINISH", 2));
                        k = 1;
                    }
                    if(k == 0){
                        line.add(new Cell(i, j, "EMPTY", 2));
                    }
                }
            }
            map.add(line);
        }

        return map;
    }
    //methods to move along the map
    void goNorth(int length,int width, ArrayList<ArrayList<Cell>> map){
        if(currentCell.Ox == 0){ System.out.println("Can't go north! :( "); }
        else{
            currentCell = map.get(currentCell.Ox - 1).get(currentCell.Oy);
        }
       if(currentCell.visited == 2)
           currentCell.visited = 3;
    }
    void goSouth(int length,int width,ArrayList<ArrayList<Cell>> map){
        if(currentCell.Ox == length - 1){ System.out.println("Can't go south! :( "); }
        else{
            currentCell = map.get(currentCell.Ox + 1).get(currentCell.Oy);
        }
        if(currentCell.visited == 2)
            currentCell.visited = 3;
    }
    void goWest(int length,int width,ArrayList<ArrayList<Cell>> map){
        if(currentCell.Oy == 0){ System.out.println("Can't go west! :( "); }
        else{
            currentCell = map.get(currentCell.Ox).get(currentCell.Oy - 1);
        }
        if(currentCell.visited == 2)
            currentCell.visited = 3;
    }
    void goEast(int length,int width,ArrayList<ArrayList<Cell>> map){
        if(currentCell.Oy == width - 1){ System.out.println("Can't go east! :( "); }
        else{
            currentCell = map.get(currentCell.Ox).get(currentCell.Oy + 1);
        }
        if(currentCell.visited == 2)
            currentCell.visited = 3;
    }
}
class Cell{
    int Ox, Oy;
    enum cellType{ EMPTY,ENEMY, SHOP, FINISH }
    int visited;
    //1 is visited; 2 is not; 3 is current cell
    cellType myCell;
    Cell(int Ox,int Oy,String x,int visited){
        if(x.compareTo("EMPTY")==0)
            myCell = cellType.EMPTY;
        if(x.compareTo("ENEMY")==0)
            myCell = cellType.ENEMY;
        if(x.compareTo("SHOP")==0)
            myCell = cellType.SHOP;
        if(x.compareTo("FINISH")==0)
            myCell = cellType.FINISH;
        this.visited = visited;
        this.Ox = Ox;
        this.Oy = Oy;
    }
    int isVisited(){ return visited; }
    int getOx(){ return Ox; }
    int getOy(){ return Oy; }
}
interface CellElement{
    char toCharacter();
}
