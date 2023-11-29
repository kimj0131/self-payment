package ezen.project.first.team2.app.payment.pages.main.views.right;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class RightView2_PointInfo extends View {

	private static final int PADDING = 20;
	
	private static final String POINT_INFO_TEXT_FORMAT = 
			"<html><center>"
			+ "결제 완료 후 포인트가 적립됩니다<br><br>"
			+ "[ 적립 예정 포인트 : %d P ]<br>"
			+ "[ 사용 가능한 포인트 : %d P ]"
			+ "</center></html>";

	private static final String USE_POINTS_BTN_TEXT = "포인트 사용";
	private static final String NOT_USE_POINTS_BTN_TEXT = "포인트 사용안함";
	
	// this.View
	private static final Color BACKGROUND_COLOR = new Color(244, 248, 251);
	
	// PointsInfo Label
	private static final Font MASSAGE_LABEL_FONT = new Font("맑은 고딕", Font.PLAIN, 29);
	private static final Color MASSAGE_LABEL_FONT_COLOR = new Color(103, 121, 133);
	private static final Color MASSAGE_LABEL_COLOR = new Color(255, 255, 255);
	
	// UsePoints & NotUsePoints Button
	private static final Font BTN_FONT = new Font("맑은 고딕", Font.BOLD, 19);
	private static final Color BTN_FONT_COLOR = new Color(255, 255, 255);
	private static final Color BTN_COLOR = new Color(79, 175, 86);
	
	JLabel mPointsInfo_label;
	JButton mUsePoints_btn;
	JButton mNotUsePoints_btn;

	// 그리드백 레이아웃을 사용하기 위한 constraint
	private GridBagConstraints mGbc;

	private ProductPurchasing mProdPurchasing;
	
	public RightView2_PointInfo() {
		super(MainPage.RIGHT_VIEW_POINT_INFO_NUM);
	}

	@Override
	protected void onInit() {
		
		mPointsInfo_label = new JLabel();
		mUsePoints_btn = new JButton(USE_POINTS_BTN_TEXT);
		mNotUsePoints_btn = new JButton(NOT_USE_POINTS_BTN_TEXT);
		
		// 그리드백 레이아웃을 사용하기 위한 constraint
		mGbc = new GridBagConstraints();
		
		// 메인 페이지에서 mProdPurchasing 가져오기
		MainPage mainPage = (MainPage) this.getPage();
		this.mProdPurchasing = mainPage.mProdPurchasing;
	}

	@Override
	protected void onSetLayout() {
		this.setBackground(BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridBagLayout());
		
		// 디자인 관련 설정
		mPointsInfo_label.setOpaque(true);
		mPointsInfo_label.setBackground(MASSAGE_LABEL_COLOR);
		mPointsInfo_label.setForeground(MASSAGE_LABEL_FONT_COLOR);
		mPointsInfo_label.setFont(MASSAGE_LABEL_FONT);
		mPointsInfo_label.setHorizontalAlignment(JLabel.CENTER);
		
		mUsePoints_btn.setFont(BTN_FONT);
		mUsePoints_btn.setBackground(BTN_COLOR);
		mUsePoints_btn.setForeground(BTN_FONT_COLOR);
		
		mNotUsePoints_btn.setFont(BTN_FONT);
		mNotUsePoints_btn.setBackground(BTN_COLOR);
		mNotUsePoints_btn.setForeground(BTN_FONT_COLOR);
	}

	@Override
	protected void onAddCtrls() {

		mGbc.fill = GridBagConstraints.BOTH;
		mGbc.weightx = 1;
		mGbc.weighty = 1;
		
		mGbc.gridwidth = 2;
		
		mGbc.gridx = 0;
		mGbc.gridy = 0;
		this.add(mPointsInfo_label, mGbc);

		mGbc.insets = new Insets(PADDING, 0, 0, PADDING / 2);
		
		mGbc.gridwidth = 1;
		
		mGbc.weighty = 0.05;
		mGbc.gridx = 0;
		mGbc.gridy = 1;
		this.add(mUsePoints_btn, mGbc);

		
		mGbc.insets = new Insets(PADDING, PADDING / 2, 0, 0);
		
		mGbc.gridx = 1;
		mGbc.gridy = 1;
		this.add(mNotUsePoints_btn, mGbc);
	}

	@Override
	protected void onAddEventListeners() {
		mUsePoints_btn.addActionListener(e -> {
			try {
				MainPage mainPage = (MainPage) this.getPage();
				mainPage.showPopupViewByNum(MainPage.POPUP_VIEW_USE_POINTS_NUM);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		mNotUsePoints_btn.addActionListener(e -> {
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

		var prodOrderItem = mProdPurchasing.getProdOrderItem();
		
		int earnedPoint = 0;
		int point = 0;
		
		try {
			// 이전 단계를 눌렀을 경우를 대비하여 리셋 시킨다
			prodOrderItem.setUsedPoint(0);
			
			// 적립될 포인트 계산해서 구매내역에 설정하고 반환
			earnedPoint = prodOrderItem.calcEarnedPoint();
			
			// 고객이 가지고 있는 포인트
			point = prodOrderItem.getCustItem().getPoint();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mPointsInfo_label.setText(String.format(POINT_INFO_TEXT_FORMAT, earnedPoint, point));
		
	}

	@Override
	protected void onHide() {
	}

	@Override
	protected void onSetResources() {
		Main main = (Main) this.getStatusManager();
		mPointsInfo_label.setFont(main.mFont0);
	}

}
