package kr.or.dgit.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.or.dgit.jdbc.dao.StudentDao;
import kr.or.dgit.jdbc.dto.Student;
import kr.or.dgit.jdbc.util.ConnectionFactory;
import kr.or.dgit.jdbc.util.JdbcUtil;

public class StudentService implements StudentDao{
	private static final StudentService instance = new StudentService();
	
	
	
	public StudentService() {}

	
	
	public static StudentService getInstance() {
		return instance;
	}



	@Override
	public Student findStudentById(int studId) {
		return null;
	}

	@Override
	public int insertStudent(Student student) {
		Connection connection = ConnectionFactory.getInstance();
		PreparedStatement pstmt =null;
		String sql = "insert into student values(?,?,?,?)";
		int res = -1;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, student.getStudId());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getEmail());
			pstmt.setTimestamp(4, new Timestamp(student.getDob().getTime()));
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
		}
		return res;
	}

	@Override
	public void deleteStudent(int studId) {
		
	}

	@Override
	public List<Student> findAllStudent() {
		Connection connection = ConnectionFactory.getInstance();
		List<Student> lists = new ArrayList<>();
		String sql ="select stud id, name, email, dob from student";
		
		try(PreparedStatement pstmt = connection.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			while(rs.next()){
				lists.add(getStudent(rs));
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}
	
	private Student getStudent(ResultSet rs) throws SQLException {
		int studId = rs.getInt("stud_id");
		String name = rs.getString("name");
		String email = rs.getString("email");
		Date dob = rs.getDate("dob");
		return new Student(studId, name, email, dob);
	}

}
