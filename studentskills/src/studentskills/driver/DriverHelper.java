package studentskills.driver;

import studentskills.exception.StudentsException;
import studentskills.mytree.Operation;
import studentskills.mytree.StudentRecord;
import studentskills.mytree.TreeHelper;
import studentskills.util.CreateUniqueID;
import studentskills.util.FileProcessor;
import studentskills.util.MyLogger;
import studentskills.util.Results;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

public class DriverHelper {
    private static int searchFlag, duplicateNodeFlag = 0;

    /**
     * Gets Data from Driver and processes it
     * @param openFp Input file
     * @param modifyFp Modification File
     * @param outputClone0 Output file 1
     * @param outputClone1 Output file 2
     * @param outputClone2 Output file 3
     * @param errorWrite Error File
     * @param debugWrite Debug File
     * @param args List of Command Line arguments
     * @throws IOException
     */
    public void processInput(FileProcessor openFp, FileProcessor modifyFp, Results outputClone0, Results outputClone1, Results outputClone2, Results errorWrite, Results debugWrite, String[] args) throws IOException {

        int bnumber, replicaID;
        String firstName, lastName, major, originalValue, newValue, words, trimWords;;
        double gpa;
        StringBuilder errorFile = new StringBuilder();
        StringBuilder debugFile = new StringBuilder();
        StudentRecord cloneNode0 = null;
        StudentRecord cloneNode1 = null;
        StudentRecord cloneNode2 = null;

        StudentRecord studentRecord = new StudentRecord();
        MyLogger.setDebugValue(MyLogger.DebugLevel.CONSTRUCTOR);
        debugWrite.addOutputToFile(debugFile.append(MyLogger.writeMessage("STUDENT RECORD CONSTRUCTOR CALLED\n",MyLogger.DebugLevel.CONSTRUCTOR)));

        MyLogger myLogger = new MyLogger();

        StudentRecord storeData = new StudentRecord();
        MyLogger.setDebugValue(MyLogger.DebugLevel.CONSTRUCTOR);
        debugWrite.addOutputToFile(debugFile.append(MyLogger.writeMessage("STUDENT RECORD CONSTRUCTOR CALLED\n",MyLogger.DebugLevel.CONSTRUCTOR)));

        TreeHelper myTree0 = new TreeHelper();
        MyLogger.setDebugValue(MyLogger.DebugLevel.CONSTRUCTOR);
        debugWrite.addOutputToFile(debugFile.append(MyLogger.writeMessage("TREEHELPER CONSTRUCTOR CALLED\n",MyLogger.DebugLevel.CONSTRUCTOR)));

        TreeHelper myTree1 = new TreeHelper();
        MyLogger.setDebugValue(MyLogger.DebugLevel.CONSTRUCTOR);
        debugWrite.addOutputToFile(debugFile.append(MyLogger.writeMessage("TREEHELPER CONSTRUCTOR CALLED\n",MyLogger.DebugLevel.CONSTRUCTOR)));

        TreeHelper myTree2 = new TreeHelper();
        MyLogger.setDebugValue(MyLogger.DebugLevel.CONSTRUCTOR);
        debugWrite.addOutputToFile(debugFile.append(MyLogger.writeMessage("TREEHELPER CONSTRUCTOR CALLED\n",MyLogger.DebugLevel.CONSTRUCTOR)));

        Operation operation;
        int UniqueIDTree0 = CreateUniqueID.getID();
        int UniqueIDTree1 = CreateUniqueID.getID();
        int UniqueIDTree2 = CreateUniqueID.getID();
        try {

            /*
             * Checks if file is empty */
            if (openFp.poll() == null) {
                throw new StudentsException("INPUT FILE IS EMPTY!");
            } else {
                openFp = new FileProcessor(args[0]);

                MyLogger.setDebugValue(MyLogger.DebugLevel.INSERT);
                debugWrite.addOutputToFile(debugFile.append(MyLogger.writeMessage("PROCESSING FILE_PROCESSOR INPUT FILE\n",MyLogger.DebugLevel.FILE_PROCESSOR)));

                ignore:
                while ((words = openFp.poll()) != null) {
                   if (words.isEmpty()) {
                        throw new StudentsException("PARSED LINE IS EMPTY!");
                    }

                    operation = Operation.INSERT;

                    bnumber = Integer.parseInt(words.substring(0, words.indexOf(":")));
                    if (String.valueOf(bnumber).length() != 4 || bnumber < 0) {
                        throw new StudentsException("BNUMBER HAS TO BE 4 DIGITS LONG AND CAN NOT BE NEGATIVE!");
                    }

                    String[] tempVar;
                    List<String> skills = new ArrayList<String>();

                    tempVar = words.split(":");
                    trimWords = tempVar[1];
                    firstName = trimWords.split(",")[0];
                    lastName = trimWords.split(",")[1];
                    gpa = Double.parseDouble(trimWords.split(",")[2]);
                    major = trimWords.split(",")[3];

                    String skill = trimWords.substring(trimWords.indexOf(trimWords.split(",")[4]), trimWords.length());
                    tempVar = skill.split(",");
                    Collections.addAll(skills, tempVar);
                    if (skills.size() > 10) {
                        errorWrite.addOutputToFile(errorFile.append("MAXIMUM LIMITS OF 10 SKILLS EXCEED!\n"));
                        continue ignore;
                    }

                    if (cloneNode0 == null) {

                        cloneNode0 = new StudentRecord();
                        MyLogger.setDebugValue(MyLogger.DebugLevel.CONSTRUCTOR);
                        debugWrite.addOutputToFile(debugFile.append(MyLogger.writeMessage("STUDENT RECORD CONSTRUCTOR CALLED\n",MyLogger.DebugLevel.CONSTRUCTOR)));

                        setFileds(cloneNode0, bnumber, firstName, lastName, gpa, major, skills);
                        setFileds(storeData, bnumber, firstName, lastName, gpa, major, skills);

                        storeData.makeNode(bnumber);

                        cloneNode1 = (StudentRecord) cloneNode0.clone();
                        cloneNode2 = (StudentRecord) cloneNode0.clone();

                        cloneNode0.registerObserver(cloneNode1);
                        cloneNode0.registerObserver(cloneNode2);

                        cloneNode1.registerObserver(cloneNode0);
                        cloneNode1.registerObserver(cloneNode2);

                        cloneNode2.registerObserver(cloneNode0);
                        cloneNode2.registerObserver(cloneNode1);
                    } else {
                        StudentRecord studentRecord1 = myTree0.search(cloneNode0, bnumber);

                        if (studentRecord1 != null){
                            duplicateNodeFlag = 1;
                            setFileds(studentRecord1, bnumber, firstName, lastName, gpa, major, skills);
                            studentRecord1.makeNode(bnumber);
                            storeData.treeNode.replace(bnumber,studentRecord1.treeNode.get(bnumber));
                            studentRecord1.notifyAll(studentRecord1,operation);
                        } else {
                            setFileds(storeData, bnumber, firstName, lastName, gpa, major, skills);
                            storeData.makeNode(bnumber);

                            StudentRecord cloneNode00 = new StudentRecord();
                            setFileds(cloneNode00, bnumber, firstName, lastName, gpa, major, skills);

                            StudentRecord cloneNode01 = (StudentRecord) cloneNode00.clone();
                            StudentRecord cloneNode02 = (StudentRecord) cloneNode00.clone();

                            cloneNode00.registerObserver(cloneNode01);
                            cloneNode00.registerObserver(cloneNode02);

                            cloneNode01.registerObserver(cloneNode00);
                            cloneNode01.registerObserver(cloneNode02);

                            cloneNode02.registerObserver(cloneNode00);
                            cloneNode02.registerObserver(cloneNode02);

                            myTree0.insert(cloneNode0, cloneNode00);
                            myTree1.insert(cloneNode1, cloneNode01);
                            myTree2.insert(cloneNode2, cloneNode02);
                        }
                    }
                }

                if (modifyFp.poll() == null){
                    throw new StudentsException("MODIFY FILE IS EMPTY!");
                }
                MyLogger.setDebugValue(MyLogger.DebugLevel.MODIFY);
                debugWrite.addOutputToFile(debugFile.append(MyLogger.writeMessage("PROCESSING FILE_PROCESSOR MODIFICATION FILE\n",MyLogger.DebugLevel.FILE_PROCESSOR)));

                modifyFp = new FileProcessor(args[1]);
                ignore2:
                while ((words = modifyFp.poll()) != null) {
                    if (words.isEmpty()) {
                        throw new StudentsException("PARSED LINE IS EMPTY!");
                    }

                    cloneNode0.treeNode = storeData.treeNode;
                    cloneNode1.treeNode = storeData.treeNode;
                    cloneNode2.treeNode = storeData.treeNode;

                    operation = Operation.MODIFY;

                    replicaID = Integer.parseInt(words.substring(0, words.indexOf(",")));

                    bnumber = Integer.parseInt(words.substring(words.indexOf(",") + 1, words.indexOf(",") + 5));
                    if (String.valueOf(bnumber).length() != 4 || bnumber < 0) {
                        throw new StudentsException("BNUMBER HAS TO BE 4 DIGITS LONG AND CAN NOT BE NEGATIVE!");
                    }

                    originalValue = words.substring(words.lastIndexOf(",") + 1, words.length());
                    originalValue = originalValue.substring(0, originalValue.indexOf(":"));
                    newValue = words.substring(words.lastIndexOf(":") + 1, words.length());
                    if (newValue.isEmpty()){
                        errorWrite.addOutputToFile(errorFile.append("VALUE TO BE MODIFIED IS EMPTY!\n"));
                        continue ignore2;
                    }

                    if (replicaID == UniqueIDTree0) {
                        studentRecord = myTree0.search(cloneNode0, bnumber);
                        if (studentRecord == null){
                            errorWrite.addOutputToFile(errorFile.append("BNUMBER: " + bnumber + " REQUESTED TO UPDATE DOES NOT EXIST IN STUDENT RECORDS!\n"));
                            continue ignore2;
                        }
                        setValues(studentRecord, cloneNode0.treeNode);

                        if (studentRecord.getFirstName().equals(originalValue)) {
                            searchFlag = 0;
                            searchAndUpdate(studentRecord, cloneNode0.treeNode, originalValue, newValue, 0, searchFlag, studentRecord.getBnumber());
                        } else if (studentRecord.getLastName().equals(originalValue)) {
                            searchFlag = 0;
                            searchAndUpdate(studentRecord, cloneNode0.treeNode, originalValue, newValue, 1, searchFlag, studentRecord.getBnumber());
                        } else if (studentRecord.getMajor().equals(originalValue)) {
                            searchFlag = 0;
                            searchAndUpdate(studentRecord, cloneNode0.treeNode, originalValue, newValue, 3, searchFlag, studentRecord.getBnumber());
                        } else if (studentRecord.getSkills().contains(originalValue)) {
                            searchFlag = 1;
                            searchAndUpdate(studentRecord, cloneNode0.treeNode, originalValue, newValue, 4, searchFlag, studentRecord.getBnumber());
                        } else {
                            errorWrite.addOutputToFile(errorFile.append("ORIGINAL VALUE YOU ARE TRYING TO UPDATE DOES NOT EXIST IN STUDENT RECORDS FOR BNUMBER:" + bnumber + "\n"));
                            continue ignore2;
                        }

                        studentRecord.notifyAll(studentRecord, operation);
                    }
                    else if (replicaID == UniqueIDTree1) {
                        studentRecord = myTree1.search(cloneNode1, bnumber);
                        if (studentRecord == null){
                            errorWrite.addOutputToFile(errorFile.append("BNUMBER: " + bnumber + " REQUESTED TO UPDATE DOES NOT EXIST IN STUDENT RECORDS!\n"));
                            continue ignore2;
                        }
                        setValues(studentRecord, cloneNode1.treeNode);

                        if (studentRecord.getFirstName().equals(originalValue)) {
                            searchFlag = 0;
                            searchAndUpdate(studentRecord, cloneNode1.treeNode, originalValue, newValue, 0, searchFlag, studentRecord.getBnumber());
                        } else if (studentRecord.getLastName().equals(originalValue)) {
                            searchFlag = 0;
                            searchAndUpdate(studentRecord, cloneNode1.treeNode, originalValue, newValue, 1, searchFlag, studentRecord.getBnumber());
                        } else if (studentRecord.getMajor().equals(originalValue)) {
                            searchFlag = 0;
                            searchAndUpdate(studentRecord, cloneNode1.treeNode, originalValue, newValue, 3, searchFlag, studentRecord.getBnumber());
                        } else if (studentRecord.getSkills().contains(originalValue)) {
                            searchFlag = 1;
                            searchAndUpdate(studentRecord, cloneNode1.treeNode, originalValue, newValue, 4, searchFlag, studentRecord.getBnumber());
                        } else {
                            errorWrite.addOutputToFile(errorFile.append("ORIGINAL VALUE YOU ARE TRYING TO UPDATE DOES NOT EXIST IN STUDENT RECORDS FOR BNUMBER: " + bnumber + "\n"));
                            continue ignore2;
                        }

                        studentRecord.notifyAll(studentRecord, operation);
                    }
                    else if (replicaID == UniqueIDTree2) {
                        studentRecord = myTree2.search(cloneNode2, bnumber);
                        if (studentRecord == null){
                            errorWrite.addOutputToFile(errorFile.append("BNUMBER: " + bnumber + " REQUESTED TO UPDATE DOES NOT EXIST IN STUDENT RECORDS!\n"));
                            continue ignore2;
                        }
                        setValues(studentRecord, cloneNode2.treeNode);

                        if (studentRecord.getFirstName().equals(originalValue)) {
                            searchFlag = 0;
                            searchAndUpdate(studentRecord, cloneNode2.treeNode, originalValue, newValue, 0, searchFlag, studentRecord.getBnumber());
                        } else if (studentRecord.getLastName().equals(originalValue)) {
                            searchFlag = 0;
                            searchAndUpdate(studentRecord, cloneNode2.treeNode, originalValue, newValue, 1, searchFlag, studentRecord.getBnumber());
                        } else if (studentRecord.getMajor().equals(originalValue)) {
                            searchFlag = 0;
                            searchAndUpdate(studentRecord, cloneNode2.treeNode, originalValue, newValue, 3, searchFlag, studentRecord.getBnumber());
                        } else if (studentRecord.getSkills().contains(originalValue)) {
                            searchFlag = 1;
                            searchAndUpdate(studentRecord, cloneNode2.treeNode, originalValue, newValue, 4, searchFlag, studentRecord.getBnumber());
                        } else {
                            errorWrite.addOutputToFile(errorFile.append("ORIGINAL VALUE YOU ARE TRYING TO UPDATE DOES NOT EXIST IN STUDENT RECORDS FOR BNUMBER: " + bnumber + "\n"));
                            continue ignore2;
                        }
                        studentRecord.notifyAll(studentRecord, operation);
                    }
                }

                printNode(outputClone0,myTree0,cloneNode0);
                printNode(outputClone1,myTree1,cloneNode1);
                printNode(outputClone2,myTree2,cloneNode2);
            }
        } catch (IOException | NullPointerException | StudentsException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            errorWrite.addOutputToFile(errorFile.append(sw.toString()));
        } finally {
            openFp.close();
            modifyFp.close();
            outputClone0.closeFile();
            outputClone1.closeFile();
            outputClone2.closeFile();
            errorWrite.closeFile();
            debugWrite.closeFile();
        }
    }

