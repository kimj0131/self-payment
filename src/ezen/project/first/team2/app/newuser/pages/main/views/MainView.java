package ezen.project.first.team2.app.newuser.pages.main.views;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import ezen.project.first.team2.app.common.framework.View;
import ezen.project.first.team2.app.common.modules.customer.CustomerItem;
import ezen.project.first.team2.app.common.modules.customer.CustomerManagerMem;
import ezen.project.first.team2.app.common.utils.UiUtils;
import ezen.project.first.team2.app.common.utils.UiUtils.MsgBoxType;
import ezen.project.first.team2.app.newuser.pages.main.MainPage;

public class MainView extends View {
   // -------------------------------------------------------------------------
   private JPanel pButton = new JPanel();

   Font font = new Font("맑은 고딕", Font.BOLD, 11);
   // 버튼
   private JButton mAddBtn = new JButton("추가");
   private JButton mResetBtn = new JButton("초기화");
   private JButton mTestBtn = new JButton("테스트");
   private JButton mCheckBtn = new JButton("번호 확인");

   // 회원 정보 라벨
   private JLabel userNum = new JLabel("회원번호: ");
   private JLabel userName = new JLabel("회원이름: ");
   private JLabel bBirth = new JLabel("생년월일: ");
   private JLabel bBrithHint = new JLabel("생년월일은 8자리 입니다 ex) 20000101");
   private JLabel bPhoneNum = new JLabel("휴대전화: ");

   // 가입 일자
   private LocalDate lDJoinDate = LocalDate.now();
   private String mJoinDate = lDJoinDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
   private JLabel joinDate = new JLabel("가입날짜: " + mJoinDate);
   String joinDateString;

   // 유저 라벨
   private JPanel pUserNum = new JPanel();
   private JTextField tfUserNum = new JTextField(11);

   private JPanel pUserName = new JPanel();
   private JTextField tfUserName = new JTextField(11);

   // 생일
   private JPanel pBirth = new JPanel();
   private JTextField tfBirth = new JTextField(11);
   private String birthDay;
   private LocalDate lDBirth;

   // 전화번호
   private JPanel pPhoneNum = new JPanel(new FlowLayout(FlowLayout.LEFT));
   String[] phoneFirstNum = { "010", "011", "016", "017", "018", "019" };
   private JComboBox<String> strPhoneNum = new JComboBox<String>(phoneFirstNum);
   private JTextField tfPhoneNum2 = new JTextField(3);
   private JTextField tfPhoneNum3 = new JTextField(3);
   private String strFirstPhoneNum = strPhoneNum.getSelectedItem().toString();
   private String phoneNumber;

   GridBagLayout gb;
   GridBagConstraints gbc;

   CustomerManagerMem mCustMngr = CustomerManagerMem.getInstance();

   // -------------------------------------------------------------------------

   // 생성자
   public MainView() {
      super(MainPage.VIEW_NUM_MAIN);

      strPhoneNum.addItemListener(e -> {
         strFirstPhoneNum = strPhoneNum.getSelectedItem().toString();
      });

   }

   // -------------------------------------------------------------------------

   // 초기화 작업
   @Override
   protected void onInit() {
      super.onInit();

      try {
         this.mCustMngr.init();
      } catch (Exception e) {

         e.printStackTrace();
      }
   }

   // 레이아웃 설정
   @Override
   protected void onSetLayout() {
      this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

      gb = new GridBagLayout();
      setLayout(gb);
      gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.BOTH;

   }

