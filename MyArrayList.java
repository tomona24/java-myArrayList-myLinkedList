package collections.list.assignment;

import java.util.*;

// "generic" type <E> : Element
public class MyArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    // <? extends E> -> any type that extends E
    public MyArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO
        if (size > a.length) {
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        }
        System.arraycopy(elementData, 0, a, 0, size);
        if (size < a.length) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        if (size == elementData.length) {
            // grow (increases by 50%)
            elementData = grow(size + 1);
        }
        elementData[size] = e;
        size++;
        return true;
    }

    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData, newCapacity(minCapacity));
    }

    /*
    The maximum size of array to allocate.
    Attempts to allocate larger arrays "may" result in OutOfMemoryOrder
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * @param minCapacity the desired minimum capacity
     * @return
     */
    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity <= minCapacity) {
            if (minCapacity < 0 || minCapacity > MAX_ARRAY_SIZE) {
                throw new OutOfMemoryError("integer overflow");
            }
            return minCapacity;
        }
        return (newCapacity <= MAX_ARRAY_SIZE) ? newCapacity : Integer.MAX_VALUE;
    }

    @Override
    public boolean remove(Object o) {
        // TODO
        if (contains(o)) {
            int index = 0;
            int firstSize = size;
            for (int i = 0; i < firstSize; i++) {
                if (elementData[i] == o) {
                    index = i;
                    break;
                }
            }
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO
        int count = 0;
        for (Object item : c) {
            for (Object e : elementData) {
                if (e == item) {
                    count++;
                    break;
                }
            }
        }
        if (count == c.size()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        // TODO
        if (size + c.size() > elementData.length) {
            // grow (increases by 50%)
            elementData = grow(c.size());
        }
        for (E item : c) {
            elementData[size] = item;
            size++;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        // TODO
        if (size + c.size() > elementData.length) {
            // grow (increases by 50%)
            elementData = grow(c.size());
        }
        int lastNewIndex = size + c.size() - 1;
        int lastIndex = size - 1;
        for (int i = lastNewIndex; i > index; i--) {
            elementData[lastNewIndex] = elementData[lastIndex];
            lastNewIndex--;
            lastIndex--;
        }
        for (E item : c) {
            elementData[index] = item;
            index++;
            size++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO
        for (Object item : c) {
            while (contains(item)) {
                remove(item);
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO
        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(elementData[i])) {
                    remove(elementData[i]);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        // TODO
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + ", size: " + size);
        }
        return (E) elementData[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + ", size: " + size);
        }
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        // TODO
        if (size == elementData.length) {
            // grow (increases by 50%)
            elementData = grow(size + 1);
        }
        for (int i = size; i >= index + 1; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = element;
        size++;
    }


    @Override
    public E remove(int index) {
        // TODO
        int firstSize = size;
        E removeE = (E) elementData[index];
        for (int k = index; k < firstSize - 1; k++) {
            elementData[k] = elementData[k + 1];
        }
        elementData[firstSize - 1] = null;
        size--;

        return removeE;
    }

    @Override
    public int indexOf(Object o) {
        // TODO
        for (int i = 0; i < size; i++) {
            if (elementData[i] == o) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO
        int last = 0;

        if (contains(o)) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == o) {
                    last = i;
                }
            }
            return last;
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        // TODO
        List<E> newList = new MyArrayList<E>(toIndex - fromIndex);
        for (int i = fromIndex; i <= toIndex; i++) {
            newList.add((E) elementData[i]);
        }
        return newList;
    }

    @Override
    public String toString() {
        Object[] arr = new Object[size];
        int counter = 0;
        for (int i = 0; i < size; i++){
            if (elementData[i] != null) {
                arr[counter] =  elementData[i];
                counter++;
            }
        }
        return Arrays.toString(arr) ;
    }
}
