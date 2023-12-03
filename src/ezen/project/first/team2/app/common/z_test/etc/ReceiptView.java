////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231204MON_021700] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.z_test.etc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Formatter;

import javax.swing.JPanel;
import javax.swing.JViewport;

import ezen.project.first.team2.app.common.modules.product.orders.ProductOrderItem;
import ezen.project.first.team2.app.common.utils.UnitUtils;

public class ReceiptView extends JPanel {
    private final int PADDING = 10;
    //
    private final int WIDTH_PROD_NAME = 16;
    private final int WIDTH_PRICE = 10;
    private final int WIDTH_QUANTITY = 6;
    private final int WIDTH_DISCOUNT = 10;
    private final int WIDTH_SUB_SUM = 10;
    //
    private final int LINE_CHAR_COUNT = WIDTH_PROD_NAME + WIDTH_PRICE +
            WIDTH_QUANTITY + WIDTH_DISCOUNT + WIDTH_SUB_SUM;
    //
    private final int X_POS_PROD_NAME = 0;
    private final int X_POS_PRICE = X_POS_PROD_NAME + WIDTH_PROD_NAME;
    private final int X_POS_QUANTITY = X_POS_PRICE + WIDTH_PRICE;
    private final int X_POS_DISCOUNT = X_POS_QUANTITY + WIDTH_QUANTITY;
    private final int X_POS_SUB_SUM = X_POS_DISCOUNT + WIDTH_DISCOUNT;
    //
    private final int X_POS_VALUE = WIDTH_PROD_NAME;
    private final int WIDTH_VALUE = WIDTH_PRICE;

    private ProductOrderItem mProdOrderItem;
    private Dimension mCharSize = new Dimension();

    public ReceiptView(ProductOrderItem item) {
        this.mProdOrderItem = item;

        this.setFont(new Font("바탕체", Font.PLAIN, 14));
    }

    public ReceiptView() {
        this(null);

    }

    public void setOrderItem(ProductOrderItem item) {
        this.mProdOrderItem = item;

        var g2 = (Graphics2D) this.getGraphics();
        int w = ((JViewport) this.getParent()).getWidth();
        int h = (30 + item.getProdOrderDetailItems().size()) * this.getCharSize(g2).height;
        // System.out.printf("w:%d, h:%d \n", w,h);
        this.setSize(new Dimension(w, h));
        this.setPreferredSize(new Dimension(w, h));
    }

    private Dimension getStringSize(Graphics2D g2, String str) {
        var rc = g2.getFont().getStringBounds(str, g2.getFontRenderContext());
        return new Dimension((int) (rc.getWidth() + 0.5), (int) (rc.getHeight() + 0.5));
    }

    private Dimension getCharSize(Graphics2D g2) {
        if (this.mCharSize.getWidth() == 0) {
            this.mCharSize = this.getStringSize(g2, "A");
            this.mCharSize.height += 4;
        }

        return this.mCharSize;
    }

    private void drawString(Graphics2D g2, int x, int y, String format, Object... args) {
        final int CHAR_WIDTH = (int) this.getCharSize(g2).getWidth();
        final int LINE_HEIGHT = (int) this.getCharSize(g2).getHeight();

        String s = new Formatter().format(format, args).toString();

        int xx = PADDING + (CHAR_WIDTH * x);
        int yy = PADDING + LINE_HEIGHT + (LINE_HEIGHT * y);

        if (x == -1) {
            xx = (int) (this.getWidth() - getStringSize(g2, s).getWidth()) / 2;
        }

        g2.drawString(s, xx, yy);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        var g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());

        g2.setColor(Color.BLACK);
        int y = 0;

        this.drawString(g2, -1, y++, "신용카드 매출전표");
        y++;

        this.drawString(g2, 0, y++, "[매장명] EZEN Mart");
        this.drawString(g2, 0, y++, "[사업자번호] 111-22-33333");
        this.drawString(g2, 0, y++, "[주소] 경기도 구리시 건원대로 44 태영빌딩 4층");
        this.drawString(g2, 0, y++, "[대표] 권혁준    [Tel] 031)555-4449");
        this.drawString(g2, 0, y++, "[매출일] %s", this.mProdOrderItem.getOrderDateTimeStr());
        y++;

        var prod_name = "상품명";
        var price = "단가";
        var qty = "수량";
        var discnt = "할인";
        var sub_sum = "금액";

        price = " ".repeat(WIDTH_PRICE - (price.length() * 2) - 1) + price;
        qty = " ".repeat(WIDTH_QUANTITY - (qty.length() * 2) - 1) + qty;
        discnt = " ".repeat(WIDTH_DISCOUNT - (discnt.length() * 2) - 1) + discnt;
        sub_sum = " ".repeat(WIDTH_SUB_SUM - (sub_sum.length() * 2) - 1) + sub_sum;

