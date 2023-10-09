package GameComponents.Bullets;

import java.awt.Graphics2D;

import GameComponents.GamePanel;
import Utilities.HitBox;
import Utilities.Image;
import Utilities.Tool;

public class Bullet {

    private Image[] sprites;
    private int animationTick, animationSprite, spriteNumber;
    private boolean inUse;
    private int  direction;
    private double speed = 8.0; // Tốc độ di chuyển
    private double numberAngle = 256;

    private double x, y; // Chuyển sang kiểu double

    private HitBox hitBox;
    private GamePanel gamePanel;
    private int owner_id;

    public int getOwnerID() {
        return owner_id;
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public void delete() {
        inUse = false;
    }

    public boolean inUse() {
        return inUse;
    }
    
    public Bullet(GamePanel gamePanel) {
        inUse = false;
        this.gamePanel = gamePanel;

        spriteNumber = 10;
        sprites = Tool.LoadSprite("Assets/Images/Bullets/bullet.png", spriteNumber, 45, 45, 1, 0);
    }

    public void create(int owner_id, int direction, int x, int y) {
        this.owner_id = owner_id;
        this.x = x;
        this.y = y;
        this.direction = direction;

        inUse = true;
        hitBox = new HitBox(x, y, 0, 0, 32, 32);
    }

    public void move() {
        double angleRad = Math.toRadians(-90+direction * 360/numberAngle); // Chuyển đổi góc thành radian
        double dx = speed * Math.cos(angleRad); // Tính toán thay đổi tọa độ x
        double dy = speed * Math.sin(angleRad); // Tính toán thay đổi tọa độ y
        x = x + dx;
        y = y + dy;
        hitBox.update((int) x, (int) y);
                if(x < 0 || x > gamePanel.SCREEN_WIDTH || y < 0 || y > gamePanel.SCREEN_HEIGHT) {
            inUse = false;
        }
        gamePanel.getCollisionChecker().bulletCheck(this);
        gamePanel.getCollisionChecker().bulletCheckTitle(this);


    }

    public void update() {
        if(inUse) {
            move();
            animationTick = animationTick + 1;
            if(animationTick > gamePanel.GAME_FPS / spriteNumber) {
                animationTick = 0;
                animationSprite = animationSprite + 1;
                if(animationSprite == spriteNumber) {
                     animationSprite = 0;
                }
            }
        } else {
            animationSprite = 0;
            animationTick = 0;
        }
    }

    public void render(Graphics2D graphics2d) {
        if(inUse) {
            graphics2d.drawImage(sprites[animationSprite].getImage(), (int)x, (int)y, sprites[animationSprite].getWidth(), sprites[animationSprite].getHeight(), null);
            hitBox.render(graphics2d);
        }
    }
}
