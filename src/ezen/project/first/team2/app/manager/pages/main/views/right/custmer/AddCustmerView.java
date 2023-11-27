package ezen.project.first.team2.app.manager.pages.main.views.right.custmer;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    CustomerManagerMem custMngr = CustomerManagerMem.getInstance();

    JLabel mLabelInfo = new JLabel("고객 데이터 추가");

    // 고객정보 기입란 패널
    JPanel mPanelAddCust = new JPanel();
    // 고객정보 기입란 왼쪽패널
    JPanel mPanelAddCustLeft = new JPanel();
    // 고객정보 기입란 오른쪽패널
    JPanel mPanelAddCustRight = new JPanel();

    JLabel mLabelSignUp = new JLabel("<html><br>고객정보<br><br><br></html>");

    // 고객정보 기입란 컴포넌트들
    // 각 속성들의 패널로 텍스트필드 왼쪽에 속성이 들어갈수 있게설정
    JPanel mPanelAddId = new JPanel();
    JLabel mLabelAddId = new JLabel("　　고객번호 : ");
    JTextField mTextFieldAddId = new JTextField(10);

    JPanel mPanelAddName = new JPanel();
    JLabel mLabelAddName = new JLabel("　　* 고객명 : ");
    JTextField mTextFieldAddName = new JTextField("", 10);

    JPanel mPanelAddBirthday = new JPanel();
    JLabel mLabelAddBirthday = new JLabel("　* 생년월일 : ");
    //
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    JFormattedTextField mTextFieldAddBirthday = new JFormattedTextField(format);

    JPanel mPanelAddPhoneNum = new JPanel();
    JLabel mLabelAddPhoneNum = new JLabel("* 휴대폰번호 : ");
    JTextField mTextFieldAddPhoneNum = new JTextField(10);

    JPanel mPanelAddRemark = new JPanel();
    JLabel mLabelAddRemark = new JLabel("비 고 : ");
    JTextField mTextFieldAddRemark = new JTextField(20);

    JButton mBtnAddCust = new JButton("고객 추가");

    JButton mBtnAddTextFieldCust = new JButton("[테스트]자동기입");

    public AddCustmerView() {
        super(MainPage.VIEW_NUM_CUST_ADD);
    }

    @Override
    protected void onInit() {

    }

    @Override
    protected void onSetLayout() {
        this.setLayout(new BorderLayout());
        this.mPanelAddCust.setLayout(new GridLayout(1, 2));
        this.mPanelAddCustLeft.setLayout(new BoxLayout(mPanelAddCustLeft, BoxLayout.Y_AXIS));
        this.mPanelAddCustRight.setLayout(new BoxLayout(mPanelAddCustRight, BoxLayout.Y_AXIS));
    }

    @Override
    protected void onAddCtrls() {

        // 컴포넌트 설정
        this.mLabelInfo.setOpaque(true);
        this.mLabelInfo.setBackground(Color.LIGHT_GRAY);
        this.mLabelInfo.setHorizontalAlignment(JLabel.CENTER);

        this.mTextFieldAddBirthday.setColumns(10);
        try {
            this.mTextFieldAddId.setText(custMngr.getNextID() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mTextFieldAddId.setEnabled(false);

        // 컴포넌트 삽입
        this.add(mLabelInfo, BorderLayout.NORTH);
        this.add(mPanelAddCust, BorderLayout.CENTER);
        this.mPanelAddCust.add(mPanelAddCustLeft);
        this.mPanelAddCust.add(mPanelAddCustRight);

        this.mPanelAddCustLeft.add(mLabelSignUp);
        this.mPanelAddCustLeft.add(mPanelAddId);
        this.mPanelAddId.add(mLabelAddId);
        this.mPanelAddId.add(mTextFieldAddId);

        this.mPanelAddCustLeft.add(mPanelAddName);
        this.mPanelAddName.add(mLabelAddName);
        this.mPanelAddName.add(mTextFieldAddName);

        this.mPanelAddCustLeft.add(mPanelAddBirthday);
        this.mPanelAddBirthday.add(mLabelAddBirthday);
        this.mPanelAddBirthday.add(mTextFieldAddBirthday);

        this.mPanelAddCustLeft.add(mPanelAddPhoneNum);
        this.mPanelAddPhoneNum.add(mLabelAddPhoneNum);
        this.mPanelAddPhoneNum.add(mTextFieldAddPhoneNum);

        this.mPanelAddCustLeft.add(mPanelAddRemark);
        this.mPanelAddRemark.add(mLabelAddRemark);
        this.mPanelAddRemark.add(mTextFieldAddRemark);

        this.mPanelAddCustRight.add(mBtnAddCust, BorderLayout.SOUTH);
        this.mPanelAddCustRight.add(mBtnAddTextFieldCust, BorderLayout.SOUTH);
    }

    @Override
    protected void onAddEventListeners() {
        mTextFieldAddBirthday.addKeyListener(new KeyAdapter() {
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
            if (e.getSource() == mBtnAddTextFieldCust) {
                this.setTestCustValue();
            }

            // Remark, Id를 제외한 TextField를 채웠는지 확인
            if (mTextFieldAddName.getText().length() > 0 &&
                    mTextFieldAddPhoneNum.getText().length() > 0 &&
                    mTextFieldAddBirthday.getText().length() > 0) {

                if (e.getSource() == mBtnAddCust) {
                    // 입력, 추가 완료 후 TextField 초기화
                    this.setCustValue();
                    try {
                        this.mTextFieldAddId.setText(String.valueOf(custMngr.getNextID()));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    this.mTextFieldAddName.setText("");
                    this.mTextFieldAddPhoneNum.setText("");
                    this.mTextFieldAddBirthday.setText("");
                    this.mTextFieldAddRemark.setText("");
                }
            } else {
                // 필수입력사항 미입력시 경고메세지박스 출력
                UiUtils.showMsgBox("* 는 필수입력사항입니다",
                        "", MsgBoxType.Warn);
            }
        };

        // 테스트용 자동기입
        this.mBtnAddTextFieldCust.addActionListener(listener);
        // 입력된 데이터를 저장
        this.mBtnAddCust.addActionListener(listener);

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
        lb1.setFont(main.mFont0);

        JPanel[] panels = { mPanelAddCust, mPanelAddId, mPanelAddBirthday,
                mPanelAddName, mPanelAddPhoneNum, mPanelAddRemark };
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
            int custId = custMngr.getNextID();
            int defaultPoint = 0;
            LocalDate joinCustDate = LocalDate.now();

            // 생년월일 LocalDate로 변환
            String dateStr = mTextFieldAddBirthday.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    "yyyyMMdd");
            LocalDate custBirthDate = LocalDate.parse(dateStr, formatter);

            customerItem.setValues(custId, joinCustDate, mTextFieldAddName.getText(),
                    custBirthDate, mTextFieldAddPhoneNum.getText(),
                    defaultPoint, mTextFieldAddRemark.getText());

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            custMngr.add(customerItem);
            System.out.println("cust Add");
        } catch (Exception e1) {
            e1.printStackTrace();
            UiUtils.showMsgBox("입력하신 휴대폰번호는 이미 등록되어있습니다.",
                    "", MsgBoxType.Warn);
        }
        // System.out.println("추가된 정보" + memberInfo.toString());
    }

    // 테스트용 자동기입 메소드
    private void setTestCustValue() {

        try {
            int nextNum = custMngr.getNextID();
            this.mTextFieldAddId.setText(String.valueOf(nextNum));
            this.mTextFieldAddName.setText(nName());
            this.mTextFieldAddBirthday.setText("20001010");
            int randomNum = (int) (Math.random() * 9999);
            int randomNum2 = (int) (Math.random() * 9999);
            this.mTextFieldAddPhoneNum.setText(String.format("010-%04d-%04d", randomNum, randomNum2));
            this.mTextFieldAddRemark.setText("test Custmer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 테스트용 랜덤 이름 생성
    public static String nName() {
        List<String> lastName = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권",
                "황",
                "안",
                "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차",
                "주",
                "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염",
                "양",
                "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕",
                "금",
                "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
        List<String> firstName = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남",
                "노", "누",
                "다",
                "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바",
                "박",
                "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙",
                "순",
                "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월",
                "위",
                "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주",
                "준",
                "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현",
                "형",
                "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을",
                "비",
                "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸",
                "삼",
                "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직",
                "흠",
                "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");
        Collections.shuffle(lastName);
        Collections.shuffle(firstName);
        return lastName.get(0) + firstName.get(0) + firstName.get(1);
    }

}