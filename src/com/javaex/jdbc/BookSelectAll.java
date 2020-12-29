package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelectAll {

	public static void main(String[] args) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			/*
			 * SELECT b.book_id,
			 *        b.title 제목,
			 *        b.pubs 출판사,
			 *        TO_CHAR(b.pub_date, 'yyyy-mm-dd') 출판일,
			 *        a.author_id,
			 *        a.author_name 지은이,
			 *        a.author_desc 기타
			 * FROM book b, author a
			 * WHERE b.author_id = a.author_id
			 */
			String query = "";
			query += " select b.book_id, ";
			query += " b.title, ";
			query += " b.pubs, ";
			query += " TO_CHAR(b.pub_date, 'yyyy-mm-dd') pub_date, ";// TO_CHAR(b.pub_date, 'yyyy-mm-dd') as pub_date; 
			query += " a.author_id, ";
			query += " a.author_name, ";
			query += " a.author_desc ";
			query += " from book b, author a ";
			query += " where b.author_id = a.author_id ";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int bId = rs.getInt("book_id");// int bId = rs.getInt(book_id);
				String bTitle = rs.getString("title");// String bTitle = rs.getInt(title);
				String bPubs = rs.getString("pubs");// String bPubs = rs.getInt(pubs);
				String bPubD = rs.getString("pub_date");// String bPubD = rs.getInt(pub_date);
				int aId = rs.getInt("author_id");// int aId = rs.getInt(a.author_id);
				String aName = rs.getString("author_name");// String aName = rs.getInt(author_name);
				String aDesc = rs.getString("author_desc");// String aDesc = rs.getInt(author_desc);

				System.out.println(bId + ". " + bTitle + ", " + bPubs + ", " + bPubD + ", " + aId + ", " + aName + ", " + aDesc);
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

	}

}
