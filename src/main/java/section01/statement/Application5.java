package section01.statement;

import model.dto.EmployeeDto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

public class Application5 {

    public static void main(String[] args) {

        /* 1. Connection 생성 */
        Connection con = getConnection();

        /* 2. Statement, ResultSet 생성 */
        Statement stmt = null;
        ResultSet rs = null;

        /* 3. 한 행의 정보를 담을 DTO 객체 생성 */
        EmployeeDto row =null;

        /* 4. 여러 DTO를 하나의 인스턴스로 묶기 위한 List 생성 */
        List<EmployeeDto> empList = new ArrayList<>();
        /* 5. 전체 직원 정보 조회 쿼리를 담은 String 변수 생성 */
        String query = "SELECT * FROM EMPLOYEE";

        /* 6. statment 생성 및 쿼리 실행 */
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);


            while (rs.next()) {
                /* 7. ResultSet에 존재하는 모든 결과값을 객체에 담아 배열에 추가 */
                EmployeeDto employee = new EmployeeDto();
                employee.setEmpId(rs.getString("EMP_ID"));
                employee.setEmpName(rs.getString("EMP_NAME"));
                employee.setEmpNo(rs.getString("EMP_NO"));
                employee.setEmail(rs.getString("EMAIL"));
                employee.setPhone(rs.getString("PHONE"));
                employee.setDeptCode(rs.getString("DEPT_CODE"));
                employee.setJobCode(rs.getString("JOB_CODE"));
                employee.setSalLevel(rs.getString("SAL_LEVEL"));
                employee.setSalary(rs.getInt("SALARY"));
                employee.setBonus(rs.getDouble("BONUS"));
                employee.setManagerId(rs.getString("MANAGER_ID"));
                employee.setHireDate(rs.getDate("HIRE_DATE"));
                employee.setEndDate(rs.getDate("ENT_DATE"));
                employee.setEntYn(rs.getString("ENT_YN"));

                empList.add(employee);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            /* 8. 자원 반납 */
            close(con);
            close(rs);
            close(stmt);
        }

        /* 9. 전체 직원 정보 오버라이딩된 toString으로 조회 */
        for (EmployeeDto emp : empList) {
            System.out.println(emp);
        }

    }

}