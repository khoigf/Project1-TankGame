package GameComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Utilities.Image;
import Utilities.Tool;

public class Menu {

    private GamePanel gamePanel;
    private int mouseX, mouseY;
    private int winner;

    public void setWinner(int team) {
        this.winner = team;
    }

    private Image[] images = {
            new Image(Tool.getBufferedImage("Assets/Images/Menu/play_no.png", 400, 150, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/exit_no.png", 400, 150, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/play_yes.png", 400, 150, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/exit_yes.png", 400, 150, 1)),
            new Image(Tool.getBufferedImage("Assets/Images/Menu/BG2.jpg", 1920, 1080, 1))
    };
    private Image image1 = new Image(Tool.getBufferedImage("Assets/Images/Menu/base.png", 300, 200, 1));
    private Image image2 = new Image(Tool.getBufferedImage("Assets/Images/Menu/teamBlue.png", 640, 400, 1));
    private Image image3 = new Image(Tool.getBufferedImage("Assets/Images/Menu/teamRed.png", 640, 400, 1));
    Rectangle playBounds = new Rectangle(1408 / 2 - 200, 400, 400, 150);
    Rectangle exitBounds = new Rectangle(1408 / 2 - 200, 550, 400, 150);

    public int gameState = -1;
    private int choice = -1;
    private static String options[] = {
            "1. Game Play.", // option[0]
            "2. Exit Game." // option[1]
    };

    public Menu(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        gamePanel.playMusic(3);

    }

    public void update() {

        mouseX = gamePanel.getMouseX();
        mouseY = gamePanel.getMouseY();
        // System.out.println(mouseX + " " + mouseY);

        if (playBounds.contains(mouseX, mouseY) && (gameState == -1||gameState == 2)) {
            choice = 0;
        }else if (exitBounds.contains(mouseX, mouseY) && (gameState == -1||gameState == 2)) {
            choice = 1;
        } else
            choice = -1;

        if (gamePanel.isMousePressed() && (gameState == -1||gameState == 2)) {
            gameState = choice;
            if (gameState == 0) {
                gamePanel.reset();
                gamePanel.mousePressed = false;
                gamePanel.playSE(2);
            }
            
        }
    }

    public void render(Graphics2D graphics2d) {

        graphics2d.drawImage(images[4].getImage(), 0, 0, gamePanel.SCREEN_WIDTH, gamePanel.SCREEN_HEIGHT, null);

        Font font1 = new Font("Lato", Font.BOLD, 90);
        graphics2d.setFont(font1);
        graphics2d.setColor(Color.WHITE);

        if ((gameState == -1)) {
            graphics2d.drawImage(image1.getImage(), gamePanel.SCREEN_WIDTH / 2 + 250, 30, 300, 200, null);

        } else {
            if (winner == 1) {
                graphics2d.drawImage(image2.getImage(), gamePanel.SCREEN_WIDTH / 2 - 300, 30, 640, 400, null);
            } else {
                graphics2d.drawImage(image3.getImage(), gamePanel.SCREEN_WIDTH / 2 - 300, 30, 640, 400, null);
            }
        }

        for (int i = 0; i < options.length; i++) {
            if (i == choice) {
                graphics2d.drawImage(images[i + 2].getImage(), gamePanel.SCREEN_WIDTH / 2 - 200, 400 + i * 150,
                        images[i + 2].getWidth(), images[i + 2].getHeight(), null);
            } else {
                graphics2d.drawImage(images[i].getImage(), gamePanel.SCREEN_WIDTH / 2 - 200, 400 + i * 150,
                        images[i].getWidth(), images[i].getHeight(), null);
            }

        }
    }

}
