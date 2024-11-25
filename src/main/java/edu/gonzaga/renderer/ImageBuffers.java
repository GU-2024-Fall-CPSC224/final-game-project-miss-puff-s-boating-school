package edu.gonzaga.renderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class ImageBuffers
{
    private final HashMap<String, BufferedImage> imageBuffers;

    public static final ImageBuffers instance = new ImageBuffers();

    public static ImageBuffers getInstance() {
        return instance;
    }

    public BufferedImage getImage(String name) {
        return imageBuffers.get(name);
    }

    private ImageBuffers() {
        imageBuffers = new HashMap<>();

        loadShip("carrier");
        loadShip("battleship");
        loadShip("cruiser");
        loadShip("submarine");
        loadShip("destroyer");

        try {
            imageBuffers.put("hit", ImageIO.read(new File("res/board/hit.png")));
            imageBuffers.put("miss", ImageIO.read(new File("res/board/miss.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadShip(String name) {
        try {
            BufferedImage base = ImageIO.read(new File("res/ships/" + name + ".png"));

            imageBuffers.put(name + "-base", base);
            imageBuffers.put(name, tint(base, Color.WHITE));
            imageBuffers.put(name + "-red", tint(base, Color.RED));
            imageBuffers.put(name + "-green", tint(base, Color.GREEN));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BufferedImage tint(BufferedImage image, Color color) {
        BufferedImage tinted = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TRANSLUCENT);

        Graphics2D g = tinted.createGraphics();
        g.setXORMode(color);
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return tinted;
    }
}
