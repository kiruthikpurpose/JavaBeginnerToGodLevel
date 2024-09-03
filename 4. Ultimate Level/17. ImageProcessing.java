import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageProcessing {
    public static void main(String[] args) {
        try {
            File input = new File("input.jpg");
            BufferedImage image = ImageIO.read(input);

            BufferedImage grayImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int rgb = image.getRGB(x, y);
                    grayImage.setRGB(x, y, rgb);
                }
            }

            File output = new File("output.jpg");
            ImageIO.write(grayImage, "jpg", output);
            System.out.println("Grayscale image created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
