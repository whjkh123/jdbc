package com.javaex.book01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void dbCnt() {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	public void close() {

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

	// INSERT
	public int bookInsert(BookVo bVo) {

		dbCnt();

		int count = 0;

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// INSERT INTO book VALUES(SEQ_BOOK_ID.nextval, '해리포터와 마법사의 돌', '문학수첩',
			// '14/12/18', 7);
			String query = "INSERT INTO book VALUES(SEQ_BOOK_ID.nextval, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bVo.getTitle());
			pstmt.setString(2, bVo.getPubs());
			pstmt.setString(3, bVo.getPub_date());
			pstmt.setInt(4, bVo.getAuthor_id());

			count = pstmt.executeUpdate(); // insert

			// rs = pstmt.executeQuery(); // select

			// 4.결과처리
			System.out.println("[DAO]: " + count + "건이 저장되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;

	}

	// LIST(SELECT)
	public List<BookVo> getBookList() {

		List<BookVo> bList = new ArrayList<BookVo>();

		dbCnt();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select book_id, ";
			query += " 		  title, ";
			query += "        pubs, ";
			query += "        pub_date, ";
			query += "        author_id ";
			query += " from   book ";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();// select

			// 4.결과처리
			while (rs.next()) {

				int book_id = rs.getInt("book_id");// int aId = rs.getInt(1);
				String title = rs.getString("title");// String aName = rs.getInt(2);
				String pubs = rs.getString("pubs");// String aDesc = rs.getInt(3);
				String pub_date = rs.getString("pub_date");// String aDesc = rs.getInt(3);
				int author_id = rs.getInt("author_id");// String aDesc = rs.getInt(3);

				BookVo bvo01 = new BookVo(book_id, title, pubs, pub_date, author_id);

				bList.add(bvo01);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return bList;

	}

	// LIST(SELECT ALL)
	public List<BookVo> getBookListAll() {

		List<BookVo> bAllList = new ArrayList<BookVo>();

		dbCnt();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select 	b.book_id, ";
			query += " 			b.title, ";
			query += " 			b.pubs, ";
			query += " 			TO_CHAR(b.pub_date, 'yyyy-mm-dd') pub_date, ";// TO_CHAR(b.pub_date, 'yyyy-mm-dd') as pub_date;
			query += " 			a.author_id, ";
			query += " 			a.author_name, ";
			query += " 			a.author_desc ";
			query += " from 	book b, author a ";
			query += " where 	b.author_id = a.author_id ";
			query += " order by b.book_id asc ";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();// select

			// 4.결과처리
			while (rs.next()) {

				int book_id = rs.getInt("book_id");// int bId = rs.getInt(book_id);
				String title = rs.getString("title");// String bTitle = rs.getInt(title);
				String pubs = rs.getString("pubs");// String bPubs = rs.getInt(pubs);
				String pub_date = rs.getString("pub_date");// String bPubD = rs.getInt(pub_date);
				int author_id = rs.getInt("author_id");// int aId = rs.getInt(a.author_id);
				String author_name = rs.getString("author_name");// String aName = rs.getInt(author_name);
				String author_desc = rs.getString("author_desc");// String aDesc = rs.getInt(author_desc);

				BookVo bvo02 = new BookVo(book_id, title, pubs, pub_date, author_id, author_name, author_desc);

				bAllList.add(bvo02);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return bAllList;

	}

	// UPDATE
	public int bookUpdate(BookVo bVo) {

		dbCnt();

		int count = 0;

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// UPDATE book SET title = '복학왕' WHERE book_id = 5;
			String query = "";
			query += " update book ";
			query += " SET title = ? ";
			query += " WHERE book_id = ? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bVo.getTitle());
			pstmt.setInt(2, bVo.getBook_id());

			count = pstmt.executeUpdate(); // update

			// rs = pstmt.executeQuery(); // select

			// 4.결과처리
			System.out.println("[DAO]: " + count + "건이 수정되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;

	}

	// DELETE
	public int bookDelete(int book_id) {

		dbCnt();

		int count = 0;

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// DELETE FROM book WHERE book_id = 5;
			String query = "DELETE FROM book WHERE book_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, book_id);

			count = pstmt.executeUpdate(); // delete

			// rs = pstmt.executeQuery(); // select

			// 4.결과처리
			System.out.println("[DAO]: " + count + "건이 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;

	}

}
