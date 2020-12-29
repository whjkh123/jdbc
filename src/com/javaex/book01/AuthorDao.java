package com.javaex.book01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// INSERT
	public int authorInsert(AuthorVo aVo) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null;

		int count = 0;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL문 준비 / 바인딩 / 실행
			// INSERT INTO author VALUES(SEQ_AUTHOR_ID.nextval, '조경환', '서울시 강서구');
			String query = "INSERT INTO author VALUES(SEQ_AUTHOR_ID.nextval, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, aVo.getAuthorName());
			pstmt.setString(2, aVo.getAuthorDesc());

			count = pstmt.executeUpdate(); // insert

			// rs = pstmt.executeQuery(); // select

			// 4.결과처리
			System.out.println("[DAO]: " + count + "건이 저장되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				/*
				 * if (rs != null) { rs.close(); }
				 */
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

		return count;

	}

	// DELETE
	public int authorDelete(int authorId) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null;

		int count = 0;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL문 준비 / 바인딩 / 실행
			// DELETE FROM author WHERE author_id = 7;
			String query = "DELETE FROM author WHERE author_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);

			count = pstmt.executeUpdate(); // delete

			// rs = pstmt.executeQuery(); // select

			// 4.결과처리
			System.out.println("[DAO]: " + count + "건이 삭제되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				/*
				 * if (rs != null) { rs.close(); }
				 */
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

		return count;

	}

	// UPDATE
	public int authorUpdate(AuthorVo aVo) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null;

		int count = 0;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL문 준비 / 바인딩 / 실행

			// UPDATE author SET author_desc = '[나 혼자 산다] 출연 웹툰작가' WHERE author_id = 4;
			String query = "";
			query += " update author ";
			query += " set author_desc = ? ";
			query += " WHERE author_id = ? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, aVo.getAuthorDesc());
			pstmt.setInt(2, aVo.getAuthorId());

			count = pstmt.executeUpdate(); // update

			// rs = pstmt.executeQuery(); // select

			// 4.결과처리
			System.out.println("[DAO]: " + count + "건이 수정되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				/*
				 * if (rs != null) { rs.close(); }
				 */
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

		return count;

	}

	// LIST(SELECT)
	public List<AuthorVo> getAuthorList() {

		List<AuthorVo> aList = new ArrayList<AuthorVo>();

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL문 준비 / 바인딩 / 실행
			/*
			 * SELECT author_id, author_name, author_desc FROM author;
			 */
			String query = "";
			query += " select author_id, author_name, author_desc ";
			query += " from author ";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();// select

			// 4.결과처리
			while (rs.next()) {

				int authorId = rs.getInt("author_id");// int aId = rs.getInt(1);
				String authorName = rs.getString("author_name");// String aName = rs.getInt(2);
				String authorDesc = rs.getString("author_desc");// String aDesc = rs.getInt(3);

				AuthorVo avo = new AuthorVo(authorId, authorName, authorDesc);

				aList.add(avo);

			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

		return aList;

	}

}
