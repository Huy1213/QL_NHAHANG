package NhaHang.View.Form.NhanVien_Form.NhanVien;

import NhaHang.Controller.Service.ServiceCustomer;
import NhaHang.Controller.Service.ServiceStaff;
import NhaHang.Model.ModelBan;
import NhaHang.Model.ModelCTHD;
import NhaHang.Model.ModelHoaDon;
import NhaHang.Model.ModelNguoiDung;
import NhaHang.Model.ModelNhanVien;
import NhaHang.View.Dialog.MS_PaymentSuccess;
import NhaHang.View.Dialog.MS_Success;
import NhaHang.View.Form.MainForm;
import NhaHang.View.Main_Frame.Main_Admin_Frame;
import NhaHang.View.Swing.CustomScrollBar.ScrollBarCustom;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDDocument;
import raven.cell.deleteBillDetail.TableActionEvent;
import raven.cell.deleteBillDetail.TableActionCellEditorDelete;
import raven.cell.deleteBillDetail.TableActionCellRenderDelete;

public class BillS_Form extends javax.swing.JPanel {

    private ServiceStaff serviceS;
    private ServiceCustomer serviceC;
    private final ModelNguoiDung user;
    private final ModelNhanVien staff;
    private final ModelBan table;
    private final ModelHoaDon bill;
    private final MainForm main;
    private ArrayList<ModelCTHD> cthd;
    private DecimalFormat df;
    private MS_Success obj;
    private MS_PaymentSuccess conf;

    public BillS_Form(ModelNguoiDung user, ModelNhanVien staff, ModelBan table, ModelHoaDon bill, MainForm main) throws SQLException {
        this.user = user;
        this.staff = staff;
        this.table = table;
        this.bill = bill;
        this.main = main;
        initComponents();
        init();
    }

