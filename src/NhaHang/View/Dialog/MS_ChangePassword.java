package NhaHang.View.Dialog;

import NhaHang.Controller.Service.ServiceUser;
import NhaHang.Model.ModelNguoiDung;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MS_ChangePassword extends javax.swing.JDialog {

    private final Animator animator;
    private boolean show = true;
    private Frame frame;
    private ServiceUser service;
    private Icon ihide;
    private Icon ishow;
    private char def;

    public MS_ChangePassword(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        service = new ServiceUser();
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
        ihide = new ImageIcon(getClass().getResource("/Icons/hide.png"));
        ishow = new ImageIcon(getClass().getResource("/Icons/view.png"));
        def = txtnewPass.getEchoChar();
        txtnewPass.setSuffixIcon(ihide);
        txtconfirm.setSuffixIcon(ihide);
    }

    public void ChangePassword(ModelNguoiDung user) {
        setLocationRelativeTo(frame);
        lbMessage.setVisible(false);
        animator.start();
        cmdOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String newPass = String.valueOf(txtnewPass.getPassword());
                    String confmPass = String.valueOf(txtconfirm.getPassword());
                    //Nếu mật khẩu mới và xác nhận mật khẩu giống nhau thực 
                    //hiện đổi mật khẩu và hiển thị thông báo đổi thành công
                    if (newPass.equals(confmPass)) {
                        lbMessage.setBackground(new Color(186, 225, 186));
                        lbMessage.setForeground(new Color(60, 117, 60));
                        lbMessage.setText("Mật khẩu của bạn đã được đổi thành công");
                        lbMessage.setVisible(true);
                        service.changePassword(user.getUserID(), newPass);
                        user.setPassword(newPass);
                    } //Nếu mật khẩu mới và xác nhận mật khẩu không giống nhau thì thông báo xác nhận mật khẩu không đúng
                    else {
                        lbMessage.setBackground(new Color(249, 181, 181));
                        lbMessage.setForeground(new Color(130, 38, 38));
                        lbMessage.setText("Xác nhận Mật Khẩu không khớp");
                        lbMessage.setVisible(true);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(MS_ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
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
        lbnewPass = new javax.swing.JLabel();
        lbConfirmPass = new javax.swing.JLabel();
        lbMessage = new javax.swing.JLabel();
        cmdClose = new javax.swing.JLabel();
        txtconfirm = new NhaHang.View.Swing.MyPasswordField();
        txtnewPass = new NhaHang.View.Swing.MyPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(215, 215, 215));
        setUndecorated(true);

        panelRound1.setBackground(new java.awt.Color(244, 244, 244));
        panelRound1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(134, 168, 231), 3));
        panelRound1.setOpaque(true);

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(108, 91, 123));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("ĐỔI MẬT KHẨU TÀI KHOẢN");

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

        lbnewPass.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbnewPass.setForeground(new java.awt.Color(89, 89, 89));
        lbnewPass.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbnewPass.setText("Mật khẩu mới ");

        lbConfirmPass.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbConfirmPass.setForeground(new java.awt.Color(89, 89, 89));
        lbConfirmPass.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbConfirmPass.setText("Xác nhận mật Khẩu mới");

        lbMessage.setBackground(new java.awt.Color(249, 181, 181));
        lbMessage.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbMessage.setForeground(new java.awt.Color(130, 38, 38));
        lbMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMessage.setText("Xác nhận Mật Khẩu không khớp");
        lbMessage.setOpaque(true);

        cmdClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cmdClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/close (2).png"))); // NOI18N
        cmdClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdCloseMouseClicked(evt);
            }
        });

        txtconfirm.setForeground(new java.awt.Color(93, 98, 101));
        txtconfirm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtconfirm.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        txtconfirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtconfirmMouseClicked(evt);
            }
        });

        txtnewPass.setForeground(new java.awt.Color(93, 98, 101));
        txtnewPass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnewPass.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        txtnewPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtnewPassMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGap(0, 439, Short.MAX_VALUE)
                .addComponent(cmdClose, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbMessage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbnewPass)
                            .addComponent(lbConfirmPass)
                            .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtconfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtnewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addComponent(cmdClose, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbnewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(txtnewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtconfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(lbMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed

    }//GEN-LAST:event_cmdOKActionPerformed

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        closeMenu();
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void cmdCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdCloseMouseClicked
        closeMenu();
    }//GEN-LAST:event_cmdCloseMouseClicked

    private void txtconfirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtconfirmMouseClicked
        if (txtconfirm.getSuffixIcon().equals(ihide)) {
            txtconfirm.setSuffixIcon(ishow);
            txtconfirm.setEchoChar((char) 0);

        } else {
            txtconfirm.setSuffixIcon(ihide);
            txtconfirm.setEchoChar(def);
        }
    }//GEN-LAST:event_txtconfirmMouseClicked

    private void txtnewPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnewPassMouseClicked
        if (txtnewPass.getSuffixIcon().equals(ihide)) {
            txtnewPass.setSuffixIcon(ishow);
            txtnewPass.setEchoChar((char) 0);

        } else {
            txtnewPass.setSuffixIcon(ihide);
            txtnewPass.setEchoChar(def);
        }
    }//GEN-LAST:event_txtnewPassMouseClicked

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
    private javax.swing.JLabel cmdClose;
    private NhaHang.View.Swing.ButtonOutLine cmdOK;
    private javax.swing.JLabel lbConfirmPass;
    private javax.swing.JLabel lbMessage;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbnewPass;
    private NhaHang.View.Swing.PanelRound panelRound1;
    private NhaHang.View.Swing.MyPasswordField txtconfirm;
    private NhaHang.View.Swing.MyPasswordField txtnewPass;
    // End of variables declaration//GEN-END:variables
}