    /**
     * Looks up for Student Record to modify and then updates it
     * @param studentRecord Object of StudentRecord which
     *                      needs to be modified
     * @param treeNode  data to be retrieved for modification
     * @param originalValue original value which needs to be modified
     * @param newValue new value to which original value needs to be
     *                 changed to
     * @param position locate which value to update in database
     * @param searchFlag1 check if skills are to be updated
     *                    then set to 1 else set to 0
     * @param bnumber which student record to modify
     */
    private static void searchAndUpdate(StudentRecord studentRecord, LinkedHashMap<Integer, List<Object>> treeNode, String originalValue, String newValue, int position, int searchFlag1, int bnumber) {
        if (treeNode.containsKey(bnumber)) {
            for (Map.Entry<Integer, List<Object>> iterator : treeNode.entrySet()) {
                int bNumber = iterator.getKey();
                List<Object> data = iterator.getValue();
                if (data.contains(originalValue) && bNumber == bnumber) {
                    if (searchFlag1 == 0) {
                        treeNode.get(bNumber).set(position, newValue);
                        return;
                    } else {
                        treeNode.get(bNumber).set(data.indexOf(originalValue), newValue);
                        searchFlag = 0;
                        return;
                    }
                }
            }
        }
    }

    /**
     *  Updates StudentRecord object values
     * @param studentRecord Object of StudentRecord which
     *                     needs to be updated after Modification
     * @param dataSet Dataset which contains copy of all
     *                Student Records
     */
    private static void setValues(StudentRecord studentRecord, LinkedHashMap<Integer, List<Object>> dataSet){

        for (Map.Entry<Integer, List<Object>> iterator : dataSet.entrySet()){
            int bnumber = iterator.getKey();
            List<Object> data = iterator.getValue();
            if (studentRecord.getBnumber() == bnumber){
                studentRecord.setFirstName((String) data.get(0));
                studentRecord.setLastName((String) data.get(1));
                studentRecord.setGPA((Double) data.get(2));
                studentRecord.setMajor((String) data.get(3));
                List<String> skills = new ArrayList<String>((List) data.subList(4, data.size()));
                studentRecord.setSkills(skills);
                return;
            }
        }
    }

