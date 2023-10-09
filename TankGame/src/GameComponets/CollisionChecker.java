package GameComponents;

import GameComponents.Base.Base;
import GameComponents.Buff.Buff;
import GameComponents.Bullets.Bullet;
import GameComponents.Flag.Flag;
import GameComponents.Players.Player;

public class CollisionChecker {

    private GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public boolean objectCheck(Player player) {
        if (gamePanel.chooseMap == 1) {
            for (int i = 0; i < gamePanel.getMap().getObjectManager().MAX_OBJECT; ++i) {
                if (gamePanel.getMap().getObjectManager().getObjects()[i].inUse()) {
                    if (player.getHitBox()
                            .isCollision(gamePanel.getMap().getObjectManager().getObjects()[i].getHitBox())) {
                        return true;
                    }
                }
            }
        } else {
            for (int i = 0; i < gamePanel.getMap2().getObjectManager().MAX_OBJECT; ++i) {
                if (gamePanel.getMap2().getObjectManager().getObjects()[i].inUse()) {
                    if (player.getHitBox()
                            .isCollision(gamePanel.getMap2().getObjectManager().getObjects()[i].getHitBox())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // public boolean bulletCheck(Bullet bullet) {
    // for (int i = 0; i < gamePanel.getMap().getObjectManager().MAX_OBJECT; ++i) {
    // if (gamePanel.getMap().getObjectManager().getObjects()[i].inUse()) {
    // if
    // (bullet.getHitBox().isCollision(gamePanel.getMap().getObjectManager().getObjects()[i].getHitBox()))
    // {
    // bullet.delete();
    // if (!(gamePanel.getMap().getObjectManager().getObjects()[i] instanceof Wall))
    // {
    // gamePanel.getMap().getObjectManager().getObjects()[i].destroy();
    // }
    // return true;
    // }
    // }
    // }
    // return false;
    // }

    public boolean bulletCheck(Bullet bullet) {
        if (gamePanel.chooseMap == 1) {
            for (int i = 0; i < gamePanel.getMap().getObjectManager().MAX_OBJECT; ++i) {
                if (gamePanel.getMap().getObjectManager().getObjects()[i].inUse()) {
                    if (bullet.getHitBox()
                            .isCollision(gamePanel.getMap().getObjectManager().getObjects()[i].getHitBox())) {
                        gamePanel.playSE(4);
                        bullet.delete();
                        gamePanel.getMap().getObjectManager().getObjects()[i].destroy();

                    }
                }
            }
        } else {
            for (int i = 0; i < gamePanel.getMap2().getObjectManager().MAX_OBJECT; ++i) {
                if (gamePanel.getMap2().getObjectManager().getObjects()[i].inUse()) {
                    if (bullet.getHitBox()
                            .isCollision(gamePanel.getMap2().getObjectManager().getObjects()[i].getHitBox())) {
                        gamePanel.playSE(4);
                        bullet.delete();
                        gamePanel.getMap2().getObjectManager().getObjects()[i].destroy();

                    }
                }
            }
        }
        return false;
    }

    public boolean tankCheck(Player player) {
        if (player.inUse() == false)
            return false;
        for (int i = 0; i < gamePanel.getBulletManager().MAX_BULLET; ++i) {
            if (gamePanel.getBulletManager().getBullets()[i].inUse()) {
                if (gamePanel.getPlayerManager()
                        .getTeam(gamePanel.getBulletManager().getBullets()[i].getOwnerID()) == player.getTeam())
                    continue;
                if (player.getHitBox().isCollision(gamePanel.getBulletManager().getBullets()[i].getHitBox())) {
                    if (player.getID() != gamePanel.getBulletManager().getBullets()[i].getOwnerID()) {
                        gamePanel.playSE(1);
                        player.destroy();
                        gamePanel.getBulletManager().getBullets()[i].delete();
                    }
                }
            }
        }
        return false;
    }

    public int buffCheck(Buff buff) {
        for (Player player : gamePanel.getPlayerManager().getPlayer()) {
            if (player.inUse() == false) {
                continue;
            }
            if (buff.getHitBox().isCollision(player.getHitBox())) {
                gamePanel.getBuffManager().destroy(buff.getID());
                gamePanel.playSE(4);
                return player.getID();
            }
        }
        return -1;
    }

    public boolean bulletCheckTitle(Bullet bullet) {
        for (int i = 0; i < gamePanel.getTitleManager().numTitle; i++) {
            if (gamePanel.getTitleManager().getTitles()[i].getInUse()) {
                if (bullet.getHitBox().isCollision(gamePanel.getTitleManager().getTitles()[i].getHitBox())) {
                    bullet.delete();
                }
            }
        }
        return false;
    }

    public int tankCheckTitle(Player player) {
        for (int i = 0; i < gamePanel.getTitleManager().numTitle; i++) {
            if (gamePanel.getTitleManager().getTitles()[i].getInUse()) {
                if (player.getHitBox().isCollision(gamePanel.getTitleManager().getTitles()[i].getHitBox())) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void BaseCheck(Base base) {
        for (Player player : gamePanel.getPlayerManager().getPlayer()) {
            if (player.inUse() == false)
                continue;
            if (player.getHitBox().isCollision(base.geHitBox())) {
                if (player.getFlag() != null && player.getTeam() == base.getTeam()) {
                    if (player.getFlag().getTeam() == base.getTeam()) {
                        base.collectTeamFlag();
                        player.lostFlag();
                        break;
                    }
                    if (player.getFlag().getTeam() != base.getTeam()) {
                        base.collectEnemyFlag();
                        player.lostFlag();
                        break;
                    }
                }
                if (player.getTeam() != base.getTeam() && player.getFlag() == null && base.getTeamFlag() > 0) {
                    base.rob();
                    player.receiveFlag(new Flag(base.getTeam()));
                }
            }
        }
    }

    public void FlagCheckPlayer(Flag flag) {
        for (Player player : gamePanel.getPlayerManager().getPlayer()) {
            if (player.inUse() == false)
                continue;
            if (player.getFlag() != null)
                continue;
            if (player.getHitBox().isCollision(flag.getHitBox())) {
                player.receiveFlag(flag);
                flag.destroy();
                break;
            }
        }
    }

    public void update() {

    }
}
