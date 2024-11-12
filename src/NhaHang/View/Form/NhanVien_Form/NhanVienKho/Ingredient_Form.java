
package NhaHang.View.Form.NhanVien_Form.NhanVienKho;

import NhaHang.Controller.Service.ServiceStaff;
import NhaHang.Model.ModelNguyenLieu;
import NhaHang.View.Dialog.MS_Confirm;
import NhaHang.View.Form.MainForm;
import NhaHang.View.Main_Frame.Main_WarehouseStaff_Frame;
import NhaHang.View.Swing.CustomScrollBar.ScrollBarCustom;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class Ingredient_Form extends javax.swing.JPanel {

    private ServiceStaff service;
    private ArrayList<ModelNguyenLieu> list;
    private final MainForm main;
    private MS_Confirm obj;
    DecimalFormat df;
    
    public Ingredient_Form(MainForm main) {
        this.main=main;
        service=new ServiceStaff();
        initComponents();
        init();
    }

    public void init(){
        obj = new MS_Confirm(Main_WarehouseStaff_Frame.getFrames()[0], true);
        txtSearch.setHint("Tìm kiếm Nguyên Liệu . . .");
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        df = new DecimalFormat("##,###,###");
        //Thêm data cho Menu
        initTable();
        getUserSales();
        //Them event cho Button ThemNL
        cmdAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                main.showForm(new InsertAndUpdate_Ingredient_Form(main,null));
            }
        });
        //Them event cho Button XoaNL
        cmdDelete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int row=tableNL.getSelectedRow();
                    ModelNguyenLieu data=service.getNLbyID(tableNL.getFirstCol_RowSelected(row));
                    boolean delete=obj.Staff_ConfirmDelete(data);
                    if(delete==true){
                        DefaultTableModel model=(DefaultTableModel) tableNL.getModel();
                        model.removeRow(row);
                        tableNL.repaint();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Ingredient_Form.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        //Them event cho Button SuaNL
        cmdUpdate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ModelNguyenLieu data=service.getNLbyID(tableNL.getFirstCol_RowSelected(tableNL.getSelectedRow()));
                    main.showForm(new InsertAndUpdate_Ingredient_Form(main,data));
                } catch (SQLException ex) {
                    Logger.getLogger(Ingredient_Form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    public void getUserSales(){
             txtTong.setText(list.size()+"");
    }
    public void initTable(){
        try {
            list = service.MenuNL();
            for(ModelNguyenLieu data:list){  
                tableNL.addRow(new Object[]{data.getId(),data.getTenNL(),df.format(data.getDonGia())+"đ",data.getDvt()});
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void searchTable(String txt){
        tableNL.removeAllRow();
        for(ModelNguyenLieu data:list){
            if((data.getTenNL()).toLowerCase().contains(txt.toLowerCase())){
                tableNL.addRow(new Object[]{data.getId(),data.getTenNL(),df.format(data.getDonGia())+"đ",data.getDvt()});
            }
        }
        tableNL.repaint();
        tableNL.revalidate();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSearch = new NhaHang.View.Swing.MyTextField();
        lbTong = new javax.swing.JLabel();
        txtTong = new NhaHang.View.Swing.MyTextField();
        lbNL = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNL = new NhaHang.View.Swing.Table();
        cmdAdd = new NhaHang.View.Swing.Button();
        cmdUpdate = new NhaHang.View.Swing.Button();
        cmdDelete = new NhaHang.View.Swing.Button();

        setBackground(new java.awt.Color(247, 247, 247));

        txtSearch.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/loupe (1).png"))); // NOI18N
        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtSearchMouseEntered(evt);
            }
        });
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        lbTong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTong.setForeground(new java.awt.Color(89, 89, 89));
        lbTong.setText("Tổng số nguyên liệu");

        txtTong.setEditable(false);
        txtTong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTong.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        lbNL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbNL.setForeground(new java.awt.Color(89, 89, 89));
        lbNL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNL.setText("Danh sách nguyên liệu");

        jSeparator2.setBackground(new java.awt.Color(76, 76, 76));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tableNL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NL", "Tên nguyên liệu", "Đơn giá", "Đơn vị tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableNL);
        if (tableNL.getColumnModel().getColumnCount() > 0) {
            tableNL.getColumnModel().getColumn(0).setMinWidth(120);
            tableNL.getColumnModel().getColumn(0).setMaxWidth(120);
            tableNL.getColumnModel().getColumn(1).setMinWidth(250);
            tableNL.getColumnModel().getColumn(1).setPreferredWidth(30);
            tableNL.getColumnModel().getColumn(1).setMaxWidth(250);
            tableNL.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        cmdAdd.setBackground(new java.awt.Color(108, 91, 123));
        cmdAdd.setForeground(new java.awt.Color(255, 255, 255));
        cmdAdd.setText("THÊM NLIỆU");
        cmdAdd.setFocusable(false);
        cmdAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmdAdd.setIconTextGap(10);

        cmdUpdate.setBackground(new java.awt.Color(108, 91, 123));
        cmdUpdate.setForeground(new java.awt.Color(255, 255, 255));
        cmdUpdate.setText("SỬA NLIỆU");
        cmdUpdate.setFocusable(false);
        cmdUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmdUpdate.setIconTextGap(10);

        cmdDelete.setBackground(new java.awt.Color(108, 91, 123));
        cmdDelete.setForeground(new java.awt.Color(255, 255, 255));
        cmdDelete.setText("XÓA NLIỆU");
        cmdDelete.setFocusable(false);
        cmdDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmdDelete.setIconTextGap(10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cmdUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNL)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbTong, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTong, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(lbNL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
       
        searchTable(txtSearch.getText().trim());
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseEntered
        
        searchTable(txtSearch.getText().trim());
    }//GEN-LAST:event_txtSearchMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private NhaHang.View.Swing.Button cmdAdd;
    private NhaHang.View.Swing.Button cmdDelete;
    private NhaHang.View.Swing.Button cmdUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbNL;
    private javax.swing.JLabel lbTong;
    private NhaHang.View.Swing.Table tableNL;
    private NhaHang.View.Swing.MyTextField txtSearch;
    private NhaHang.View.Swing.MyTextField txtTong;
    // End of variables declaration//GEN-END:variables
}
