package GameComponents.Objects;

import Utilities.Image;
import Utilities.Tool;

public class HWall extends Object {
    public HWall(int x, int y) {
        super(x, y, 62, 44);
        image = new Image(Tool.getBufferedImage("Assets/Images/Objects/barrelGrey_h.png", 64, 45, 1));
        unKillable = true;
    }
}
