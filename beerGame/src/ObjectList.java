import Field.Position;
import Objects.Movable;
import java.util.Iterator;

import java.util.LinkedList;

public class ObjectList implements Iterable<Movable>{

    private LinkedList<Movable>  objectList = new LinkedList<>();


    @Override
    public Iterator<Movable> iterator(){
        return objectList.iterator();
    }



    public void add(Movable movable){
        objectList.add(movable);
    }


}
