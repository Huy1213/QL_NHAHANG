package NhaHang.View.Dialog;

import NhaHang.Controller.Service.ServiceCustomer;
import NhaHang.Model.ModelCTHD;
import NhaHang.Model.ModelMonAn;
import NhaHang.View.Form.KhachHang_Form.AccountC_Form;
import NhaHang.View.ThanhPhan.KhachHang_ThanhPhan.CardMonAn;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MS_ConfirmOrderFood extends javax.swing.JDialog {

    private final Animator animator;
    private boolean show = true;
    private Frame frame;
    private ServiceCustomer service;

    public MS_ConfirmOrderFood(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        service = new ServiceCustomer();
        this.frame = parent;
        setOpacity(0f);
        getContentPane().setBackground(Color.WHITE);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (show) {
                    setOpacity(fraction);
                } else {
                    setOpacity(1f - fraction);
                }
            }

            @Override
            public void end() {
                if (show == false) {
                    setVisible(false);
                }
            }

        };
        animator = new Animator(200, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
    }

    public void ConfirmOrder(ModelMonAn monan, int soluong, int ID_HoaDon, Runnable reloadCallback) {
        setLocationRelativeTo(frame);
        lbMessage.setText("Bạn có chắc đặt món (" + monan.getTitle() + ") với số lượng " + soluong + " không ?");
        lbTotal.setText("Tổng tiền : " + (monan.getValue() * soluong));
        animator.start();

        cmdOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<ModelCTHD> listCTHD = service.getCTHD(ID_HoaDon);
                    boolean isExist = false;

                    // Kiểm tra xem món ăn đã tồn tại trong CTHD chưa
                    for (ModelCTHD cthd : listCTHD) {
                        if (cthd.getID_MonAn() == monan.getId()) {
                            // Nếu đã tồn tại, cập nhật số lượng
                            int newQuantity = cthd.getSoluong() + soluong;
                            service.updateQuantityCTHD(ID_HoaDon, monan.getId(), newQuantity);
                            isExist = true;
                            break;
                        }
                    }
                    // Nếu không tồn tại, thêm mới vào CTHD
                    if (!isExist) {
                        service.InsertCTHD(ID_HoaDon, monan.getId(), soluong);
                    }

                    JOptionPane.showMessageDialog(null, "Đặt món thành công!");
                    if (reloadCallback != null) {
                        reloadCallback.run(); // Gọi callback để reload danh sách
                    }
                    setVisible(false);
                } catch (SQLException ex) {
                    Logger.getLogger(AccountC_Form.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Lỗi khi đặt món: " + ex.getMessage());
                }
            }
        });

        show = true;
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new NhaHang.View.Swing.PanelRound();
        lbTitle = new javax.swing.JLabel();
        cmdOK = new NhaHang.View.Swing.ButtonOutLine();
        cmdCancel = new NhaHang.View.Swing.ButtonOutLine();
        lbMessage = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(215, 215, 215));
        setUndecorated(true);

        panelRound1.setBackground(new java.awt.Color(244, 244, 244));
        panelRound1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(134, 168, 231), 2, true));
        panelRound1.setOpaque(true);

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(108, 91, 123));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("XÁC NHẬN ĐẶT MÓN");

        cmdOK.setBackground(new java.awt.Color(17, 153, 142));
        cmdOK.setForeground(new java.awt.Color(108, 91, 123));
        cmdOK.setText("Xác nhận");
        cmdOK.setFocusable(false);
        cmdOK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmdOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOKActionPerformed(evt);
            }
        });

        cmdCancel.setBackground(new java.awt.Color(237, 33, 58));
        cmdCancel.setForeground(new java.awt.Color(108, 91, 123));
        cmdCancel.setText("Hủy");
        cmdCancel.setFocusable(false);
        cmdCancel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });

        lbMessage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbMessage.setForeground(new java.awt.Color(108, 91, 123));
        lbMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMessage.setText("Message");

        lbTotal.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(108, 91, 123));
        lbTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotal.setText("Total");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lbMessage)
                .addGap(18, 18, 18)
                .addComponent(lbTotal)
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdOK, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(cmdCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed
        closeMenu();
    }//GEN-LAST:event_cmdOKActionPerformed

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        closeMenu();
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void closeMenu() {
        if (animator.isRunning()) {
            animator.stop();
        }
        show = false;
        animator.start();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0, 0, Color.decode("#516395"), 0, getHeight(), Color.decode("#614385"));
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponents(g);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private NhaHang.View.Swing.ButtonOutLine cmdCancel;
    private NhaHang.View.Swing.ButtonOutLine cmdOK;
    private javax.swing.JLabel lbMessage;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbTotal;
    private NhaHang.View.Swing.PanelRound panelRound1;
    // End of variables declaration//GEN-END:variables
}
