package NhaHang.View.Form.NhanVien_Form.QuanLi;

import NhaHang.Controller.Service.ServiceAdmin;
import NhaHang.Controller.Service.ServiceStaff;
import NhaHang.Model.ModelNguoiDung;
import NhaHang.Model.ModelNhanVien;
import NhaHang.View.Form.MainForm;
import NhaHang.View.Swing.CustomScrollBar.ScrollBarCustom;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StaffManagement_Form extends javax.swing.JPanel {

    private ServiceAdmin serviceA;
    private ServiceStaff serviceS;
    private ArrayList<ModelNhanVien> list;
    private ModelNguoiDung user;
    private ModelNhanVien admin;
    private final MainForm main;

    public StaffManagement_Form(ModelNguoiDung user, MainForm main) {
        this.user=user;
        this.main = main;
        serviceA = new ServiceAdmin();
        serviceS = new ServiceStaff();
        initComponents();
        init();
    }

    public void init() {
        txtSearch.setHint("Tìm kiếm Nhân Viên . . .");
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        getInfoNQL();
        //Thêm data cho Menu
        initTable();
        getNumberofS();
        //Them event cho Button ThemNL
        cmdAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.showForm(new InsertAndUpdate_Staff_Form(user, admin, null, main));
            }
        });

        cmdUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ModelNhanVien staff=serviceA.getNV(tableNV.getFirstCol_RowSelected(tableNV.getSelectedRow()));
                    main.showForm(new InsertAndUpdate_Staff_Form(user, admin, staff, main));
                } catch (SQLException ex) {
                    Logger.getLogger(StaffManagement_Form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void getInfoNQL() {
        try {
            admin = serviceS.getStaff(user.getUserID());
        } catch (SQLException ex) {
            Logger.getLogger(StaffManagement_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getNumberofS() {
        txtTong.setText(list.size() + " NV");
    }

    public void initTable() {
        try {
            list = serviceA.getListNV();
            for (ModelNhanVien data : list) {
                tableNV.addRow(new Object[]{data.getId_NV(), data.getTenNV(), data.getNgayVL(), data.getSdt(), data.getChucvu(),data.getTinhTrang()});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void searchTable(String txt) {
        tableNV.removeAllRow();
        for (ModelNhanVien data : list) {
            if ((data.getTenNV()).toLowerCase().contains(txt.toLowerCase())) {
                tableNV.addRow(new Object[]{data.getId_NV(), data.getTenNV(), data.getNgayVL(), data.getSdt(), data.getChucvu(),data.getTinhTrang()});
            }
        }
        tableNV.repaint();
        tableNV.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSearch = new NhaHang.View.Swing.MyTextField();
        lbTong = new javax.swing.JLabel();
        txtTong = new NhaHang.View.Swing.MyTextField();
        lbNV = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNV = new NhaHang.View.Swing.Table();
        cmdAdd = new NhaHang.View.Swing.Button();
        cmdUpdate = new NhaHang.View.Swing.Button();

        setBackground(new java.awt.Color(247, 247, 247));

        txtSearch.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/loupe (1).png"))); // NOI18N
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        lbTong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTong.setText("Tổng số Nhân Viên");

        txtTong.setEditable(false);
        txtTong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTong.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        lbNV.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbNV.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNV.setText("Danh sách Nhân Viên");

        jSeparator2.setBackground(new java.awt.Color(76, 76, 76));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tableNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Tên nhân viên", "Ngày vào làm", "Số điện thoại", "Chức vụ", "Tình Trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableNV);
        if (tableNV.getColumnModel().getColumnCount() > 0) {
            tableNV.getColumnModel().getColumn(0).setMinWidth(100);
            tableNV.getColumnModel().getColumn(0).setMaxWidth(100);
            tableNV.getColumnModel().getColumn(1).setMinWidth(200);
            tableNV.getColumnModel().getColumn(1).setPreferredWidth(30);
            tableNV.getColumnModel().getColumn(1).setMaxWidth(200);
            tableNV.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        cmdAdd.setBackground(new java.awt.Color(255, 102, 0));
        cmdAdd.setForeground(new java.awt.Color(255, 255, 255));
        cmdAdd.setText("THÊM NV");
        cmdAdd.setFocusable(false);
        cmdAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmdAdd.setIconTextGap(15);

        cmdUpdate.setBackground(new java.awt.Color(255, 102, 0));
        cmdUpdate.setForeground(new java.awt.Color(255, 255, 255));
        cmdUpdate.setText("SỬA THÔNG TIN");
        cmdUpdate.setFocusable(false);
        cmdUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)
                            .addComponent(jSeparator2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNV)
                                    .addComponent(lbTong))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTong, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed

        searchTable(txtSearch.getText().trim());
    }//GEN-LAST:event_txtSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private NhaHang.View.Swing.Button cmdAdd;
    private NhaHang.View.Swing.Button cmdUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbNV;
    private javax.swing.JLabel lbTong;
    private NhaHang.View.Swing.Table tableNV;
    private NhaHang.View.Swing.MyTextField txtSearch;
    private NhaHang.View.Swing.MyTextField txtTong;
    // End of variables declaration//GEN-END:variables
}
