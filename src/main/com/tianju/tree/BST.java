package com.tianju.tree;

/**
 * Tianju Zhou
 * Jun 5, 2020
 */
public class BST<K extends Comparable<K>, V> extends BinaryTree<K, V> {

    public BST() {
        super();
    }

    public BST(K key, V val) {
        super(key, val);
    }

    public V search(K key) {
        TreeNode<K, V> node = root;
        return searchHelper(node, key);
    }

    private V searchHelper(TreeNode<K, V> n, K key) {
        if(n == null) return null;
        if(key.compareTo(n.key) == 0) {
            return n.val;
        } else if(key.compareTo(n.key) < 0) {
            return searchHelper(n.left, key);
        } else {
            return searchHelper(n.right, key);
        }
    }

    // Return the inserted node. If exists, update original val
    public TreeNode<K, V> insert(K key, V val) {
        if(root == null) {
            size++;
            root = new TreeNode<>(key, val);
            updateHeight(root);
            return root;
        }
        TreeNode<K, V> node = root;
        return insertHelper(node, key, val);
    }

    protected TreeNode<K, V> insertHelper(TreeNode<K, V> n, K key, V val) {
        if(n == null) {
            size++;
            return new TreeNode<>(key, val);
        } else if(key.compareTo(n.key) < 0) {
            n.left = insertHelper(n.left, key, val);
        } else if(key.compareTo(n.key) > 0) {
            n.right = insertHelper(n.right, key, val);
        } else {
            n.val = val;
        }
        updateHeight(n);
        return n;
    }

    // only return true if element t exists in BST
    public boolean delete(K key) {
        TreeNode<K, V> node = root;
        int prevSize = size;
        root = deleteHelper(node, key);
        return prevSize != size;
    }

    protected TreeNode<K, V> deleteHelper(TreeNode<K, V> n, K key) {
        if(n == null) return null;
        if(key.compareTo(n.key) < 0) {
            n.left = deleteHelper(n.left, key);
        } else if(key.compareTo(n.key) > 0) {
            n.right = deleteHelper(n.right, key);
        } else {
            if(n.left == null || n.right == null) {
                size--;
                n = (n.left == null) ? n.right : n.left;
            } else {
                // assign the <K,V> of successor node to the root node
                TreeNode<K, V> successor = successor(n);
                n.val = successor.val;
                n.key = successor.key;
                // Then delete the successor
                n.right = deleteHelper(n.right, successor.key);
            }
        }
        // if n is the successor, then n is null coz the successor has been deleted
        if(n != null)
            updateHeight(n);
        return n;
    }

    public TreeNode<K, V> min() {
        TreeNode<K, V> n = root;
        while(n.left != null)
            n = n.left;
        return n;
    }

    public TreeNode<K, V> max() {
        TreeNode<K, V> n = root;
        while(n.right != null)
            n = n.right;
        return n;
    }

    public TreeNode<K, V> predecessor(TreeNode<K, V> n) {
        if(n == null) throw new NullPointerException("Node is empty");
        n = n.left;
        while(n != null && n.right != null)
            n = n.right;
        return n;
    }

    public TreeNode<K, V> successor(TreeNode<K, V> n) {
        if(n == null) throw new NullPointerException("Node is empty");
        n = n.right;
        while(n != null && n.left != null)
            n = n.left;
        return n;
    }
}
