package ezen.project.first.team2.app.payment.pages.main.views.right;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextArea;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class RightView2_PointInfo extends View {

	private static final int PADDING = 10;
	private static final String TEXT_AREA_TEXT_FORMAT = "결제 완료 후 포인트가 적립 됩니다\n"
			+ "[ 적립 예정 포인트 : %d P ]\n"
			+ "[ 사용 가능한 포인트 : %d P ]";

	GridBagConstraints gbc = new GridBagConstraints();

	int mEarnedPoints = 100;
	int mAvailablePoints;

	JTextArea mPointInfo;
	JButton mUsePointButton;
	JButton mNotUsePointButton;

	CustomerItem mCustomerItem;

	public RightView2_PointInfo() {
		super(MainPage.RIGHT_VIEW_POINT_INFO_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.DARK_GRAY);
		
		mPointInfo = new JTextArea();
		mUsePointButton = new JButton("포인트사용");
		mNotUsePointButton = new JButton("포인트사용안함");
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridBagLayout());
	}

	@Override
	protected void onAddCtrls() {
		mPointInfo.setEditable(false);
		mPointInfo.setFocusable(false);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		this.add(mPointInfo, gbc);

		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		this.add(mUsePointButton, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(mNotUsePointButton, gbc);
	}

	@Override
	protected void onAddEventListeners() {
		mUsePointButton.addActionListener(e -> {
			try {
				UiUtils.showMsgBox("포인트 사용 팝업", MainPage.TITLE);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		mNotUsePointButton.addActionListener(e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_PAYMENT_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_PAYMENT_NUM);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	@Override
	protected void onShow(boolean firstTime) {
		MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
		RightView1_CheckMember rightView = (RightView1_CheckMember) mainView
				.getViewByNum(MainPage.RIGHT_VIEW_CHECK_MEMBER_NUM);
		mCustomerItem = rightView.getCustomerItem();
		mPointInfo.setText(String.format(TEXT_AREA_TEXT_FORMAT, mEarnedPoints, mCustomerItem.getPoint()));
	}

	@Override
	protected void onHide() {
	}

	@Override
	protected void onSetResources() {
		Main main = (Main) this.getStatusManager();
		mPointInfo.setFont(main.mFont0);

	}

}
