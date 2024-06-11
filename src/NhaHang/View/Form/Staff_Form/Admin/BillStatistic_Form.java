package NhaHang.View.Form.Staff_Form.Admin;

import EasyXLS.Constants.DataType;
import EasyXLS.ExcelDocument;
import EasyXLS.ExcelTable;
import EasyXLS.ExcelWorksheet;
import NhaHang.Controller.Service.ServiceAdmin;
import NhaHang.Model.ModelHoaDon;
import NhaHang.View.Dialog.MS_Success;
import NhaHang.View.Form.MainForm;
import NhaHang.View.Main_Frame.Main_Admin_Frame;
import NhaHang.View.Swing.CustomScrollBar.ScrollBarCustom;
import java.awt.Color;
import java.io.File;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class BillStatistic_Form extends javax.swing.JPanel {

    private ServiceAdmin serviceA;
    private ArrayList<ModelHoaDon> list;
    private final MainForm main;
    private DecimalFormat df;
    private SimpleDateFormat simpleDateFormat;
    private MS_Success obj;

    public BillStatistic_Form(MainForm main) {
        this.main = main;
        serviceA = new ServiceAdmin();
        initComponents();
        init();
    }

    public void init() {
        txtSearch.setHint("Tìm kiếm Hóa Đơn . . .");
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        df = new DecimalFormat("##,###,###");
        obj=new MS_Success(Main_Admin_Frame.getFrames()[0], true);
        //Thêm data cho Menu
        initTable();
        getRevenue();
        setCurrentDate();
    }
    //Lấy doanh thu hóa đơn trong ngày
    public void getRevenue() {
        try {
            txtprofit.setText(df.format(serviceA.getRevenueHD("Hôm nay")) + "đ");
        } catch (SQLException ex) {
            Logger.getLogger(BillStatistic_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setCurrentDate() {
        simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");
        lbDate.setText("Ngày hiện tại: " + simpleDateFormat.format(new Date()));
    }

    public void initTable() {
        try {
            list = serviceA.getListHDIn("Tất cả");
            for (ModelHoaDon data : list) {
                tableHD.addRow(new Object[]{data.getIdHoaDon(), data.getIdKH(), data.getIdBan(), data.getNgayHD(), df.format(data.getTongtien()) + "đ"});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void searchTable(String txt) {
        tableHD.removeAllRow();
        for (ModelHoaDon data : list) {
            if ((data.getIdHoaDon() + "").toLowerCase().contains(txt.toLowerCase())) {
                tableHD.addRow(new Object[]{data.getIdHoaDon(), data.getIdKH(), data.getIdBan(), data.getNgayHD(), df.format(data.getTongtien()) + "đ"});
            }
        }
        tableHD.repaint();
        tableHD.revalidate();
    }

    public void FilterTable(String txt) {
        tableHD.removeAllRow();
        list.clear();
        try {
            list = serviceA.getListHDIn(txt);
        } catch (SQLException ex) {
            Logger.getLogger(BillStatistic_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (ModelHoaDon data : list) {
            tableHD.addRow(new Object[]{data.getIdHoaDon(), data.getIdKH(), data.getIdBan(), data.getNgayHD(), df.format(data.getTongtien()) + "đ"});
        }
        tableHD.repaint();
        tableHD.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSearch = new NhaHang.View.Swing.MyTextField();
        lbprofit = new javax.swing.JLabel();
        txtprofit = new NhaHang.View.Swing.MyTextField();
        lbCus = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHD = new NhaHang.View.Swing.Table();
        lbDate = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        filter = new javax.swing.JComboBox<>();
        cmdCTHD = new NhaHang.View.Swing.Button();

        setBackground(new java.awt.Color(247, 247, 247));

        txtSearch.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/loupe (1).png"))); // NOI18N
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        lbprofit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbprofit.setText("Doanh thu Hóa Đơn trong ngày");

        txtprofit.setEditable(false);
        txtprofit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtprofit.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        lbCus.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbCus.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCus.setText("Danh sách Hóa Đơn");

        jSeparator2.setBackground(new java.awt.Color(76, 76, 76));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tableHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ", "Mã KH", "Mã Bàn", "Ngày Hóa Đơn", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableHD);
        if (tableHD.getColumnModel().getColumnCount() > 0) {
            tableHD.getColumnModel().getColumn(0).setMinWidth(120);
            tableHD.getColumnModel().getColumn(0).setMaxWidth(120);
            tableHD.getColumnModel().getColumn(1).setMinWidth(120);
            tableHD.getColumnModel().getColumn(1).setMaxWidth(120);
            tableHD.getColumnModel().getColumn(2).setMinWidth(120);
            tableHD.getColumnModel().getColumn(2).setMaxWidth(120);
        }

        lbDate.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbDate.setForeground(new java.awt.Color(108, 91, 123));
        lbDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/calendar.png"))); // NOI18N
        lbDate.setText("Ngày hiện tại");
        lbDate.setIconTextGap(20);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Hiển thị theo");

        filter.setEditable(true);
        filter.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Hôm nay", "Tháng này", "Năm này" }));
        filter.setToolTipText("Sắp xếp");
        filter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(164, 145, 145), 2));
        filter.setFocusable(false);
        filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterActionPerformed(evt);
            }
        });

        cmdCTHD.setBackground(new java.awt.Color(255, 102, 0));
        cmdCTHD.setForeground(new java.awt.Color(255, 255, 255));
        cmdCTHD.setText("XEM CTHĐ");
        cmdCTHD.setFocusable(false);
        cmdCTHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmdCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCTHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                            .addComponent(jSeparator2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmdCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbCus)
                                .addGap(121, 121, 121)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbprofit)
                                .addGap(18, 18, 18)
                                .addComponent(txtprofit, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbDate, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbprofit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprofit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDate))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCus, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        searchTable(txtSearch.getText().trim());
    }//GEN-LAST:event_txtSearchActionPerformed

    private void filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterActionPerformed
        FilterTable(filter.getSelectedItem().toString());
    }//GEN-LAST:event_filterActionPerformed

    private void cmdCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCTHDActionPerformed
        int idHD=tableHD.getFirstCol_RowSelected(tableHD.getSelectedRow());
        ModelHoaDon bill=null;
        for(ModelHoaDon data:list){
            if(data.getIdHoaDon()==idHD){
                bill=data;
                break;
            }
        }
        main.showForm(new CTHD_Form(bill,main));
    }//GEN-LAST:event_cmdCTHDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private NhaHang.View.Swing.Button cmdCTHD;
    private javax.swing.JComboBox<String> filter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbCus;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbprofit;
    private NhaHang.View.Swing.Table tableHD;
    private NhaHang.View.Swing.MyTextField txtSearch;
    private NhaHang.View.Swing.MyTextField txtprofit;
    // End of variables declaration//GEN-END:variables
}
