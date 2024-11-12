package NhaHang.View.ThanhPhan.KhachHang_ThanhPhan;

import NhaHang.Controller.Service.ServiceCustomer;
import NhaHang.Model.ModelHoaDon;
import NhaHang.Model.ModelMonAn;
import NhaHang.View.Dialog.MS_ConfirmOrderFood;
import NhaHang.View.Main_Frame.Main_Customer_Frame;
import NhaHang.Controller.Service.ServiceStaff;
import NhaHang.Model.ModelBan;
import NhaHang.Model.ModelCTHD;
import NhaHang.Model.ModelKhachHang;
import NhaHang.View.Form.NhanVien_Form.NhanVien.BillS_Form;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CardMonAn extends javax.swing.JPanel {

    private ServiceStaff serviceS;
    private BillS_Form bill;
    private ModelBan table;
    private ModelMonAn data;
    private MS_ConfirmOrderFood obj;
    private ModelHoaDon HoaDon;

    private Runnable reloadCallback;
    private ModelKhachHang customer;

    public CardMonAn(ModelMonAn data, ModelHoaDon HoaDon,Runnable reloadCallback) {

        this.HoaDon = HoaDon;
        this.data = data;
        initComponents();
        init();
           this.reloadCallback = reloadCallback;
        setOpaque(false);
//        customer = new ModelKhachHang();
    }

    public void init() {
        obj = new MS_ConfirmOrderFood(Main_Customer_Frame.getFrames()[0], true);
        setPreferredSize(new Dimension(300, 300));
        img.setIcon(data.getIcon());
        lbTitle.setText(data.getTitle());
        DecimalFormat df = new DecimalFormat("##,###,###");
        lbValue.setText(df.format(data.getValue()) + "đ");

        cmdOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                obj.ConfirmOrder(data, Integer.parseInt(txtNum.getText()), HoaDon.getIdHoaDon(), reloadCallback);
                data = null;
                txtNum.setText("1");
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        lbValue = new javax.swing.JLabel();
        cmdMinus = new NhaHang.View.Swing.ButtonOutLine();
        cmdAdd = new NhaHang.View.Swing.ButtonOutLine();
        cmdOrder = new NhaHang.View.Swing.Button();
        txtNum = new NhaHang.View.Swing.MyTextField();
        img = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(108, 91, 123));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Title");

        lbValue.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbValue.setForeground(new java.awt.Color(108, 91, 123));
        lbValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbValue.setText("Value");

        cmdMinus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/minus.png"))); // NOI18N
        cmdMinus.setFocusable(false);
        cmdMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMinusActionPerformed(evt);
            }
        });

        cmdAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add.png"))); // NOI18N
        cmdAdd.setFocusable(false);
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });

        cmdOrder.setBackground(new java.awt.Color(108, 91, 123));
        cmdOrder.setForeground(new java.awt.Color(242, 242, 242));
        cmdOrder.setText("THÊM");
        cmdOrder.setToolTipText("");
        cmdOrder.setFocusable(false);
        cmdOrder.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmdOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOrderActionPerformed(evt);
            }
        });

        txtNum.setBorder(null);
        txtNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNum.setText("1");
        txtNum.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbValue, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(cmdMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(cmdOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(94, 94, 94))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(cmdOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMinusActionPerformed
        int num = Integer.parseInt(txtNum.getText());
        if (num > 1) {
            txtNum.setText((num - 1) + "");
        } else {
            txtNum.setText("1");
        }
    }//GEN-LAST:event_cmdMinusActionPerformed

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        int num = Integer.parseInt(txtNum.getText());
        txtNum.setText((num + 1) + "");
    }//GEN-LAST:event_cmdAddActionPerformed

    private void txtNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumActionPerformed

    private void cmdOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOrderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdOrderActionPerformed

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        super.paintComponent(g);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private NhaHang.View.Swing.ButtonOutLine cmdAdd;
    private NhaHang.View.Swing.ButtonOutLine cmdMinus;
    private NhaHang.View.Swing.Button cmdOrder;
    private javax.swing.JLabel img;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbValue;
    private NhaHang.View.Swing.MyTextField txtNum;
    // End of variables declaration//GEN-END:variables
}
