package studentskills.mytree;

public class TreeHelper {
    private String id;
    protected String type;

    private StudentRecord rootNode;

    public TreeHelper(){
        type = "tree";
    }

    /**
     * Insert Childnode to either left or right of root node
     * @param root Root node
     * @param childNode ChildNode to add
     * @return the current Root node
     */
     public StudentRecord insert(StudentRecord root, StudentRecord childNode) {

        if (root == null) {
            root = childNode;
            return root;
        }

        /*
         * Recursively add to left side of tree,
         * if Child Node is smaller than Root Node
         * else Recursively add to right side of tree,
         * if Child Node is greater than Root Node
         */
        if (childNode.getBnumber() > root.getBnumber()) {
            root.setrChild(insert(root.getrChild(), childNode));
        } else if (childNode.getBnumber() < root.getBnumber()){
            root.setlChild(insert(root.getlChild(), childNode));
        }

        return root;
    }

    /**
     * Prints inOrder of the tree
     * @param node the node to print
     * @param order to store output of inOrder
     * @return the output of inOder
     */
    public StringBuilder inOrder(StudentRecord node, StringBuilder order) {
        if (node == null) {
            return order;
        }
        inOrder(node.getlChild(), order);
        order.append(node.getBnumber() + " ");
        inOrder(node.getrChild(), order);
        return order;
    }

    /**
     * Prints PreOder of a tree
     * (Unused method)
     * @param root the node to print
     */
    private void preOrder(StudentRecord root) {
        if (root == null) {
            return;
        }
        System.out.print(root.getValue() + " ");
        preOrder(root.getlChild());
        preOrder(root.getrChild());
    }

    /**
     * Prints PostOrder
     * (Unused method)
     * @param root the node to print
     */
    private void postOrder(StudentRecord root) {
        if (root == null) {
            return;
        }
        postOrder(root.getlChild());
        postOrder(root.getrChild());
        System.out.print(root.getValue() + " ");
    }

    /**
     * Searched for given node in tree
     * @param studentRecord the node to hold reference of searched
     *                      Bnumber
     * @param bnumber the Bnumber to search for in tree
     * @return studentRecord object which holds value for the given
     * Bnumber
     */
    public StudentRecord search(StudentRecord studentRecord, Integer bnumber) {

        if (studentRecord == null)
            return null;

        //root found!
        if (studentRecord.getBnumber() == bnumber) {
            return studentRecord;
        }else if (studentRecord.getBnumber() < bnumber) {
            // then recur on right subtree
            studentRecord = search(studentRecord.getrChild(), bnumber);
        } else if(studentRecord.getBnumber() > bnumber){
            // then recur on left subtree
            studentRecord = search(studentRecord.getlChild(), bnumber);
        }
        return studentRecord;
    }

    String rtn;

    /**
     * Prints InOder of tree in String format
     * @param root node to print
     */
    public void toStringInOrder(StudentRecord root) {
        if (root == null) {
            return;
        }
        toStringInOrder(root.getlChild());
        rtn += " " + root.getValue();
        toStringInOrder(root.getrChild());
    }

    /**
     * toString method to print
     * @return String which contains inOder
     */
    @Override
    public String toString() {
        rtn = "";
        if (rootNode == null) {
            return "Empty Tree";
        }
        toStringInOrder(rootNode);
        return rtn;
    }
}
