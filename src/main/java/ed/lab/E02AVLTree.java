package ed.lab;

import java.util.Comparator;

public class E02AVLTree<T> {
    private final Comparator<T> comparator;
    private int size;
    private Node<T>root;

    public E02AVLTree(Comparator<T> comparator) {
        this.comparator = comparator;
        this.size=0;
        this.root=null;
    }
    public void insert(T value) {
        this.root = insert(root, value);
    }
    public void delete(T value) {
        this.root = delete(root, value);
    }
    public T search(T value){
        return search(root,value);
    }

    private T search(Node<T>root, T value) {
        Node<T> nodActual = root;
        if (root == null) return null;
        int cmp = comparator.compare(value, root.value);
        if (cmp < 0) {return search(root.left, value);}
        else if (cmp > 0) {return search(root.right, value);}
        else
            return root.value;
    }
    public int height() {
        return getHeight(root);
    }
    public int size() {
        return size;
    }
    private Node<T> insert(Node<T> root, T value) {
        if (root == null) {
            size++;
            return new Node<>(value);
        }
        int compare = comparator.compare(value, root.value);
        if (compare < 0) {
            root.left = insert(root.left, value);
        } else if (compare > 0) {
            root.right = insert(root.right, value);
        } else {
            return root;
        }
        return balance(root);
    }
    private Node<T> delete(Node<T> root, T value) {
        if (root == null) return null;
        int compare = comparator.compare(value, root.value);
        if (compare < 0) {
            root.left = delete(root.left, value);
        } else if (compare > 0) {
            root.right = delete(root.right, value);
        } else {
            this.size--;
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            Node<T> successor = findMin(root.right);
            root.value = successor.value;
            this.size++;
            root.right = delete(root.right, successor.value);
        }
        return balance(root);
    }
    private Node<T> findMin(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    private Node<T> balance(Node<T> root) {
        updateHeight(root);
        int balance = getBalance(root);
        if (balance > 1 && getBalance(root.left) >= 0)
            return rotateRight(root);
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        if (balance< -1 && getBalance(root.right) <= 0)
            return rotateLeft(root);
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }
        return root;
    }
    private Node<T> rotateLeft(Node<T> root) {
        if (root == null) return null;
        Node<T> newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        updateHeight(root);
        updateHeight(newRoot);
        return newRoot;
    }

    private Node<T> rotateRight(Node<T> root) {
        if (root == null) return null;
        Node<T> newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        updateHeight(root);
        updateHeight(newRoot);
        return newRoot;
    }
    private void updateHeight(Node<T> root) {
        if (root == null) return;
        root.height = 1 + Math.max(getHeight(root.left),
                getHeight(root.right));
    }
    private int getHeight(Node<T> node) {
        return node == null ? 0 : node.height;
    }
    private int getBalance(Node<T> node) {
        return node == null ? 0 : getHeight(node.left) -
                getHeight(node.right);
    }
    static class Node<T> {
        protected T value;
        protected Node<T> left;
        protected Node<T> right;
        protected int height;
        public Node(T value) {
            this.value = value;
            this.height = 1;
        }
    }










}
