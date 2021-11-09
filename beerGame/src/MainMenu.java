import Field.Field;
import org.academiadecodigo.bootcamp.Sound;
import org.academiadecodigo.simplegraphics.keyboard.*;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.*;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.graphics.Color;

public class MainMenu implements KeyboardHandler {

    public static final int PADDING = 10;
    private Text[] menuOptions = new Text[3];
    private Text[] helpTexts = new Text[6];
    private boolean isHelpText = false;
    private Text keyHelp;

    private Sound selectOption;

    private Game game;

    KeyboardEvent upPressed;
    KeyboardEvent spacePressed;
    KeyboardEvent downPressed;
    KeyboardEvent qPressed;
    Keyboard keyboard;

    Picture menuBg;




    public void init() {
        menuBg = new Picture(PADDING, PADDING, "bar1.png");
        menuBg.draw();
        initMenuOptions();
        drawOptions(menuOptions);
        keyHelp = new Text(155, 490, "[Space]: Enter / [Q]: Quit / [Arrows]: Move");
        keyHelp.draw();
        keyboardInit();
        initHelpTexts();
        game = new Game();
    }


    public void initMenuOptions() {
        menuOptions[0] = new Text(200, 200, "PLAY");
        menuOptions[0].setColor(Color.RED);
        menuOptions[1] = new Text(200, 280, "HELP");
        menuOptions[2] = new Text(200, 360, "QUIT");
        for (Text option : menuOptions) {
            option.grow(20, 20);
        }
    }

    public void initHelpTexts() {
        int x = 110;
        helpTexts[0] = new Text(x, 170, "Catch <BEERS> to increase your score.");
        helpTexts[1] = new Text(x, 220, "Be careful!!!  Some beers may have an");
        helpTexts[2] = new Text(x, 270, "<UNDESIRED> effect, but as they say");
        helpTexts[3] = new Text(x, 320, "\"LIKE <CURES> LIKE\"...  Also, don't let");
        helpTexts[4] = new Text(x, 370, "the <MCS> see you drinking, or you'll");
        helpTexts[5] = new Text(x, 420, "lose a <LIVER>! GOOD CODEBREAK!!!");
        for (Text helps : helpTexts) {
            helps.grow(20, 15);
        }
    }

    public void drawOptions(Text[] menuOptions) {
        for (Text option : menuOptions) {
            option.draw();
        }
    }

    public void deleteOptions(Text[] menuOptions) {
        for (Text options : menuOptions) {
            options.delete();
        }
    }

    public void drawHelpText(Text[] helpTexts) {
        for (Text helps : helpTexts) {
            helps.draw();
        }
    }

    public void deleteHelpText(Text[] helpTexts) {
        for (Text helps : helpTexts) {
            helps.delete();
        }
    }

    public void keyboardInit() {
        keyboard = new Keyboard(this);

        downPressed = new KeyboardEvent();
        downPressed.setKey(KeyboardEvent.KEY_DOWN);
        downPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        upPressed = new KeyboardEvent();
        upPressed.setKey(KeyboardEvent.KEY_UP);
        upPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        spacePressed = new KeyboardEvent();
        spacePressed.setKey(KeyboardEvent.KEY_SPACE);
        spacePressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        qPressed = new KeyboardEvent();
        qPressed.setKey(KeyboardEvent.KEY_Q);
        qPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(upPressed);
        keyboard.addEventListener(downPressed);
        keyboard.addEventListener(spacePressed);
        keyboard.addEventListener(qPressed);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        selectOption = new Sound("/mouthdrop.wav");

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_DOWN:
                selectOption.play(true);
                if (menuOptions[1].getColor() == Color.RED) {
                    menuOptions[1].setColor(Color.BLACK);
                    menuOptions[2].setColor(Color.RED);

                } else if (menuOptions[0].getColor() == Color.RED) {
                    menuOptions[0].setColor(Color.BLACK);
                    menuOptions[1].setColor(Color.RED);

                } else if (menuOptions[2].getColor() == Color.RED) {
                    menuOptions[2].setColor(Color.BLACK);
                    menuOptions[0].setColor(Color.RED);

                }
                break;

            case KeyboardEvent.KEY_UP:
                selectOption.play(true);
                if (menuOptions[1].getColor() == Color.RED) {
                    menuOptions[1].setColor(Color.BLACK);
                    menuOptions[0].setColor(Color.RED);

                } else if (menuOptions[0].getColor() == Color.RED) {
                    menuOptions[0].setColor(Color.BLACK);
                    menuOptions[2].setColor(Color.RED);

                } else if (menuOptions[2].getColor() == Color.RED) {
                    menuOptions[2].setColor(Color.BLACK);
                    menuOptions[1].setColor(Color.RED);

                }
                break;


            case KeyboardEvent.KEY_SPACE:

                if (menuOptions[0].getColor() == Color.RED) {
                    removeKeyPressed();
                    game = new Game();
                    game.init();
                    selectOption.play(true);
                    break;
                }
                if (menuOptions[1].getColor() == Color.RED) {
                    isHelpText = true;
                    removeKeyPressed();
                    deleteOptions(menuOptions);
                    drawHelpText(helpTexts);
                    selectOption.play(true);
                    break;
                }
                if (menuOptions[2].getColor() == Color.RED) {
                    selectOption.play(true);
                    System.exit(0);
                    break;
                }

                break;


            case KeyboardEvent.KEY_Q:

                if (menuOptions[0].getColor() == Color.RED) {
                    if (game.isGameOn()) {
                        keyboard.addEventListener(spacePressed);
                        keyboard.addEventListener(upPressed);
                        keyboard.addEventListener(downPressed);
                        selectOption.play(true);
                        game.stop();

                        break;
                    } else {
                        menuOptions[2].setColor(Color.RED);
                        menuOptions[0].setColor(Color.BLACK);
                        menuOptions[1].setColor(Color.BLACK);
                        selectOption.play(true);
                        break;
                    }
                }

                if (menuOptions[1].getColor() == Color.RED) {
                    if (isHelpText == true) {
                        isHelpText = false;
                        deleteHelpText(helpTexts);
                        drawOptions(menuOptions);
                        keyboard.addEventListener(spacePressed);
                        keyboard.addEventListener(upPressed);
                        keyboard.addEventListener(downPressed);
                        selectOption.play(true);
                        break;
                    } else {
                        menuOptions[2].setColor(Color.RED);
                        menuOptions[0].setColor(Color.BLACK);
                        menuOptions[1].setColor(Color.BLACK);
                        selectOption.play(true);
                        break;
                    }
                }

                if (menuOptions[2].getColor() == Color.RED) {
                    selectOption.play(true);
                    System.exit(0);
                    break;
                }
                break;

            default:
                break;
        }

    }

    public void removeKeyPressed() {
        keyboard.removeEventListener(spacePressed);
        keyboard.removeEventListener(upPressed);
        keyboard.removeEventListener(downPressed);
    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

}


