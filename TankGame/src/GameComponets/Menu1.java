package GameComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Utilities.Image;
import Utilities.Tool;

public class Menu1 {

    private GamePanel gamePanel;
    private int mouseX, mouseY;
    private Image[] images = {
            new Image(Tool.getBufferedImage("Assets/Images/Menu/play1off.png", 400, 225, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/play2off.png", 400, 225, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/play3off.png", 400, 225, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/back.png", 230, 80, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/play1on.png", 400, 225, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/play2on.png", 400, 225, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/play3on.png", 400, 225, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/backon.png", 230, 80, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/BG1.jpg", 1920, 1080, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/how1.png", 400, 225, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/howon.png", 400, 225, 1)),
    };
    Rectangle play1Bounds = new Rectangle(1408 / 2 - 200, 200, 400, 225);
    Rectangle play2Bounds = new Rectangle(1408 / 2 - 200, 350, 400, 225);
    Rectangle play3Bounds = new Rectangle(1408 / 2 - 200, 500, 400, 225);
    Rectangle backBounds = new Rectangle(10, 10, 230, 80);
    Rectangle howBounds = new Rectangle(900, 500, 400, 225);


    private Image image1 = new Image(Tool.getBufferedImage("Assets/Images/Menu/image1.png", 1000, 500, 1));


    public int gameState = -1;
    private int choice = -1;
    private static String options[] = {
            "1. 1 Play.",
            "2. 2 Play.",
            "3. 3 Play."
    };

    public Menu1(GamePanel gamePanel) {
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
        } else if (howBounds.contains(mouseX, mouseY) && gameState == -1) {
            choice = 4;
        } else
            choice = -1;

        if (gamePanel.isMousePressed() && gameState == -1) {
            gameState = choice;
            if (gameState != -1) {
                gamePanel.reset();
                gamePanel.playSE(2);
                gamePanel.mousePressed = false;
            }
        }
    }

    public void render(Graphics2D graphics2d) {

        graphics2d.drawImage(images[8].getImage(), 0, 0, gamePanel.SCREEN_WIDTH, gamePanel.SCREEN_HEIGHT, null);

        Font font1 = new Font("Lato", Font.BOLD, 90);
        graphics2d.setFont(font1);
        graphics2d.setColor(Color.RED);
        if ((gameState == -1)) {
            graphics2d.drawImage(image1.getImage(), gamePanel.SCREEN_WIDTH / 2 - 300, 20, 750, 250, null);

        }
        for (int i = 0; i < options.length; i++) {
            if (i == choice) {
                graphics2d.drawImage(images[i + 4].getImage(), gamePanel.SCREEN_WIDTH / 2 - 200, 200 + i * 150,
                        images[i + 4].getWidth(), images[i + 4].getHeight(), null);
            } else {
                graphics2d.drawImage(images[i].getImage(), gamePanel.SCREEN_WIDTH / 2 - 200, 200 + i * 150,
                        images[i].getWidth(), images[i].getHeight(), null);
            }

        }
        if(choice == 3) {
            graphics2d.drawImage(images[7].getImage(), 10,10,
                        images[7].getWidth(), images[7].getHeight(), null);
        }else {
            graphics2d.drawImage(images[3].getImage(), 10,10,
                        images[3].getWidth(), images[3].getHeight(), null);
        }
        if(choice == 4) {
            graphics2d.drawImage(images[10].getImage(), 900,500,
                        images[10].getWidth(), images[10].getHeight(), null);
        }else {
            graphics2d.drawImage(images[9].getImage(), 900,500,
                        images[9].getWidth(), images[9].getHeight(), null);
        }
    }

}
