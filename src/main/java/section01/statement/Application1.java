package section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;

public class Application1 {

    public static void main(String[] args) {

        Connection con = getConnection();

        /* 쿼리문을 저장하고 실행하는 기능을 하는 인터페이스 */
        Statement stmt = null;
        /* select */
        ResultSet rset = null;

        try {
            /* connection을 이용하여 statement 생성 */
            stmt = con.createStatement();

            rset = stmt.executeQuery("Select EMP_ID, EMP_NAME from EMPLOYEE");
            /* next() : ResultSet의 커서를 하나 내리면서 */
            while(rset.next()){
                System.out.println(rset.getString("EMP_ID")+":"+rset.getString("EMP_NAME"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con);
            close(stmt);
            close(rset);

        }


    }




}
