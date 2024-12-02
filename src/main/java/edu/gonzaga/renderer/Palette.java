package edu.gonzaga.renderer;

import java.awt.*;
import java.io.File;

public class Palette {
    public static final Color CLEAR = new Color(0, 0, 0, 0);
    public static final Color WHITE = Color.WHITE;
    public static final Color BLACK = Color.BLACK;
    public static final Color SHIP_OK = new Color(23, 109, 41);
    public static final Color SHIP_SUNK = new Color(109, 23, 41);
    public static final Color SHIP_PLACE_OK = new Color(11, 246, 58);
    public static final Color SHIP_PLACE_BAD = new Color(246, 11, 11);

    private static Font TITLE_FONT;
    private static Font PRIMARY_FONT;

    public static Font getTitleFont(int size) {
        if (TITLE_FONT == null) {
            try {
                TITLE_FONT = Font.createFont(Font.TRUETYPE_FONT, new File(
                        "res/fonts/SairaStencilOne-Regular.ttf"
                ));
            } catch (Exception e) {
                System.out.println("Error loading font: " + e.getMessage());
                TITLE_FONT = new Font("Arial", Font.BOLD, 1);
            }
        }

        return TITLE_FONT.deriveFont(Font.PLAIN, size);
    }

    public static Font getPrimaryFont(int size) {
        if (PRIMARY_FONT == null) {
            try {
                PRIMARY_FONT = Font.createFont(Font.TRUETYPE_FONT, new File(
                        "res/fonts/Geo-Regular.ttf"
                ));
            } catch (Exception e) {
                System.out.println("Error loading font: " + e.getMessage());
                PRIMARY_FONT = new Font("Arial", Font.PLAIN, 1);
            }
        }

        return PRIMARY_FONT.deriveFont(Font.PLAIN, size);
    }
}
