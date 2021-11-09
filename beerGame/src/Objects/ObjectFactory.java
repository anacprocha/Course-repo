package Objects;

import Field.Field;
import Field.Position;
import Objects.Enemies.BadBeer;
import Objects.Enemies.Enemy;
import Objects.Enemies.MC;

public class ObjectFactory {

    public static Player createPlayer(){
        return new Player(new Position(Field.getCols()/2, Field.getRows() -1));
    }

    public static Movable createBeers () {
        return (Math.random() < 0.75 ? createGoodBeer() : createBadBeer());
    }

    public static Movable createObjects(){
        return (Math.random() < 0.60 ? createGoodBeer() : createEnemies());
    }

    private static GoodBeer createGoodBeer(){
        return new GoodBeer(new Position( randomCol(),0));
    }

    private static BadBeer createBadBeer(){
        return new BadBeer(new Position(randomCol(),0));
    }

    private static MC createMC(){
        return new MC(new Position( randomCol(), 0));
    }

    private static Enemy createEnemies(){
        return (Math.random() < 0.6 ? createBadBeer() : createMC());
    }

    private static int randomCol(){
        return (int) (Math.random() * Field.getCols());
    }

}
