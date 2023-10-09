package GameComponents.Cannon;

import java.awt.Color;
import java.awt.Graphics2D;

import GameComponents.GamePanel;
import Utilities.HitBox;
import Utilities.Image;
import Utilities.Tool;

public class Cannon {
    public final int TANK_SIZE = 80;
    private boolean position;
    private GamePanel gamePanel;
    private int id, angle = 0, direction;
    private double x, y;
    private double numberAngle = 256;
    private Image body;
    private int timeTick = 0;
    public boolean isMove = false;
    private HitBox hitBox;

    public int getID() {
        return id;
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public Cannon(GamePanel gamePanel, int id) {
        this.gamePanel = gamePanel;
        this.id = id;
        if (id % 2 == 0) {
            angle = 0;
        } else {
            angle = (int) numberAngle / 2;
        }
    }

    public void create(int x, int y, boolean position) {
        this.position = position;
        this.x = x;
        this.y = y;
        if (position) {
            body = new Image("Assets/Images/Cannon/blueCannon.png", TANK_SIZE, TANK_SIZE, 1);
        } else {
            body = new Image("Assets/Images/Cannon/redCannon.png", TANK_SIZE, TANK_SIZE, 1);
        }
        hitBox = new HitBox(x, y, 15, 15, 30, 30);

    }

    private void changeAngle() {
        timeTick = timeTick + 1;
        if (isMove == true) {
            if (timeTick == 4) {
                if (angle * 360 / numberAngle % 45 == 0) {
                    shoot();
                }
                timeTick = 0;
                if (id % 2 == 0) {
                    angle = angle + 1;
                } else {
                    angle = angle - 1;
                }
                if (angle == numberAngle - 1)
                    angle = 0;
            }
        } else {
            if (timeTick == 80) {
                shoot();
                timeTick = 0;
            }
        }
    }

    private void changeDirection() {
        direction = angle;
    }

    public void shoot() {
        if (position) {
            gamePanel.getBulletManager().create(0, direction, (int) x, (int) y);
        } else {
            gamePanel.getBulletManager().create(1, direction, (int) x, (int) y);
        }
    }

    public void update() {
        changeAngle();
        changeDirection();
        // System.out.println("change1");
    }

    public void render(Graphics2D graphics2d) {
        graphics2d.setColor(Color.RED);
        graphics2d.setFont(graphics2d.getFont().deriveFont(20F));
        int intX = (int) x; // Chuyển đổi tọa độ x thành int
        int intY = (int) y; // Chuyển đổi tọa độ y thành int
        graphics2d.drawImage(Tool.rotateImage(body.getImage(), angle * 360 / numberAngle), intX, intY, body.getWidth(),
                body.getHeight(), null);
        hitBox.render(graphics2d);
    }
}