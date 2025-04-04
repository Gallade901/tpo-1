package main.algorithm;

class Node {
    int data;
    Node left;
    Node right;
    Node parent;
    boolean color; //true черный

    public Node(int data) {
        this.data = data;
        this.color = false; // Новые узлы всегда красные
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}

