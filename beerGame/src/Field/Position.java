package Field;

public class Position {

    private int col;
    private int row;

    public Position(int col, int row) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row){
        this.row = row;
    }
    public void sideMove(int distance) {
        this.col += distance;
    }



    //cellSize
    public void fallDown(int distance) {
        if(this.row + distance < Field.getRows()) {
           row += distance;
        }
    }

    public boolean equals(Position position){
        return (this.col == position.getCol() && this.row == position.getRow());
    }

    public int rowToY(int row){
        return row * Field.CELL_SIZE;
    }

    public int colToX(int col){
        return col * Field.CELL_SIZE;
    }


}
