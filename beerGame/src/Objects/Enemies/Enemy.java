package Objects.Enemies;

import Field.Position;
import Objects.Movable;

public abstract class Enemy implements Movable {

    private Position position;

    public Enemy(Position position){
        this.position = position;
    }

    @Override
    public Position getPosition(){
        return position;
    }

    @Override
    public void move(){
        position.fallDown(1);
    }

}
