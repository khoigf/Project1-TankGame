package GameComponents.Objects;

import Utilities.Image;
import Utilities.Tool;

public class Tree extends Object{
    public Tree(int x, int y) {
        super(x, y, 50, 50);
        image = new Image(Tool.getBufferedImage("Assets/Images/Objects/Tree.png", 50, 50, 1));
        unKillable = false;
    }
}


