package ezen.project.first.team2.app.manager.pages.main.views.right;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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
import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerManagerMem;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.common.utils.UiUtils.MsgBoxType;
import ezen.project.first.team2.app.manager.Main;
import ezen.project.first.team2.app.manager.pages.main.MainPage;

public class AddCustmerView extends View {

    CustomerManagerMem memMngr = CustomerManagerMem.getInstance();

    JLabel mInfoLabel = new JLabel("고객 추가뷰 초기화면입니다");

    // 고객정보 기입란 패널
    JPanel mAddCustPanel = new JPanel();
    // 고객정보 기입란 왼쪽패널
    JPanel mAddCustLeftPanel = new JPanel();
    // 고객정보 기입란 오른쪽패널
    JPanel mAddCustRightPanel = new JPanel();

    JLabel mLabelSignUp = new JLabel("<html><br>고객정보<br><br><br></html>");

    /////////////////////////////////////////////////
    // ## 가입일 비활성화된 TextField 추가?
    // ## >> setText로 미리 채워넣고, 입력을 비활성화
    /////////////////////////////////////////////////
    // 고객정보 기입란 컴포넌트들
    // 가입일은 현재시간으로 자동 추가
    // 각 속성들의 패널로 텍스트필드 왼쪽에 속성이 들어갈수 있게설정
    JPanel mAddIdPanel = new JPanel();
    JLabel mAddIdLabel = new JLabel("　　고객번호 : ");
    JTextField mAddIdTextField = new JTextField(10);

    JPanel mAddNamePanel = new JPanel();
    JLabel mAddNameLabel = new JLabel("　　* 고객명 : ");
    JTextField mAddNameTextField = new JTextField("", 10);

    JPanel mAddBirthdayPanel = new JPanel();
    JLabel mAddBirthdayLabel = new JLabel("　* 생년월일 : ");
    //
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    JFormattedTextField mAddBirthdayField = new JFormattedTextField(format);

    JPanel mAddPhoneNumPanel = new JPanel();
    JLabel mAddPhoneNumLabel = new JLabel("* 휴대폰번호 : ");
    JTextField mAddPhoneNumTextField = new JTextField(10);

    JPanel mAddRemarkPanel = new JPanel();
    JLabel mAddRemarkLabel = new JLabel("　　비　고 : ");
    JTextField mAddRemark = new JTextField(10);

    JButton mAddCustBtn = new JButton("고객 추가");
    JButton mAddTextFieldCustBtn = new JButton("[테스트]자동기입");

    public AddCustmerView() {
        super(MainPage.VIEW_NUM_CUST_ADD);
    }

    @Override
    protected void onInit() {

    }

    @Override
    protected void onSetLayout() {
        this.setLayout(new BorderLayout());
        this.mAddCustPanel.setLayout(new GridLayout(1, 2));
        this.mAddCustLeftPanel.setLayout(new BoxLayout(mAddCustLeftPanel, BoxLayout.Y_AXIS));
        this.mAddCustRightPanel.setLayout(new BoxLayout(mAddCustRightPanel, BoxLayout.Y_AXIS));
    }

