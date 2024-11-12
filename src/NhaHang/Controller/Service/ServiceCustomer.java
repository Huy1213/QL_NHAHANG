package NhaHang.Controller.Service;

import NhaHang.Controller.KetNoi.DatabaseConnection;
import NhaHang.Model.ModelCTHD;
import NhaHang.Model.ModelMonAn;
import NhaHang.Model.ModelKhachHang;
import NhaHang.Model.ModelHoaDon;
import NhaHang.Model.ModelVoucher;
import NhaHang.Model.ModelBan;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;

public class ServiceCustomer {

    private final Connection con;

    //Connect tới DataBase       
    public ServiceCustomer() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    //Lấy toàn bộ danh sách Món ăn theo loại Món Ăn đang kinh doanh
    public ArrayList<ModelMonAn> MenuFood(String type) throws SQLException {
        ArrayList<ModelMonAn> list = new ArrayList<>();
        String sql = "SELECT ID_MonAn,TenMon,DonGia FROM MonAn WHERE Loai=? AND TrangThai='Dang kinh doanh'";
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, type);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int id = r.getInt("ID_MonAn");
            String name = r.getString("TenMon");
            int value = r.getInt("DonGia");
            ModelMonAn data;
            if (id < 90) {
                data = new ModelMonAn(new ImageIcon(getClass().getResource("/MonAn/" + type + "/" + id + ".jpg")), id, name, value, type);
            } else {
                data = new ModelMonAn(new ImageIcon(getClass().getResource("/MonAn/Unknown/unknown.jpg")), id, name, value, type);
            }
            list.add(data);
        }
        r.close();
        p.close();
        return list;
    }

    //Lấy danh sách Món ăn theo loại món ăn và thứ tự Tên/Giá tăng dần/Giá giảm dần đang kinh doanh
    public ArrayList<ModelMonAn> MenuFoodOrder(String type, String orderBy) throws SQLException {
        ArrayList<ModelMonAn> list = new ArrayList<>();

        String sql = "SELECT ID_MonAn,TenMon,DonGia FROM MonAn WHERE Loai=? AND TrangThai='Dang kinh doanh'";
        switch (orderBy) {
            case "Tên A->Z" -> {
                sql = "SELECT ID_MonAn,TenMon,DonGia FROM MonAn WHERE Loai=? AND TrangThai='Dang kinh doanh' ORDER BY TenMon";
            }
            case "Giá tăng dần" -> {
                sql = "SELECT ID_MonAn,TenMon,DonGia FROM MonAn WHERE Loai=? AND TrangThai='Dang kinh doanh' ORDER BY DonGia";
            }
            case "Giá giảm dần" -> {
                sql = "SELECT ID_MonAn,TenMon,DonGia FROM MonAn WHERE Loai=? AND TrangThai='Dang kinh doanh' ORDER BY DonGia DESC";
            }
        }
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, type);

        ResultSet r = p.executeQuery();
        while (r.next()) {
            int id = r.getInt("ID_MonAn");
            String name = r.getString("TenMon");
            int value = r.getInt("DonGia");
            ModelMonAn data;
            if (id < 90) {
                data = new ModelMonAn(new ImageIcon(getClass().getResource("/MonAn/" + type + "/" + id + ".jpg")), id, name, value, type);
            } else {
                data = new ModelMonAn(new ImageIcon(getClass().getResource("/MonAn/Unknown/unknown.jpg")), id, name, value, type);
            }
            list.add(data);
        }
        r.close();
        p.close();
        return list;
    }

    //Lấy toàn bộ danh sách bàn theo tầng
    public ArrayList<ModelBan> MenuTable(String floor) throws SQLException {
        ArrayList<ModelBan> list = new ArrayList<>();
        String sql = "SELECT ID_Ban,TenBan,Trangthai FROM Ban WHERE Vitri=?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, floor);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int id = r.getInt("ID_Ban");
            String name = r.getString("TenBan");
            String status = r.getString("Trangthai");
            ModelBan data = new ModelBan(id, name, status);
            list.add(data);
        }
        r.close();
        p.close();
        return list;
    }
    //Lấy danh sách bàn theo trạng thái bàn Tất cả/Còn trống/Đã đặt trước/Đang dùng bữa

    public ArrayList<ModelBan> MenuTableState(String floor, String state) throws SQLException {
        ArrayList<ModelBan> list = new ArrayList<>();
        String sql = "SELECT ID_Ban,TenBan,Trangthai FROM Ban WHERE Vitri=?";
        switch (state) {
            case "Tất cả" ->
                sql = "SELECT ID_Ban,TenBan,Trangthai FROM Ban WHERE Vitri=?";
            case "Còn trống" ->
                sql = "SELECT ID_Ban,TenBan,Trangthai FROM Ban WHERE Vitri=? AND Trangthai='Con trong'";
            case "Đã đặt trước" ->
                sql = "SELECT ID_Ban,TenBan,Trangthai FROM Ban WHERE Vitri=? AND Trangthai='Da dat truoc'";
            case "Đang dùng bữa" ->
                sql = "SELECT ID_Ban,TenBan,Trangthai FROM Ban WHERE Vitri=? AND Trangthai='Dang dung bua'";
        }
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, floor);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int id = r.getInt("ID_Ban");
            String name = r.getString("TenBan");
            String status = r.getString("Trangthai");
            ModelBan data = new ModelBan(id, name, status);
            list.add(data);
        }
        r.close();
        p.close();
        return list;
    }

    //Lấy thông tin khách hàng từ ID người dùng
    public ModelKhachHang getCustomer(int userID) throws SQLException {
        ModelKhachHang data = null;
        String sql = "SELECT ID_KH, TenKH,Ngaythamgia AS NgayTG, Doanhso,Diemtichluy,SDT FROM KhachHang";
        PreparedStatement p = con.prepareStatement(sql);

        ResultSet r = p.executeQuery();
        while (r.next()) {

            int id = r.getInt("ID_KH");
            String name = r.getString("TenKH");
            String date = r.getString("NgayTG");
            int sales = r.getInt("Doanhso");
            int points = r.getInt("Diemtichluy");
            String sdt = r.getString("SDT");
            data = new ModelKhachHang(id, name, date, sales, points, sdt);
        }
        r.close();
        p.close();
        return data;
    }

    public void insertKhachHang(String tenKH, String sdt) throws SQLException {
        String sql = "INSERT INTO KhachHang (TenKH, Ngaythamgia, Doanhso, Diemtichluy, SDT) VALUES (?, GETDATE(), 0, 0, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, tenKH); // Tên khách hàng
            stmt.setString(2, sdt);   // Số điện thoại

            stmt.executeUpdate(); // Thực thi lệnh SQL để chèn dữ liệu vào bảng
        }
    }

    // Đổi tên Khách hàng 
    public void reNameCustomer(ModelKhachHang data) throws SQLException {
        String sql = "UPDATE KhachHang SET TenKH=? WHERE ID_KH=?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, data.getName());
        p.setInt(2, data.getID_KH());
        p.execute();
        p.close();
    }

    /*
        Khi khách hàng đặt bàn thì tự động thêm mới một hóa đơn với ID_Ban và ID_KH từ tham số 
        Tiền món ăn và Tiền giảm mặc định là 0
        Trạng thái Hóa đơn mặc định là Chưa thanh toán
     */
    public void InsertHoaDon(ModelBan table, int id) throws SQLException {
        //Tìm ID_HD tiếp theo
        int idHD = 0;
        PreparedStatement p_ID = con.prepareStatement("SELECT MAX(ID_HoaDon) +1 FROM HoaDon");
        ResultSet r_id = p_ID.executeQuery();
        if (r_id.next()) {
            idHD = r_id.getInt(1);
        }

        //Thêm Hoá Đơn mới
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String sql = "INSERT INTO HoaDon(ID_HoaDon,ID_KH,ID_Ban,NgayHD,Trangthai)"
                + " VALUES (?,?,?,CONVERT(DATE, ?, 105),'Chua thanh toan')";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, idHD);
        p.setInt(2, id);
        p.setInt(3, table.getID());
        p.setString(4, simpleDateFormat.format(new Date()));
        p_ID.close();
        r_id.close();
        p.execute();
        p.close();

    }

    public int getIDKhachHang(String hoten, String sdt) throws SQLException {
        int idKhachHang = 0; // Khởi tạo ID khách hàng
        String sql = "SELECT ID_KH FROM KhachHang WHERE TenKH = ? AND SDT = ?";

        // Tạo PreparedStatement để tránh SQL Injection
        try (PreparedStatement p = con.prepareStatement(sql)) {
            p.setString(1, hoten);
            p.setString(2, sdt);

            // Thực hiện truy vấn
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    idKhachHang = r.getInt("ID_KH"); // Lấy ID_KH từ kết quả
                }
            }
        }

        return idKhachHang; // Trả về ID khách hàng
    }

    public Integer getIDKhachHangBySDT(String sdt) throws SQLException {
        Integer idKhachHang = null;
        String sql = "SELECT ID_KH FROM KhachHang WHERE SDT = ?";  // Câu lệnh SQL kiểm tra số điện thoại

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, sdt);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                idKhachHang = rs.getInt("ID_KH");  // Lấy ID nếu có kết quả
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm ID khách hàng: " + e.getMessage());
            throw e;  // Ném ngoại lệ để xử lý ở nơi gọi phương thức
        }

        return idKhachHang;  // Trả về null nếu không tìm thấy khách hàng
    }

    //Lấy thông tin HoaDon mà Khách hàng vừa đặt, Hóa Đơn có trạng thái 'Chưa thanh toán'
    public ModelHoaDon FindHoaDon(ModelKhachHang customer) throws SQLException {
        ModelHoaDon hoadon = null;
        String sql = "SELECT ID_HoaDon,ID_KH,ID_Ban,CONVERT(VARCHAR(10), NgayHD, 105) AS Ngay,Tongtien,Trangthai FROM HoaDon "
                + "WHERE ID_KH=? AND Trangthai='Chua thanh toan'";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, customer.getID_KH());
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int idHoaDon = r.getInt(1);
            int idKH = r.getInt(2);
            int idBan = r.getInt(3);
            String ngayHD = r.getString(4);
            int tongtien = r.getInt(5);
            String trangthai = r.getString(6);
            hoadon = new ModelHoaDon(idHoaDon, idKH, idBan, ngayHD, tongtien, trangthai);
        }
        r.close();
        p.close();
        return hoadon;
    }

    //Thêm món ăn mới khách hàng vừa đặt vào CTHD
    public void InsertCTHD(int ID_HoaDon, int ID_MonAn, int soluong) throws SQLException {
        //Kiểm tra món ăn đã có trong CTHD hay chưa, nếu đã có cập nhật số lượng, nếu chưa thì thêm CTHD mới
        String sql = "SELECT 1 FROM CTHD WHERE ID_HoaDon=? AND ID_MonAn=?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, ID_HoaDon);
        p.setInt(2, ID_MonAn);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            // Nếu tồn tại 
            String sql_update = "UPDATE CTHD SET SoLuong=SoLuong+? WHERE ID_HoaDon=? AND ID_MonAn=?";
            PreparedStatement p1 = con.prepareStatement(sql_update);
            p1.setInt(1, soluong);
            p1.setInt(2, ID_HoaDon);
            p1.setInt(3, ID_MonAn);
            p1.execute();
            p1.close();
        } else {
            //Nếu không tồn tại
            String sql_insert = "INSERT INTO CTHD(ID_HoaDon,ID_MonAn,SoLuong) VALUES (?,?,?)";
            PreparedStatement p1 = con.prepareStatement(sql_insert);
            p1.setInt(1, ID_HoaDon);
            p1.setInt(2, ID_MonAn);
            p1.setInt(3, soluong);
            p1.execute();
            p1.close();
        }
        p.close();
        r.close();
    }

    // Lấy danh sách CTHD từ ID_HoaDon
    public ArrayList<ModelCTHD> getCTHD(int ID_HoaDon) throws SQLException {
        ArrayList<ModelCTHD> list = new ArrayList<>();
        String sql = "SELECT ID_HoaDon,CTHD.ID_MonAn, TenMon,SoLuong,Thanhtien FROM CTHD "
                + "JOIN MonAn ON MonAn.ID_MonAn=CTHD.ID_MonAn WHERE ID_HoaDon=?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, ID_HoaDon);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int ID_HD = r.getInt(1);
            int ID_MonAn = r.getInt(2);
            String tenMonAn = r.getString(3);
            int soluong = r.getInt(4);
            int thanhTien = r.getInt(5);
            ModelCTHD data = new ModelCTHD(ID_HD, ID_MonAn, tenMonAn, soluong, thanhTien);
            list.add(data);
        }
        r.close();
        p.close();
        return list;
    }

    //Lấy toàn bộ danh sách hóa đơn của một khách hàng
    public ArrayList<ModelHoaDon> getListHD(int ID_KH) throws SQLException {
        ArrayList<ModelHoaDon> list = new ArrayList<>();
        String sql = "SELECT ID_HoaDon,ID_KH,ID_Ban,NgayHD AS Ngay,Tongtien,Trangthai FROM HoaDon "
                + "WHERE ID_KH=? ORDER BY ID_HoaDon";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, ID_KH);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int idHoaDon = r.getInt(1);
            int idKH = r.getInt(2);
            int idBan = r.getInt(3);
            String ngayHD = r.getString(4);

            int tongtien = r.getInt(5);
            String trangthai = r.getString(6);
            ModelHoaDon hoadon = new ModelHoaDon(idHoaDon, idKH, idBan, ngayHD, tongtien, trangthai);
            list.add(hoadon);
        }
        r.close();
        p.close();
        return list;
    }

    //Lấy toàn bộ danh sách hóa đơn của một khách hàng theo mốc Tổng tiền Hóa Đơn
    public ArrayList<ModelHoaDon> getListHDOrder(int ID_KH, String order) throws SQLException {
        ArrayList<ModelHoaDon> list = new ArrayList<>();
        String sql = "SELECT ID_HoaDon,ID_KH,ID_Ban,CONVERT(VARCHAR(10), NgayHD, 105) AS Ngay,Tongtien,Trangthai FROM HoaDon "
                + "WHERE ID_KH=? ORDER BY ID_HoaDon";
        switch (order) {
            case "Tất cả":
                sql = "SELECT ID_HoaDon,ID_KH,ID_Ban,CONVERT(VARCHAR(10), NgayHD, 105) AS Ngay,Tongtien,Trangthai FROM HoaDon "
                        + "WHERE ID_KH=? ORDER BY ID_HoaDon";
                break;
            case "Dưới 1.000.000đ":
                sql = "SELECT ID_HoaDon,ID_KH,ID_Ban,CONVERT(VARCHAR(10), NgayHD, 105) AS Ngay,Tongtien,Trangthai FROM HoaDon "
                        + "WHERE ID_KH=? AND Tongtien <1000000 ORDER BY ID_HoaDon";
                break;
            case "Từ 1 đến 5.000.000đ":
                sql = "SELECT ID_HoaDon,ID_KH,ID_Ban,CONVERT(VARCHAR(10), NgayHD, 105) AS Ngay,Tongtien,Trangthai FROM HoaDon "
                        + "WHERE ID_KH=? AND Tongtien BETWEEN 1000000 AND 5000001 ORDER BY ID_HoaDon";
                break;
            case "Trên 5.000.000đ":
                sql = "SELECT ID_HoaDon,ID_KH,ID_Ban,CONVERT(VARCHAR(10), NgayHD, 105) AS Ngay,Tongtien,Trangthai FROM HoaDon "
                        + "WHERE ID_KH=? AND Tongtien >5000000 ORDER BY ID_HoaDon";
                break;
            default:
                break;
        }
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, ID_KH);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int idHoaDon = r.getInt(1);
            int idKH = r.getInt(2);
            int idBan = r.getInt(3);
            String ngayHD = r.getString(4);
            int tongtien = r.getInt(5);
            String trangthai = r.getString(6);
            ModelHoaDon hoadon = new ModelHoaDon(idHoaDon, idKH, idBan, ngayHD, tongtien, trangthai);
            list.add(hoadon);
        }
        r.close();
        p.close();
        return list;
    }

    //Sau khi khách hàng đổi Voucher ở phần Điểm tích lũy, áp dụng trực tiếp lên hóa đơn mà khách hàng đang sử dụng
    public void deleteCTHD(int idHoaDon, int idMonAn) throws SQLException {
        String sql = "DELETE FROM CTHD WHERE ID_HoaDon = ? AND ID_MonAn = ?";
        try (PreparedStatement p = con.prepareStatement(sql)) {
            p.setInt(1, idHoaDon);
            p.setInt(2, idMonAn);
            int rowsAffected = p.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("Không tìm thấy chi tiết hóa đơn với ID_HoaDon = " + idHoaDon + " và ID_MonAn = " + idMonAn);
            }
        } catch (SQLException e) { // In ra lỗi nếu có
            // In ra lỗi nếu có
            throw e; // Ném lại ngoại lệ để xử lý bên ngoài nếu cần
        }
    }

    public void updateQuantityCTHD(int ID_HoaDon, int ID_MonAn, int newQuantity) throws SQLException {
        String sql = "UPDATE CTHD SET SoLuong = ? WHERE ID_HoaDon = ? AND ID_MonAn = ?";
        try (PreparedStatement p = con.prepareStatement(sql)) {
            p.setInt(1, newQuantity);
            p.setInt(2, ID_HoaDon);
            p.setInt(3, ID_MonAn);
            p.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật số lượng món ăn: " + e.getMessage());
            throw e;
        }
    }
    // Hàm lấy điểm tích lũy của khách hàng dựa trên ID khách hàng

    public int getDiemTichLuy(int idKhachHang) throws SQLException {
        int diemTichLuy = 0; // Khởi tạo giá trị mặc định

        String sql = "SELECT Diemtichluy FROM KhachHang WHERE ID_KH = ?";
        try (PreparedStatement p = con.prepareStatement(sql)) {
            p.setInt(1, idKhachHang); // Truyền tham số ID khách hàng vào câu lệnh SQL

            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    diemTichLuy = r.getInt("Diemtichluy"); // Lấy giá trị cột Diemtichluy từ kết quả truy vấn
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy điểm tích lũy của khách hàng: " + e.getMessage());
            throw e;
        }

        return diemTichLuy; // Trả về điểm tích lũy
    }

    public void updateDiemTichLuy(int idKH, int newDiemTichLuy) throws SQLException {
        String sql = "UPDATE KhachHang SET Diemtichluy = ? WHERE ID_KH = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, newDiemTichLuy); // Điểm tích lũy mới
            stmt.setInt(2, idKH);           // Mã khách hàng
            stmt.executeUpdate();           // Thực thi lệnh cập nhật
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật điểm tích lũy: " + e.getMessage());
            throw e;
        }
    }

}
