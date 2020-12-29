package com.javaex.author01;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {

		AuthorDao aDao = new AuthorDao();

		List<AuthorVo> aList = aDao.getAuthorList();

		// int count = aDao.authorInsert("이문열","경북영양");
		// System.out.println("[" + count + "건이 저장되었습니다.]");
		aDao.authorInsert("이문열", "경북 영양");
		aDao.authorInsert("박경리", "경남 통영");
		aDao.authorInsert("유시민", "제17대 국회의원");

		aList = aDao.getAuthorList();

		for (int i = 0; i < aList.size(); i++) {

			AuthorVo vo = aList.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());

		}

		aDao.authorDelete(3);

		aList = aDao.getAuthorList();

		for (int i = 0; i < aList.size(); i++) {

			AuthorVo vo = aList.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());

		}

		aDao.authorUpdate(2, "김경리", "제주광역시");

		for (int i = 0; i < aList.size(); i++) {

			AuthorVo vo = aList.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());

		}

	}

}
