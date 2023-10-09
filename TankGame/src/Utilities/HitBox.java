package Utilities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class HitBox {

    private Rectangle hitBox;
    private int w, h;
    public int x, y, width, height;

    public Rectangle getHitBox() {
        return hitBox;
    }

    public HitBox(int x, int y, int w, int h, int width, int height) {
        hitBox = new Rectangle(x + w, y + h, width, height);
        this.x = x + w;
        this.y = y + h;
        this.w = w;
        this.h = h;
        this.width = width;
        this.height = height;
    }

    public void update(int x, int y) {
        this.x = x + w;
        this.y = y + h;
        hitBox.x = this.x;
        hitBox.y = this.y;
    }

    public boolean isCollision(HitBox hitBox) {
        return this.hitBox.intersects(hitBox.getHitBox());
    }

    public void render(Graphics2D graphics2D) {
        // graphics2D.fillRect(x , y, width, height);
    }
}