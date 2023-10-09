package GameComponents.Buff;

import java.awt.Graphics2D;

import GameComponents.GamePanel;
import Utilities.Tool;

public class BuffManager {

    public final int MAX = 100;

    private Buff[] buffs = new Buff[MAX];
    private GamePanel gamePanel;
    private int buffNumber;

    public void createRandom(int number) {
        for (int i = 0; i < number; ++i) {
            createBuff(Tool.Random(200, gamePanel.SCREEN_WIDTH - 200), Tool.Random(150, gamePanel.SCREEN_HEIGHT - 150));
        }
    }

    public BuffManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        buffNumber = 0;
        init();
    }

    public void init() {
        for (int i = 0; i < MAX; ++i) {
            buffs[i] = new Buff(gamePanel, i);
        }
    }

    public void createBuff(int x, int y) {
        buffNumber = buffNumber + 1;
        for (int i = 0; i < MAX; ++i) {
            if (buffs[i].inUse() == false) {
                buffs[i].active(x, y);
                break;
            }
        }
    }

    public void destroy(int id) {
        buffNumber = buffNumber - 1;
        buffs[id].delete();
    }

    public void update() {
        for (int i = 0; i < MAX; ++i) {
            buffs[i].update();
            if(buffs[i].getTime() <= 0) destroy(buffs[i].getID());
        }
        while(buffNumber < 5) {
            createRandom(1);
        }
    }

    public void render(Graphics2D graphics2d) {
        for (int i = 0; i < MAX; ++i) {
            buffs[i].render(graphics2d);
        }
    }
}
