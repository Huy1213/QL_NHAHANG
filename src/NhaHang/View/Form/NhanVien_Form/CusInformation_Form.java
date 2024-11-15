package NhaHang.View.Form.NhanVien_Form;

import NhaHang.Controller.Service.ServiceStaff;
import NhaHang.Model.ModelKhachHang;
import NhaHang.View.Form.MainForm;
import NhaHang.View.Swing.CustomScrollBar.ScrollBarCustom;
import java.awt.Color;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CusInformation_Form extends javax.swing.JPanel {

    private ServiceStaff service;
    private ArrayList<ModelKhachHang> list;
    private final MainForm main;
    private DecimalFormat df;

    public CusInformation_Form(MainForm main) {
        this.main = main;
        service = new ServiceStaff();
        initComponents();
        init();
    }

    public void init() {
        txtSearch.setHint("Tìm kiếm Khách Hàng . . .");
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        df = new DecimalFormat("##,###,###");
        //Thêm data cho Menu
        initTable();
        //getSLKH();
        setCurrentDate();
    }

    /*public void getSLKH() {
        txtTong.setText(list.size() + " Members");
    }*/

    public void setCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");
        lbDate.setText("Ngày hiện tại: " + simpleDateFormat.format(new Date()));
    }

    public void initTable() {
        try {
            list = service.MenuKH();
            for (ModelKhachHang data : list) {
                tableKH.addRow(new Object[]{data.getID_KH(), data.getName(), data.getSDT(), data.getDateJoin(), df.format(data.getSales()) + "đ", data.getPoints() + " xu"});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void searchTable(String txt) {
        tableKH.removeAllRow();
        for (ModelKhachHang data : list) {
            if ((data.getName()).toLowerCase().contains(txt.toLowerCase())) {
                tableKH.addRow(new Object[]{data.getID_KH(), data.getName(), data.getDateJoin(), df.format(data.getSales()) + "đ", data.getPoints() + " xu"});
            }
        }
        tableKH.repaint();
        tableKH.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSearch = new NhaHang.View.Swing.MyTextField();
        lbCus = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKH = new NhaHang.View.Swing.Table();
        lbDate = new javax.swing.JLabel();

        setBackground(new java.awt.Color(247, 247, 247));

        txtSearch.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/loupe (1).png"))); // NOI18N
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        lbCus.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbCus.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCus.setText("Danh sách Khách Hàng");

        jSeparator2.setBackground(new java.awt.Color(76, 76, 76));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tableKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Tên khách hàng", "Số điện thoại", "Ngày tham gia", "Doanh số"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableKH.setFocusable(false);
        jScrollPane1.setViewportView(tableKH);
        if (tableKH.getColumnModel().getColumnCount() > 0) {
            tableKH.getColumnModel().getColumn(0).setMinWidth(120);
            tableKH.getColumnModel().getColumn(0).setMaxWidth(120);
            tableKH.getColumnModel().getColumn(1).setMinWidth(200);
            tableKH.getColumnModel().getColumn(1).setMaxWidth(200);
            tableKH.getColumnModel().getColumn(3).setMinWidth(200);
            tableKH.getColumnModel().getColumn(3).setMaxWidth(200);
        }

        lbDate.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/calendar.png"))); // NOI18N
        lbDate.setText("Ngày hiện tại");
        lbDate.setIconTextGap(20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbCus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbDate, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDate)
                    .addComponent(lbCus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        searchTable(txtSearch.getText().trim());
    }//GEN-LAST:event_txtSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbCus;
    private javax.swing.JLabel lbDate;
    private NhaHang.View.Swing.Table tableKH;
    private NhaHang.View.Swing.MyTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
