package ezen.project.first.team2.app.payment.pages.main.views.right;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextArea;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class RightView2_PointInfo extends View {

	private static final int PADDING = 10;
	
	private static final String POINT_INFO_TEXT_FORMAT = "결제 완료 후 포인트가 적립 됩니다\n"
			+ "[ 적립 예정 포인트 : %d P ]\n"
			+ "[ 사용 가능한 포인트 : %d P ]";
	private static final String USE_POINTS_BTN_TEXT = "포인트 사용";
	private static final String NOT_USE_POINTS_BTN_TEXT = "포인트 사용안함";
	
	GridBagConstraints gbc = new GridBagConstraints();

	JTextArea mPointsInfo_ta;
	JButton mUsePoints_btn;
	JButton mNotUsePoints_btn;

	private ProductPurchasing mProdPurchasing;
	
	
	public RightView2_PointInfo() {
		super(MainPage.RIGHT_VIEW_POINT_INFO_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.DARK_GRAY);
		
		mPointsInfo_ta = new JTextArea();
		mUsePoints_btn = new JButton(USE_POINTS_BTN_TEXT);
		mNotUsePoints_btn = new JButton(NOT_USE_POINTS_BTN_TEXT);
		
		// 메인 페이지에서 mProdPurchasing 가져오기
		MainPage mainPage = (MainPage) this.getPage();
		this.mProdPurchasing = mainPage.mProdPurchasing;
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new GridBagLayout());
	}

	@Override
	protected void onAddCtrls() {
		mPointsInfo_ta.setEditable(false);
		mPointsInfo_ta.setFocusable(false);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		this.add(mPointsInfo_ta, gbc);

		gbc.weighty = 0.1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		this.add(mUsePoints_btn, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(mNotUsePoints_btn, gbc);
	}

	@Override
	protected void onAddEventListeners() {
		mUsePoints_btn.addActionListener(e -> {
			try {
				try {
					MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
					mainView.setSelectedRightViewByNum(MainPage.POPUP_VIEW_USE_POINTS_NUM);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
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
			// 적립된 포인트 계산해서 구매내역에 설정하고 반환
			earnedPoint = prodOrderItem.calcEarnedPoint();
			point = prodOrderItem.getCustItem().getPoint();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mPointsInfo_ta.setText(String.format(POINT_INFO_TEXT_FORMAT, earnedPoint, point));
		
	}

	@Override
	protected void onHide() {
	}

	@Override
	protected void onSetResources() {
		Main main = (Main) this.getStatusManager();
		mPointsInfo_ta.setFont(main.mFont0);
	}

}
