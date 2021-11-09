package Objects;

import Field.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public interface Movable {

    void move();

    Position getPosition();

    Picture getPicture();


}
