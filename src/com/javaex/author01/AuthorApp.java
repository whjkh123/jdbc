package com.javaex.author01;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {

		AuthorDao aDao = new AuthorDao();

		List<AuthorVo> aList = aDao.getAuthorList();

		// INSERT
		// int count = aDao.authorInsert("이문열","경북영양");
		// System.out.println("[" + count + "건이 저장되었습니다.]");
		AuthorVo aVo1 = new AuthorVo("이문열", "경북 영양");
		aDao.authorInsert(aVo1);

		AuthorVo aVo2 = new AuthorVo("박경리", "경남 통영");
		aDao.authorInsert(aVo2);

		AuthorVo aVo3 = new AuthorVo("유시민", "제17대 국회의원");
		aDao.authorInsert(aVo3);

		aList = aDao.getAuthorList();

		for (int i = 0; i < aList.size(); i++) {

			AuthorVo vo = aList.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());

		}

		// DELETE
		aDao.authorDelete(3);

		aList = aDao.getAuthorList();

		for (int i = 0; i < aList.size(); i++) {

			AuthorVo vo = aList.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());

		}

		// UPDATE
		AuthorVo aVo4 = new AuthorVo(2, "김경리", "제주광역시");
		aDao.authorUpdate(aVo4);

		aList = aDao.getAuthorList();

		for (int i = 0; i < aList.size(); i++) {

			AuthorVo vo = aList.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());

		}

	}

}