   // 컨트롤 추가
   @Override
   protected void onAddCtrls() {

      // gbAdd(joinDate, 0, 4, 1 ,1);
      pUserNum.add(tfUserNum);
      pUserName.add(tfUserName);

      pBirth.add(tfBirth);

      pPhoneNum.add(strPhoneNum);
      pPhoneNum.add(new JLabel(" - "));
      pPhoneNum.add(tfPhoneNum2);
      pPhoneNum.add(new JLabel(" - "));
      pPhoneNum.add(tfPhoneNum3);

      pButton.add(mAddBtn);
      pButton.add(mResetBtn);
      pButton.add(mTestBtn);
      pPhoneNum.add(mCheckBtn);

      // gbAdd(remark, 0, 5, 1, 1);
      // gbAdd(tfRemark, 0, 6, 4, 1);

      gbc.weightx = 0.3;
      gbc.weighty = 1.0;
      gbAdd(bBirth, 0, 2, 1, 1);
      gbAdd(bPhoneNum, 0, 4, 1, 1);

      gbc.anchor = GridBagConstraints.WEST;
      gbAdd(userNum, 0, 0, 5, 1);
      tfUserNum.setEnabled(false);
      gbAdd(userName, 0, 1, 1, 1);
      gbAdd(joinDate, 0, 8, 1, 1);

      gbc.fill = GridBagConstraints.WEST;

      gbc.weightx = 0.8;
      gbc.weighty = 1.0;
      gbAdd(pUserNum, 1, 0, 1, 1);
      gbAdd(pUserName, 1, 1, 1, 1);
      gbAdd(pBirth, 1, 2, 1, 1);
      gbAdd(pPhoneNum, 1, 4, 1, 1);
      gbAdd(pButton, 0, 10, 4, 1);
      gbAdd(bBrithHint, 1, 3, 1, 1);
      mAddBtn.setEnabled(false);

      addMaxLengthFilter(tfUserNum, 11);
      addMaxLengthFilter(tfUserName, 11);
      addMaxLengthFilter(tfBirth, 8);
      addMaxLengthFilter(tfPhoneNum2, 4);
      addMaxLengthFilter(tfPhoneNum3, 4);

      bBrithHint.setFont(font);

   }

   // gbAdd 메서드에서 anchor 속성을 지정하도록 수정
   private void gbAdd(JComponent c, int x, int y, int w, int h) {
      gbc.gridx = x;
      gbc.gridy = y;
      gbc.gridwidth = w;
      gbc.gridheight = h;
      gbc.insets = new Insets(2, 2, 2, 2);
      gbc.anchor = GridBagConstraints.WEST; // 왼쪽 정렬
      add(c, gbc);
   }

