package ezen.project.first.team2.app.payment.pages.main.views.popup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import ezen.project.first.team2.app.common.framework.PopupView;
import ezen.project.first.team2.app.common.modules.product.purchasing.ProductPurchasing;
import ezen.project.first.team2.app.common.utils.UnitUtils;
import ezen.project.first.team2.app.payment.pages.main.MainPage;
import ezen.project.first.team2.app.payment.pages.main.views.MainView;

public class PopUpView3_UsePoints extends PopupView {

	private static final int PADDING = 20;
	private static final Dimension VIEW_SIZE = new Dimension(600, 500);

	// 컴포넌트에 설정되는 텍스트 모음
	private static final String AVAIL_POINTS_TITLE_LABEL_TEXT = "사용가능한 포인트";
	private static final String POINTS_TO_USE_TITLE_LABEL_TEXT = "사용할 포인트";
	private static final String ALL_USED_BTN_TEXT = "전부사용";
	private static final String DEL_BTN_TEXT = "전체삭제";
	private static final String CHECK_BTN_TEXT = "확인";
	private static final String CANCEL_BTN_TEXT = "취소";
	
	// this.View
	private static final Color BACKGROUND_COLOR = new Color(244, 248, 251);
	
	// title Label
	private static final Font TITLE_LABEL_FONT = new Font("맑은 고딕", Font.BOLD, 19);
	private static final Color TITLE_LABEL_FONT_COLOR = new Color(103, 121, 133);
	private static final Color TITLE_LABEL_COLOR = new Color(225, 239, 248);
	
	// Label
	private static final Font LABEL_FONT = new Font("맑은 고딕", Font.PLAIN, 19);
	private static final Color LABEL_FONT_COLOR = new Color(103, 121, 133);
	private static final Color LABEL_COLOR = new Color(225, 239, 248);
	
	// number pad Button
	private static final Font NUM_BTN_FONT = new Font("맑은 고딕", Font.BOLD, 20);
	private static final Color NUM_BTN_FONT_COLOR = new Color(91, 111, 125);
	private static final Color NUM_BTN_COLOR = new Color(255, 255, 255);
	
	// Check & Cancel Button
	private static final Font BTN_FONT = new Font("맑은 고딕", Font.BOLD, 19);
	private static final Color BTN_FONT_COLOR = new Color(255, 255, 255);
	private static final Color BTN_COLOR = new Color(3, 181, 208);
	
	private JLabel mAvailPoints_title_label;
	private JLabel mAvailPoints_label;

	private JLabel mPointsToUse_title_label;
	private JLabel mPointsToUse_label;

	// 숫자패드
	private StringBuilder mPointsToUse; // 사용할 포인트
	private JPanel mNum_panel; // 숫자버튼을 담을 패널
	private JButton[] mNum_btn_arr; // "숫자" 버튼을 모아두는 배열
	private JButton mAllUsed_btn; // 포인트 전부 사용 버튼
	private JButton mDel_btn; // 사용하려고 했던 포인트 전부 삭제 버튼
	//
	
	// mAvailPoints_title_label / mAvailPoints_tf
	// mPointsToUse_title_label / mPointsToUse_tf
	// mNum_panel
	// 들어가는 패널
	private JPanel mTop_panel;

	private JButton mCheck_btn;
	private JButton mCancel_btn;

	// 그리드백 레이아웃을 사용하기 위한 constraint
	private GridBagConstraints mGbc;

	private ProductPurchasing mProdPurchasing;

	public PopUpView3_UsePoints() {
		super(MainPage.POPUP_VIEW_USE_POINTS_NUM, VIEW_SIZE);
	}

