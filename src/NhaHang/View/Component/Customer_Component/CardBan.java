package NhaHang.View.Component.Customer_Component;

import NhaHang.Controller.Service.ServiceCustomer;
import NhaHang.Controller.Service.ServiceStaff;
import NhaHang.View.Dialog.MS_ConfirmReserve;
import NhaHang.Model.ModelKhachHang;
import NhaHang.Model.ModelBan;
import NhaHang.View.Dialog.MS_Confirm;
import NhaHang.View.Main_Frame.Main_Customer_Frame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CardBan extends javax.swing.JPanel {
    
    private ServiceStaff serviceS = new ServiceStaff();
    private final ModelBan data;
    private final ModelKhachHang customer;
    private MS_Confirm obj;
    private MS_ConfirmReserve book;
    private ServiceCustomer service;
   
    public CardBan(ModelBan data,ModelKhachHang customer) {
        this.data = data;
        this.customer=customer;
        initComponents();
        init();
        setOpaque(false);
    }
    public void init(){
        MS_Confirm obj = new MS_Confirm(Main_Customer_Frame.getFrames()[0], true);
        setPreferredSize(new Dimension(300, 200));
        lbTitle.setText(data.getName());
        lbValue.setText(data.getStatus());
        switch (data.getStatus()) {
            case "Con trong" -> {
                //Nếu bàn trống thì thêm event xử lý đặt bàn
                cmdBook.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       obj.Cus_ConfirmBook(data,customer);
                        try {
                           
                            serviceS.updatetrangthaidangdung(data.getID());
                        } catch (java.sql.SQLException ex) {
                            Logger.getLogger(CardBan.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
            case "Dang dung bua" -> {
                cmdBook.setText("KHÔNG THỂ ĐẶT");
                cmdBook.setBackground(Color.decode("#232526"));
                cmdBook.setEnabled(false);
            }
            case "Da dat truoc" -> {
                cmdBook.setText("KHÔNG THỂ ĐẶT");
                cmdBook.setBackground(Color.decode("#232526"));
                cmdBook.setEnabled(false);
            }
            default -> {
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new NhaHang.View.Swing.PanelRound();
        lbValue = new javax.swing.JLabel();
        cmdBook = new NhaHang.View.Swing.Button();
        lbTitle = new javax.swing.JLabel();

        panelRound1.setPreferredSize(new java.awt.Dimension(300, 300));

        lbValue.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbValue.setForeground(new java.awt.Color(108, 91, 123));
        lbValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbValue.setText("Value");

        cmdBook.setBackground(new java.awt.Color(108, 91, 123));
        cmdBook.setForeground(new java.awt.Color(255, 255, 255));
        cmdBook.setText("ĐẶT BÀN");
        cmdBook.setToolTipText("");
        cmdBook.setFocusable(false);
        cmdBook.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmdBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBookActionPerformed(evt);
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
            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(cmdBook, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdBook, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdBookActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private NhaHang.View.Swing.Button cmdBook;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbValue;
    private NhaHang.View.Swing.PanelRound panelRound1;
    // End of variables declaration//GEN-END:variables
}
