package section02.preparedstatement;

import com.google.protobuf.EmptyProto;
import model.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class Application5 {

    public static void main(String[] args) {

        /* 1. Connection 생성 */
        Connection con = getConnection();

        /* 2. PreparedStatement, ResultSet 생성 */
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        /* 3. Scanner를 활용하여 조회할 성 입력받기 */
        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 성 입력하기 : ");
        String firstName = sc.nextLine();

        /* 4. 조회를 위한 쿼리 작성 (placeholder 사용) */
        String query = "SELECT * FROM EMPLOYEE WHERE EMP_NAME LIKE ?";
        List<EmployeeDto> empList = new ArrayList<>();
        EmployeeDto row = null;

        /* 6. prepareStatement 생성 및 쿼리 준비 */
        try {
            pstmt = con.prepareStatement(query);
            /* 7. 조건에 해당하는 성씨 세팅 */
            pstmt.setString(1, firstName + "%");
            rset = pstmt.executeQuery();

            while(rset.next()) {
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
                /* 8. ResultSet에 존재하는 모든 결과값을 객체에 담아 배열에 추가 */
                empList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            /* 9. 자원 반납 */
            close(con);
            close(rset);
            close(pstmt);
        }
        /* 10. 조회한 직원 정보 오버라이딩된 toString으로 출력  */
        for (EmployeeDto emp : empList) {
            System.out.println(emp);
        }
    }
}
