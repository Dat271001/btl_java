package controller;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import javax.swing.*;


public class NoScalingIcon implements Icon
{
    private Icon icon;

    public NoScalingIcon(Icon icon)
    {
        this.icon = icon;
    }

    public int getIconWidth()
    {
        return icon.getIconWidth();
    }

    public int getIconHeight()
    {
        return icon.getIconHeight();
    }

    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Graphics2D g2d = (Graphics2D)g.create();

        AffineTransform at = g2d.getTransform();

        int scaleX = (int)(x * at.getScaleX());
        int scaleY = (int)(y * at.getScaleY());

        int offsetX = (int)(icon.getIconWidth() * (at.getScaleX() - 1) / 2);
        int offsetY = (int)(icon.getIconHeight() * (at.getScaleY() - 1) / 2);

        int locationX = scaleX + offsetX;
        int locationY = scaleY + offsetY;

        AffineTransform scaled = AffineTransform.getScaleInstance(1.0 / at.getScaleX(), 1.0 / at.getScaleY());
        at.concatenate( scaled );
        g2d.setTransform( at );

        icon.paintIcon(c, g2d, locationX, locationY);

        g2d.dispose();
    }


    public  void createAndShowGUI()
    {
        JButton button = new JButton();
        button.setSize(100,80);
        button.setBackground(Color.red);
        NoScalingIcon icon = new NoScalingIcon( new ImageIcon(new File("D:\\New Folder\\BTL_Java\\src\\img\\icons8-home-screen-100.png").getAbsolutePath()));
        button.setIcon( icon );

        JPanel panel = new JPanel( );
        panel.setLayout(null);
        panel.add( button );

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(panel);
        f.setSize(200, 200);
        f.setLocationRelativeTo( null );
        f.setVisible(true);
    }
}