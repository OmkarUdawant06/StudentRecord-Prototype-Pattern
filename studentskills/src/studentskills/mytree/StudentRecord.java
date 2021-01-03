package studentskills.mytree;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

public class StudentRecord implements Cloneable, SubjectI, ObserverI{
   private int bNumber;
   private int value;
   private String firstName;
   private String lastName;
   private String major;
   private double gpa;
   private List<String> skills;
   StudentRecord left;
   StudentRecord right;
   public LinkedHashMap<Integer, List<Object>> treeNode = new LinkedHashMap<Integer, List<Object>>();
   private List<StudentRecord> subscribe = new ArrayList<StudentRecord>();

   public StudentRecord(){
   }

   /**
    * Creates Node
    * @param value Bnumber to set as root
    */
   public StudentRecord(int value){
      this.value = value;
      left = null;
      right = null;
   }

   /**
    * Get value of root
    * @return root value
    */
   public Integer getValue(){
      return value;
   }

   /**
    * Get left child
    * @return left child
    */
   public StudentRecord getlChild() {
      return left;
   }

   /**
    * Get right child
    * @return right child
    */
   public StudentRecord getrChild() {
      return right;
   }

   /**
    * Get Bnumber
    * @return Bnumber
    */
   public int getBnumber(){
      return bNumber;
   }

   /**
    * Get First Name
    * @return First Name
    */
   public String getFirstName(){
      return firstName;
   }

   /**
    * Get Last Name
    * @return Last Name
    */
   public String getLastName(){
      return lastName;
   }

   /**
    * Get GPA
    * @return GPA
    */
   public Double getGPA(){
      return gpa;
   }

   /**
    * Get Major
    * @return Major
    */
   public String getMajor(){
      return major;
   }

   /**
    * Get Skills
    * @return Skills
    */
   public List<String> getSkills(){
      return skills;
   }

   /**
    * Set value for root
    * @param value value to set
    */
   public void setValue(int value){
      this.value = value;
   }

   /**
    * Set left child of root
    * @param lChild left value to set
    */
   public void setlChild(StudentRecord lChild) {
      this.left = lChild;
   }

   /**
    * Set right child of root
    * @param rChild right value to set
    */
   public void setrChild(StudentRecord rChild) {
      this.right = rChild;
   }

   /**
    * Set Bnumber
    * @param bNumber Bnumber to set
    */
   public void setBnumber(int bNumber){
      this.bNumber = bNumber;
   }

   /**
    * Set First Name
    * @param firstName First Name to set
    */
   public void setFirstName(String firstName){
      this.firstName = firstName;
   }

   /**
    * Set Last Name
    * @param lastName Last Name to set
    */
   public void setLastName(String lastName){
      this.lastName = lastName;
   }

   /**
    * Set GPA
    * @param gpa GPA to set
    */
   public void setGPA(Double gpa){
      this.gpa = gpa;
   }

   /**
    * Set Major
    * @param major Major to set
    */
   public void setMajor(String major){
      this.major = major;
   }

   /**
    * Set Skills
    * @param skills Skills to set
    */
   public void setSkills(List<String> skills){
      this.skills = skills;
   }

   /**
    * Insert Student Record in Data Structure
    * @param bNumber search for Bnumber to checks if it exists
    */
   public void makeNode(int bNumber){
      List<Object> data = new ArrayList<Object>();
      if (!treeNode.containsKey(bNumber)){
         data.add(firstName);
         data.add(lastName);
         data.add(gpa);
         data.add(major);
         data.addAll(skills);
         treeNode.put(bNumber,data);
         //System.out.println("Tree: "+treeNode);
      }
   }

   /**
    * Deep Cloning of node
    * @return Cloned Object of Student Record
    */
   public Object clone() {
      StudentRecord studentRecord = new StudentRecord();

      studentRecord.setValue(this.value);
      studentRecord.setBnumber(this.bNumber);
      studentRecord.setFirstName(this.firstName);
      studentRecord.setLastName(this.lastName);
      studentRecord.setGPA(this.gpa);
      studentRecord.setMajor(this.major);
      studentRecord.setSkills(this.skills);

      return studentRecord;
   }

   /**
    * Set Observer to monitor
    * @param studentRecord register studentRecord into list
    */
   @Override
   public void registerObserver(StudentRecord studentRecord) {
      subscribe.add(studentRecord);
   }

   /**
    * Remove Observer from list
    * @param studentRecord remove studentRecord object from list
    */
   @Override
   public void removeObserver(StudentRecord studentRecord) {
      subscribe.remove(studentRecord);
   }

   /**
    * Notify list of observers if Subject changes/updates
    * @param studentRecord Notify which object updated
    * @param operation check if operation is INSERT or MODIFY
    */
   @Override
   public void notifyAll(StudentRecord studentRecord, Operation operation){
      for (StudentRecord subscriberList : subscribe){
         subscriberList.update(studentRecord, operation);
      }
   }

   /**
    * Update the Data based on subject
    * @param studentRecord subject who got modified
    * @param operation check if operation is INSERT or MODIFY
    */
   @Override
   public void update(StudentRecord studentRecord, Operation operation){
      if (operation.equals(Operation.INSERT)) {
         this.setBnumber(studentRecord.getBnumber());
         this.setFirstName(studentRecord.getFirstName());
         this.setLastName(studentRecord.getLastName());
         this.setGPA(studentRecord.getGPA());
         this.setMajor(studentRecord.getMajor());
         this.setSkills(studentRecord.getSkills());
      } else {
         this.setFirstName(studentRecord.getFirstName());
         this.setLastName(studentRecord.getLastName());
         this.setMajor(studentRecord.getMajor());
         this.setSkills(studentRecord.getSkills());
      }
   }
}
