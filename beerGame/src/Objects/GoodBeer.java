package Objects;

import Field.Field;
import Field.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class GoodBeer implements Movable {

    private Position position;
    private Picture picture;
    private boolean isCollided;

    public GoodBeer(Position position){
        this.position = position;
        picture = new Picture(position.colToX(position.getCol()), position.rowToY(position.getRow()), "superbock.png");
        picture.draw();
        this.isCollided = false;
    }

    @Override
    public void move(){
        picture.translate(0, Field.CELL_SIZE);
        position.fallDown(1);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public Picture getPicture(){
        return picture;
    }

    public void isCollided(boolean isCollided){
        this.isCollided = isCollided;
    }

    public boolean getIsCollided() {
        return isCollided;
    }
}