    /**
     * Updates all fileds specified in parameter list
     * @param studentRecord StudentRecord object who's fileds
     *                     need to be set
     * @param bnumber Bnumber which needs to be set
     * @param firstName First Name which needs to be set
     * @param lastName Last Name which needs to be set
     * @param gpa GPA which needs to be set
     * @param major Major which needs to be set
     * @param skills skills which needs to be set
     *               If new skills are added, perform
     *               union operation
     */
    private static void setFileds(StudentRecord studentRecord, int bnumber, String firstName, String lastName, double gpa, String major, List<String> skills){
        if (duplicateNodeFlag == 0) {
            studentRecord.setBnumber(bnumber);
            studentRecord.setFirstName(firstName);
            studentRecord.setLastName(lastName);
            studentRecord.setGPA(gpa);
            studentRecord.setMajor(major);
            if (skills.size()>10){
                skills = skills.subList(0,10);
            }
            studentRecord.setSkills(skills);
        } else {
            studentRecord.setFirstName(firstName);
            studentRecord.setLastName(lastName);
            studentRecord.setGPA(gpa);
            studentRecord.setMajor(major);
            List<String> newSkills = union(studentRecord.getSkills(),skills);
            if (newSkills.size()>10){
                newSkills = newSkills.subList(0,10);
            }
            studentRecord.setSkills(newSkills);
            duplicateNodeFlag = 0;
        }
    }

