package kr.or.ddit.board.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import kr.or.ddit.board.repository.BoardRepositoryI;

public class Person {
	public static void main(String[] args) {
		Person person = new Person();
	}
	private int age;
	private BoardRepositoryI boardRepository;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthdate;
	
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date hiredate;
	
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public BoardRepositoryI getBoardRepository() {
		return boardRepository;
	}
	public void setBoardRepository(BoardRepositoryI boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	
}
