package GameComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Utilities.Image;
import Utilities.Tool;

public class Menu3 {

    private GamePanel gamePanel;
    private int mouseX, mouseY;
    private Image[] images = {
            new Image(Tool.getBufferedImage("Assets/Images/Menu/home.png", 250, 75, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/about.png", 250, 75, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/exit.png", 250, 75, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/xbutoff.png", 60, 30, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/homeon.png", 250, 75, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/abouton.png", 250, 75, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/exiton.png", 250, 75, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/xbut.png", 60, 30, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/BG5.png", 384, 320, 1)),
    };
    Rectangle play1Bounds = new Rectangle(64*9-70, 270, 250, 75);
    Rectangle play2Bounds = new Rectangle(64*9-70, 345, 250, 75);
    Rectangle play3Bounds = new Rectangle(64*9-70, 420, 250, 75);
    Rectangle backBounds = new Rectangle(750, 230, 60, 30);

    private Image image1 = new Image(Tool.getBufferedImage("Assets/Images/Menu/pause.png", 266, 133, 1));


    public int gameState = -1;
    private int choice = -1;
    private static String options[] = {
            "1. Home.",
            "2. About.",
            "3. Exit."
    };

    public Menu3(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    public void update() {

        mouseX = gamePanel.getMouseX();
        mouseY = gamePanel.getMouseY();

        if (play1Bounds.contains(mouseX, mouseY) && gameState == -1) {
            choice = 0;
        } else if (play2Bounds.contains(mouseX, mouseY) && gameState == -1) {
            choice = 1;
        } else if (play3Bounds.contains(mouseX, mouseY) && gameState == -1) {
            choice = 2;
        } else if (backBounds.contains(mouseX, mouseY) && gameState == -1) {
            choice = 3;
        } else
            choice = -1;

        if (gamePanel.isMousePressed() && gameState == -1) {
            gameState = choice;
            if (gameState != -1) {
                gamePanel.playSE(2);
                gamePanel.mousePressed = false;
            }
        }
    }

    public void render(Graphics2D graphics2d) {

        graphics2d.drawImage(images[8].getImage(), 64*7-20, 3*64+20, 64*6, 64*5, null);

        Font font1 = new Font("Lato", Font.BOLD, 75);
        graphics2d.setFont(font1);
        graphics2d.setColor(Color.RED);
        if ((gameState == -1)) {
            graphics2d.drawImage(image1.getImage(), 418, 194, 400, 150, null);

        }
        for (int i = 0; i < options.length; i++) {
            if (i == choice) {
                graphics2d.drawImage(images[i + 4].getImage(), 576-70, 270 + i * 75,
                        images[i + 4].getWidth(), images[i + 4].getHeight(), null);
            } else {
                graphics2d.drawImage(images[i].getImage(), 576-70, 270 + i * 75,
                        images[i].getWidth(), images[i].getHeight(), null);
            }

        }
        if(choice == 3) {
            graphics2d.drawImage(images[7].getImage(), 750,230,
                        images[7].getWidth(), images[7].getHeight(), null);
        }else {
            graphics2d.drawImage(images[3].getImage(), 750,230,
                        images[3].getWidth(), images[3].getHeight(), null);
        }
    }

}
