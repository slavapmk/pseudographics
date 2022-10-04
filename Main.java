package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static final int HEIGHT = 30, WIDTH = 119;
    public static final int FPS = 1;
    public static final double charProportion = 0.5;
    public static final String GRADIENT = " .:-=+*#%@";

    public static void main(String[] args) throws IOException {
        new Main().init();
    }

    public void init() throws IOException {
        System.out.println(
                convert(
                        ImageIO.read(new File("C:\\Users\\sch1561-it-25\\Pictures\\test.png"))
                )
        );
    }

//    public void init() {
//        long last = System.currentTimeMillis();
//        while (true) {
//            long current = System.currentTimeMillis();
//            if (current - last >= 1000 / FPS) {
//                update();
//                last = current;
//            }
//        }
//    }
//
//    int i = 0;
//
//    public void update() {
//        BufferedImage abc = render(i++ + "");
//        System.out.println(convert(abc));
//    }

    public StringBuilder convert(BufferedImage image) {
        StringBuilder sb = new StringBuilder("\n");
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++)
                sb.append(parse(image.getRGB(x, y)));
            sb.append("\n");
        }
        return sb;
    }

    public static String parse(int color) {
        Color color1 = new Color(color);
        double darkness = (color1.getRed() + color1.getGreen() + color1.getBlue()) / 3.0 / 255.0;
        return String.valueOf(GRADIENT.charAt((int) Math.round((GRADIENT.length() - 1) * darkness)));
    }

    public BufferedImage render(String s) {
        double height = HEIGHT / charProportion;
        BufferedImage bufferedImage = new BufferedImage(WIDTH, (int) height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();

        FontMetrics fontMetrics = graphics.getFontMetrics();
        int parseWidth = fontMetrics.stringWidth(s);
        int parseHeight = s.split("\n").length * fontMetrics.getHeight();

        graphics.setFont(new Font("TimesRoman", Font.PLAIN, HEIGHT));

        graphics.drawString(
                s,
                WIDTH / 2 - parseWidth / 2,
                (int) (height / 2 + parseHeight / 2)
        );

        return resize(bufferedImage, WIDTH, HEIGHT);
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}
