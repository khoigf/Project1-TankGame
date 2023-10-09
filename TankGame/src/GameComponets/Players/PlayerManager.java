package GameComponents.Players;

import java.awt.Graphics2D;

import GameComponents.GamePanel;

public class PlayerManager {

    public final int TEAM_A = 1;
    public final int TEAM_B = 2;
    public final int TEAM_SIZE = 3;
    
    public final int MAX_PLAYER = 10;
    public int soPlayer;
    private GamePanel gamePanel;

    private Player[] players = new Player[MAX_PLAYER];

    private int numPlayer = 0;

    public void reset() {
        for(int i = 0; i < MAX_PLAYER; ++i) {
                players[i].reset();

        }
        numPlayer=0;
    }
    
    public int getTeam(int player_id) {
        return players[player_id].getTeam();
    }

    public Player[] getPlayer() {
        return players;
    }

    public Player getPlayer(int player_id) {
        return players[player_id];
    }

    public void shoot(int id) {
        players[id].shoot();
    }

    public void createPlayer(int team) {
        if(numPlayer >= MAX_PLAYER) return;
        if(team == 1) {
            players[numPlayer].create(200-50, 160 + 50 * (numPlayer + 1), team);
        } else {
            players[numPlayer].create(1000+50, 160 + 50 * (numPlayer ), team);
        }
        numPlayer = numPlayer + 1;
    }

    public void init() {
        for(int i = 0; i < MAX_PLAYER; ++i) {
            players[i] = new Player(gamePanel, i);
        }
    }

    public PlayerManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        init();
    }

    public void update() {
        for(int i = 0; i < gamePanel.NumPlayer; ++i) {
            players[i].update();
        }
    }

    public void render(Graphics2D graphics2d) {
        for(int i = 0; i < gamePanel.NumPlayer; ++i) {
            players[i].render(graphics2d);
        }
    }
}
