package RTDRestaurant.Model;

public class ModelHoaDon {

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public int getIdKH() {
        return idKH;
    }

    public int getIdBan() {
        return idBan;
    }

    public String getNgayHD() {
        return ngayHD;
    }

    public int getTongtien() {
        return tongtien;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public ModelHoaDon() {
    }

    public ModelHoaDon(int idHoaDon, int idKH, int idBan, String ngayHD, int tongtien){
        this.idHoaDon = idHoaDon;
        this.idKH = idKH;
        this.idBan = idBan;
        this.ngayHD = ngayHD;
        
        this.tongtien = tongtien;
    }
    
    public ModelHoaDon(int idHoaDon, int idKH, int idBan, String ngayHD, int tongtien, String trangthai) {
        this.idHoaDon = idHoaDon;
        this.idKH = idKH;
        this.idBan = idBan;
        this.ngayHD = ngayHD;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
    }
    
    
    
    private int idHoaDon;
    private int idKH;
    private int idBan;
    private String ngayHD;
    private int tongtien;
    private String trangthai;
}
