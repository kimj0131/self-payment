package ezen.project.first.team2.app.payment.pages.main.views.popup;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.common.utils.UnitUtils;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class PopUpView3_UsePoints extends View {
	
	private static final int PADDING = 10;
	
	// 컴포넌트에 설정되는 텍스트 모음
	private static final String AVAIL_POINTS_TITLE_LABEL_TEXT = "사용가능한 포인트";
	private static final String POINTS_TO_USE_TITLE_LABEL_TEXT = "사용할 포인트";
	private static final String ALL_USED_BTN_TEXT = "전부사용";
	private static final String DEL_BTN_TEXT = "전체삭제";
	private static final String CHECK_BTN_TEXT = "확인";
	private static final String CANCEL_BTN_TEXT = "취소";
	
	private JLabel mAvailPoints_title_label;
	private JTextField mAvailPoints_tf;
	
	private JLabel mPointsToUse_title_label;
	private JTextField mPointsToUse_tf;
		
	// 숫자패드
	private StringBuilder mPointsToUse; // 사용할 포인트
	private JPanel mNum_panel; // 숫자버튼을 담을 패널
	private JButton[] mNum_btn_arr; // "숫자" 버튼을 모아두는 배열
	private JButton mAllUsed_btn; // 포인트 전부 사용 버튼
	private JButton mDel_btn; // 사용하려고 했던 포인트 전부 삭제 버튼
	//
	
	private JButton mCheck_btn;
	private JButton mCancel_btn;
	
	// 그리드백 레이아웃을 사용하기 위한 constraint
	private GridBagConstraints mGbc;

	private ProductPurchasing mProdPurchasing;
	
	public PopUpView3_UsePoints() {
		super(MainPage.POPUP_VIEW_USE_POINTS_NUM);
	}

	@Override
	protected void onInit() {
		setSize(100, 100);
		
		//
		mAvailPoints_title_label = new JLabel(AVAIL_POINTS_TITLE_LABEL_TEXT);
		mAvailPoints_tf = new JTextField();
		
		mPointsToUse_title_label = new JLabel(POINTS_TO_USE_TITLE_LABEL_TEXT);
		mPointsToUse_tf = new JTextField();
		//
		
		
		// 숫자 패드 만들기
		mPointsToUse = new StringBuilder(0);
		mNum_panel = new JPanel();
		mNum_btn_arr = new JButton[10];
		for (int i = 0; i < mNum_btn_arr.length; ++i) {
			mNum_btn_arr[i] = new JButton(String.valueOf(i));
		}
		mAllUsed_btn = new JButton(ALL_USED_BTN_TEXT);
		mDel_btn = new JButton(DEL_BTN_TEXT);

		
		mCheck_btn = new JButton(CHECK_BTN_TEXT);
		mCancel_btn = new JButton(CANCEL_BTN_TEXT);
		
		
		mGbc = new GridBagConstraints();
		
		// 메인 페이지에서 mProdPurchasing 가져오기
		MainPage mainPage = (MainPage) this.getPage();
		this.mProdPurchasing = mainPage.mProdPurchasing;
	}

	@Override
	protected void onSetLayout() {
		setLayout(new GridBagLayout());
		
		// 숫자패드 레이아웃 설정
		mNum_panel.setLayout(new GridLayout(4, 3));
	}

	@Override
	protected void onAddCtrls() {
		//
		mGbc.fill = GridBagConstraints.BOTH;
		//
		
		mGbc.gridx = 0;
		mGbc.gridy = 0;
		this.add(mAvailPoints_title_label, mGbc);
		
		mGbc.gridx = 1;
		mGbc.gridy = 0;
		this.add(mAvailPoints_tf, mGbc);
		
		mGbc.gridx = 0;
		mGbc.gridy = 1;
		this.add(mPointsToUse_title_label, mGbc);
		
		mGbc.gridx = 1;
		mGbc.gridy = 1;
		this.add(mPointsToUse_tf, mGbc);
		

		// 숫자패널에 숫자, 전부사용, 취소 버튼 달기
		for (JButton btn : mNum_btn_arr) {
			if (btn.getName() != "0")
				mNum_panel.add(btn);
		}
		mNum_panel.add(mAllUsed_btn);
		mNum_panel.add(mNum_btn_arr[0]);
		mNum_panel.add(mDel_btn);

		mGbc.gridwidth = 2;
		mGbc.gridx = 0;
		mGbc.gridy = 2;
		this.add(mNum_panel, mGbc);
		//
		
		mGbc.gridwidth = 1;
		mGbc.gridx = 0;
		mGbc.gridy = 4;
		this.add(mCheck_btn, mGbc);
		
		mGbc.gridx = 1;
		mGbc.gridy = 4;
		this.add(mCancel_btn, mGbc);
	}

	@Override
	protected void onAddEventListeners() {
		
		// 숫자버튼에 액션리스너 달기
		for (JButton btn : mNum_btn_arr) {
			btn.addActionListener(e -> {
				
				if (e.getSource() == mNum_btn_arr[0] && mPointsToUse.length() == 0) {
					return;
				}
				
				for (int i = 0; i < mNum_btn_arr.length; ++i) {
					if (e.getSource() == mNum_btn_arr[i]) {
						// mPointsToUse에 숫자 추가후 형식맞게 변환하여 TextField에 적용
						mPointsToUse.append(i);
						String pointsToUse = UnitUtils.numToCurrencyStr(Integer.valueOf(mPointsToUse.toString()));
						mPointsToUse_tf.setText(pointsToUse);
						break;
					}
				}
			});
		}
		
		
		mAllUsed_btn.addActionListener(e -> {
			try {
				// 고객이 가지고 있는 포인트
				int custPoint = mProdPurchasing.getProdOrderItem().getCustItem().getPoint();

				// 고객이 가지고 있는 포인트가 최종 가격보다 크다면 최종가격 만큼만 포인트 사용
				if (mProdPurchasing.getProdOrderItem().getFinalTotalPrice() < custPoint)
					custPoint = mProdPurchasing.getProdOrderItem().getFinalTotalPrice();

				// mPointsToUse에 숫자 적용 후 형식맞게 변환하여 TextField에 적용
				mPointsToUse.delete(0, mPointsToUse.length());
				mPointsToUse.append(custPoint);
				String pointsToUse = UnitUtils.numToCurrencyStr(Integer.valueOf(mPointsToUse.toString()));
				mPointsToUse_tf.setText(pointsToUse);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		});
		
		mDel_btn.addActionListener(e -> {
			mPointsToUse.delete(0, mPointsToUse.length());
			mPointsToUse_tf.setText(mPointsToUse.toString());
		});
		
		
		mCheck_btn.addActionListener(e -> {
			try {
			
				mProdPurchasing._4_setUsedPoint(Integer.valueOf(mPointsToUse.toString()));
				
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedLeftViewByNum(MainPage.LEFT_VIEW_PAYMENT_NUM);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_PAYMENT_NUM);
				
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		});
		
		mCancel_btn.addActionListener(e -> {
			try {
				MainView mainView = (MainView) this.getPage().getViewByNum(MainPage.VIEW_NUM_MAIN);
				mainView.setSelectedRightViewByNum(MainPage.RIGHT_VIEW_POINT_INFO_NUM);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}

	@Override
	protected void onShow(boolean firstTime) {
		
		try {
			int custPoint = mProdPurchasing.getProdOrderItem().getCustItem().getPoint();
			mAvailPoints_tf.setText(UnitUtils.numToCurrencyStr(custPoint));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void onHide() {
	}

	@Override
	protected void onSetResources() {}
	
}
