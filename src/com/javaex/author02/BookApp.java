package com.javaex.author02;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {

		// 작가 등록(6명)
		// AuthorDao, AuthorVo 이용
		AuthorDao aDao = new AuthorDao();

		List<AuthorVo> aList = aDao.getAuthorList();

		// INSERT
		AuthorVo aisrtVo1 = new AuthorVo("이문열", "경상북도 영양");
		AuthorVo aisrtVo2 = new AuthorVo("박경리", "경상남도 통영");
		AuthorVo aisrtVo3 = new AuthorVo("유시민", "제17대 국회의원");
		AuthorVo aisrtVo4 = new AuthorVo("기안84", "기안동 거주 84년생");
		AuthorVo aisrtVo5 = new AuthorVo("강풀", "1세대 온라인만화가");
		AuthorVo aisrtVo6 = new AuthorVo("김영하", "알쓸신잡");

		aDao.authorInsert(aisrtVo1);
		aDao.authorInsert(aisrtVo2);
		aDao.authorInsert(aisrtVo3);
		aDao.authorInsert(aisrtVo4);
		aDao.authorInsert(aisrtVo5);
		aDao.authorInsert(aisrtVo6);

		// 책 등록(8권)
		// BookDao, BookVo 이용
		BookDao bDao = new BookDao();

		List<BookVo> bList = bDao.getBookList();

		// INSERT
		BookVo bisrtVo1 = new BookVo("우리들의 일그러진 영웅", "다림", "98/02/22", 1);
		BookVo bisrtVo2 = new BookVo("삼국지", "민음사", "02/03/01", 1);
		BookVo bisrtVo3 = new BookVo("토지", "마로니에북스", "12/08/15", 2);
		BookVo bisrtVo4 = new BookVo("유시민의 글쓰기 특강", "생각의 길", "15/04/01", 3);
		BookVo bisrtVo5 = new BookVo("패션왕", "중앙북스(books)", "12/02/22", 4);
		BookVo bisrtVo6 = new BookVo("순정만화", "재미주의", "11/08/03", 5);
		BookVo bisrtVo7 = new BookVo("오직 두 사람", "문학동네", "17/05/04", 6);
		BookVo bisrtVo8 = new BookVo("26년", "재미주의", "12/02/04", 5);

		bDao.bookInsert(bisrtVo1);
		bDao.bookInsert(bisrtVo2);
		bDao.bookInsert(bisrtVo3);
		bDao.bookInsert(bisrtVo4);
		bDao.bookInsert(bisrtVo5);
		bDao.bookInsert(bisrtVo6);
		bDao.bookInsert(bisrtVo7);
		bDao.bookInsert(bisrtVo8);

		// UPDATE
		BookVo bupdVo = new BookVo(5, "복학왕");
		bDao.bookUpdate(bupdVo);

		AuthorVo aupdVo = new AuthorVo(4, "[나 혼자 산다] 출연 웹툰작가");
		aDao.authorUpdate(aupdVo);

		// Delete
		bDao.bookDelete(5);

		// 전체 출력(작가 ↔ 책 조인)
		// BookVo 중요
		bList = bDao.getBookListAll();

		for (int i = 0; i < bList.size(); i++) {

			BookVo info = bList.get(i);
			System.out.println(info.book_id + ", " + info.title + ", " + info.pubs + ", " + info.pub_date + ", "
					+ info.author_id + ", " + info.authorName + ", " + info.authorDesc);
		}

	}

}
