package ezen.project.first.team2.app.z_test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class SplitPaneTest {
	JFrame mFrame = new JFrame();
	JSplitPane mCntr = new JSplitPane();
	JPanel leftView = new JPanel();
	JPanel rightView0 = new JPanel();
	JPanel rightView1 = new JPanel();
	JPanel rightView2 = new JPanel();
	JPanel rightView3 = new JPanel();

	public static void main(String[] args) {
		SplitPaneTest test = new SplitPaneTest();
		test.test();
	}

	void createFrame() {
		mFrame.setSize(640, 360);
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setLocationRelativeTo(null);
		mFrame.setVisible(true);
	}

	void createCntr() {
		// mCntr.setOrientation(JSplitPane.VERTICAL_SPLIT);
		mCntr.setDividerSize(0);

		// mFrame.add(mCntr);
		mFrame.getContentPane().add(mCntr);
	}

	void createLeftView() {
		JButton btn0 = new JButton("버튼0");
		JButton btn1 = new JButton("버튼1");
		JButton btn2 = new JButton("버튼2");
		JButton btn3 = new JButton("버튼3");

		this.leftView.setLayout(new BoxLayout(this.leftView, BoxLayout.PAGE_AXIS));
		this.leftView.setBackground(Color.BLUE);
		this.leftView.setPreferredSize(new Dimension(240, 0));

		ActionListener listener = e -> {
			JButton btn = (JButton) e.getSource();
			if (btn == btn0) {
				this.mCntr.setRightComponent(this.rightView0);
			} else if (btn == btn1) {
				this.mCntr.setRightComponent(this.rightView1);
			} else if (btn == btn2) {
				this.mCntr.setRightComponent(this.rightView2);
			} else if (btn == btn3) {
				this.mCntr.setRightComponent(this.rightView3);
			}
		};

		btn0.addActionListener(listener);
		btn1.addActionListener(listener);
		btn2.addActionListener(listener);
		btn3.addActionListener(listener);

		this.leftView.add(btn0);
		this.leftView.add(btn1);
		this.leftView.add(btn2);
		this.leftView.add(btn3);

		this.mCntr.setLeftComponent(this.leftView);
	}

	void createRightViews() {
		JLabel lbl0 = new JLabel("첫 번째 뷰");
		JLabel lbl1 = new JLabel("두 번째 뷰");
		JLabel lbl2 = new JLabel("세 번째 뷰");
		JLabel lbl3 = new JLabel("네 번째 뷰");

		this.rightView0.add(lbl0);
		this.rightView1.add(lbl1);
		this.rightView2.add(lbl2);
		this.rightView3.add(lbl3);

		this.mCntr.setRightComponent(this.rightView0);
	}

	void test() {
		this.createFrame();
		this.createCntr();
		this.createLeftView();
		this.createRightViews();
	}

}