    /**
     * Prints InOrder of the built tree
     * @param results Results object to write output
     *                to output files
     * @param mytree Tree which needs to be written to file
     * @param cloneNode to write the skills to output file
     */
    private static void printNode(Results results, TreeHelper mytree, StudentRecord cloneNode){
        StringBuilder order = new StringBuilder();
        String[] bnumberS;
        List<String> skills = new ArrayList<String>();
        int bnumber;
        order = mytree.inOrder(cloneNode, order);
        bnumberS = order.toString().split(" ");
        order = new StringBuilder();
        for (String s : bnumberS) {
            bnumber = Integer.parseInt(s);
            for (Map.Entry<Integer,List<Object>> iterator : cloneNode.treeNode.entrySet()){
                int bnum = iterator.getKey();
                List<Object> data = iterator.getValue();
                if (bnumber == bnum) {
                    //skills = new ArrayList<String>((List) data.subList(4, data.size()));
                    order.append(bnumber).append(":").append(data).append("\n");
                }
            }
        }
        results.addOutputToFile(order);
    }

    /**
     * returns a list which is union of old skills and new skill set
     * @param list1 Old skill List
     * @param list2 New skill List
     * @return List with unique skills
     */
    private static List<String> union(List<String> list1, List<String> list2) {
        Set<String> set = new HashSet<String>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<String>(set);
    }

    /**
     * Provides only common skills from list 1 & List 2
     * (For experimenting purpose, Unused in project)
     * @param list1 Old skill List
     * @param list2 New skill List
     * @return List set with common skills
     */
    private static List<String> intersection(List<String> list1, List<String> list2) {
        List<String> list = new ArrayList<String>();

        for (String itr : list1) {
            if(list2.contains(itr)) {
                list.add(itr);
            }
        }
        return list;
    }
}