package NhaHang.View.ThanhPhan.NhanVien_ThanhPhan;

import NhaHang.Model.ModelBan;
import NhaHang.Model.ModelNguoiDung;
import NhaHang.Model.ModelNhanVien;
import NhaHang.View.Dialog.MS_CancelReserve;
import NhaHang.View.Dialog.MS_ConfirmReserve;
import NhaHang.View.Dialog.MS_Confirm_DatBan;
import NhaHang.View.Form.MainForm;
import NhaHang.View.Form.NhanVien_Form.NhanVien.OrderFood_Form;
import NhaHang.View.Main_Frame.Main_Staff_Frame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardBanS extends javax.swing.JPanel {
    
    private final ModelBan table;
    private ModelNhanVien staff;
    private MainForm main;
    private ModelNguoiDung user;
    private MS_ConfirmReserve book;
    private MS_CancelReserve cancel;
    
    public CardBanS(ModelNguoiDung user,ModelNhanVien staff,ModelBan table,MainForm main) {
        this.user=user;
        this.staff=staff;
        this.table = table;
        this.main=main;
        initComponents();
        init();
    }
    
    public void init(){
        book = new MS_ConfirmReserve(Main_Staff_Frame.getFrames()[0], true);
        cancel = new MS_CancelReserve(Main_Staff_Frame.getFrames()[0], true);
        setPreferredSize(new Dimension(300, 160));
        lbTitle.setText("Mã bàn: "+table.getID()+" - "+table.getName());
        lbValue.setText(table.getStatus());
        switch (table.getStatus()) {
            case "Con trong" -> {
                cmdAdj.setBackground(Color.decode("#355C7D"));
                cmdAdj.setText("ĐẶT TRƯỚC");
              
                //Nếu bàn trống thì thêm event xử lý đặt bàn
                cmdAdj.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       book.ConfirmReserve(table);                  
                    }
                });
            }
            case "Dang dung bua" -> {
                cmdAdj.setText("GỌI MÓN");
                cmdAdj.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       main.showForm(new OrderFood_Form(user,staff,table,main));
                    }
                });
            }
            case "Da dat truoc" -> {
                cmdAdj.setText("HỦY ĐẶT TRƯỚC");
                cmdAdj.setBackground(Color.decode("#c94b4b"));
               
                cmdAdj.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       cancel.CancelReserve(table);
                       updateStatus();
                    }
                });
                
            }
            default -> {
            }
        }
    }
    private void setActionListenerForReserve() {
    cmdAdj.removeActionListener(cmdAdj.getActionListeners()[0]); // Xóa action listener cũ
        cmdAdj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                book.ConfirmReserve(table);
               
            }
        });
    }

    private void setActionListenerForCancel() {
        cmdAdj.removeActionListener(cmdAdj.getActionListeners()[0]); // Xóa action listener cũ
        cmdAdj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel.CancelReserve(table);            
            }
        });
    }
    public void updateStatus() {
        // Cập nhật thông tin trạng thái từ model bàn
        lbValue.setText(table.getStatus());

        // Cập nhật nút lệnh dựa trên trạng thái mới
        switch (table.getStatus()) {
            case "Con trong":
                cmdAdj.setText("HỦY ĐẶT TRƯỚC");
                cmdAdj.setBackground(Color.decode("#c94b4b"));
                cmdAdj.removeActionListener(cmdAdj.getActionListeners()[0]); // Xóa action listener cũ
                cmdAdj.addActionListener(e -> cancel.CancelReserve(table));      
            case "Da dat truoc":
                cmdAdj.setBackground(Color.decode("#355C7D"));
                cmdAdj.setText("ĐẶT TRƯỚC");
                cmdAdj.removeActionListener(cmdAdj.getActionListeners()[0]); // Xóa action listener cũ
                cmdAdj.addActionListener(e -> cancel.CancelReserve(table));
                break;
            default:
                break;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new NhaHang.View.Swing.PanelRound();
        lbValue = new javax.swing.JLabel();
        cmdAdj = new NhaHang.View.Swing.Button();
        lbTitle = new javax.swing.JLabel();

        panelRound1.setPreferredSize(new java.awt.Dimension(300, 300));

        lbValue.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbValue.setForeground(new java.awt.Color(108, 91, 123));
        lbValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbValue.setText("Value");

        cmdAdj.setBackground(new java.awt.Color(108, 91, 123));
        cmdAdj.setForeground(new java.awt.Color(255, 255, 255));
        cmdAdj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/levels.png"))); // NOI18N
        cmdAdj.setText("TÙY CHỈNH");
        cmdAdj.setToolTipText("");
        cmdAdj.setFocusable(false);
        cmdAdj.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        cmdAdj.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cmdAdj.setIconTextGap(20);
        cmdAdj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAdjActionPerformed(evt);
            }
        });

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(108, 91, 123));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Title");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdAdj, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdAdj, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdAdjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAdjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdAdjActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private NhaHang.View.Swing.Button cmdAdj;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbValue;
    private NhaHang.View.Swing.PanelRound panelRound1;
    // End of variables declaration//GEN-END:variables
}
