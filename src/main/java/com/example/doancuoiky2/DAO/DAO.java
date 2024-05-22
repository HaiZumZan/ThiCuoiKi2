package com.example.doancuoiky2.DAO;
import java.sql.*;
public class DAO {
    public static Connection connection;
    public  DAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/khachhang?user=root&password=123456789");
            System.out.println("Kết nối đến cơ sở dữ liệu thành công!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addGuest(){
        try {
            Statement st = connection.createStatement();
            String sql = "insert into thongtinkh (makh, username,matkhau,email,securecode,sdt) values (\n" +
                    "'KKK42','heisenberg','white','fuck123@gmail.com','456222','09178945'\n" +
                    ")";
            int check = st.executeUpdate(sql);

            if (check>0){
                System.out.println("Thêm khách hàng thành công");
            }else{
                System.out.println("Thêm khách hàng thất bại");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
   /* public void closeConnection(){
       try {
           if (connection!=null){
             connection.close();
               System.out.println("Đã đóng kết nối với cơ sở dữ liệu!");
           }
       }catch (Exception e){
          e.printStackTrace();
       }
   }*/

    public static void main(String[] args) {
        DAO dao = new DAO();
        dao.addGuest();
//      dao.closeConnection();

    }
}
