package GameComponents.Gun;

import java.awt.Graphics2D;

import GameComponents.GamePanel;
import Utilities.Image;
import Utilities.Tool;

public class Gun {

    public final int LEFT = 0;
    public final int RIGHT = 1220;
    
    private int x, y;
    private boolean direction, position;
    private boolean inUse;
    private int movementSpeed = 2;
    private Image image;
    private GamePanel gamePanel;
    private int attackDelay = 200;

    /// 0 1 2 
    /// 3 4 5

    public void shoot() {
        if(attackDelay == 0) {
            attackDelay = 200;
            if(position) {
                gamePanel.getBulletManager().create(0, Tool.Random(96, 224) - 64, x, y);
            } else {
                gamePanel.getBulletManager().create(1, Tool.Random(32, 96) - 64, x, y);
            }
        }
        attackDelay = attackDelay - 1;
    }


    public Gun(GamePanel gamePanel, int x, int y, boolean position) {
        this.gamePanel = gamePanel;
        this.x = x;
        this.y = y;
        inUse = true;
        this.position = position;
        if(position) {
            image = new Image("Assets/Images/Cannon/TopGun1.png", 50, 50, 1);
            direction = true;
        } else {
            image = new Image("Assets/Images/Cannon/BotGun1.png", 50, 50, 1);
            direction = false;
        }
    }

    public void update() {
        if(inUse) {
            shoot();
            if(direction) { // left -> right
                x = x + movementSpeed;
            } else {
                x = x - movementSpeed;
            }
            if(x < LEFT || x > RIGHT) {
                direction = !direction;
            }
        }
    }

    public void render(Graphics2D graphics2D) {
        if(inUse) {
            graphics2D.drawImage(image.getImage(), x, y, image.getWidth(), image.getHeight(), null);
        }
    }

}
