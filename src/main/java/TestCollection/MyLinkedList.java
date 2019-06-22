package TestCollection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * author sheyang
 * created at 2018/8/5
 */


public class MyLinkedList implements List {
    private Node first;
    private Node last;

    public MyLinkedList() {
    }

    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        Node n = new Node();

        if(first == null){
            n.setPrevious(null);
            n.setObj(o);
            n.setNext(null);

            first = n;
            last = n;
        }else{
            n.setPrevious(last);
            n.setObj(o);
            n.setNext(null);

            last.setNext(n);

            last = n;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Node get(int index) {
        if(0 > index || index >= size || first == null){
            return null;
        }else{
            Node temp = first;
            for(int i=0;i<index;i++){
                temp = temp.getNext();
            }
            return temp;
        }
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {
        Node temp = new Node();
        temp.setObj(element);
        Node node = get(index);
        if(node == null){
            add(element);
        }else{
            temp.setPrevious(node.getPrevious());
            temp.setNext(node);
            node.setPrevious(temp);
            node.getPrevious().setNext(temp);
            size++;

        }


    }

    @Override
    public Node remove(int index) {

        Node temp = get(index);
        temp.getPrevious().setNext(temp.getNext());
        temp.getNext().setNext(temp.getPrevious());

        size--;

        return temp;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }




    public static void main(String[] args){
        List list = new MyLinkedList();
        list.add(10);

        list.add(20);
        list.add("aaa");

        System.out.println(((MyLinkedList) list).size());

        System.out.println(((MyLinkedList) list).get(2).getObj());

        list.remove(1);

        System.out.println(list.size());

        list.add(1,5);
        System.out.println(((MyLinkedList) list).size());



    }

}