	@Override
	protected void onInit() {
		
		super.onInit();

		mAvailPoints_title_label = new JLabel(AVAIL_POINTS_TITLE_LABEL_TEXT);
		mAvailPoints_label = new JLabel();

		mPointsToUse_title_label = new JLabel(POINTS_TO_USE_TITLE_LABEL_TEXT);
		mPointsToUse_label = new JLabel();
		
		mTop_panel = new JPanel();

		// 숫자 패드 만들기
		mPointsToUse = new StringBuilder(0);
		mNum_panel = new JPanel();
		mNum_btn_arr = new JButton[10];
		for (int i = 0; i < mNum_btn_arr.length; ++i)
			mNum_btn_arr[i] = makeButton(String.valueOf(i));
		mAllUsed_btn = makeButton(ALL_USED_BTN_TEXT);
		mDel_btn = makeButton(DEL_BTN_TEXT);

		mCheck_btn = new JButton(CHECK_BTN_TEXT);
		mCancel_btn = new JButton(CANCEL_BTN_TEXT);

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
		setLayout(new GridBagLayout());
		
		mTop_panel.setLayout(new GridBagLayout());
		mTop_panel.setBackground(Color.WHITE);
		mTop_panel.setBorder(BorderFactory.createEmptyBorder(
				PADDING, PADDING, PADDING, PADDING));

		// 디자인 관련 설정 ------------------------------------------------
		
		// 숫자패드 레이아웃 설정
		GridLayout numPanalGrl = new GridLayout(4, 3);
		numPanalGrl.setHgap(10);
		numPanalGrl.setVgap(10);
		mNum_panel.setLayout(numPanalGrl);
		mNum_panel.setBackground(Color.WHITE);
		
		// 라벨 배경색 설정하기 위해 setOpaque(true); 
		mAvailPoints_title_label.setOpaque(true);
		mAvailPoints_label.setOpaque(true);
		mPointsToUse_title_label.setOpaque(true);
		mPointsToUse_label.setOpaque(true);
		
		// 라벨 안 상하좌우 여백 설정
		Border labelBorder = BorderFactory.createEmptyBorder(0, 5, 0, 5);
		mAvailPoints_title_label.setBorder(labelBorder);
		mAvailPoints_label.setBorder(labelBorder);
		mPointsToUse_title_label.setBorder(labelBorder);
		mPointsToUse_label.setBorder(labelBorder);
		
		mAvailPoints_title_label.setFont(TITLE_LABEL_FONT);
		mAvailPoints_title_label.setForeground(TITLE_LABEL_FONT_COLOR);
		mAvailPoints_title_label.setBackground(TITLE_LABEL_COLOR);
		mAvailPoints_label.setFont(LABEL_FONT);
		mAvailPoints_label.setForeground(LABEL_FONT_COLOR);
		mAvailPoints_label.setBackground(LABEL_COLOR);
		mAvailPoints_label.setHorizontalAlignment(JLabel.RIGHT);
		
		mPointsToUse_title_label.setFont(TITLE_LABEL_FONT);
		mPointsToUse_title_label.setForeground(TITLE_LABEL_FONT_COLOR);
		mPointsToUse_title_label.setBackground(TITLE_LABEL_COLOR);
		mPointsToUse_label.setFont(LABEL_FONT);
		mPointsToUse_label.setForeground(LABEL_FONT_COLOR);
		mPointsToUse_label.setBackground(LABEL_COLOR);
		mPointsToUse_label.setHorizontalAlignment(JLabel.RIGHT);
		
		mCheck_btn.setBackground(BTN_COLOR);
		mCheck_btn.setForeground(BTN_FONT_COLOR);
		mCheck_btn.setFont(BTN_FONT);
		
		mCancel_btn.setBackground(BTN_COLOR);
		mCancel_btn.setForeground(BTN_FONT_COLOR);
		mCancel_btn.setFont(BTN_FONT);
	}

