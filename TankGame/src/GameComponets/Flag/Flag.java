package GameComponents.Flag;

import java.awt.Graphics2D;

import GameComponents.GamePanel;
import Utilities.HitBox;
import Utilities.Image;

public class Flag {

    private GamePanel gamePanel;
    private int x, y;
    private Image flagImage;
    private boolean inUse;
    private HitBox hitBox;
    private int team;

    public HitBox getHitBox() {
        return hitBox;
    }

    public int getTeam() {
        return team;
    }

    public Image getImage() {
        return flagImage;
    }

    public void destroy() {
        inUse = false;
    }

    public boolean inUse() {
        return inUse;
    }

    public Flag(GamePanel gamePanel) {
        inUse = false;
        hitBox = new HitBox(x, y, 0, 0, 32, 32);
        team = -1;
        this.gamePanel = gamePanel;
    }

    public Flag(int team) {
        this.team = team;
        if (team == 1) {
            flagImage = new Image("Assets/Images/Objects/BlueFlag.png", 32, 32, 1);
        } else {
            flagImage = new Image("Assets/Images/Objects/RedFlag.png", 32, 32, 1);
        }
    }

    public void active(int x, int y, int team) {
        this.team = team;
        if (team == 1) {
            flagImage = new Image("Assets/Images/Objects/BlueFlag.png", 32, 32, 1);
        } else {
            flagImage = new Image("Assets/Images/Objects/RedFlag.png", 32, 32, 1);
        }
        inUse = true;
        this.x = x;
        this.y = y;
        hitBox.update(x, y);
    }

    public void update() {
        if (inUse) {
            gamePanel.getCollisionChecker().FlagCheckPlayer(this);
        }
    }

    public void render(Graphics2D graphics2d) {
        if (inUse) {
            graphics2d.drawImage(flagImage.getImage(), x, y, flagImage.getWidth(), flagImage.getHeight(), null);
            hitBox.render(graphics2d);
        }
    }
}
