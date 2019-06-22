package TestCollection;

/**
 * author sheyang
 * created at 2018/8/5
 */
public class Node {
    private Node previous;
    private Object obj;
    private Node next;

    public Node() {
    }
    public Node(Node previous,Object obj,Node next){
        super();
        this.previous = previous;
        this.obj = obj;
        this.next = next;
    }


    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
