package com.tianju.tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tianju Zhou
 * Jun 13, 2020
 */
public class AVLTest {

    @Test
    public void AVLTreeInsertTest() {
        AVL<Integer, String> avl = new AVL<>();
        Assertions.assertThrows(NullPointerException.class, avl::getRoot);
        Assertions.assertThrows(NullPointerException.class, avl::height);
        Assertions.assertThrows(NullPointerException.class, avl::max);
        Assertions.assertThrows(NullPointerException.class, avl::min);

        avl.insert(10,"ten");
        Assertions.assertEquals(avl.size(), 1);
        Assertions.assertEquals(avl.getRoot().val, "ten");
        Assertions.assertEquals(0, avl.height());
        Assertions.assertEquals("ten", avl.max().val);
        Assertions.assertEquals("ten", avl.min().val);

        avl.insert(6,"six");
        Assertions.assertEquals(avl.size(), 2);
        Assertions.assertEquals(1, avl.height());
        Assertions.assertEquals("ten", avl.max().val);
        Assertions.assertEquals("six", avl.min().val);

        avl.insert(12,"twelve");
        Assertions.assertEquals(avl.size(), 3);
        Assertions.assertEquals(1, avl.height());
        Assertions.assertEquals("twelve", avl.max().val);
        Assertions.assertEquals("six", avl.min().val);

        avl.insert(8,"eight");
        Assertions.assertEquals(avl.size(), 4);
        Assertions.assertEquals(2, avl.height());

        avl.insert(11,"eleven");
        Assertions.assertEquals(avl.size(), 5);
        Assertions.assertEquals(2, avl.height());

        /*
         * current tree
         *      10
         *     /  \
         *    6   12
         *    \   /
         *     8 11
         *
         * After add 7
         *
         *      10
         *     /  \
         *    6   12
         *    \   /
         *     8 11
         *    /
         *   7
         *  need to do the height rebalance
         **/
        avl.insert(7, "seven");
        Assertions.assertEquals(avl.size(), 6);
        // height should be 2 after rebalance
        Assertions.assertEquals(2, avl.height());

        Assertions.assertEquals(avl.search(8), "eight");
        Assertions.assertNull(avl.search(100));

        avl.insert(12, "new twelve");
        Assertions.assertEquals(6, avl.size());
        Assertions.assertEquals(2, avl.height());
        Assertions.assertEquals(avl.search(12), "new twelve");
    }

    @Test
    public void AVLTreeDeleteTest() {
        AVL<Integer, String> avl = new AVL<>();
        avl.insert(10, "ten");
        avl.insert(6, "six");
        avl.insert(12, "twelve");
        avl.insert(8, "eight");
        avl.insert(11, "eleven");
        avl.insert(7, "seven");

        /*
         * current tree
         *      10
         *     /  \
         *    7   12
         *   /\   /
         *  6 8  11
         **/
        avl.delete(11);
        Assertions.assertEquals(avl.size(), 5);
        Assertions.assertNull(avl.search(11));
        Assertions.assertEquals(avl.getRoot().val, "ten");
        Assertions.assertEquals(avl.height(), 2);

        /*
         * After deleting 11 and 12
         *      10
         *     /
         *    7
         *   /\
         *  6 8
         *
         * Need to do the height rebalance
         *      8
         *     / \
         *    7  10
         *   /
         *  6
         **/
        avl.delete(12);
        Assertions.assertEquals(avl.size(), 4);
        Assertions.assertNull(avl.search(11));
        Assertions.assertEquals(avl.getRoot().val, "eight");
        Assertions.assertEquals(avl.height(), 2);
        Assertions.assertFalse(avl.delete(20));

        Assertions.assertTrue(avl.delete(6));
        Assertions.assertEquals(avl.size(), 3);
        Assertions.assertEquals(avl.height(), 1);

        Assertions.assertTrue(avl.delete(8));
        Assertions.assertEquals(avl.height(), 1);
        Assertions.assertEquals(avl.size(), 2);

        Assertions.assertTrue(avl.delete(10));
        Assertions.assertEquals(avl.height(), 0);
        Assertions.assertEquals(avl.size(), 1);

        Assertions.assertTrue(avl.delete(7));
        Assertions.assertEquals(avl.size(), 0);
        Assertions.assertThrows(NullPointerException.class, avl::height);
        Assertions.assertThrows(NullPointerException.class, avl::getRoot);
    }
}
