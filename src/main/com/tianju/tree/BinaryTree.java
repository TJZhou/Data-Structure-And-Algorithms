package com.tianju.tree;

import com.tianju.ds.DSDeque;
import com.tianju.ds.DSLinkedList;

/**
 * Tianju Zhou
 * Jun 4, 2020
 */
public abstract class BinaryTree<K extends Comparable<K>, V> {

    static class TreeNode<K extends Comparable<K>, V> {
        K key;
        V val;
        TreeNode<K, V> left;
        TreeNode<K, V> right;
        int height;

        TreeNode(K key, V val) {
            if(key == null)
                throw new IllegalArgumentException("Key Cannot Be Null!");
            this.key = key;
            this.val = val;
        }
    }

    TreeNode<K, V> root;
    int size;

   public BinaryTree() {
        this.root = null;
        this.size = 0;
    }

    public BinaryTree(K key, V val) {
        this.size = 1;
        this.root = new TreeNode<>(key, val);
    }

    abstract V search(K key);

    abstract TreeNode<K, V> insert(K key, V val);

    abstract boolean delete(K key);

    public int height() {
        if(root == null) throw new NullPointerException("Tree is empty");
        return root.height;
    }

    public int height(TreeNode<K, V> n) {
        return n == null ? -1 : n.height;
    }

    protected void updateHeight(TreeNode<K, V> n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    public void bfs() {
        TreeNode<K, V> node = root;
        bfs(node);
    }

    public void bfs(TreeNode<K, V> node) {
        if(node == null) return;
        DSDeque<TreeNode<K, V>> dq = new DSLinkedList<>();
        dq.offer(node);
        while(!dq.isEmpty()) {
            int size = dq.size();
            for(int i = 0; i < size; i++) {
                TreeNode<K, V> n = dq.poll();
                /*
                 * do something for BFS
                 */
                if(n.left != null) dq.offer(n.left);
                if(n.right != null) dq.offer(n.right);
            }
        }
    }

    public void inOrder() {
        TreeNode<K, V> node = root;
        inOrder(node);
    }

    public void inOrder(TreeNode<K, V> node) {
        if(node == null) {
            return;
        }
        inOrder(node.left);
        /*
         * do something for in-order traversal
         */
        inOrder(node.right);
    }

    public void preOrder() {
        TreeNode<K, V> node = root;
        preOrder(node);
    }

    public void preOrder(TreeNode<K, V> node) {
        if(node == null) {
            return;
        }
        /*
          do something for pre-order traversal
         */
        preOrder(node.left);
        preOrder(node.right);
    }

    public void postOrder() {
        TreeNode<K, V> node = root;
        postOrder(node);
    }

    public void postOrder(TreeNode<K, V> node) {
        if(node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        /*
          do something for post-order traversal
         */
    }

    public TreeNode<K, V> getRoot() {
        if(root == null) throw new NullPointerException("Tree is empty!");
        return root;
    }

    public int size() {
        return size;
    }
}
