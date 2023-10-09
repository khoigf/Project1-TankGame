package GameComponents.Base;

import java.awt.Graphics2D;

import GameComponents.GamePanel;

public class BaseManager {

    public final int MAX = 10;

    private Base[] bases = new Base[MAX];
    private GamePanel gamePanel;

    public void reset() {
        for(int i = 0; i < MAX; ++i) bases[i].reset();
    }
    
    public BaseManager( GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        init();
    }

    private void init() {
        for(int i = 0; i < MAX; ++i) bases[i] = new Base(gamePanel, i);
    }

    public Base getBase(int team) {
        return bases[team];
    }

    public void update() {
        for(int i = 0; i < MAX; ++i) bases[i].update();
    }

    public void render(Graphics2D graphics2d) {
         for(int i = 0; i < MAX; ++i) bases[i].render(graphics2d);
    }
}
