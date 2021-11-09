package Field;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.swing.*;

public class Field {

    private static int rows;
    private static int cols;
    private static int height;
    private static int width;
    public static final int PADDING = 10;
    public static final int CELL_SIZE = 50;
    private static Picture background;

    //init()
    public static void init() {
        background = new Picture(PADDING, PADDING, "interior.jpg");
        //Rectangle background = new Rectangle(PADDING, PADDING + 40, 800, 900);
        height = background.getHeight();
        width = background.getWidth();
        cols = (int) (background.getWidth() / CELL_SIZE);
        rows = (int) (background.getHeight() / CELL_SIZE);
        //background.grow(100, 100);
        background.draw();
    }

    public static int getCols(){
        return cols;
    }

    public static int getRows() {
        return rows;}

        public static int getHeight(){
        return height;
        }

    public static void delete() {
        background.delete();
    }


}

