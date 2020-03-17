package collections.list.assignment;

public class MyLinkedList<E> {
    // add, remove, get, contains, get
    private int size;
    private Node head, tail;  // the first element in this list.
//    private Node tail;  // the last element in this list.

    // I use this Node class only in this MyLinkedList.
    // "static class Node" vs "class Node"
    // Non-static class can be used by every instances from MyLinkedList.

    // static inner class: nested class ( This! )
    // non static inner class: inner class
    private static class Node<E> {
        E data;
        Node next;
        Node prev;

        Node(E data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

    }
//    Following is same as the top codes.
//    public MyLinkedList() {
//        Node d = new Node();
//    }


//    public MyLinkedList() {
//        head = null;
//        size = 0;  // we don't have to do this originally.
//    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean contains(E data) {
        // TODO
        if (!isEmpty()) {
            Node node = head;
            for (int k = 0; k < size; k++) {
                if (node == data) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    public void add(E data) {
        // TODO
        // We have to identify if this data is the first element or not.
        Node node = new Node(data, null, null);

        // 1.) first element
        if (isEmpty()) {
            head = node;
        } else {
            // 2.) not first element
            // - 1. got to the last node.
            // - 2. then make the last node point to the new node
            tail.next = node;

//            #########今回は要らない？
//            node.prev = tail;
        }
        tail = node;
        size++;
    }

    public void add(int i, E data) {
        // TODO
        checkBounds(i);
        Node node = new Node(data, null, null);
        // 最初の場合
        if (i == 0) {
            head.prev = node;
            head = node;
            // 最後の場合
        } else if (i == size - 1) {
            tail.next = node;
            tail = node;
            // 間の場合
        } else if (i < size / 2) {
            Node prev = head;
            for (int k = 0; k < i; k++) {
                prev = prev.next;
            }
            node.next = prev.next;
            node.prev = prev;
            prev.next.prev = node;
            prev.next = node;
        } else {
            Node prev = tail;
            for (int k = size; k > i; k--) {
                prev = prev.prev;
            }
            node.prev = prev;
            node.next = prev.next;
            prev.next.prev = node;
            prev.next = node;
        }
    }


    public E remove(int index) {
        checkBounds(index);
        if (index < size / 2) {
            Node prev = head;
            for (int k = 0; k < index; k++) {
                prev = prev.next;
            }
            prev.next.prev = prev.prev;
            prev.prev.next = prev.next;
            prev.prev = null;
            prev.next = null;
            return (E) prev;
        } else {
            Node prev = tail;
            for (int k = size - 1; k > index; k--) {
                prev = prev.prev;
            }
            prev.prev.next = prev.next;
            prev.next.prev = prev.prev;
            prev.prev = null;
            prev.next = null;
            return (E) prev;
        }
    }

    public E get(int i) {
        Node temp = head;
        for (int k = 0; k < i; k++) {
            temp = temp.next;
        }
        return (E) temp.data;

    }

    public E set(int i, Object newData) {
        // Missing point(now ) = size and i
        checkBounds(i);
        Node prev = head;
        for (int k = 0; k < i - 1; k++) {
            prev = prev.next;
        }
        Object middleData = prev.next.data;
        Node next = prev.next.next;
        Node newNode = new Node(newData, null,null);
        prev.next = newNode;
        newNode.next = next;
        return (E) middleData;
    }

    private void checkBounds(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + ", size: " + size);
        }
    }


    // datatype = String?????
        @Override
        public String toString() {
            // TODO
            // ex) "Obj0"=="Obj1"==null
            Node node = head;
            String s = "";
            while( node != null) {
                s += node.data.toString() + "=";
                node = node.next;
            }
            s += "null";
            return s;
        }
    }
