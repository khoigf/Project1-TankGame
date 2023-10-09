package GameComponents.Base;

import java.awt.Graphics2D;

import GameComponents.GamePanel;
import Utilities.HitBox;
import Utilities.Image;

public class Base {

    public final int SIZE = 64;
    public final int WIN = 4; // số cờ win

    private GamePanel gamePanel;
    private int x, y;
    private Image image;
    private boolean inUse;
    private int team;
    private Image flagImage;
    private HitBox hitBox;

    private int teamFlag, enemyFlag;

    public int getTeamFlag() {
        return teamFlag;
    }

    public int getTeam() {
        return team;
    }

    public HitBox geHitBox() {
        return hitBox;
    }

    public void reset() {
        enemyFlag = 0;
        teamFlag = 4;
    }

    public Base(GamePanel gamePanel, int team) {
        this.gamePanel = gamePanel;
        this.team = team;
        inUse = false;
        if(team == 1) {
            image = new Image("Assets/Images/Objects/BlueBase.png", SIZE, SIZE, 1);
        } else {
            image = new Image("Assets/Images/Objects/RedBase.png", SIZE, SIZE, 1);
        }
        hitBox = new HitBox(x, y, 0, 0, SIZE, SIZE);
    }

    public void active(int x, int y) {
        this.x = x;
        this.y = y;
        hitBox.update(x, y);
        inUse = true;
        teamFlag = 4;
        enemyFlag = 0;
        if(team == 1) {
            flagImage = new Image("Assets/Images/Objects/BlueFlag.png", 16, 16, 2);
        } else {
            flagImage = new Image("Assets/Images/Objects/RedFlag.png", 16, 16, 2);
        }
    }

    public void update() {
        if(inUse) {
            if(winCondition()) {
                gamePanel.setWinner(team);
            }
            gamePanel.getCollisionChecker().BaseCheck(this);
        }
    }

    public void collectTeamFlag() {
        teamFlag = teamFlag + 1;
    }

    public void collectEnemyFlag() {
        enemyFlag = enemyFlag + 1;
    }

    public void rob() {
        teamFlag = teamFlag - 1;
    }

    public boolean winCondition() {
        if(enemyFlag >= WIN) return true;
        return false;
    }

    public void render(Graphics2D graphics2d) {
        if(inUse) {
            if(image != null) {
                graphics2d.drawImage(image.getImage(), x, y, image.getWidth(), image.getHeight(), null);
            } else {
                graphics2d.fillRect(x, y, SIZE, SIZE);
            }
            if(teamFlag > 0) {
                graphics2d.drawImage(flagImage.getImage(), x + 15, y - 5, flagImage.getWidth(), flagImage.getHeight(), null);
            }

            graphics2d.setFont(graphics2d.getFont().deriveFont(20F));
            graphics2d.drawString(String.valueOf(enemyFlag), x + 26, y + 72);
            hitBox.render(graphics2d);
        }
    }
}