	@Override
	protected void onAddCtrls() {
		//
		mGbc.fill = GridBagConstraints.BOTH;
		mGbc.weightx = 1;
		//

		mGbc.weighty = 0.05;		

		mGbc.gridx = 0;
		mGbc.gridy = 0;
		mTop_panel.add(mAvailPoints_title_label, mGbc);

		mGbc.gridx = 1;
		mGbc.gridy = 0;
		mTop_panel.add(mAvailPoints_label, mGbc);

		mGbc.insets = new Insets(5, 0, 0, 0);

		mGbc.gridx = 0;
		mGbc.gridy = 1;
		mTop_panel.add(mPointsToUse_title_label, mGbc);

		mGbc.gridx = 1;
		mGbc.gridy = 1;
		mTop_panel.add(mPointsToUse_label, mGbc);

		// 숫자패널에 숫자, 전부사용, 취소 버튼 달기
		for (JButton btn : mNum_btn_arr) {
			if (btn.getName() != "0")
				mNum_panel.add(btn);
		}
		mNum_panel.add(mAllUsed_btn);
		mNum_panel.add(mNum_btn_arr[0]);
		mNum_panel.add(mDel_btn);

		mGbc.weighty = 0.5;
		mGbc.insets = new Insets(PADDING, 0, 0, 0);
		
		mGbc.gridwidth = 2;
		mGbc.gridx = 0;
		mGbc.gridy = 2;
		mTop_panel.add(mNum_panel, mGbc);

		
		// TopPanel, CheckBtn, CancelBtn
		
		mGbc.weighty = 1;
		mGbc.insets = new Insets(0, 0, 0, 0);
		
		mGbc.gridwidth = 2;
		mGbc.gridx = 0;
		mGbc.gridy = 0;
		this.add(mTop_panel, mGbc);
		
		
		mGbc.weighty = 0.06;
		mGbc.insets = new Insets(PADDING, 0, 0, PADDING / 2);
		
		mGbc.gridwidth = 1;
		mGbc.gridx = 0;
		mGbc.gridy = 1;
		this.add(mCheck_btn, mGbc);
		
		mGbc.insets = new Insets(PADDING, PADDING / 2, 0, 0);
		mGbc.gridx = 1;
		mGbc.gridy = 1;
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
						mPointsToUse_label.setText(pointsToUse);
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
				mPointsToUse_label.setText(pointsToUse);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		});

		mDel_btn.addActionListener(e -> {
			mPointsToUse.delete(0, mPointsToUse.length());
			mPointsToUse_label.setText(mPointsToUse.toString());
		});

		mCheck_btn.addActionListener(e -> {
			try {
				performClose();

				if (mPointsToUse.length() != 0)
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
				performClose();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}

	@Override
	protected void onShow(boolean firstTime) {

		try {
			// 이전 단계로 다시 왔을 경우를 대비해 리셋
			mPointsToUse.delete(0, mPointsToUse.length());
			mPointsToUse_label.setText("");

			int custPoint = mProdPurchasing.getProdOrderItem().getCustItem().getPoint();
			mAvailPoints_label.setText(UnitUtils.numToCurrencyStr(custPoint));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onHide() {
	}

	@Override
	protected void onSetResources() {
	}

	private JButton makeButton(String text) {

		JButton numBtn = new JButton(text) {
			@Override
			protected void paintComponent(Graphics g) {
				int width = getWidth();
				int height = getHeight();
				Graphics2D graphics = (Graphics2D) g;
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				// 마우스를 올리면 밝아지고 누르면 어두워진다
				if (getModel().isArmed()) {
					graphics.setColor(NUM_BTN_COLOR.darker());
				} else if (getModel().isRollover()) {
					graphics.setColor(NUM_BTN_COLOR.brighter());
				} else {
					graphics.setColor(NUM_BTN_COLOR);
				}

				// 라운드 모양을 설정
				graphics.fillRoundRect(0, 0, width, height, 10, 10);

				FontMetrics fontMetrics = graphics.getFontMetrics();
				Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
				int textX = (width - stringBounds.width) / 2;
				int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();

				// 폰트 draw 작업
				graphics.setColor(NUM_BTN_FONT_COLOR);
				graphics.setFont(getFont());
				graphics.drawString(getText(), textX, textY);
				graphics.drawRoundRect(0, 0, width - 1, height - 1, 10, 10);
				graphics.dispose();
				super.paintComponent(g);
			}
		};

		numBtn.setFont(NUM_BTN_FONT);
		numBtn.setBorderPainted(false);
		numBtn.setContentAreaFilled(false);

		return numBtn;
	}
}
