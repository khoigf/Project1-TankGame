package GameComponents.Gun;

import java.awt.Graphics2D;

import GameComponents.GamePanel;

public class GunManager {
    
    private Gun topGun, botGun;
    private Gun topGun2, botGun2;

    public GunManager(GamePanel gamePanel) {
        topGun = new Gun(gamePanel, 0, 0, true);
        botGun = new Gun(gamePanel, 1220, 640, false);

        topGun2 = new Gun(gamePanel, 1220, 0, true);
        botGun2 = new Gun(gamePanel, 0, 640, false);
    }

    public void update() {
        topGun.update();
        botGun.update();

        topGun2.update();
        botGun2.update();
    }

    public void render(Graphics2D graphics2d) {
        topGun.render(graphics2d);
        botGun.render(graphics2d);

        topGun2.render(graphics2d);
        botGun2.render(graphics2d);
    }
}
