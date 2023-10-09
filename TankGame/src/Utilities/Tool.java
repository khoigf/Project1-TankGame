package Utilities;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Tool {

    public static BufferedImage scaleImage(BufferedImage bufferedImage, int width, int height, int factor) {
        BufferedImage scaledImage = new BufferedImage(width * factor, height * factor, bufferedImage.getType());
        Graphics2D graphics2D = (Graphics2D) scaledImage.getGraphics();
        graphics2D.drawImage(bufferedImage, 0, 0, width * factor, height * factor, null);
        return scaledImage;
    }

    public static BufferedImage getBufferedImage(String imagePath, int width, int height, int factor) {
        BufferedImage bufferedImage = null;
        InputStream inputStream = Tool.class.getClassLoader().getResourceAsStream(imagePath);
        try {
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return scaleImage(bufferedImage, width, height, factor);
    }

    public static Image[] LoadSprite(String spritePath, int spriteNumber, int width, int height, int factor,
            double angle) {
        Image[] sprite = new Image[spriteNumber];
        BufferedImage spriteSheet = getBufferedImage(spritePath, width * spriteNumber, height, factor);
        for (int i = 0; i < spriteNumber; ++i) {
            sprite[i] = new Image(rotateImage(spriteSheet.getSubimage(i * width, 0, width, height), angle));
        }

        return sprite;
    }

    public static Image[] LoadFolderSprite(String spritePath, int spriteNumber, int width, int height, int factor,
            double angle) {
        Image[] sprite = new Image[spriteNumber];
        BufferedImage spriteImage = null;
        for (int i = 0; i < spriteNumber; ++i) {
            spriteImage = getBufferedImage(spritePath + "/" + (i + 1) + ".png", width, height, factor);
            sprite[i] = new Image(rotateImage(spriteImage, angle));
        }
        return sprite;
    }

    public static int Random(int left, int right) {
        return (int) Math.floor(Math.random() * (right - left + 1) + left);
    }

    public static BufferedImage rotateImage(BufferedImage buffImage, double angle) {
        double radian = Math.toRadians(angle);

        AffineTransform transform = new AffineTransform();
        transform.rotate(radian, buffImage.getWidth() / 2, buffImage.getHeight() / 2);

        // Create a new BufferedImage with the same dimensions and transparency type as the original image
        BufferedImage rotatedImage = new BufferedImage(
                buffImage.getWidth(), buffImage.getHeight(), buffImage.getType());

        // Create a Graphics2D object and apply the transformation
        Graphics2D g2d = rotatedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.transform(transform);

        // Draw the original image onto the rotated image
        g2d.drawImage(buffImage, 0, 0, null);
        g2d.dispose();

        return rotatedImage;
    }
}
