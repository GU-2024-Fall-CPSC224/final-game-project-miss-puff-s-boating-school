package edu.gonzaga.renderer;

import edu.gonzaga.ships.Ship;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class ImageBuffers
{
    private static final String[] SHIP_NAMES = {"carrier", "battleship", "cruiser", "submarine", "destroyer"};
    private final HashMap<String, Image> imageBuffers;

    public static final ImageBuffers instance = new ImageBuffers();

    public static ImageBuffers getInstance() {
        return instance;
    }

    public Image getImage(String name) {
        return imageBuffers.get(name);
    }

    public Image getShipImage(Ship.ShipType type, String suffix) {
        String key = type.name().toLowerCase();

        if (suffix != null) {
            key += "-" + suffix;
        }

        return imageBuffers.get(key);
    }

    public Image getShipSideImage(Ship.ShipType type) {
        return imageBuffers.get(type.name().toLowerCase() + "-side");
    }

    private ImageBuffers() {
        imageBuffers = new HashMap<>();

        for (String name : SHIP_NAMES) {
            loadShip(name);
        }

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

            imageBuffers.put(name, tint(base, Color.WHITE));
            imageBuffers.put(name + "-red", tint(base, Palette.SHIP_PLACE_BAD));
            imageBuffers.put(name + "-green", tint(base, Palette.SHIP_PLACE_OK));

            BufferedImage side = ImageIO.read(new File("res/ships/" + name + "-side.png"));
            imageBuffers.put(name + "-side", side);
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
