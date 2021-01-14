package model;

public class User extends Player{
	private int score;
	private String id;
	private String pwd;
	private String email;
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
