
public class BinaryTree<T extends Comparable> implements MyCollection<T> {

    private Node<T> root;

    /**
     * Constructor. Set the root to null
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Returns the string that represents this binary tree in the .dot language
     */
    public String getDigraph() {
        if (root == null) {
            return "root->null";
        }
        return "root->" + toDigraphString(root)/*root.getValue() + getDigraphBalanceFactor(root)*/ + "\n" + digraph(root);
    }

    /**
     * Return the balance factor as a string for viewing as a digraph
     * @param node The node whose balance factor we need
     * @return A string version of the balance factor. Negative numbers are prepended with an 'n' 
     * instead of a '-'
     */
    private String getDigraphBalanceFactor(Node<T> node) {
        return "_" + (node.getBalanceFactor() < 0 ? "n" : "") + Math.abs(node.getBalanceFactor());
    }

    private String toDigraphString(Node<T> node){
        return node.getValue().toString() + getDigraphBalanceFactor(node) + "_L_" + node.getHeighLeft() + "_R_" + node.getHeightRight();
    }
    
    /**
     * Recursively get the .dot formatted definition of a node
     * @param node The node in question
     * @return The string that representds this node in the .dot language
     */

    private String digraph(Node<T> node) {

        if (node == null)
            return "";
        Node<T> left = node.getLeft();
        Node<T> right = node.getRight();

        String nodeValue = toDigraphString(node);//node.getValue().toString() + getDigraphBalanceFactor(node);

        String leftValue = "";
        String rightValue = "";
        String middle = "";

        var showNull = false;
        if (showNull) {
            leftValue = left == null ? "null_l_" + nodeValue : left.getValue().toString();
            rightValue = right == null ? "null_r_" + nodeValue : right.getValue().toString();
            middle = nodeValue + "->" + leftValue + "[label=\"left\"]\n" + nodeValue + "->" + rightValue
                    + "[label=\"right\"]\n";
        } else {
            leftValue = left == null ? "null" : toDigraphString(left);//left.getValue().toString() + getDigraphBalanceFactor(left);
            rightValue = right == null ? "null" : toDigraphString(right);//right.getValue().toString() + getDigraphBalanceFactor(right);
            if (!(leftValue.contentEquals("null"))) {
                middle += nodeValue + "->" + leftValue + "[label=\"left\"]\n";
            }
            if (!(rightValue.contentEquals("null"))) {
                middle += nodeValue + "->" + rightValue + "[label=\"right\"]\n";
            }

        }

        return digraph(left) + middle + digraph(right);
    }

    /**
     * Add a new entry to the tree
     * 
     */
    public void add(T toAdd) {
        //Wrap the value T in a new Node object
        Node<T> node = new Node<T>(toAdd);

        if (root == null) {
            //If it's the first entry, make it the root
            root = node;
        } else {
            //Otherwise, add it recursively
            addNode(root, node);
        }
        calculateBalance(root);
        balance(root);
        Main.viewTree(this);
    }

    private void balance(Node<T> node){
        //We need to balance this node
        if(node.getBalanceFactor() > 1 || node.getBalanceFactor() < -1){
            if(node.getBalanceFactor() < 0){
                //We need to do a right rotation
                //1. Get a new reference to the root
                Node<T> tempRoot = root;
                //2. Move the root reference to the left child
                root = root.getLeft();
                //3. Change the root.right refence to the tempRoot
                root.setRight(tempRoot);
                //4. Change the tempRoot.left reference to null
                tempRoot.setLeft(null); 
            }
            if(node.getBalanceFactor() > 0){
                Node<T> tempRoot = root;
                root = root.getRight();
                root.setLeft(tempRoot);
                tempRoot.setRight(null);
            }
        }
    }

    

    private int calculateBalance(Node<T> parentNode){
        if(parentNode == null) return 0;
        int heightLeft = calculateBalance(parentNode.getLeft());
        int heightRight = calculateBalance(parentNode.getRight());
        parentNode.setHeightLeft((heightLeft));
        parentNode.setHeightRight((heightRight));
        parentNode.setBalanceFactor(heightRight - heightLeft);
        return Math.max(heightLeft, heightRight) + 1;
    }


    /**
     * Recursively add a node to our tree
     * @param parentNode the node that needs to place the new node
     * @param toAdd The new node
     */
    private void addNode(Node<T> parentNode, Node<T> toAdd) {
        if (toAdd.getValue().compareTo(parentNode.getValue()) <= 0) {

            // Go left
            if (parentNode.getLeft() == null) {
                parentNode.addBalanceLeft();
                parentNode.setLeft(toAdd);
            } else {
                addNode(parentNode.getLeft(), toAdd);
            }
        } else {
            // Go right
            if (parentNode.getRight() == null) {
                parentNode.addBalanceRight();
                parentNode.setRight(toAdd);
            } else {
                addNode(parentNode.getRight(), toAdd);
            }
        }
    }

    /**
     * Get the number of nodes in the tree
     */
    public int size() {
        return size(root);
    }

    /**
     * Recursively find the number of nodes below this node and this node
     * @param parent The node in question
     * @return The number node below this node plus itself (if it existis)
     */
    private int size(Node<T> parent) {
        if (parent == null)
            return 0;
        int leftSize = size(parent.getLeft());
        int rightSize = size(parent.getRight());
        return leftSize + rightSize + 1;
    }

    /**
     * True if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Remove a node from the tree whose value matches the provided value
     */
    public void remove(T toRemove) {

    }

    /**
     * True if a node in the true has a value that matches the one provided.
     * False otherwise.
     */
    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(Node<T> parent, T element) {
        if (parent == null)
            return false;
        boolean left = contains(parent.getLeft(), element);
        boolean right = contains(parent.getRight(), element);
        if (left || right)
            return true;
        if (parent.getValue().equals(element)) {
            return true;
        }
        return false;
    }

}
