package GameComponents.Objects;

import Utilities.Image;
import Utilities.Tool;

public class VWall extends Object {
    public VWall(int x, int y) {
        super(x, y, 45, 62);
        image = new Image(Tool.getBufferedImage("Assets/Images/Objects/barrelGrey_v.png", 45, 64, 1));
        unKillable = true;
    }
}
