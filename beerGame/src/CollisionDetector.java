import Objects.Enemies.BadBeer;
import Objects.Enemies.MC;
import Objects.GoodBeer;
import Objects.Movable;
import Objects.Player;
import org.academiadecodigo.bootcamp.Sound;

public class CollisionDetector {

    private Sound goodBeerFX;
    private Sound badBeerFX;
    private Sound mcFX;

    public void check(Player player, Movable obj) {


        if (player.getPosition().equals(obj.getPosition())) {

            if (obj instanceof MC) {
                if (!((MC) obj).getIsCollided()) {
                    ((MC) obj).isCollided(true);
                    mcFX = new Sound("/MCFX.wav");
                    mcFX.play(true);

                    player.loseLivers();
                    obj.getPicture().delete();
                    obj.getPosition().setRow(obj.getPosition().getRow()+2);
                }
            }

            if (obj instanceof BadBeer) {
                if (!((BadBeer) obj).getIsCollided()) {
                    ((BadBeer) obj).isCollided(true);
                    badBeerFX = new Sound("/BadBeerFX.wav");
                    badBeerFX.play(true);
                    goodBeerFX = new Sound("/GoodBeerFX.wav");
                    goodBeerFX.play(true);

                    if (!player.isSwitchedKeyboard()) {
                        player.setSwitchedKeyboard(true);
                        obj.getPicture().delete();
                        obj.getPosition().setRow(obj.getPosition().getRow()+2);

                    }
                    else {
                        player.setSwitchedKeyboard(false);
                        obj.getPicture().delete();
                        obj.getPosition().setRow(obj.getPosition().getRow()+2);
                    }
                }
            }

            if (obj instanceof GoodBeer) {
                if (!((GoodBeer) obj).getIsCollided()) {
                    ((GoodBeer) obj).isCollided(true);
                    goodBeerFX = new Sound("/GoodBeerFX.wav");
                    goodBeerFX.play(true);
                    player.setScore(5);
                    obj.getPicture().delete();
                    obj.getPosition().setRow(obj.getPosition().getRow()+2);

                }
            }
        }
    }
}

