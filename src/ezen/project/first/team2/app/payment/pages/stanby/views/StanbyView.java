package ezen.project.first.team2.app.payment.pages.stanby.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.stanby.StanbyPage;

public class StanbyView extends View {
	
	private static final int PADDING = 20;
	
	private static final String MSG_TEXT = "화면을 터치해 주세요";
	
	// title
	private static final Font MSG_FONT = new Font("맑은 고딕", Font.BOLD, 40);
	private static final Color MSG_FONT_COLOR = new Color(103, 121, 133);
	
	// this.View
	private static final Color BACKGROUND_COLOR = new Color(244, 248, 251);
	
	private JLabel mMsg_label;
	private JPanel mBackground_panel;

	public StanbyView() {
		super(StanbyPage.VIEW_NUM_STANBY);
		
		mMsg_label = new JLabel(MSG_TEXT);
		mBackground_panel = new JPanel();
	}

	@Override
	protected void onInit() {
	}

	@Override
	protected void onSetLayout() {
		GridLayout gl = new GridLayout(1, 1);
		
		this.setBackground(BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(gl);
		this.setBackground(BACKGROUND_COLOR);
		
		mBackground_panel.setLayout(gl);
		mBackground_panel.setBackground(Color.WHITE);
		
		mMsg_label.setFont(MSG_FONT);
		mMsg_label.setForeground(MSG_FONT_COLOR);
		mMsg_label.setHorizontalAlignment(JLabel.CENTER);
	}

	@Override
	protected void onAddCtrls() {
		mBackground_panel.add(mMsg_label);
		this.add(mBackground_panel);
	}

	@Override
	protected void onAddEventListeners() {
		// 화면 터치시 메인 페이지로
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					getStatusManager().setSelectedPageByNum(Main.PAGE_NUM_MAIN);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void onShow(boolean firstTime) {
	}

	@Override
	protected void onHide() {
	}

}