        this.drawString(g2, 0, y++, "-".repeat(LINE_CHAR_COUNT));
        this.drawString(g2, X_POS_PROD_NAME, y, prod_name);
        this.drawString(g2, X_POS_PRICE, y, price);
        this.drawString(g2, X_POS_QUANTITY, y, qty);
        this.drawString(g2, X_POS_DISCOUNT, y, discnt);
        this.drawString(g2, X_POS_SUB_SUM, y, sub_sum);
        y++;

        this.drawString(g2, 0, y++, "-".repeat(LINE_CHAR_COUNT));

        for (var podi : this.mProdOrderItem.getProdOrderDetailItems()) {
            prod_name = podi.getProdItem().getName();
            price = UnitUtils.numToCurrencyStr(podi.getOrgPrice());
            qty = podi.getQuantity() + "";
            discnt = UnitUtils.numToCurrencyStr(podi.getProdDiscntItem().getAmount());
            sub_sum = UnitUtils.numToCurrencyStr(podi.getFinalPrice());

            price = " ".repeat(WIDTH_PRICE - price.length() - 1) + price;
            qty = " ".repeat(WIDTH_QUANTITY - qty.length() - 1) + qty;
            discnt = " ".repeat(WIDTH_DISCOUNT - discnt.length() - 1) + discnt;
            sub_sum = " ".repeat(WIDTH_SUB_SUM - sub_sum.length() - 1) + sub_sum;

            this.drawString(g2, X_POS_PROD_NAME, y, prod_name);
            this.drawString(g2, X_POS_PRICE, y, price);
            this.drawString(g2, X_POS_QUANTITY, y, qty);
            this.drawString(g2, X_POS_DISCOUNT, y, discnt);
            this.drawString(g2, X_POS_SUB_SUM, y, sub_sum);
            y++;
        }

        this.drawString(g2, 0, y++, "-".repeat(LINE_CHAR_COUNT));

        var org_price = UnitUtils.numToCurrencyStr(this.mProdOrderItem.getOrgTotalPrice());
        var used_pnt = UnitUtils.numToCurrencyStr(this.mProdOrderItem.getUsedPoint());
        var earned_pnt = UnitUtils.numToCurrencyStr(this.mProdOrderItem.getEarnedPoint());
        var total_price = UnitUtils.numToCurrencyStr(this.mProdOrderItem.getFinalTotalPrice());

        org_price = " ".repeat(WIDTH_VALUE - org_price.length() - 1) + org_price;
        used_pnt = " ".repeat(WIDTH_VALUE - used_pnt.length() - 1) + used_pnt;
        earned_pnt = " ".repeat(WIDTH_VALUE - earned_pnt.length() - 1) + earned_pnt;
        total_price = " ".repeat(WIDTH_VALUE - total_price.length() - 1) + total_price;

        this.drawString(g2, 0, y, "합계 금액:");
        this.drawString(g2, X_POS_VALUE, y++, org_price);
        this.drawString(g2, 0, y, "포인트 사용:");
        this.drawString(g2, X_POS_VALUE, y++, used_pnt);
        this.drawString(g2, 0, y, "적립 포인트:");
        this.drawString(g2, X_POS_VALUE, y++, earned_pnt);
        this.drawString(g2, 0, y, "최종 금액:");
        this.drawString(g2, X_POS_VALUE, y++, total_price);

        this.drawString(g2, 0, y++, "=".repeat(LINE_CHAR_COUNT));

        this.drawString(g2, -1, y++, "***신용승인정보***");
        y++;
        this.drawString(g2, 0, y++, "[카드종류] 국민카드       [할부개월] 일시불");
        this.drawString(g2, 0, y++, "[카드번호] 1234-5678-XXXX-XXXX");
        this.drawString(g2, 0, y++, "[유효기간] mm/yy");
        this.drawString(g2, 0, y++, "[승인금액] %s",
                UnitUtils.numToCurrencyStr(this.mProdOrderItem.getFinalTotalPrice()));
        this.drawString(g2, 0, y++, "[승인번호] 12345678");
        this.drawString(g2, 0, y++, "[승인일시] %s",
                this.mProdOrderItem.getOrderDateTimeStr());

        // System.out.printf("y: %d, detail_itms: %d, base_rows: %d ",
        // y, this.mProdOrderItem.getProdOrderDetailItems().size(),
        // y - this.mProdOrderItem.getProdOrderDetailItems().size());
    }
}