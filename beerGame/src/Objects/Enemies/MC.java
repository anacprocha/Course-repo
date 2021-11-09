package Objects.Enemies;

import Field.Field;
import Field.Position;
import Objects.Movable;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class MC extends Enemy {

    private enum MCType{
        PAULO ("paulo.png"),
        PEDRO ("pedro.png"),
        SARA ("sara.png");

        private String picture;

        MCType(String picture){
            this.picture = picture;
        }

        public String getPicture(){
            return picture;
        }
    }

    private Picture picture;
    private boolean isCollided;

    public MC(Position position){
        super(position);
        int McType = (int) (Math.random() * MCType.values().length);
        picture = new Picture(position.colToX(position.getCol()), position.rowToY(position.getRow()), MCType.values()[McType].getPicture());
        picture.draw();
        this.isCollided = false;
    }

    @Override
    public void move() {
        picture.translate(0 ,Field.CELL_SIZE);
        super.move();
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
