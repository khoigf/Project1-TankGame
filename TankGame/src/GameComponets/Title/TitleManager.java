package GameComponents.Title;

import java.awt.Graphics2D;

import GameComponents.GamePanel;

public class TitleManager {

    public final int MAX_TITLE = 10;
    private GamePanel gamePanel;

    private Title[] Titles = new Title[MAX_TITLE];

    public int numTitle = 2;

    public Title[] getTitles(){
        return Titles;
    }

    public void createTitle1() {
        Titles[0].create(64 * 5, 64*6, 64, 64 * 2);
        Titles[1].create(64 * 14, 64 * 3, 64, 64 * 2);
        
    }
    
    public void createTitle2() {
        Titles[0].create(64 * 10, 64*7, 64, 64 * 2);
        Titles[1].create(64 * 9, 64 * 2, 64, 64 * 2);

    }


    public void init() {
        for (int i = 0; i < MAX_TITLE; ++i) {
            Titles[i] = new Title(gamePanel, i);
        }
    }

    public TitleManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        init();
    }

    public void update() {
        for (int i = 0; i < numTitle; ++i) {
            Titles[i].update();
        }
    }

    public void render(Graphics2D graphics2d) {
        for (int i = 0; i < numTitle; ++i) {
            Titles[i].render(graphics2d);
        }
    }
}

