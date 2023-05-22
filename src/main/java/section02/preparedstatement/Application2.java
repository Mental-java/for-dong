package section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class Application2 {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String empId = "200";

        try {
            pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID= ?");
            pstmt.setString(1, empId);
            rset = pstmt.executeQuery();
            if(rset.next()) {
                System.out.println(rset.getString("EMP_ID") + " : " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }


    }
}
