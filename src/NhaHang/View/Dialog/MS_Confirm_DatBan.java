package NhaHang.View.Dialog;

import NhaHang.Controller.Service.ServiceCustomer;
import NhaHang.Controller.Service.ServiceStaff;
import NhaHang.Model.ModelKhachHang;
import NhaHang.Model.ModelBan;
import NhaHang.Model.ModelNguyenLieu;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MS_Confirm_DatBan extends javax.swing.JDialog {

    private final Animator animator;
    private boolean show = true;
    private Frame frame;
    private ServiceCustomer service;
    private ServiceStaff serviceS;
    boolean delete;
    public MS_Confirm_DatBan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        service = new ServiceCustomer();
        serviceS = new ServiceStaff();
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
    //Xác nhận đặt bàn ở Khách Hàng
    public boolean Cus_ConfirmBook(ModelBan table, ModelKhachHang customer) 
    {
        final boolean[] isConfirmed = {false};  // Biến cờ để lưu trạng thái xác nhận
        
        setLocationRelativeTo(frame);
        lbTitle.setText("XÁC NHẬN ĐẶT BÀN");     
        lbMessage.setText("Bạn có chắc đặt bàn " + table.getName() + " không ?");
        animator.start();

        // Xử lý khi người dùng nhấn nút OK (Xác nhận)
        cmdOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                     String sdt = txtSDT.getText();
                     System.out.println("Giá trị SĐT: " + sdt);// Lấy số điện thoại từ trường nhập liệu
                     String hoten = txtTen.getText();  // Lấy họ tên từ trường nhập liệu
                    if (hoten.isEmpty() || sdt.isEmpty()) {
                        lbMessage.setText("Họ tên và số điện thoại không được để trống.");
                        return;
                    }
                    Integer idKhachHangSDT = service.getIDKhachHangBySDT(sdt);
                     if (idKhachHangSDT != null) 
                     {
                         service.InsertHoaDon(table, idKhachHangSDT);
                     }
                     else 
                     {
                        service.insertKhachHang(hoten, sdt); // Giả sử bạn đã tạo phương thức insertKhachHang  
                        System.out.println("Đã thêm khách hàng: " + hoten + ", SĐT: " + sdt);
                        int idKhachHang = service.getIDKhachHang(hoten, sdt);
                        service.InsertHoaDon(table, idKhachHang);  // Chèn hóa đơn khi người dùng xác nhận
                     }
                              
                    isConfirmed[0] = true;  // Cập nhật biến cờ thành true khi người dùng xác nhận
                    setVisible(false);  // Đóng hộp thoại sau khi xác nhận
                } catch (SQLException ex) {
                    Logger.getLogger(MS_Confirm_DatBan.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Lỗi khi thêm khách hàng: " + ex.getMessage());
                    Logger.getLogger(MS_Confirm_DatBan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // Xử lý khi người dùng nhấn nút Hủy (Đóng hộp thoại mà không thực hiện)
        cmdCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isConfirmed[0] = false;  // Đặt biến cờ thành false khi người dùng hủy
                setVisible(false);  // Đóng hộp thoại
            }
        });

        setVisible(true);  // Hiển thị hộp thoại xác nhận
        return isConfirmed[0];  // Trả về kết quả xác nhận
    }

    //Xác nhận xóa nguyên liệu ở nhân viên kho
    public boolean Staff_ConfirmDelete(ModelNguyenLieu data ){
        setLocationRelativeTo(frame);
        delete=false;
        lbTitle.setText("XÁC NHẬN XÓA NGUYÊN LIỆU");
        lbMessage.setText("Bạn có chắc xóa nguyên liệu " + data.getTenNL() + " không ?");
        animator.start();
        cmdOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    delete=true;
                    serviceS.DeleteNL(data);
                } catch (SQLException ex) {
                    Logger.getLogger(MS_Confirm_DatBan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        setVisible(true);
        return delete;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new NhaHang.View.Swing.PanelRound();
        lbTitle = new javax.swing.JLabel();
        cmdOK = new NhaHang.View.Swing.ButtonOutLine();
        cmdCancel = new NhaHang.View.Swing.ButtonOutLine();
        lbMessage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTen = new NhaHang.View.Swing.MyTextField();
        txtSDT = new NhaHang.View.Swing.MyTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(215, 215, 215));
        setUndecorated(true);

        panelRound1.setBackground(new java.awt.Color(244, 244, 244));
        panelRound1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(134, 168, 231), 2));
        panelRound1.setOpaque(true);

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(108, 91, 123));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("BẠN ĐÃ CHẮC CHƯA ?\n");

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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(89, 89, 89));
        jLabel2.setText("Số Điện Thoại");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(89, 89, 89));
        jLabel1.setText("Tên Người Đặt");

        txtTen.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        txtSDT.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(106, 106, 106))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lbMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
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
            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbMessage;
    private javax.swing.JLabel lbTitle;
    private NhaHang.View.Swing.PanelRound panelRound1;
    private NhaHang.View.Swing.MyTextField txtSDT;
    private NhaHang.View.Swing.MyTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
