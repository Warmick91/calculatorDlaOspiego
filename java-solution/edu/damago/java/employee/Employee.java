package edu.damago.java.employee;

/**
 * Instances represent employees.
 */
// Changed into JavaBean class featuring encapsulation.
// Added gender as instance variable of an enum type
// (later using valueOf(String) and name() to convert
// the enum instances from/to text)
public class Employee {
	static public enum Gender { DIVERSE, FEMALE, MALE }

	private long identity;
	private String surname;
	private String forename;
	private float age;
	private Gender gender;


	public long getIdentity () {
		return this.identity;
	}


	public void setIdentity (final long identity) {
		this.identity = identity;
	}


	public String getSurname () {
		return this.surname;
	}


	public void setSurname (final String surname) {
		this.surname = surname;
	}


	public String getForename () {
		return this.forename;
	}


	public void setForename (final String forename) {
		this.forename = forename;
	}


	public float getAge () {
		return this.age;
	}


	public void setAge (final float age) {
		this.age = age;
	}


	public Gender getGender () {
		return this.gender;
	}


	public void setGender (final Gender gender) {
		this.gender = gender;
	}
}