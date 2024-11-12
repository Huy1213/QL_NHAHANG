package NhaHang.View.Form.KhachHang_Form;

import NhaHang.Controller.Service.ServiceCustomer;
import NhaHang.Model.ModelMonAn;
import NhaHang.Model.ModelKhachHang;
import NhaHang.Model.ModelHoaDon;
import NhaHang.Model.ModelNguoiDung;
import NhaHang.View.ThanhPhan.KhachHang_ThanhPhan.CardMonAn;
import NhaHang.View.Dialog.MS_PayBill;
import NhaHang.View.Dialog.MS_Warning;
import NhaHang.View.Main_Frame.Main_Customer_Frame;
import NhaHang.View.Swing.CustomScrollBar.ScrollBarCustom;
import NhaHang.View.Swing.WrapLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class FoodMenu_Form extends javax.swing.JPanel {

    private final String type;
    private final ServiceCustomer service;
    private ArrayList<ModelMonAn> list;
    private final ModelNguoiDung user;
    private ModelKhachHang customer;
    private ModelHoaDon HoaDon;
    private final MS_Warning warning;
    private MS_PayBill obj;
    
    public void reloadMenuFood() {
        panel.removeAll(); // Xóa các món ăn hiện tại khỏi panel
        initMenuFood(); // Gọi lại hàm khởi tạo danh sách món ăn
        panel.repaint(); // Làm mới giao diện để hiển thị danh sách mới
        panel.revalidate();
    }

    public FoodMenu_Form(String type, ModelNguoiDung user) {
        this.type = type;
        this.user = user;
        service = new ServiceCustomer();
        warning = new MS_Warning(Main_Customer_Frame.getFrames()[0], true);
        obj = new MS_PayBill(Main_Customer_Frame.getFrames()[0], true);
        initComponents();    
        init();
        
        //Kiểm tra Khách hàng đã đặt bàn trước khi gọi món hay chưa
        if (HoaDon == null) {
            warning.WarningBook();
        } else {
            txtTableName.setText(HoaDon.getIdBan() + "");
        }
        
    }

    public void init() {
        try {
            panel.setLayout(new WrapLayout(WrapLayout.LEADING, 27, 20));
            txtSearch.setHint("Tìm kiếm món ăn . . .");
            jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
            customer = service.getCustomer(user.getUserID());
            
            //Tìm thông tin Hóa Đơn mà Khách Hàng vừa tạo
            HoaDon = service.FindHoaDon(customer);
            
            //Thêm data cho Menu
            initMenuFood();
            
            
            //Set Data cho Tiêu đề Menu
            switch (type) {
                case "Cuu" -> {
                    lbTitle.setText("Menu - Cừu");
                    
                }
                case "Bo" -> {
                    lbTitle.setText("Menu - Bò");
                  
                }
                case "Combo" -> {
                    lbTitle.setText("Menu - Combo");
                   
                }
                case "Cua" -> {
                    lbTitle.setText("Menu - Cua");
                 
                }
                case "MonHan" -> {
                    lbTitle.setText("Menu - Món hàn");
            
                }
                case "BanhNgot" -> {
                    lbTitle.setText("Menu - Bánh ngọt");
                 
                }
                case "Salad" -> {
                    lbTitle.setText("Menu - Salad");
                 
                }              
                case "De" -> {
                    lbTitle.setText("Menu - Dê");
                
                }
                case "DoUong" -> {
                    lbTitle.setText("Menu - Đồ uống");
                
                }
                case "Ca" -> {
                    lbTitle.setText("Menu - Cá");
                }
                default -> {
                }
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FoodMenu_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initMenuFood() {
        try {
            list = service.MenuFood(type);
            for (ModelMonAn data : list) {
                panel.add(new CardMonAn(data, HoaDon,this::reloadMenuFood));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void searchFood(String txt) {
        panel.removeAll();
        for (ModelMonAn data : list) {
            if (data.getTitle().toLowerCase().contains(txt.toLowerCase())) {
                panel.add(new CardMonAn(data, HoaDon,this::reloadMenuFood));
            }
        }
        panel.repaint();
        panel.revalidate();
    }

    public void initMenuFoodOrderby(String txt) {
        try {
            list = service.MenuFoodOrder(type, txt);
            panel.removeAll();
            for (ModelMonAn data : list) {
                panel.add(new CardMonAn(data, HoaDon,this::reloadMenuFood));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FoodMenu_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        panel.repaint();
        panel.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        txtSearch = new NhaHang.View.Swing.MyTextField();
        orderby = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cmdShowBill = new NhaHang.View.Swing.Button();
        lbTable = new javax.swing.JLabel();
        txtTableName = new NhaHang.View.Swing.MyTextField();
        jSeparator2 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(247, 247, 247));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setBackground(new java.awt.Color(215, 221, 232));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 963, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panel);

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(108, 91, 123));
        lbTitle.setText("Menu/Arias - Cừu");
        lbTitle.setIconTextGap(10);
        lbTitle.setInheritsPopupMenu(false);

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

        orderby.setEditable(true);
        orderby.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        orderby.setForeground(new java.awt.Color(108, 91, 123));
        orderby.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên A->Z", "Giá tăng dần", "Giá giảm dần" }));
        orderby.setSelectedIndex(-1);
        orderby.setToolTipText("Sắp xếp");
        orderby.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(164, 145, 145), 2));
        orderby.setFocusable(false);
        orderby.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderbyActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(108, 91, 123));
        jLabel1.setText("Sắp xếp theo");

        cmdShowBill.setBackground(new java.awt.Color(108, 91, 123));
        cmdShowBill.setForeground(new java.awt.Color(255, 255, 255));
        cmdShowBill.setText("XEM HÓA ĐƠN");
        cmdShowBill.setFocusable(false);
        cmdShowBill.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmdShowBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdShowBillActionPerformed(evt);
            }
        });

        lbTable.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTable.setForeground(new java.awt.Color(89, 89, 89));
        lbTable.setText("MÃ BÀN CỦA BẠN");

        txtTableName.setEditable(false);
        txtTableName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTableName.setText("Chưa đặt bàn");
        txtTableName.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        jSeparator2.setBackground(new java.awt.Color(76, 76, 76));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbTable)
                                .addGap(38, 38, 38)
                                .addComponent(txtTableName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(orderby, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdShowBill, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTable, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTableName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(orderby, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdShowBill, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseEntered
        searchFood(txtSearch.getText().trim());
    }//GEN-LAST:event_txtSearchMouseEntered

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        searchFood(txtSearch.getText().trim());
    }//GEN-LAST:event_txtSearchActionPerformed

    private void orderbyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderbyActionPerformed
        initMenuFoodOrderby((String) orderby.getSelectedItem());
    }//GEN-LAST:event_orderbyActionPerformed

    private void cmdShowBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdShowBillActionPerformed
        try {
            HoaDon=service.FindHoaDon(customer);
            obj.showBill(HoaDon);
        } catch (SQLException ex) {
            Logger.getLogger(FoodMenu_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmdShowBillActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private NhaHang.View.Swing.Button cmdShowBill;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbTable;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JComboBox<String> orderby;
    private javax.swing.JPanel panel;
    private NhaHang.View.Swing.MyTextField txtSearch;
    private NhaHang.View.Swing.MyTextField txtTableName;
    // End of variables declaration//GEN-END:variables
}
