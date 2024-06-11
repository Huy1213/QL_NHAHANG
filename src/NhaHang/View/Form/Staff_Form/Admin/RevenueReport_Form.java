package NhaHang.View.Form.Staff_Form.Admin;

import NhaHang.Controller.Service.ServiceAdmin;
import NhaHang.Model.ModelCard;
import java.awt.Color;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class RevenueReport_Form extends javax.swing.JPanel {

    private ServiceAdmin service;
    private DecimalFormat df;
    private SimpleDateFormat simpleDateFormat;

    public RevenueReport_Form() {
        initComponents();
        init();
    }

    public void init() {
        service = new ServiceAdmin();
        df = new DecimalFormat("##,###,###");
        setCurrentDate();
        // Thêm data cho Card
        initCard("Hôm nay");
    }

    public void initCard(String filter) {
        try {
            int revenue = 0;
            String descR = ". . .";

            revenue = service.getRevenueHD(filter);

            Crevenue.setData(new ModelCard(new ImageIcon(getClass().getResource("")), "Doanh Thu", df.format(revenue) + "đ", descR));

            Crevenue.repaint();

        } catch (SQLException ex) {
            Logger.getLogger(RevenueReport_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setCurrentDate() {
        simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");
        lbDate.setText("Ngày hiện tại: " + simpleDateFormat.format(new Date()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        lbprofit = new javax.swing.JLabel();
        panelCard = new javax.swing.JLayeredPane();
        Crevenue = new NhaHang.View.Component.Admin_Component.Card();
        lbDate = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        lbchart = new javax.swing.JLabel();
        filter = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(247, 247, 247));

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(108, 91, 123));
        lbTitle.setText("Báo cáo Doanh Thu");
        lbTitle.setIconTextGap(10);

        lbprofit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbprofit.setForeground(new java.awt.Color(89, 89, 89));
        lbprofit.setText("Thống kê Doanh Thu");

        panelCard.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        Crevenue.setColor1(new java.awt.Color(200, 175, 200));
        Crevenue.setColor2(new java.awt.Color(200, 175, 200));
        panelCard.add(Crevenue);

        lbDate.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbDate.setForeground(new java.awt.Color(108, 91, 123));
        lbDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/calendar.png"))); // NOI18N
        lbDate.setText("Ngày hiện tại");
        lbDate.setIconTextGap(20);

        jSeparator2.setBackground(new java.awt.Color(76, 76, 76));

        jSeparator3.setBackground(new java.awt.Color(76, 76, 76));



        filter.setEditable(true);
        filter.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        filter.setForeground(new java.awt.Color(108, 91, 123));
        filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "Tháng này", "Năm này" }));
        filter.setToolTipText("Sắp xếp");
        filter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(164, 145, 145), 2));
        filter.setFocusable(false);
        filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelCard, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lbTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbDate, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbchart, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbprofit, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(filter, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(lbprofit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbchart)
                .addContainerGap())
        );
    }// </editor-fold>                        

    private void filterActionPerformed(java.awt.event.ActionEvent evt) {                                       
        initCard(filter.getSelectedItem().toString());
    }                                      

    // Variables declaration - do not modify                     
    private NhaHang.View.Component.Admin_Component.Card Crevenue;
    private javax.swing.JComboBox<String> filter;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbchart;
    private javax.swing.JLabel lbprofit;
    private javax.swing.JLayeredPane panelCard;
    // End of variables declaration                   
}
