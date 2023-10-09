package GameComponents.Buff;

import java.awt.Graphics2D;

import GameComponents.GamePanel;
import Utilities.HitBox;
import Utilities.Image;
import Utilities.Tool;

public class Buff {

    public final int SIZE = 32;

    protected int x, y;
    protected boolean inUse;
    protected Image image;
    protected HitBox hitBox;
    protected int powerId;
    protected GamePanel gamePanel;
    protected int id;
    protected int time;

    public int getTime() {
        if(inUse == false) return 1;
        return time;
    }

    public int getID() {
        return id;
    }

    public boolean inUse() {
        return inUse;
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public Buff(GamePanel gamePanel, int id) {
        this.id = id;
        this.gamePanel = gamePanel;
        inUse = false;
        image = new Image("Assets/Images/Buff/Buff.png", SIZE / 2, SIZE / 2, 2);
        hitBox = new HitBox(x, y, 0, 0, SIZE, SIZE);
    }

    public void active(int x, int y) {
        time = 1000;
        this.x = x;
        this.y = y;
        hitBox.update(this.x, this.y);
        inUse = true;
    }

    public void execute(int player_id) {

        if (player_id < 0)
            return;
        powerId = Tool.Random(1, 4);
        if (powerId == 1) {
            // tang toc
            gamePanel.getPlayerManager().getPlayer(player_id).setMovementSpeed(4.0);
        }
        if (powerId == 2) {
            gamePanel.getPlayerManager().getPlayer(player_id).setUnKillable();
        }
        if (powerId == 3) {
            gamePanel.getPlayerManager().getPlayer(player_id).setMovementSpeed(4.0);

        }
        if (powerId == 4) {
            gamePanel.getPlayerManager().getPlayer(player_id).setUnKillable();
        }
    }

    public void delete() {
        inUse = false;
    }

    public void update() {
        if (inUse) {
            time = time - 1;
            int check = gamePanel.getCollisionChecker().buffCheck(this);
            if (check != -1){
                execute(check);
                gamePanel.playSE(0);
            }
            
        }
    }

    public void render(Graphics2D graphics2d) {
        if (inUse) {
            if (image == null) {
                graphics2d.fillRect(x, y, SIZE, SIZE);
            } else {
                graphics2d.drawImage(image.getImage(), x, y, image.getWidth(), image.getHeight(), null);
            }
            hitBox.render(graphics2d);
        }
    }

}