    public void init() throws SQLException {
        serviceS = new ServiceStaff();
        serviceC = new ServiceCustomer();
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        df = new DecimalFormat("##,###,###");
        obj = new MS_Success(Main_Admin_Frame.getFrames()[0], true);
        conf = new MS_PaymentSuccess(Main_Admin_Frame.getFrames()[0], true);

        txtSuDungDiem.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateTongTienWithDiem();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateTongTienWithDiem();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateTongTienWithDiem();
            }
        });
        txtTongtien.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateTienTraLai();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateTienTraLai();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateTienTraLai();
            }
        });
        txtTienKH.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateTienTraLai();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateTienTraLai();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateTienTraLai();
            }
        });
        //Thêm data cho CTHD 
        initTable();
        //Thêm data cho Tiền hóa đơn
        initCash();
        txtidHD.setText(bill.getIdHoaDon() + "");
        txtidKH.setText(bill.getIdKH() + "");
        int diemTichLuy = serviceC.getDiemTichLuy(bill.getIdKH());
        lbDiemTichLuy.setText(String.valueOf(diemTichLuy)); // Hiển thị điểm tích lũy trên JLabel

        lbNgHD.setText("Ngày: " + bill.getNgayHD());
        cbTichDiem.setSelected(true);

    }

    private void updateTienTraLai() {
        try {
            int tongTien = Integer.parseInt(txtTongtien.getText().replace(",", "").replace("d", "").trim());
            int tienKhachDua = Integer.parseInt(txtTienKH.getText().trim());

            // Tính tiền trả lại và cập nhật vào txtTientra
            int tienTraLai = tienKhachDua - tongTien;
            txtTientra.setText(df.format(tienTraLai) + "d");
        } catch (NumberFormatException e) {
            // Nếu có lỗi định dạng số, đặt lại tiền trả lại về 0
            txtTientra.setText("0d");
        }
    }

    public void initTable() {
        try {
            //Lấy danh sách CTHD từ mã hóa đơn
            cthd = serviceC.getCTHD(bill.getIdHoaDon());

            DefaultTableModel model = (DefaultTableModel) tableCTHD.getModel();
            model.setRowCount(0);

            for (ModelCTHD data : cthd) {
                model.addRow(new Object[]{
                    data.getID_MonAn(),
                    data.getTenMonAn(),
                    data.getSoluong(),
                    df.format(data.getThanhTien()) + "d",
                    "Xóa"
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillS_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onDelete(int row) {
                if (tableCTHD.isEditing()) {
                    tableCTHD.getCellEditor().stopCellEditing();
                }
                int idMonAn = (int) tableCTHD.getModel().getValueAt(row, 0);
                try {
                    // Xóa khỏi cơ sở dữ liệu
                    serviceC.deleteCTHD(bill.getIdHoaDon(), idMonAn);

                    // Xóa dòng khỏi bảng
                    cthd.remove(row);
                    ((DefaultTableModel) tableCTHD.getModel()).removeRow(row);

                    // Cập nhật tổng tiền
                    updateTotalAmount();

                } catch (SQLException ex) {
                    Logger.getLogger(BillS_Form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        tableCTHD.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRenderDelete());
        tableCTHD.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditorDelete(event));

        // Ẩn cột ID món ăn
        tableCTHD.getColumnModel().getColumn(0).setMinWidth(0);
        tableCTHD.getColumnModel().getColumn(0).setMaxWidth(0);
        tableCTHD.getColumnModel().getColumn(0).setWidth(0);
    }

    private void updateTongTienWithDiem() {
        try {
            // Lấy điểm tích lũy hiện tại từ lbDiemTichLuy
            int diemTichLuyHienTai = Integer.parseInt(lbDiemTichLuy.getText().trim());

            // Lấy số điểm muốn sử dụng từ txtSuDungDiem
            String suDungDiemText = txtSuDungDiem.getText().trim();
            int suDungDiem = suDungDiemText.isEmpty() ? 0 : Integer.parseInt(suDungDiemText);

            // Kiểm tra nếu số điểm muốn sử dụng vượt quá điểm tích lũy hiện tại
            if (suDungDiem > diemTichLuyHienTai) {
                JOptionPane.showMessageDialog(this, "Số điểm muốn sử dụng vượt quá điểm tích lũy hiện có!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                // Đặt lại số điểm sử dụng về điểm tối đa
                txtSuDungDiem.setText(String.valueOf(diemTichLuyHienTai));
                suDungDiem = diemTichLuyHienTai;
            }

            // Tính tổng tiền sau khi trừ điểm sử dụng
            int soTienGiam = suDungDiem;
            int tongTien = bill.getTongtien() - soTienGiam;
            txtTongtien.setText(df.format(tongTien) + "d");

        } catch (NumberFormatException e) {
            txtTongtien.setText(df.format(bill.getTongtien()) + "d");
        }
    }

    private void updateTotalAmount() {
        double totalAmount = 0;
        for (ModelCTHD item : cthd) {
            totalAmount += item.getThanhTien();
        }
        txtTongtien.setText(df.format(totalAmount) + "d");
    }

    public void initCash() {
        txtTongtien.setText(df.format(bill.getTongtien()) + "d");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new NhaHang.View.Swing.PanelRound();
        lbTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cmdOK = new NhaHang.View.Swing.ButtonOutLine();
        cmdCancel = new NhaHang.View.Swing.ButtonOutLine();
        lbNgHD = new javax.swing.JLabel();
        txtidHD = new NhaHang.View.Swing.MyTextField();
        lbidHD = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        cmdExportBill = new NhaHang.View.Swing.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCTHD = new NhaHang.View.Swing.Table();
        lbTongtien = new javax.swing.JLabel();
        txtTongtien = new NhaHang.View.Swing.MyTextField();
        lbTienKH = new javax.swing.JLabel();
        txtTienKH = new NhaHang.View.Swing.MyTextField();
        lbTientra = new javax.swing.JLabel();
        txtTientra = new NhaHang.View.Swing.MyTextField();
        lbidKH = new javax.swing.JLabel();
        txtidKH = new NhaHang.View.Swing.MyTextField();
        lbDiemTichLuy = new javax.swing.JLabel();
        cbTichDiem = new javax.swing.JCheckBox();
        lbTientra2 = new javax.swing.JLabel();
        lbTientra3 = new javax.swing.JLabel();
        txtSuDungDiem = new NhaHang.View.Swing.MyTextField();
        lbDiemTichLuy1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(222, 222, 222));

        bg.setBackground(new java.awt.Color(247, 247, 247));

        lbTitle.setBackground(new java.awt.Color(255, 255, 255));
        lbTitle.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        lbTitle.setText("Thông tin Hóa Đơn");
        lbTitle.setIconTextGap(10);

        jSeparator1.setBackground(new java.awt.Color(76, 76, 76));

        cmdOK.setBackground(new java.awt.Color(17, 153, 142));
        cmdOK.setForeground(new java.awt.Color(108, 91, 123));
        cmdOK.setText("Xác nhận Thanh Toán");
        cmdOK.setFocusable(false);
        cmdOK.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        cmdOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOKActionPerformed(evt);
            }
        });

        cmdCancel.setBackground(new java.awt.Color(237, 33, 58));
        cmdCancel.setForeground(new java.awt.Color(108, 91, 123));
        cmdCancel.setText("Hủy");
        cmdCancel.setFocusable(false);
        cmdCancel.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });

        lbNgHD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbNgHD.setForeground(new java.awt.Color(89, 89, 89));
        lbNgHD.setText("Ngày ");

        txtidHD.setEditable(false);
        txtidHD.setBackground(new java.awt.Color(204, 204, 204));
        txtidHD.setForeground(new java.awt.Color(0, 0, 0));
        txtidHD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtidHD.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        lbidHD.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbidHD.setText("Mã Hóa Đơn");

        jSeparator3.setBackground(new java.awt.Color(76, 76, 76));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        cmdExportBill.setBackground(new java.awt.Color(255, 102, 0));
        cmdExportBill.setForeground(new java.awt.Color(255, 255, 255));
        cmdExportBill.setText("XUẤT HÓA ĐƠN");
        cmdExportBill.setFocusable(false);
        cmdExportBill.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmdExportBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExportBillActionPerformed(evt);
            }
        });

        tableCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mon An", "So luong", "Thanh tien", "Xoa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCTHD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jScrollPane1.setViewportView(tableCTHD);
        if (tableCTHD.getColumnModel().getColumnCount() > 0) {
            tableCTHD.getColumnModel().getColumn(0).setMinWidth(300);
            tableCTHD.getColumnModel().getColumn(0).setMaxWidth(300);
        }

        lbTongtien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTongtien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTongtien.setText("Tổng tiền");

        txtTongtien.setEditable(false);
        txtTongtien.setBackground(new java.awt.Color(204, 204, 204));
        txtTongtien.setForeground(new java.awt.Color(0, 0, 0));
        txtTongtien.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTongtien.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        lbTienKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTienKH.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTienKH.setText("Tiền khách đưa ");

        txtTienKH.setBackground(new java.awt.Color(204, 204, 204));
        txtTienKH.setForeground(new java.awt.Color(0, 0, 0));
        txtTienKH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTienKH.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        txtTienKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTienKHFocusLost(evt);
            }
        });
        txtTienKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKHActionPerformed(evt);
            }
        });
        txtTienKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienKHKeyTyped(evt);
            }
        });

        lbTientra.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTientra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTientra.setText("Tiền trả lại");

        txtTientra.setEditable(false);
        txtTientra.setBackground(new java.awt.Color(204, 204, 204));
        txtTientra.setForeground(new java.awt.Color(0, 0, 0));
        txtTientra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTientra.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        lbidKH.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbidKH.setText("Mã Khách Hàng");

        txtidKH.setEditable(false);
        txtidKH.setBackground(new java.awt.Color(204, 204, 204));
        txtidKH.setForeground(new java.awt.Color(0, 0, 0));
        txtidKH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtidKH.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        lbDiemTichLuy.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbDiemTichLuy.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDiemTichLuy.setText("Tích điểm");

        cbTichDiem.setText("Active");

        lbTientra2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTientra2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTientra2.setText("Tích điểm");

        lbTientra3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTientra3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTientra3.setText("Sử dụng điểm");

        txtSuDungDiem.setBackground(new java.awt.Color(204, 204, 204));
        txtSuDungDiem.setForeground(new java.awt.Color(0, 0, 0));
        txtSuDungDiem.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSuDungDiem.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        lbDiemTichLuy1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbDiemTichLuy1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDiemTichLuy1.setText("Điểm hiện tại :");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jScrollPane1)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(lbidHD)
                                .addGap(30, 30, 30)
                                .addComponent(txtidHD, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbidKH)
                                .addGap(30, 30, 30)
                                .addComponent(txtidKH, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(cmdExportBill, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(lbTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbNgHD))))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addContainerGap(163, Short.MAX_VALUE)
                                .addComponent(lbTongtien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bgLayout.createSequentialGroup()
                                .addContainerGap(156, Short.MAX_VALUE)
                                .addComponent(lbTientra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bgLayout.createSequentialGroup()
                                .addContainerGap(29, Short.MAX_VALUE)
                                .addComponent(lbTienKH, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                            .addComponent(lbTientra2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTientra3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 373, Short.MAX_VALUE)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(cbTichDiem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbDiemTichLuy1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbDiemTichLuy))
                            .addComponent(txtTientra, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txtTienKH, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txtTongtien, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txtSuDungDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149)
                .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTitle)
                    .addComponent(lbNgHD, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtidKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbidKH, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtidHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbidHD, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmdExportBill, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTienKH, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTienKH, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTientra, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTientra, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDiemTichLuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbTichDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDiemTichLuy1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbTientra2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSuDungDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(lbTientra3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOKActionPerformed
        try {
            String suDungDiemText = txtSuDungDiem.getText().trim();
            if (cbTichDiem.isSelected()) {
                int suDungDiem = Integer.parseInt(suDungDiemText);
                int tongTienHienTai = bill.getTongtien();
                int soTienGiam = suDungDiem; // Giả sử mỗi điểm tương đương với 1 đơn vị tiền
                int tongTienSauGiam = tongTienHienTai - soTienGiam;

                int diemMoi = tongTienSauGiam / 100;
                serviceS.insertDiemTichLuy(bill.getIdHoaDon(), bill.getIdKH(), diemMoi);
                int diemTichLuy = Integer.parseInt(lbDiemTichLuy.getText());
                int newDiemTichLuy = diemTichLuy + diemMoi;
                serviceC.updateDiemTichLuy(bill.getIdKH(), newDiemTichLuy);
            }

            if (!suDungDiemText.isEmpty()) {
                int suDungDiem = Integer.parseInt(suDungDiemText);
                int tongTienHienTai = bill.getTongtien();
                int soTienGiam = suDungDiem; // Giả sử mỗi điểm tương đương với 1 đơn vị tiền
                int tongTienSauGiam = tongTienHienTai - soTienGiam;
                //Update lại tổng tiền cho Hóa đơn
                serviceS.updateTongTien(bill.getIdHoaDon(), tongTienSauGiam);
                int diemTichLuyHienTai = Integer.parseInt(lbDiemTichLuy.getText().trim());
                int newDiemTichLuy = diemTichLuyHienTai - suDungDiem;
                serviceC.updateDiemTichLuy(bill.getIdKH(), newDiemTichLuy);
            }
            //Khi NV bấm xác nhận thanh toán, thay đổi trạng thái hóa đơn từ Chưa thanh toán thành đã thanh toán
            serviceS.UpdateHoaDonStatus(bill.getIdHoaDon());
            serviceS.CancelTableReserve(table.getID());
            conf.ConfirmPaymentSuccess(bill.getIdHoaDon());
            main.showForm(new TableMenuS_Form("Tang 1", user, main));

        } catch (SQLException ex) {
            Logger.getLogger(BillS_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmdOKActionPerformed

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        main.showForm(new OrderFood_Form(user, staff, table, main));
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void txtTienKHKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKHKeyTyped
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9')
                || (c == KeyEvent.VK_BACK_SPACE)
                || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtTienKHKeyTyped

    private void txtTienKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKHActionPerformed
        txtTientra.setText(df.format(Integer.parseInt(txtTienKH.getText()) - bill.getTongtien()) + "d");
    }//GEN-LAST:event_txtTienKHActionPerformed

    private void txtTienKHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTienKHFocusLost
        txtTientra.setText(df.format(Integer.parseInt(txtTienKH.getText()) - bill.getTongtien()) + "d");
    }//GEN-LAST:event_txtTienKHFocusLost

    private void cmdExportBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExportBillActionPerformed
        //Tạo Document
        PDDocument invc = new PDDocument();
        //Tạo trang trống
        PDPage newpage = new PDPage();
        //Thêm trang trống
        invc.addPage(newpage);
        String title = "SAI GON RESTAURANT";
        String subtitle = "HOA DON THANH TOAN";
        String footer = "CAM ON QUY KHACH";
        String tenKH = "";
        try {
            tenKH = serviceS.getTenKH(bill.getIdKH());
        } catch (SQLException ex) {
            Logger.getLogger(BillS_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultTableModel model = (DefaultTableModel) tableCTHD.getModel();
        PDFont font = PDType1Font.HELVETICA;
        //Thêm dữ liệu vào file pdf
        PDPage mypage = invc.getPage(0);
        try {
            PDPageContentStream cs = new PDPageContentStream(invc, mypage);
            //Viết tiêu đề Hóa đơn
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA, 22);
            cs.newLineAtOffset(165, 750);
            cs.showText(title);
            cs.endText();

            cs.beginText();
            cs.setFont(font, 18);
            cs.newLineAtOffset(220, 690);
            cs.showText(subtitle);
            cs.endText();
            //Thêm thông tin khách hàng
            cs.beginText();
            cs.setFont(font, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(60, 610);
            cs.showText("Ma Hoa Don: ");
            cs.newLine();
            cs.showText("Ngay: ");
            cs.newLine();
            cs.showText("Khach Hang: ");
            cs.endText();

            cs.beginText();
            cs.setFont(font, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(170, 610);
            cs.showText(bill.getIdHoaDon() + "");
            cs.newLine();
            cs.showText(bill.getNgayHD());
            cs.newLine();
            cs.showText(tenKH);
            cs.endText();

            //Thêm CTHD
            //Cột
            int x = 80;
            for (int col = 0; col < model.getColumnCount(); col++) {
                cs.beginText();
                cs.setFont(font, 14);
                cs.newLineAtOffset(x, 520);
                cs.showText(model.getColumnName(col));
                cs.endText();
                if (col == 0) {
                    x += 200;
                } else {
                    x += 120;
                }
            }
            //Hàng
            x = 80;
            for (int col = 0; col < model.getColumnCount(); col++) {
                cs.beginText();
                cs.setFont(font, 14);
                cs.setLeading(20f);
                cs.newLineAtOffset(x, 500);
                for (int row = 0; row < model.getRowCount(); row++) {
                    cs.showText(model.getValueAt(row, col).toString());
                    cs.newLine();
                }
                cs.endText();
                if (col == 0) {
                    x += 200;
                } else {
                    x += 120;
                }

            }
            //Thêm dữ liệu phần thanh toán
            cs.beginText();
            cs.setFont(font, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(280, 480 - (model.getRowCount() * 20));
            cs.showText("Tong tien: ");
            cs.newLine();
            cs.newLine();

            cs.newLine();

            cs.newLine();

            cs.endText();

            cs.beginText();
            cs.setFont(font, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(400, 480 - (model.getRowCount() * 20));
            cs.showText(txtTongtien.getText());
            cs.endText();
            //Cuối Hóa đơn
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 20);
            cs.newLineAtOffset(150, 350 - (model.getRowCount() * 20));
            cs.showText(footer);
            cs.endText();
            //Đóng file
            cs.close();
            //Lưu file
            invc.save(".\\src\\ExportBill\\HoaDon_ID-" + bill.getIdHoaDon() + ".pdf");
            File file = new File("src\\ExportBill\\HoaDon_ID-" + bill.getIdHoaDon() + ".pdf");
            String path = file.getAbsolutePath();
            obj.ExportFileSuccess(path);

        } catch (IOException ex) {
            Logger.getLogger(BillS_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmdExportBillActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private NhaHang.View.Swing.PanelRound bg;
    private javax.swing.JCheckBox cbTichDiem;
    private NhaHang.View.Swing.ButtonOutLine cmdCancel;
    private NhaHang.View.Swing.Button cmdExportBill;
    private NhaHang.View.Swing.ButtonOutLine cmdOK;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbDiemTichLuy;
    private javax.swing.JLabel lbDiemTichLuy1;
    private javax.swing.JLabel lbNgHD;
    private javax.swing.JLabel lbTienKH;
    private javax.swing.JLabel lbTientra;
    private javax.swing.JLabel lbTientra2;
    private javax.swing.JLabel lbTientra3;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbTongtien;
    private javax.swing.JLabel lbidHD;
    private javax.swing.JLabel lbidKH;
    private NhaHang.View.Swing.Table tableCTHD;
    private NhaHang.View.Swing.MyTextField txtSuDungDiem;
    private NhaHang.View.Swing.MyTextField txtTienKH;
    private NhaHang.View.Swing.MyTextField txtTientra;
    private NhaHang.View.Swing.MyTextField txtTongtien;
    private NhaHang.View.Swing.MyTextField txtidHD;
    private NhaHang.View.Swing.MyTextField txtidKH;
    // End of variables declaration//GEN-END:variables
}
