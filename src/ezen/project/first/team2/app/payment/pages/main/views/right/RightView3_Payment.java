package ezen.project.first.team2.app.payment.pages.main.views.right;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.product.order_details.ProductOrderDetailsManager;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class RightView3_Payment extends View {

	private static final int PADDING = 20;
	
	// this.View
	private static final Color BACKGROUND_COLOR = new Color(244, 248, 251);

	private boolean mIsPaymentComplete = false;

	private JButton mPaymentCompleted_btn;
	private JPanel mPanel0;
	
	private ProductPurchasing mProdPurchasing;

	
	public RightView3_Payment() {
		super(MainPage.RIGHT_VIEW_PAYMENT_NUM);
	}

	@Override
	protected void onInit() {
		
		mPaymentCompleted_btn = new JButton("결제완료");
		mPanel0 = new JPanel();

		// 메인 페이지에서 mProdPurchasing 가져오기
		MainPage mainPage = (MainPage) this.getPage();
		this.mProdPurchasing = mainPage.mProdPurchasing;

	}

	@Override
	protected void onSetLayout() {
		this.setBackground(BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));
		this.setLayout(new BorderLayout());
		
		mPanel0.setBackground(Color.WHITE);
	}

	@Override
	protected void onAddCtrls() {
		mPanel0.add(mPaymentCompleted_btn);
		this.add(mPanel0);
	}

	@Override
	protected void onAddEventListeners() {
		mPaymentCompleted_btn.addActionListener(e -> {

			mIsPaymentComplete = true;
			RightView0_OrderList.RECEIPT_ISSUANCE = false;

			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_ORDER_LIST_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_ORDER_LIST_NUM);

				RightView0_OrderList rv0 = (RightView0_OrderList) mainView
						.getViewByNum(MainPage.RIGHT_VIEW_ORDER_LIST_NUM);

				// 결제 완료가 됐다면 RightView0_OrdetList에 테이블 초기화
				rv0.get_mTableModel().setNumRows(0);
				// 결제 완료가 됐다면 RightView0_OrdetList에 총금액 텍스트필드 초기화
				rv0.get_mSum_tf().setText("");
				// 결제 완료가 됐다면 RightView0_OrdetList에 결제하기 버튼 비활성화
				rv0.deactivateButton();

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// 콘솔에 영수증 출력
			System.out.println("right3 - 결제완료버튼 누름");
			System.out.println("right3 - 영수증");
			try {
				ProductOrderDetailsManager.getInstance().iterate((item, idx) -> {
					if (item.getProdOrderId() == mProdPurchasing.getProdOrderId()) {
						System.out.println(item);
					}
					return true;
				});
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}

	@Override
	protected void onShow(boolean firstTime) {
	}

	@Override
	protected void onHide() {
		// 결제완료 버튼을 눌러 결제가 완료되었다면 커밋한다
		if (mIsPaymentComplete) {
			try {
				System.out.println("right3 - 커밋되었습니다");
				mProdPurchasing._6_commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			mIsPaymentComplete = false;
		}
	}

}
