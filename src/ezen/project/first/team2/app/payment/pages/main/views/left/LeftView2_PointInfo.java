package ezen.project.first.team2.app.payment.pages.main.views.left;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerManagerMem;
import ezen.project.first.team2.app.common.modules.product.orders.ProductOrdersManagerMem;
import ezen.project.first.team2.app.payment.Main;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;
import ezen.project.first.team2.app.payment.pages.main.views.right.RightView0_OrderList;

public class LeftView2_PointInfo extends View {
	
	private static final int PADDING = 10;

	private static final String MSG_LABEL_TEXT_FORMAT = "<html>%s 고객님<br>확인해<br>주셔서<br>감사합니다</html>";
	private static final String PREV_BTN_TEXT = "이전단계";
	
	private String mMemName;
	
	JLabel mMsg_label;
	JButton mPrev_btn;
	
	private CustomerManagerMem mCustMngr;
	private ProductOrdersManagerMem mProdOrdersMngr;
	
	
	public LeftView2_PointInfo() {
		super(MainPage.LEFT_VIEW_POINT_INFO_NUM);
	}

	@Override
	protected void onInit() {
		setBackground(Color.GRAY);
		mMsg_label = new JLabel();
		mPrev_btn = new JButton(PREV_BTN_TEXT);
		
		mCustMngr = CustomerManagerMem.getInstance();
		mProdOrdersMngr = ProductOrdersManagerMem.getInstance();
	}

	@Override
	protected void onSetLayout() {
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	@Override
	protected void onAddCtrls() {
		this.add(this.mMsg_label);
		this.add(this.mPrev_btn);
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
			MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
			RightView0_OrderList rightView0 = (RightView0_OrderList) mainView.getViewByNum(MainPage.RIGHT_VIEW_ORDER_LIST_NUM);
			var prodOrderItem = mProdOrdersMngr.findById(rightView0.get_mGeneratedProdOrderId());
			
			// RightView0에서 발급된 구매내역id로 구매내역 찾고 구매내역에 있는 고객id로 고객 이름 가져오기
			mMemName = mCustMngr.findById(prodOrderItem.getCustId()).getName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mMsg_label.setText(String.format(MSG_LABEL_TEXT_FORMAT, mMemName));
		
		System.out.println();
	}

	@Override
	protected void onHide() {
	}

	@Override
	protected void onSetResources() {
		Main main = (Main) this.getStatusManager();
		mMsg_label.setFont(main.mFont0);
	}
}
