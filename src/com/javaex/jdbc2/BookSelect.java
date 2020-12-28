package com.javaex.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelect {

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
			 * SELECT  book_id,
        			   title,
        			   pubs,
        			   pub_date,
        			   author_id
			   FROM    book;
			 */
			String query = "";
			query += " select book_id, title, pubs, pub_date, author_id ";
			query += " from book ";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();// select

			// 4.결과처리
			while (rs.next()) {
				int bId = rs.getInt("book_id");// int bId = rs.getInt(1);
				String bTitle = rs.getString("title");// String bTitle = rs.getInt(2);
				String bPubs = rs.getString("pubs");// String bPubs = rs.getInt(4);
				String bPubD = rs.getString("pub_date");// String bPubD = rs.getInt(5);
				int bFk = rs.getInt("author_id");// int bFk = rs.getInt(6);

				System.out.println(bId + ", " + bTitle + ", " + bPubs + ", " + bPubD + ", " + bFk);
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
