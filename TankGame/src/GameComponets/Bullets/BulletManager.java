package GameComponents.Bullets;

import java.awt.Graphics2D;

import GameComponents.GamePanel;

public class BulletManager {

    public final int MAX_BULLET = 50;

    private Bullet[] bullets;
    private GamePanel gamePanel;

    public Bullet[] getBullets() {
        return bullets;
    }

    public BulletManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        bullets = new Bullet[MAX_BULLET];
        init();
    }

    private void init() {
        for (int i = 0; i < MAX_BULLET; ++i) {
            bullets[i] = new Bullet(gamePanel);
        }
    }

    public void create(int owner_id, int direction, int x, int y) {
        for (int i = 0; i < MAX_BULLET; ++i) {
            if(bullets[i].inUse() == false) {
                bullets[i].create(owner_id, direction, x, y);
                break;
            }
        }
    }

    public void update() {
        for (int i = 0; i < MAX_BULLET; ++i) {
            bullets[i].update();
        }
    }

    public void render(Graphics2D graphics2d) {
        for (int i = 0; i < MAX_BULLET; ++i) {
            bullets[i].render(graphics2d);
        }
    }
}
