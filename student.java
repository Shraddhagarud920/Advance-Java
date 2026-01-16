package hibernateCURD;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "studData")
public class student {
@Id
int studid;
String studName;
int marks;
public student() {
	

}
public student(int studid, String studName, int marks) {
	super();
	this.studid = studid;
	this.studName = studName;
	this.marks = marks;
}
public int getStudid() {
	return studid;
}
public void setStudid(int studid) {
	this.studid = studid;
}
public String getStudName() {
	return studName;
}
public void setStudName(String studName) {
	this.studName = studName;
}
public int getMarks() {
	return marks;
}
public void setMarks(int marks) {
	this.marks = marks;
}
@Override
public String toString() {
	return "student [studid=" + studid + ", studName=" + studName + ", marks=" + marks + "]";
}






	
	
	

}
