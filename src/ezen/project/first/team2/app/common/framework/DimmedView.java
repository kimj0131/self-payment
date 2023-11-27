////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231127MON_125600] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.framework;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class DimmedView extends JPanel {
	// -------------------------------------------------------------------------

	// -------------------------------------------------------------------------

	// 생성자
	public DimmedView() {
	}

	// -------------------------------------------------------------------------

	public void init(JFrame frame) {
		var viewSize = frame.getContentPane().getSize();

		this.setSize(viewSize);
		this.setOpaque(false);
		frame.getLayeredPane().add(this, JLayeredPane.PALETTE_LAYER);
		this.setVisible(false);

		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				e.consume();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				e.consume();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				e.consume();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				e.consume();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				e.consume();
			}
		});
	}

	// -------------------------------------------------------------------------

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
}
