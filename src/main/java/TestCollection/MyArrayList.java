package TestCollection;

import java.util.*;

/**
 * author sheyang
 * created at 2018/8/5
 */
public class MyArrayList implements List {

    private Object[] elementData;

    private int size;

    public MyArrayList() {
        this(10);
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        elementData = new Object[initialCapacity];
    }

    public static void main(String[] args) {
        List list = new MyArrayList();
        for (int i = 0; i < 15; i++) {
            list.add(i);
        }
        list.add(1000);
        list.add(6);

        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).toString() + " ");

        }
        System.out.println();
        System.out.println(list.get(14));

        list.remove(5);
        Object obj = 1000;
        Object obj2 = 6;
        list.remove(obj);
        list.remove(obj2);

        list.set(3, 298);

        list.add(2, 128);

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).toString() + " ");

        }


        List ll = new ArrayList();
        for (int i = 0; i < 10; i++) {
            ll.add(i);

        }

        for (Object i :
                ll) {
            ll.remove(i);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return 0 != size;
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

    private void rangeCheck(int index) {
        if (0 > index || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void ensureCapacity() {
        if (size == elementData.length) {
            Object[] newArray = new Object[size * 2 + 1];
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            elementData = newArray;
        }
    }

    @Override
    public boolean add(Object o) {
        ensureCapacity();
        elementData[size++] = o;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return true;
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
    public Object get(int index) {
        rangeCheck(index);
        return elementData[index];
    }

    @Override
    public Object set(int index, Object element) {
        rangeCheck(index);
        Object oldValue = elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, Object element) {
        rangeCheck(index);
        ensureCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
    }

    @Override
    public Object remove(int index) {
        rangeCheck(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return null;
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

}
