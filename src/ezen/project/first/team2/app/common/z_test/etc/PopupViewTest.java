package ezen.project.first.team2.app.common.z_test.etc;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class PopupViewTest {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        JLayeredPane cntr = f.getLayeredPane();

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setPreferredSize(new Dimension(640, 360));

        f.pack();
        Dimension viewSize = f.getContentPane().getSize();
        System.out.printf("f.getBounds(): %s, viewSize: %s \n",
                f.getBounds(), viewSize);

        f.setLocationRelativeTo(null);

        var view = new JPanel();
        view.setSize(viewSize);
        view.setBackground(Color.GREEN);
        cntr.add(view, JLayeredPane.DEFAULT_LAYER);

        var dimmedView = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g.create();
                // 50% transparent Alpha
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

                g2d.setColor(Color.BLACK);
                g2d.fill(getBounds());
                g2d.dispose();
            }
        };
        dimmedView.setSize(viewSize);
        dimmedView.setOpaque(false);
        cntr.add(dimmedView, JLayeredPane.PALETTE_LAYER);
        dimmedView.setVisible(false);
        dimmedView.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                arg0.consume();
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                arg0.consume();
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                arg0.consume();
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                arg0.consume();
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                arg0.consume();
            }
        });

        var popupView = new JPanel();
        popupView.setSize(new Dimension(160, 90));
        popupView.setBackground(Color.BLUE);
        cntr.add(popupView, JLayeredPane.POPUP_LAYER);
        popupView.setVisible(false);

        var closeBtn = new JButton("닫기");
        closeBtn.addActionListener(e -> {
            popupView.setVisible(false);
            dimmedView.setVisible(false);
        });
        popupView.add(closeBtn);

        var showPopupBtn = new JButton("팝업");
        showPopupBtn.addActionListener(e -> {
            int x = (int) ((viewSize.getWidth() - popupView.getWidth()) / 2);
            int y = (int) ((viewSize.getHeight() - popupView.getHeight()) / 2);
            popupView.setLocation(x, y);

            dimmedView.setVisible(true);
            popupView.setVisible(true);
        });
        view.add(showPopupBtn);

        f.setVisible(true);
    }
}