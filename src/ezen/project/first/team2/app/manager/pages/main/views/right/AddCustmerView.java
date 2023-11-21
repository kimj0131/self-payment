package ezen.project.first.team2.app.manager.pages.main.views.right;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.customer.CustomerInfo;
import ezen.project.first.team2.app.common.modules.customer.CustomerManagerMem;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class AddCustmerView extends View {

    CustomerManagerMem memMngr = CustomerManagerMem.getInstance();

    JLabel mLabelInfo = new JLabel("회원 추가뷰 초기화면입니다");

    JLabel mLabelSignUp = new JLabel("회원정보기입");

    JPanel mPanelAddCust = new JPanel();

    // 고객정보 기입란
    // 가입일은 자동 추가
    JPanel mAddIdPanel = new JPanel();
    JLabel mAddIdLabel = new JLabel("회원 번호 : ");
    JTextField mAddId = new JTextField(10);

    JPanel mAddNamePanel = new JPanel();
    JLabel mAddNameLabel = new JLabel("이름 : ");
    JTextField mAddName = new JTextField("", 10);

    JPanel mAddBirthdayPanel = new JPanel();
    JLabel mAddBirthdayLabel = new JLabel("생년월일 : ");
    //
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    JFormattedTextField mAddBirthday = new JFormattedTextField(format);
    //

    JPanel mAddPhoneNumPanel = new JPanel();
    JLabel mAddPhoneNumLabel = new JLabel("휴대폰번호 : ");
    JTextField mAddPhoneNum = new JTextField(10);

    JPanel mAddRemarkPanel = new JPanel();
    JLabel mAddRemarkLabel = new JLabel("비고 : ");
    JTextField mAddRemark = new JTextField(10);

    JButton mAddCustBtn = new JButton("[테스트]고객 추가");
    JButton mAddTextFieldCustBtn = new JButton("자동기입");

    public AddCustmerView() {
        super(MainPage.VIEW_NUM_CUST_ADD);
    }

    @Override
    protected void onInit() {

    }

    @Override
    protected void onSetLayout() {
        this.setLayout(new BorderLayout());
        // this.mAddCustPanel.setLayout(new GridLayout(7, 1));
        this.mPanelAddCust.setLayout(new BoxLayout(mPanelAddCust, BoxLayout.Y_AXIS));
    }

    @Override
    protected void onAddCtrls() {

        // 컴포넌트 설정
        // 최상단 라벨 설정
        this.mLabelInfo.setOpaque(true);
        this.mLabelInfo.setBackground(Color.LIGHT_GRAY);
        this.mLabelInfo.setHorizontalAlignment(JLabel.CENTER);

        // 라벨 위치설정
        this.mLabelSignUp.setHorizontalAlignment(JLabel.CENTER);

        this.mAddIdLabel.setHorizontalAlignment(JLabel.LEFT);
        this.mAddNameLabel.setHorizontalAlignment(JLabel.LEFT);
        this.mAddBirthdayLabel.setHorizontalAlignment(JLabel.LEFT);
        this.mAddPhoneNumLabel.setHorizontalAlignment(JLabel.LEFT);
        this.mAddRemarkLabel.setHorizontalAlignment(JLabel.LEFT);

        this.mPanelAddCust.setBackground(Color.WHITE);

        this.mAddBirthday.setColumns(10);

        // 컴포넌트 삽입
        this.add(mLabelInfo, BorderLayout.NORTH);
        this.add(mPanelAddCust, BorderLayout.CENTER);

        this.mPanelAddCust.add(mLabelSignUp);

        this.mPanelAddCust.add(mAddIdPanel);
        this.mAddIdPanel.add(mAddIdLabel);
        this.mAddIdPanel.add(mAddId);

        this.mPanelAddCust.add(mAddNamePanel);
        this.mAddNamePanel.add(mAddNameLabel);
        this.mAddNamePanel.add(mAddName);

        this.mPanelAddCust.add(mAddBirthdayPanel);
        this.mAddBirthdayPanel.add(mAddBirthdayLabel);
        this.mAddBirthdayPanel.add(mAddBirthday);

        this.mPanelAddCust.add(mAddPhoneNumPanel);
        this.mAddPhoneNumPanel.add(mAddPhoneNumLabel);
        this.mAddPhoneNumPanel.add(mAddPhoneNum);

        this.mPanelAddCust.add(mAddRemarkPanel);
        this.mAddRemarkPanel.add(mAddRemarkLabel);
        this.mAddRemarkPanel.add(mAddRemark);

        this.mPanelAddCust.add(mAddCustBtn, BorderLayout.SOUTH);
        this.mPanelAddCust.add(mAddTextFieldCustBtn, BorderLayout.SOUTH);
    }

    @Override
    protected void onAddEventListeners() {
        mAddBirthday.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!((ch >= '0') && (ch <= '9') || (ch == KeyEvent.VK_BACK_SPACE) ||
                        (ch == KeyEvent.VK_DELETE))) {
                    UiUtils.showMsgBox("유효한 날짜를 입력하세요", "");
                    e.consume();
                }
            }
        });

        ActionListener listener = e -> {

            if (e.getSource() == mAddCustBtn) {
                this.setCustValue();
            }

            // 테스트용 자동기입
            if (e.getSource() == mAddTextFieldCustBtn) {
                this.setTestCustValue();
            }
        };

        this.mAddCustBtn.addActionListener(listener);
        // 테스트용 자동기입
        this.mAddTextFieldCustBtn.addActionListener(listener);

    }

    @Override
    protected void onShow(boolean firstTime) {
        System.out.println("[AddCustmerView.onShow()]");

    }

    @Override
    protected void onHide() {
        System.out.println("[AddCustmerView.onHide()]");
    }

    @Override
    protected void onSetResources() {
        Main main = (Main) this.getStatusManager();

        JLabel lb1 = (JLabel) this.getComponents()[0];
        lb1.setFont(main.mFont2);

        JPanel[] panels = { mPanelAddCust, mAddIdPanel, mAddBirthdayPanel,
                mAddNamePanel, mAddPhoneNumPanel, mAddRemarkPanel };
        for (JPanel Panel : panels) {
            for (int i = 0; i < Panel.getComponents().length; i++) {
                JComponent cp = (JComponent) Panel.getComponents()[i];
                cp.setFont(main.mFont2);
            }
        }
    }

    private void setCustValue() {
        CustomerInfo memberInfo = new CustomerInfo();

        int custId = Integer.valueOf(this.mAddId.getText());
        int defaultPoint = 0;

        String dateStr = mAddBirthday.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "yyyyMMdd");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        memberInfo.setValues(custId, LocalDate.now(), mAddName.getText(),
                date, mAddPhoneNum.getText(),
                defaultPoint, mAddRemark.getText());

        try {
            memMngr.add(memberInfo);
            System.out.println("add 완료");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        // System.out.println("추가된 정보" + memberInfo.toString());
    }

    // 테스트용 자동기입 메소드
    private void setTestCustValue() {
        try {
            int nextNum = memMngr.getCount() + 1;
            this.mAddId.setText("" + nextNum);
            this.mAddName.setText("김철수");
            this.mAddBirthday.setText("20001010");
            this.mAddPhoneNum.setText("010-0000-0001");
            this.mAddRemark.setText("test Custmer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}