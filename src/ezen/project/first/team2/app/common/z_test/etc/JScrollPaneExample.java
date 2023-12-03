package ezen.project.first.team2.app.common.z_test.etc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

public class JScrollPaneExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("JScrollPane Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 360);

        // 스크롤 가능한 텍스트 영역 생성
        JTextArea textArea = new JTextArea(10, 20);
        textArea.setText("This is a JTextArea inside a JScrollPane. It can contain a large amount of text.");

        // JScrollPane에 텍스트 영역 추가
        // JScrollPane scrollPane = new JScrollPane(textArea);
        JPanel panel = new JPanel() {
            public void paint(Graphics g) {
                var g2 = (Graphics2D) g;
                var vp = (JViewport) this.getParent();
                // var sp = (JScrollPane) vp.getParent();

                System.out.println(vp.getViewRect());
                // System.out.println(vp.getViewPosition());

                g2.setColor(Color.WHITE);
                g2.fill(vp.getViewRect());
                // System.out.println(getBounds());

                g2.setColor(Color.BLUE);
                g2.drawString("Hello", 100, 100);

                for (int y = 0; y < 1024; y += 20) {
                    g2.drawString(y + "", 0, y);
                }

            };
        };
        // panel.setPreferredSize(new Dimension(1024, 1024));

        JScrollPane scrollPane = new JScrollPane(panel);

        // 프레임에 스크롤 패널 추가
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
