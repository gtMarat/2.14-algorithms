package com.example.demo;

import java.util.Arrays;

import static com.example.demo.SortMethods.selectionSortMethod;
import static java.util.Arrays.binarySearch;

public class IntegerListImpl implements IntegerList {

    private final Integer[] storage;
    private int size;

    public IntegerListImpl () {
        storage = new Integer[10] ;
    }

    public IntegerListImpl(int inItSize) {
        storage = new Integer[inItSize];
    }


    @Override

    public Integer add(Integer item) {
       validateSize();
       validateItem(item);
        storage[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        validateSize();
        validateItem(item);
        validateIndex(index);

        if (index == size) {
            storage[size++] = item;
            return item;
        }

        System.arraycopy(storage,index,storage,index+1,size -index);
        storage[index] = item;
        size++;

        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        storage[index]= item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);

        int index = indexOf(item);

        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);

        Integer item = storage[index];


        if (index != size) {
            System.arraycopy(storage,index + 1,storage , index , size -index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        return binarySearch(selectionSortMethod(toArray()), item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }

        }

        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size-1; i >=0 ; i--) {
            if (storage[i].equals(item)) {
                return i;
            }

        }

        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(),otherList.toArray());
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
    public void clear() {
        size = 0;

    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage,size);
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void validateSize() {
        if (size == storage.length) {
            throw new StorageIsFullException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }
    private boolean binarySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(arr[mid])) {
                return true;
            }
            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}
