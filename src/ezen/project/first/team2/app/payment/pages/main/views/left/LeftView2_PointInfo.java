package ezen.project.first.team2.app.payment.pages.main.views.left;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.customer.CustomerManager;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class LeftView2_PointInfo extends View {

	private static final int PADDING = 20;

	private static final String TITLE_LABEL_TEXT_FORMAT = "<html>%s 회원님<br>확인해<br>주셔서<br>감사합니다</html>";
	private static final String PREV_BTN_TEXT = "이전단계";
	
	// this.View
	private static final Color BACKGROUND_COLOR = new Color(244, 248, 251);
	
	// title
	private static final Font TITLE_FONT = new Font("맑은 고딕", Font.PLAIN, 29);
	private static final Color TITLE_FONT_COLOR = new Color(103, 121, 133);
	
	// Prev button
	private static final Font PREV_BTN_FONT = new Font("맑은 고딕", Font.BOLD, 19);
	private static final Color PREV_BTN_FONT_COLOR = new Color(255, 255, 255);
	private static final Color PREV_BTN_COLOR = new Color(21, 150, 136);

	private String mMemName;

	JLabel mTitle_label;
	JButton mPrev_btn;
	
	// 그리드백 레이아웃을 사용하기 위한 constraint
	private GridBagConstraints mGbc;

	private CustomerManager mCustMngr;
	private ProductPurchasing mProdPurchasing;

	public LeftView2_PointInfo() {
		super(MainPage.LEFT_VIEW_POINT_INFO_NUM);
	}

	@Override
	protected void onInit() {
		mTitle_label = new JLabel();
		mPrev_btn = new JButton(PREV_BTN_TEXT);
		
		// 그리드백 레이아웃을 사용하기 위한 constraint
		mGbc = new GridBagConstraints();

		mCustMngr = CustomerManager.getInstance();

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
		mTitle_label.setFont(TITLE_FONT);
		mTitle_label.setForeground(TITLE_FONT_COLOR);
		
		mPrev_btn.setFont(PREV_BTN_FONT);
		mPrev_btn.setBackground(PREV_BTN_COLOR);
		mPrev_btn.setForeground(PREV_BTN_FONT_COLOR);
	}

	@Override
	protected void onAddCtrls() {
		mGbc.anchor = GridBagConstraints.NORTH;
		mGbc.weighty = 1;
		
		mGbc.gridx = 0;
		mGbc.gridy = 0;
		this.add(this.mTitle_label, mGbc);
		
		mGbc.fill = GridBagConstraints.BOTH;
		mGbc.weighty = 0.05;
		mGbc.gridx = 0;
		mGbc.gridy = 1;
		this.add(this.mPrev_btn, mGbc);
	}

	@Override
	protected void onAddEventListeners() {
		mPrev_btn.addActionListener(e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_CHECK_MEMBER_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_CHECK_MEMBER_NUM);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	@Override
	protected void onShow(boolean firstTime) {

		try {
			var prodOrderItem = mProdPurchasing.getProdOrderItem();

			// 구매내역에 들어간 고객ID로 이름 가져오기
			mMemName = mCustMngr.findById(prodOrderItem.getCustId()).getName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		mTitle_label.setText(String.format(TITLE_LABEL_TEXT_FORMAT, mMemName));

		System.out.println();
	}

	@Override
	protected void onHide() {
	}

	@Override
	protected void onSetResources() {
		Main main = (Main) this.getStatusManager();
		mTitle_label.setFont(main.mFont0);
	}
}
