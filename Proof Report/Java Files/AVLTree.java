public class AVLTree<T extends Comparable<T>> {

    private class Node {
        T data;
        int height;
        Node left;
        Node right;

        public Node(T data) {
            this.data = data;
            this.height = 1;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public AVLTree() {
        this.root = null;
    }

    public void insert(T data) {
        root = insertHelper(root, data);
    }

    private Node insertHelper(Node node, T data) {
        if (node == null) {
            return new Node(data);
        }
        if (data.compareTo(node.data) < 0) {
            node.left = insertHelper(node.left, data);
        } else {
            node.right = insertHelper(node.right, data);
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        if (balance > 1 && data.compareTo(node.left.data) < 0) {
            return rightRotate(node);
        }
        if (balance < -1 && data.compareTo(node.right.data) > 0) {
            return leftRotate(node);
        }
        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node rightRotate(Node node) {
        Node newRoot = node.left;
        if (newRoot == null) {
            return node;
        }
        node.left = newRoot.right;
        newRoot.right = node;

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));

        return newRoot;
    }

    private Node leftRotate(Node node) {
        Node newRoot = node.right;
        if (newRoot == null) {
            return node;
        }
        node.right = newRoot.left;
        newRoot.left = node;

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));

        return newRoot;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    public boolean search(T data) {
        Node node = searchHelper(root, data);
        return (node != null);
    }

    private Node searchHelper(Node node, T data) {
        if (node == null || node.data.equals(data)) {
            return node;
        }
        if (data.compareTo(node.data) < 0) {
            return searchHelper(node.left, data);
        }
        return searchHelper(node.right, data);
    }
}