    @Override
    protected void onAddCtrls() {

        // 컴포넌트 설정
        this.mInfoLabel.setOpaque(true);
        this.mInfoLabel.setBackground(Color.LIGHT_GRAY);
        this.mInfoLabel.setHorizontalAlignment(JLabel.CENTER);

        this.mAddBirthdayField.setColumns(10);
        try {
            this.mAddIdTextField.setText(memMngr.getNextID() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mAddIdTextField.setEnabled(false);

        // 컴포넌트 삽입
        this.add(mInfoLabel, BorderLayout.NORTH);
        this.add(mAddCustPanel, BorderLayout.CENTER);
        this.mAddCustPanel.add(mAddCustLeftPanel);
        this.mAddCustPanel.add(mAddCustRightPanel);

        this.mAddCustLeftPanel.add(mLabelSignUp);
        this.mAddCustLeftPanel.add(mAddIdPanel);
        this.mAddIdPanel.add(mAddIdLabel);
        this.mAddIdPanel.add(mAddIdTextField);

        this.mAddCustLeftPanel.add(mAddNamePanel);
        this.mAddNamePanel.add(mAddNameLabel);
        this.mAddNamePanel.add(mAddNameTextField);

        this.mAddCustLeftPanel.add(mAddBirthdayPanel);
        this.mAddBirthdayPanel.add(mAddBirthdayLabel);
        this.mAddBirthdayPanel.add(mAddBirthdayField);

        this.mAddCustLeftPanel.add(mAddPhoneNumPanel);
        this.mAddPhoneNumPanel.add(mAddPhoneNumLabel);
        this.mAddPhoneNumPanel.add(mAddPhoneNumTextField);

        this.mAddCustLeftPanel.add(mAddRemarkPanel);
        this.mAddRemarkPanel.add(mAddRemarkLabel);
        this.mAddRemarkPanel.add(mAddRemark);

        this.mAddCustRightPanel.add(mAddCustBtn, BorderLayout.SOUTH);
        this.mAddCustRightPanel.add(mAddTextFieldCustBtn, BorderLayout.SOUTH);
    }

    @Override
    protected void onAddEventListeners() {
        mAddBirthdayField.addKeyListener(new KeyAdapter() {
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

            // 테스트용 자동기입
            if (e.getSource() == mAddTextFieldCustBtn) {
                this.setTestCustValue();
            }

            ////////////////////////////////////////////////////
            // ## 231121 KJH >> 휴대폰번호 중복확인도 필요할까요?
            ////////////////////////////////////////////////////
            // Remark를 제외한 TextField를 채웠는지 확인
            if (mAddNameTextField.getText().length() > 0 &&
                    mAddPhoneNumTextField.getText().length() > 0 &&
                    mAddBirthdayField.getText().length() > 0
            // && mAddIdTextField.getText().length() > 0
            ) {

                if (e.getSource() == mAddCustBtn) {
                    this.setCustValue();
                    // 입력, 추가 완료 후 TextField 초기화
                    try {
                        this.mAddIdTextField.setText(memMngr.getNextID() + "");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    this.mAddNameTextField.setText("");
                    this.mAddPhoneNumTextField.setText("");
                    this.mAddBirthdayField.setText("");
                    this.mAddRemark.setText("");
                }

            } else {
                // 필수입력사항 미입력시 경고메세지박스 출력
                UiUtils.showMsgBox("* 는 필수입력사항입니다",
                        "", MsgBoxType.Warn);
            }

        };

        // 입력된 데이터를 저장
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

        JPanel[] panels = { mAddCustPanel, mAddIdPanel, mAddBirthdayPanel,
                mAddNamePanel, mAddPhoneNumPanel, mAddRemarkPanel };
        for (JPanel Panel : panels) {
            for (int i = 0; i < Panel.getComponents().length; i++) {
                JComponent cp = (JComponent) Panel.getComponents()[i];
                cp.setFont(main.mFont2);
            }
        }
    }

    // textField에 작성한 내용을 추가
    private void setCustValue() {
        CustomerItem customerItem = new CustomerItem();

        try {

            int defaultPoint = 0;
            int custId = memMngr.getNextID();
            LocalDate joinCustDate = LocalDate.now();

            // 생년월일 LocalDate로 변환
            String dateStr = mAddBirthdayField.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    "yyyyMMdd");
            LocalDate custBirthDate = LocalDate.parse(dateStr, formatter);

            customerItem.setValues(custId, joinCustDate, mAddNameTextField.getText(),
                    custBirthDate, mAddPhoneNumTextField.getText(),
                    defaultPoint, mAddRemark.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            memMngr.add(customerItem);
            System.out.println("add 완료");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        // System.out.println("추가된 정보" + memberInfo.toString());
    }

    // 테스트용 자동기입 메소드
    private void setTestCustValue() {
        try {
            int nextNum = memMngr.getNextID();
            this.mAddIdTextField.setText("" + nextNum);
            this.mAddNameTextField.setText("김철수");
            this.mAddBirthdayField.setText("20001010");
            this.mAddPhoneNumTextField.setText("010-0000-0001");
            this.mAddRemark.setText("test Custmer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}