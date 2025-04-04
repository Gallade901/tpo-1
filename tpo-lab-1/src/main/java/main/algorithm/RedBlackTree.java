package main.algorithm;

import java.util.ArrayList;
import java.util.List;

public class RedBlackTree {
    public Node root;
    private final Node TNULL; // Сентинельный узел для null-ссылок

    public RedBlackTree() {
        TNULL = new Node(Integer.MIN_VALUE);
        TNULL.color = true; // Черный цвет для sentinal
        root = TNULL;
    }

    public void insert(int key) {
        Node node = new Node(key); // 50 black --> 25 red --> 12/40 red
        node.parent = null;
        node.left = TNULL;
        node.right = TNULL;
        node.color = false; // Новый узел красный

        Node y = null;
        Node x = root;

        while (x != TNULL) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = true; // Корень всегда черный
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    private void fixInsert(Node k) {
        Node u;
        while (k.parent.color == false) { // Пока родитель красный
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == false) {
                    u.color = true;
                    k.parent.color = true;
                    k.parent.parent.color = false;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = true;
                    k.parent.parent.color = false;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;
                if (u.color == false) { //Красный
                    u.color = true;
                    k.parent.color = true;
                    k.parent.parent.color = false;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = true;
                    k.parent.parent.color = false;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = true;
    }

    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
//        if (y.left != TNULL) {
//            y.left.parent = x;
//        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node y) {
        Node x = y.left; // 17
        y.left = x.right;
//        if (x.right != TNULL) {
//            x.right.parent = y;
//        }
        x.parent = y.parent; //
        if (y.parent == null) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }
        x.right = y;
        y.parent = x;
    }

    public boolean verifyProperties() {
        return checkRedBlackProperties(root) && countBlackNodes(root.left) == countBlackNodes(root.right);
    }

    public boolean checkRedBlackProperties(Node node) {
        if (node == TNULL) return true;

        // Проверка свойства 1: корень черный
        if (node.parent == null && node.color != true) {
            System.out.println("Нарушено свойство 1: корень должен быть черным");
            return false;
        }

        // Проверка свойства 3: красный узел не может иметь красных детей
        if (node.color == false &&
                ((node.left != TNULL && node.left.color == false) ||
                        (node.right != TNULL && node.right.color == false))) {
            System.out.println("Нарушено свойство 3: красный узел имеет красного ребенка");
            return false;
        }

        return checkRedBlackProperties(node.left) && checkRedBlackProperties(node.right);
    }

    public int countBlackNodes(Node node) {
        if (node == TNULL || root == TNULL) return 0;
        return (node.color == true ? 1 : 0) + countBlackNodes(node.left);
    }

    public List<Integer> getTraversalSequence() {
        List<Integer> sequence = new ArrayList<>();
        inOrderTraversal(root, sequence);
        return sequence;
    }

    private void inOrderTraversal(Node node, List<Integer> sequence) {
        if (node != TNULL) {
            inOrderTraversal(node.left, sequence);
            sequence.add(node.data);
            inOrderTraversal(node.right, sequence);
        }
    }

    public Node find(int key) {
        Node current = root;
        while (current != TNULL && current.data != key) {
            if (key < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current == TNULL ? null : current;
    }

    public void transplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    public Node minimum(Node node) {
        while (node.left != TNULL) {
            node = node.left;
        }
        return node;
    }

    public void delete(int key) {
        Node z = find(key);
        if (z == null) return;

        Node y = z;
        Node x;
        boolean yOriginalColor = y.color;

        if (z.left == TNULL) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == TNULL) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (yOriginalColor == true) {
            fixDelete(x);
        }
    }

    private void fixDelete(Node x) {
        Node s;
        while (x != root && x.color == true) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color == false) {
                    s.color = true;
                    x.parent.color = false;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == true && s.right.color == true) {
                    s.color = false;
                    x = x.parent;
                } else {
                    if (s.right.color == true) {
                        s.left.color = true;
                        s.color = false;
                        rightRotate(s);
                        s = x.parent.right;
                    }

                    s.color = x.parent.color;
                    x.parent.color = true;
                    s.right.color = true;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color == false) {
                    s.color = true;
                    x.parent.color = false;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }

                if (s.right.color == true && s.left.color == true) {
                    s.color = false;
                    x = x.parent;
                } else {
                    if (s.left.color == true) {
                        s.right.color = true;
                        s.color = false;
                        leftRotate(s);
                        s = x.parent.left;
                    }

                    s.color = x.parent.color;
                    x.parent.color = true;
                    s.left.color = true;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = true;
    }


}