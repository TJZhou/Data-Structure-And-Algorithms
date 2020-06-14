package com.tianju.tree;

/**
 * Tianju Zhou
 * June 13, 2020
 * Built balanced AVL tree based on BST.
 * Add rotate and rebalance function
 * refer to: https://www.baeldung.com/java-avl-trees
 */
public class AVL<K extends Comparable<K>,V> extends BST<K,V> {

    public AVL() {
        super();
    }

    public AVL(K key, V val) {
        super(key, val);
    }

    private TreeNode<K,V> rotateRight(TreeNode<K,V> y) {
        TreeNode<K,V> x = y.left;
        TreeNode<K,V> z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private TreeNode<K,V> rotateLeft(TreeNode<K,V> y) {
        TreeNode<K,V> x = y.right;
        TreeNode<K,V> z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private TreeNode<K, V> rebalance(TreeNode<K, V> z) {
        int balance = getBalance(z);
        if (balance > 1) {
            if (z.right != null && height(z.right.right) <= height(z.right.left))
                z.right = rotateRight(z.right);
            z = rotateLeft(z);
        } else if (balance < -1) {
            if (z.left != null && height(z.left.left) <= height(z.left.right))
                z.left = rotateLeft(z.left);
            z = rotateRight(z);
        }
        return z;
    }

    private int getBalance(TreeNode<K, V> n) {
        return n == null ? 0 : (super.height(n.right) - super.height(n.left));
    }

    @Override
    protected TreeNode<K, V> insertHelper(TreeNode<K, V> n, K key, V val) {
        return rebalance(super.insertHelper(n, key, val));
    }

    @Override
    public TreeNode<K, V> deleteHelper(TreeNode<K, V> n, K key) {
        TreeNode<K, V> node = super.deleteHelper(n, key);
        if(node != null)
            node = rebalance(node);
        return node;
    }
}
