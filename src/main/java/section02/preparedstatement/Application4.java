package section02.preparedstatement;

import model.dto.EmployeeDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class Application4 {

    public static void main(String[] args) {

        /* 1. Connection 생성 */
        Connection con = getConnection();

        /* 2. PreparedStatement, ResultSet 생성 */
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        /* 3. Scanner를 활용하여 조회할 사번 입력받기 */
        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 사번 입력 : ");
        String num = sc.nextLine();

        /* 4. 조회를 위한 쿼리 작성 (placeholder 사용) */
        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";

        /* 5. prepareStatement 생성 및 쿼리 준비 */
        try {
            /* 6. 조건에 해당하는 사번 세팅 */
            pstmt = con.prepareStatement(query);
            /* 7. 한 행의 정보를 담을 DTO 객체 생성하여 ResultSet에 존재하는 결과값을 객체에 세팅 */
            pstmt.setString(1, num);
            EmployeeDto row = null;
            rset = pstmt.executeQuery();

            if(rset.next()) {
                row = new EmployeeDto();
                row.setEmpId(rset.getString("EMP_ID"));
                row.setEmpName(rset.getString("EMP_NAME"));
                row.setEmpNo(rset.getString("EMP_NO"));
                row.setEmail(rset.getString("EMAIL"));
                row.setPhone(rset.getString("PHONE"));
                row.setDeptCode(rset.getString("DEPT_CODE"));
                row.setJobCode(rset.getString("JOB_CODE"));
                row.setSalLevel(rset.getString("SAL_LEVEL"));
                row.setSalary(rset.getInt("SALARY"));
                row.setBonus(rset.getDouble("BONUS"));
                row.setManagerId(rset.getString("MANAGER_ID"));
                row.setHireDate(rset.getDate("HIRE_DATE"));
                row.setEndDate(rset.getDate("ENT_DATE"));
                row.setEntYn(rset.getString("ENT_YN"));
            }
            /* 10. 조회한 직원 정보 오버라이딩된 toString으로 출력  */
            System.out.println("직언 정보 : " + row);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            /* 8. 자원 반납 */
            close(con);
            close(rset);
            close(pstmt);
        }
    }
}
