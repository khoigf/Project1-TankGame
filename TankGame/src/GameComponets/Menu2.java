package GameComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Utilities.Image;
import Utilities.Tool;

public class Menu2 {

    private GamePanel gamePanel;
    private int mouseX, mouseY;
    private Image[] images = {
            new Image(Tool.getBufferedImage("Assets/Images/Menu/back.png", 230, 80, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/backon.png", 230, 80, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/howto.png", 1920, 1080, 1))
    };
    Rectangle backBounds = new Rectangle(10, 10, 230, 80);

    public int gameState = -1;
    private int choice = -1;

    public Menu2(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    public void update() {

        mouseX = gamePanel.getMouseX();
        mouseY = gamePanel.getMouseY();

        if (backBounds.contains(mouseX, mouseY) && gameState == -1) {
            choice = 0;
        } else
            choice = -1;

        if (gamePanel.isMousePressed() && gameState == -1) {
            gameState = choice;
            if (gameState != -1) {
                gamePanel.playSE(2);
                gamePanel.mousePressed =false;
            }
        }
    }

    public void render(Graphics2D graphics2d) {

        graphics2d.drawImage(images[2].getImage(), 0, 0, gamePanel.SCREEN_WIDTH, gamePanel.SCREEN_HEIGHT, null);

        Font font1 = new Font("Lato", Font.BOLD, 90);
        graphics2d.setFont(font1);
        graphics2d.setColor(Color.RED);
        if(choice == 0) {
            graphics2d.drawImage(images[1].getImage(), 10,10,
                        images[1].getWidth(), images[1].getHeight(), null);
        }else {
            graphics2d.drawImage(images[0].getImage(), 10,10,
                        images[0].getWidth(), images[0].getHeight(), null);
        }
    }

}