   // 이벤트 리스너 추가
   @Override
   protected void onAddEventListeners() {

      // 전화번호 유효성 검사
      this.mCheckBtn.addActionListener(e -> {
         String phoneNumber = String.format("%s-%s-%s", strFirstPhoneNum, tfPhoneNum2.getText(),
               tfPhoneNum3.getText());

         // 전화번호 유효성 검사를 위한 정규식 패턴

         String phonePattern = "^010-\\d{4}-\\d{4}$|^(011|016|019)-\\d{3,4}-\\d{4}$|^(017|018)-\\d{3}-\\d{4}$";

         // 패턴 컴파일

         Pattern pattern2 = Pattern.compile(phonePattern);

         // 패턴 일치 여부 확인
         Matcher matcher2 = pattern2.matcher(phoneNumber);

         if (matcher2.matches()) {
            UiUtils.showMsgBox("유효한 번호입니다.","전화번호 확인");
            mAddBtn.setEnabled(true);

            
         } else {
            UiUtils.showMsgBox("유효하지 않은 전화번호입니다.", "전화번호 확인",
            MsgBoxType.Error);
            mAddBtn.setEnabled(false);
         }

         try {
         // 전화번호 중복 검사 수행
         if (this.mCustMngr.findByPhoneNumber(this.phoneNumber) != null) {
         UiUtils.showMsgBox("이미 등록된 전화번호입니다.", "전화번호 중복", MsgBoxType.Error);
         mAddBtn.setEnabled(false);
         return; // 고객 추가 진행하지 않음             
         }
         } catch (Exception ex) {
               ex.printStackTrace();
         }
         
      });

      
      this.mAddBtn.addActionListener(e -> {
         // 이름에 유효하지 않는 문자가 포함되어 있는지 검사
         String koreanPattern = "^[가-힣]+$";
         Pattern pattern = Pattern.compile(koreanPattern);
         Matcher matcher = pattern.matcher(tfUserName.getText());
     
         if (!matcher.matches()) {
             UiUtils.showMsgBox("이름에 유효하지 않는 문자가 포함되어 있습니다.", "경고", MsgBoxType.Error);
             tfUserName.requestFocus();
             return;
         }
         
     
         this.phoneNumber = String.format("%s-%s-%s",
                 this.strFirstPhoneNum, this.tfPhoneNum2.getText(), this.tfPhoneNum3.getText());

         try {
         // 전화번호 중복 검사 수행
         if (this.mCustMngr.findByPhoneNumber(this.phoneNumber) != null) {
         UiUtils.showMsgBox("이미 등록된 전화번호입니다.", "전화번호 중복", MsgBoxType.Error);
         mAddBtn.setEnabled(false);
         return; // 고객 추가 진행하지 않음             
         }
         } catch (Exception ex) {
               ex.printStackTrace();
         }

     
         this.birthDay = String.format("%s", this.tfBirth.getText());
     
         try {
             this.lDBirth = LocalDate.parse(birthDay, DateTimeFormatter.ofPattern("yyyyMMdd"));
         } catch (Exception ex) {
             UiUtils.showMsgBox("생년월일을 다시 입력해주세요. ex)20000101", "생년월일 오류", MsgBoxType.Error);
             this.tfBirth.requestFocus();
             return;
         }
     
     
         joinDateString = String.format(this.mJoinDate);
     
         CustomerItem info = new CustomerItem();
         int id = Integer.parseInt(this.tfUserNum.getText());
         String name = this.tfUserName.getText();
         LocalDate birthday = this.lDBirth;
         String phoneNum = phoneNumber;
         int point = 0;
         String remark = "";
     
         System.out.println(birthDay);
         System.out.println(phoneNum);
         info.setValues(id, lDJoinDate, name, birthday, phoneNum, point, remark);
     
         try {
             this.mCustMngr.add(info);
         } catch (Exception e1) {
             e1.printStackTrace();
         }
     
         UiUtils.showMsgBox("가입이 완료되었습니다.", MainPage.TITLE);
     });

      this.mResetBtn.addActionListener(e -> {
         tfPhoneNum2.setText("");
         tfPhoneNum3.setText("");
         tfBirth.setText("");
         tfUserName.setText("");
         mAddBtn.setEnabled(false);

         UiUtils.showMsgBox("초기화가 완료되었습니다.", MainPage.TITLE);
      });

      // 테스트 기능 미완
      this.mTestBtn.addActionListener(e -> {
         try {
            mCustMngr.iterate((item, idx) -> {
               System.out.println("  " + item.toString());
               return true;
            });
         } catch (Exception e1) {
            e1.printStackTrace();
         }
         UiUtils.showMsgBox("테스트.", MainPage.TITLE);
      });

      tfUserNum.addActionListener(e -> tfUserName.requestFocusInWindow());
      tfUserName.addActionListener(e -> tfBirth.requestFocusInWindow());
      tfBirth.addActionListener(e -> tfPhoneNum2.requestFocusInWindow());
      tfPhoneNum2.addActionListener(e -> tfPhoneNum3.requestFocusInWindow());
      tfPhoneNum3.addActionListener(e -> mCheckBtn.doClick());
   }

   // 뷰가 표시될 때
   @Override
   protected void onShow(boolean firstTime) {
      System.out.println("[MainView.onShow()]");

      if (firstTime) {

         try {
            int id = this.mCustMngr.getNextID();
            tfUserNum.setText(String.format("%06d", id)); // => id + ""
         } catch (Exception e) {

            e.printStackTrace();
         }
      }
   }

   // 뷰가 숨겨질 때
   @Override
   protected void onHide() {
      System.out.println("[MainView.onHide()]");
   }

   // 텍스트 필드 입력값 제한
   private void addMaxLengthFilter(JTextField textField, int maxLength) {
      DocumentFilter filter = new DocumentFilter() {
         @Override
         public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
               throws BadLocationException {
            if ((fb.getDocument().getLength() + string.length()) <= maxLength) {
               super.insertString(fb, offset, string, attr);
            }
         }

         @Override
         public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
               throws BadLocationException {
            if ((fb.getDocument().getLength() + text.length() - length) <= maxLength) {
               super.replace(fb, offset, length, text, attrs);
            }
         }
      };

      Document doc = textField.getDocument();
      if (doc instanceof PlainDocument) {
         ((PlainDocument) doc).setDocumentFilter(filter);
      }
   }

   @Override
   public void setFont(Font font) {

   }
  

}