package GameComponents.Flag;

import java.awt.Graphics2D;

import GameComponents.GamePanel;

public class FlagManager {
    
    public final int MAX = 30;

    private Flag[] flags = new Flag[MAX];
    private GamePanel gamePanel;

    public FlagManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        init();
    }

    private void init() {
        for(int i = 0; i < MAX; ++i) flags[i] = new Flag(gamePanel);
    }

    public void createFlag(int x, int y, int team) {
        for(int i = 0; i < MAX; ++i) {
            if(flags[i].inUse() == false) {
                flags[i].active(x, y, team);
                break;
            }
        }
    }

    public void update() {
        for(int i = 0; i < MAX; ++i) flags[i].update();
    }

    public void render(Graphics2D graphics2d) {
        for(int i = 0; i < MAX; ++i) flags[i].render(graphics2d);
    }
}
