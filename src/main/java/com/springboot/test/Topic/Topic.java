package com.springboot.test.Topic;

public class Topic {

	private String Id;
	private String course;
	private String lession;
	
	public Topic(){
		
	}
	
	public Topic(String id, String course, String lession) {
		super();
		Id = id;
		this.course = course;
		this.lession = lession;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		Id = id;
	}
	/**
	 * @return the course
	 */
	public String getCourse() {
		return course;
	}
	/**
	 * @param course the course to set
	 */
	public void setCourse(String course) {
		this.course = course;
	}
	/**
	 * @return the lession
	 */
	public String getLession() {
		return lession;
	}
	/**
	 * @param lession the lession to set
	 */
	public void setLession(String lession) {
		this.lession = lession;
	}
}
