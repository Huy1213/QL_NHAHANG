package NhaHang.View.Form.Customer_Form;

import NhaHang.Controller.Service.ServiceCustomer;
import NhaHang.Model.ModelKhachHang;
import NhaHang.Model.ModelNguoiDung;
import NhaHang.View.Dialog.MS_ChangePassword;
import NhaHang.View.Dialog.MS_ConfirmRename;
import NhaHang.View.Main_Frame.Main_Customer_Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class AccountC_Form extends javax.swing.JPanel {

    private ModelNguoiDung user;
    private ModelKhachHang customer;
    private ServiceCustomer service;
    private Icon hide;
    private Icon show;
    private char def;
    private MS_ChangePassword changepass;
    public AccountC_Form() {
        service = new ServiceCustomer();
        initComponents();

    }

    public AccountC_Form(ModelNguoiDung user) {
        this.user = user;
        initComponents();
        init();
    }

    public void init() {
        service = new ServiceCustomer();
        changepass = new MS_ChangePassword(Main_Customer_Frame.getFrames()[0], true);
        hide = new ImageIcon(getClass().getResource("/Icons/hide.png"));
        show = new ImageIcon(getClass().getResource("/Icons/view.png"));
        def = txtmatkhau.getEchoChar();
        initUser_information();
        initCustomer_information();
        txtmatkhau.addMouseListener(new MouseAdapter() {
         
            public void mouseClicked(MouseEvent e) {
                if (txtmatkhau.getSuffixIcon().equals(hide)) {
                    txtmatkhau.setSuffixIcon(show);
                    txtmatkhau.setEchoChar((char) 0);

                } else {
                    txtmatkhau.setSuffixIcon(hide);
                    txtmatkhau.setEchoChar(def);
                }
            }

        });
    }

    public void initUser_information() {
        txtemail.setText(user.getEmail());
        txtmatkhau.setText(user.getPassword());
        txtrole.setText(user.getRole());
        txtmatkhau.setSuffixIcon(hide);
    }

    public void initCustomer_information() {
        try {
            customer = service.getCustomer(user.getUserID());
            txtmaKH.setText(customer.getID_KH() + "");
            txttenKH.setText(customer.getName());
            txtngayTG.setText(customer.getDateJoin());
            txtdso.setText(customer.getSales() + "đ");
        } catch (SQLException ex) {
            Logger.getLogger(AccountC_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void confirmRename(String message) {
        MS_ConfirmRename obj = new MS_ConfirmRename(Main_Customer_Frame.getFrames()[0], true);
        obj.ConfirmReName(message, customer);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new NhaHang.View.Swing.PanelRound();
        lbmaKH = new javax.swing.JLabel();
        txtmaKH = new NhaHang.View.Swing.MyTextField();
        titleKH = new javax.swing.JLabel();
        lbtenKH = new javax.swing.JLabel();
        txttenKH = new NhaHang.View.Swing.MyTextField();
        lbngayTG = new javax.swing.JLabel();
        lbdso = new javax.swing.JLabel();
        txtdso = new NhaHang.View.Swing.MyTextField();
        titleND = new javax.swing.JLabel();
        txtngayTG = new NhaHang.View.Swing.MyTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lbEmail = new javax.swing.JLabel();
        txtemail = new NhaHang.View.Swing.MyTextField();
        lbmk = new javax.swing.JLabel();
        txtmatkhau = new NhaHang.View.Swing.MyPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        cmdDMK = new NhaHang.View.Swing.Button();
        lbRole = new javax.swing.JLabel();
        txtrole = new NhaHang.View.Swing.MyTextField();

        setBackground(new java.awt.Color(222, 222, 222));

        bg.setBackground(new java.awt.Color(247, 247, 247));

        lbmaKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbmaKH.setForeground(new java.awt.Color(89, 89, 89));
        lbmaKH.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbmaKH.setText("Mã Khách hàng");

        txtmaKH.setEditable(false);
        txtmaKH.setBackground(new java.awt.Color(204, 204, 204));
        txtmaKH.setForeground(new java.awt.Color(93, 98, 101));
        txtmaKH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmaKH.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        titleKH.setBackground(new java.awt.Color(255, 255, 255));
        titleKH.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        titleKH.setForeground(new java.awt.Color(108, 91, 123));
        titleKH.setText("THÔNG TIN KHÁCH HÀNG");

        lbtenKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbtenKH.setForeground(new java.awt.Color(89, 89, 89));
        lbtenKH.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbtenKH.setText("Tên Khách Hàng");

        txttenKH.setBackground(new java.awt.Color(204, 204, 204));
        txttenKH.setForeground(new java.awt.Color(93, 98, 101));
        txttenKH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttenKH.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        txttenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttenKHActionPerformed(evt);
            }
        });

        lbngayTG.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbngayTG.setForeground(new java.awt.Color(89, 89, 89));
        lbngayTG.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbngayTG.setText("Ngày tham gia");

        lbdso.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbdso.setForeground(new java.awt.Color(89, 89, 89));
        lbdso.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbdso.setText("Doanh số");

        txtdso.setEditable(false);
        txtdso.setBackground(new java.awt.Color(204, 204, 204));
        txtdso.setForeground(new java.awt.Color(93, 98, 101));
        txtdso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdso.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        titleND.setBackground(new java.awt.Color(255, 255, 255));
        titleND.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        titleND.setForeground(new java.awt.Color(108, 91, 123));
        titleND.setText("THÔNG TIN TÀI KHOẢN");

        txtngayTG.setEditable(false);
        txtngayTG.setBackground(new java.awt.Color(204, 204, 204));
        txtngayTG.setForeground(new java.awt.Color(93, 98, 101));
        txtngayTG.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtngayTG.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        jSeparator1.setBackground(new java.awt.Color(76, 76, 76));

        lbEmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbEmail.setForeground(new java.awt.Color(89, 89, 89));
        lbEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbEmail.setText("Email");

        txtemail.setEditable(false);
        txtemail.setBackground(new java.awt.Color(204, 204, 204));
        txtemail.setForeground(new java.awt.Color(93, 98, 101));
        txtemail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtemail.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        lbmk.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbmk.setForeground(new java.awt.Color(89, 89, 89));
        lbmk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbmk.setText("Mật Khẩu");

        txtmatkhau.setEditable(false);
        txtmatkhau.setBackground(new java.awt.Color(204, 204, 204));
        txtmatkhau.setForeground(new java.awt.Color(93, 98, 101));
        txtmatkhau.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmatkhau.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        jSeparator2.setBackground(new java.awt.Color(76, 76, 76));

        cmdDMK.setBackground(new java.awt.Color(255, 102, 0));
        cmdDMK.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmdDMK.setText("Đổi mật khẩu");
        cmdDMK.setEffectColor(new java.awt.Color(0, 0, 0));
        cmdDMK.setFocusable(false);
        cmdDMK.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cmdDMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDMKActionPerformed(evt);
            }
        });

        lbRole.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbRole.setForeground(new java.awt.Color(89, 89, 89));
        lbRole.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbRole.setText("Vai trò");

        txtrole.setEditable(false);
        txtrole.setBackground(new java.awt.Color(204, 204, 204));
        txtrole.setForeground(new java.awt.Color(93, 98, 101));
        txtrole.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtrole.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(titleND)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(80, 80, 80))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(bgLayout.createSequentialGroup()
                                    .addComponent(txtmaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(52, 52, 52)
                                    .addComponent(txttenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(bgLayout.createSequentialGroup()
                                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbmaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbngayTG)
                                        .addComponent(txtdso, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(52, 52, 52)
                                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbdso)
                                        .addComponent(lbtenKH)
                                        .addComponent(txtngayTG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(titleKH))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(252, 252, 252)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbRole)
                                    .addComponent(txtrole, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbmk, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(txtmatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cmdDMK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 49, Short.MAX_VALUE))))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(titleKH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbmaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbtenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbdso, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbngayTG, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtngayTG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(lbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(txtrole, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(titleND)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbmk, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(cmdDMK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txttenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenKHActionPerformed
        confirmRename(txttenKH.getText().trim());
    }//GEN-LAST:event_txttenKHActionPerformed

    private void cmdDMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDMKActionPerformed
        changepass.ChangePassword(user);
        txtmatkhau.setText(user.getPassword());
    }//GEN-LAST:event_cmdDMKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private NhaHang.View.Swing.PanelRound bg;
    private NhaHang.View.Swing.Button cmdDMK;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbRole;
    private javax.swing.JLabel lbdso;
    private javax.swing.JLabel lbmaKH;
    private javax.swing.JLabel lbmk;
    private javax.swing.JLabel lbngayTG;
    private javax.swing.JLabel lbtenKH;
    private javax.swing.JLabel titleKH;
    private javax.swing.JLabel titleND;
    private NhaHang.View.Swing.MyTextField txtdso;
    private NhaHang.View.Swing.MyTextField txtemail;
    private NhaHang.View.Swing.MyTextField txtmaKH;
    private NhaHang.View.Swing.MyPasswordField txtmatkhau;
    private NhaHang.View.Swing.MyTextField txtngayTG;
    private NhaHang.View.Swing.MyTextField txtrole;
    private NhaHang.View.Swing.MyTextField txttenKH;
    // End of variables declaration//GEN-END:variables
}
