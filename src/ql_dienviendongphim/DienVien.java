/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ql_dienviendongphim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ngô Đình Tiên - 201A290001
 */
public class DienVien {

    public String DuongDan = "jdbc:mysql://localhost:3306/qldienvien?characterEncoding=latin1&useConfigs=maxPerformance";
    public String TaiKhoan = "root";
    public String MatKhau = "123456";
    Connection KetNoi;
    Statement BoDem;
    ResultSet BanGhi;

    public DienVien() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            KetNoi = DriverManager.getConnection(DuongDan, TaiKhoan, MatKhau);
        } catch (Exception e) {
        }
    }

    public void LoadDanhSachDienVien(JTable pDanhSach) {

        Vector Data = new Vector();
        Vector DanhSachCot = new Vector();
        try {

            BoDem = KetNoi.createStatement();
            BanGhi = BoDem.executeQuery("select * from qldienvien");
            ResultSetMetaData metaData = BanGhi.getMetaData();
            int SoLuongCot = metaData.getColumnCount();
            for (int i = 1; i <= SoLuongCot; i++) {
                DanhSachCot.addElement(metaData.getColumnName(i));
            }
            while (BanGhi.next()) {
                Vector row = new Vector(SoLuongCot);
                for (int i = 1; i <= SoLuongCot; i++) {
                    row.addElement(BanGhi.getObject(i));
                }
                Data.addElement(row);
            }
            while (BanGhi.next()) {
                Vector row = new Vector(SoLuongCot);
                for (int i = 1; i <= SoLuongCot; i++) {
                    row.addElement(BanGhi.getObject(i));
                }
                Data.addElement(row);
            }
            BanGhi.close();
            pDanhSach.setModel(new DefaultTableModel(Data, DanhSachCot));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void Insert(int MSDV, String Name, String SDT, String Email,
            String DiaChi, String DanToc, String TonGiao, String HocVan, String Khac, String DanhSachPhim) {

        try {
            String Query = "Insert into qldienvien (MSDV, HoVaTen,SDT,Email,DiaChi,DanToc,TonGiao,HocVan,DanhSachPhim) "
                    + " values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = KetNoi.prepareStatement(Query);
            preparedStmt.setInt(1, MSDV);
            preparedStmt.setString(2, Name);
            preparedStmt.setString(3, SDT);
            preparedStmt.setString(4, Email);
            preparedStmt.setString(5, DiaChi);
            preparedStmt.setString(6, DanToc);
            preparedStmt.setString(7, TonGiao);
            if (HocVan.equals("Khac")) {
                preparedStmt.setString(8, Khac);
            } else {
                preparedStmt.setString(8, HocVan);
            }
            preparedStmt.setString(9, DanhSachPhim);
            preparedStmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Delete(int MSDV) {
        try {
            System.out.println(MSDV);
            String Query = " delete from qldienvien where MSDV=?";
            PreparedStatement preparedStmt = KetNoi.prepareStatement(Query);
            preparedStmt.setInt(1, MSDV);
            preparedStmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CapNhat(String MSDV, String Name, String SDT, String Email,
            String Diachi, String DanToc, String TonGiao, String HocVan, String DanhSachPhim) {
        try {
            String Query = " update qldienvien set HoVaTen=?,SDT=?,Email=?,DiaChi=?,DanToc=?,TonGiao=?,HocVan=?,DanhSachPhim=? where MSDV=?";

            PreparedStatement preparedStmt = KetNoi.prepareStatement(Query);

            preparedStmt.setString(1, Name);
            preparedStmt.setString(2, SDT);
            preparedStmt.setString(3, Email);
            preparedStmt.setString(4, Diachi);
            preparedStmt.setString(5, DanToc);
            preparedStmt.setString(6, TonGiao);
            preparedStmt.setString(7, HocVan);
            preparedStmt.setString(8, DanhSachPhim);
            preparedStmt.setString(9, MSDV);
            preparedStmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
