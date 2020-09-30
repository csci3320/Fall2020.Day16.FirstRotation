
public class Node<T extends Comparable> {

    private Node<T> left;
    private Node<T> right;
    private T value;
    private int balanceFactor;
    private int heightLeft;
    private int heightRight;

    public Node(T object) {
        value = object;
        balanceFactor = 0;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setLeft(Node<T> inLeft) {
        left = inLeft;
    }

    public void setRight(Node<T> inRight) {
        right = inRight;
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor(int i) {
        balanceFactor = i;
    }

    public void addBalanceLeft() {
        balanceFactor -= 1;
    }

    public void addBalanceRight() {
        balanceFactor += 1;
    }

    public void setHeightLeft(int height){
        heightLeft = height;
    }
    public void setHeightRight(int height) {
        heightRight = height;
    }

    public int getHeighLeft(){
        return heightLeft;
    }
    public int getHeightRight() {
        return heightRight;
    }

}
