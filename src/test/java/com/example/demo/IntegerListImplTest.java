package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegerListImplTest {

    public static final Integer ITEM_1 = 1;
    public static final Integer ITEM_2 = 2;
    public static final Integer ITEM_3 = 3;
    public static final Integer ITEM_4 = 4;
    public static final Integer ITEM_5 = 5;
    public static final Integer NOT_ADDED_ITEM = 125;


    private IntegerList IntegerList;

    @BeforeEach
    public void setUp() {
        IntegerList = new IntegerListImpl();
        IntegerList.add(ITEM_2);
        IntegerList.add(ITEM_4);
    }

    @Test
    public void addItemsToEnd() {
        assertThat(IntegerList.toArray())
                .isEqualTo(new Integer[]{ITEM_2, ITEM_4});
    }

    @Test
    public void addItemsByIndex() {
        IntegerList.add(0, ITEM_1);
        IntegerList.add(2, ITEM_3);
        IntegerList.add(4, ITEM_5);

        assertThat(IntegerList.toArray())
                .isEqualTo(List.of(ITEM_1, ITEM_2, ITEM_3, ITEM_4, ITEM_5).toArray());
    }

    @Test
    public void addItemsByIndexException() {
        assertThatExceptionOfType(InvalidIndexException.class)
                .isThrownBy(() -> IntegerList.add(3, ITEM_3));
    }

    @Test
    public void addNullItem() {
        assertThatExceptionOfType(NullItemException.class)
                .isThrownBy(() -> IntegerList.add(null));


        assertThatExceptionOfType(NullItemException.class)
                .isThrownBy(() -> IntegerList.add(2, null));
    }

    @Test
    public void setItem() {
        IntegerList.set(0, ITEM_1);
        IntegerList.set(1, ITEM_2);
        assertThat(IntegerList.toArray())
                .isEqualTo(List.of(ITEM_1, ITEM_2).toArray());
    }

    @Test
    public void setItemNegative() {
        assertThatExceptionOfType(NullItemException.class)
                .isThrownBy(() -> IntegerList.set(0, null));

        assertThatExceptionOfType(InvalidIndexException.class)
                .isThrownBy(() -> IntegerList.set(-5, NOT_ADDED_ITEM));
    }

    @Test
    public void removeByItem() {
        Integer actual = IntegerList.remove(ITEM_4);
        assertEquals(ITEM_4, actual);
        assertThat(IntegerList.toArray())
                .isEqualTo(new Integer[]{ITEM_2});
    }

    @Test
    public void removeByIndex() {
        IntegerList.remove(0);
        assertThat(IntegerList.toArray())
                .isEqualTo(new Integer[]{ITEM_4});
    }

    @Test
    public void removeByItemNegative() {
        assertThatExceptionOfType(InvalidIndexException.class)
                .isThrownBy(() -> IntegerList.remove(NOT_ADDED_ITEM));
    }

    @Test
    public void containOrNotContain() {
        assertFalse(IntegerList.contains(ITEM_1));
        Integer actual = IntegerList.add(ITEM_1);
        assertEquals(ITEM_1, actual);
        assertTrue(IntegerList.contains(ITEM_1));
    }

    @Test
    public void lastIndexOf() {
        assertEquals(IntegerList.lastIndexOf(ITEM_2), 0);
        assertEquals(IntegerList.lastIndexOf(NOT_ADDED_ITEM), -1);
    }

    @Test
    public void getItem() {
        assertEquals(ITEM_2, IntegerList.get(0));
        assertEquals(ITEM_4, IntegerList.get(1));
    }

    @Test
    public void listEquals() {
        IntegerList s2 = new IntegerListImpl();
        s2.add(0, ITEM_1);
        s2.add(1, ITEM_2);
        s2.add(2, ITEM_3);
        assertFalse(IntegerList.equals(s2));
        IntegerList.add(0, ITEM_1);
        IntegerList.add(2, ITEM_3);
        IntegerList.remove(ITEM_4);
        assertTrue(IntegerList.equals(s2));
    }

    @Test
    public void sizeBeforeAndAfterClearing() {
        assertEquals(2, IntegerList.size());
        IntegerList.clear();
        assertTrue(IntegerList.isEmpty());
    }
}

