package NhaHang.View.Form.Staff_Form.Admin;

import NhaHang.Controller.Service.ServiceAdmin;
import NhaHang.Model.ModelMonAn;
import NhaHang.View.Form.MainForm;
import NhaHang.View.Swing.CustomScrollBar.ScrollBarCustom;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuManagement_Form extends javax.swing.JPanel {

    private ServiceAdmin serviceA;
    private ArrayList<ModelMonAn> list;

    private final MainForm main;
    private DecimalFormat df;

    public MenuManagement_Form(MainForm main) {

        this.main = main;
        serviceA = new ServiceAdmin();

        initComponents();
        init();
    }

    public void init() {
        txtSearch.setHint("Tìm kiếm Món Ăn . . .");
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        df = new DecimalFormat("##,###,###");
        //Thêm data cho Menu
        initTable();
        getNumberofF();
        //Them event cho Button ThemNL
        cmdAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.showForm(new InsertAndUpdate_Food_Form(null,main));
            }
        });

        cmdUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idMA =tableMA.getFirstCol_RowSelected(tableMA.getSelectedRow());
                ModelMonAn food=null;
                for(ModelMonAn data:list){
                    if(data.getId()==idMA){
                        food=data;
                        break;
                    }
                }
                main.showForm(new InsertAndUpdate_Food_Form(food,main));
            }
        });

    }

    public void getNumberofF() {
        try {
            txtTong.setText(serviceA.getNumberFood_inBusiness() + " Món");
        } catch (SQLException ex) {
            Logger.getLogger(MenuManagement_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initTable() {
        try {
            list = serviceA.getMenuFood();
            for (ModelMonAn data : list) {
                tableMA.addRow(new Object[]{data.getId(), data.getTitle(), df.format(data.getValue()) + "đ", data.getType(), data.getState()});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void searchTable(String txt) {
        tableMA.removeAllRow();
        for (ModelMonAn data : list) {
            if ((data.getTitle()).toLowerCase().contains(txt.toLowerCase())) {
                tableMA.addRow(new Object[]{data.getId(), data.getTitle(), df.format(data.getValue()) + "đ", data.getType(), data.getState()});
            }
        }
        tableMA.repaint();
        tableMA.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSearch = new NhaHang.View.Swing.MyTextField();
        lbTong = new javax.swing.JLabel();
        txtTong = new NhaHang.View.Swing.MyTextField();
        lbMA = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMA = new NhaHang.View.Swing.Table();
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
        lbTong.setText("Số Món Ăn đang kinh doanh");

        txtTong.setEditable(false);
        txtTong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTong.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        lbMA.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbMA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbMA.setText("Danh sách Món Ăn");

        jSeparator2.setBackground(new java.awt.Color(76, 76, 76));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tableMA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã MA", "Tên món ăn", "Đơn giá", "Loại món", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableMA);
        if (tableMA.getColumnModel().getColumnCount() > 0) {
            tableMA.getColumnModel().getColumn(0).setMinWidth(100);
            tableMA.getColumnModel().getColumn(0).setMaxWidth(100);
            tableMA.getColumnModel().getColumn(1).setMinWidth(330);
            tableMA.getColumnModel().getColumn(1).setMaxWidth(330);
            tableMA.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableMA.getColumnModel().getColumn(3).setMinWidth(120);
            tableMA.getColumnModel().getColumn(3).setMaxWidth(120);
        }

        cmdAdd.setBackground(new java.awt.Color(108, 91, 123));
        cmdAdd.setForeground(new java.awt.Color(255, 255, 255));
        cmdAdd.setText("THÊM MÓN ĂN");
        cmdAdd.setFocusable(false);
        cmdAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        cmdUpdate.setBackground(new java.awt.Color(108, 91, 123));
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbTong)
                                    .addComponent(lbMA))
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTong, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cmdAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cmdUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTong, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbMA)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
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
    private javax.swing.JLabel lbMA;
    private javax.swing.JLabel lbTong;
    private NhaHang.View.Swing.Table tableMA;
    private NhaHang.View.Swing.MyTextField txtSearch;
    private NhaHang.View.Swing.MyTextField txtTong;
    // End of variables declaration//GEN-END:variables
}
