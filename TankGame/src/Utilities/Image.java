package Utilities;

import java.awt.image.BufferedImage;

public class Image {
    private BufferedImage bufferedImage;
    private int width, height;

    public Image(String imagePath, int width, int height, int factor) {
        bufferedImage = Tool.getBufferedImage(imagePath, width, height, factor);
        this.width = width * factor;
        this.height = height * factor;
    }

    public Image(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
    }

    public BufferedImage getImage() {
        return bufferedImage;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
