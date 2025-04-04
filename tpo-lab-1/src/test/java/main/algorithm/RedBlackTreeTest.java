package main.algorithm;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import static org.junit.Assert.*;

public class RedBlackTreeTest {
    @Test
    public void testEmptyTree() {
        RedBlackTree tree = new RedBlackTree();
        assertTrue(tree.verifyProperties());

        List<Integer> sequence = tree.getTraversalSequence();
        assertEquals(0, sequence.size());
    }

    @Test
    public void testSingleInsertion() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(5);

        assertTrue(tree.verifyProperties());
        List<Integer> sequence = tree.getTraversalSequence();
        assertEquals(1, sequence.size());
        assertEquals(5, sequence.get(0).intValue());
    }

    @Test
    public void testMultipleInsertions() {
        RedBlackTree tree = new RedBlackTree();
        int[] keys = {100, 75, 50};
        int black = 0;
        for (int key : keys) {
            tree.insert(key);
            assertTrue(tree.verifyProperties());

            List<Integer> sequence = tree.getTraversalSequence();
            boolean containsKey = false;
            for (Integer val : sequence) {
                if (val == key) {
                    containsKey = true;
                    break;
                }
            }
            assertTrue(containsKey);

            // Проверяем упорядоченность
            for (int i = 1; i < sequence.size(); i++) {
                assertTrue(sequence.get(i) > sequence.get(i-1));
            }
            if (key == 75) {
                black = tree.countBlackNodes(tree.root.left);
            }
            if (key == 50) {
                assertEquals(black, tree.countBlackNodes(tree.root.left));
            }
        }
    }

    @Test
    public void testBalancingProperty() {
        RedBlackTree tree = new RedBlackTree();
        int[] sortedKeys = {1, 2, 3, 4, 5};
        int size = 0;
        // Вставляем уже отсортированные данные для проверки балансировки
        for (int key : sortedKeys) {
            tree.insert(key);
            assertTrue(tree.verifyProperties());
            size++;
            List<Integer> sequence = tree.getTraversalSequence();
            assertEquals(size, sequence.size());

            // Проверяем количество черных узлов в левом и правом поддеревьях
            int blackLeft = tree.countBlackNodes(tree.root.left);
            int blackRight = tree.countBlackNodes(tree.root.right);
            assertEquals(blackLeft, blackRight);
        }
    }

    @Test
    public void testRedBlackProperties() {
        RedBlackTree tree = new RedBlackTree();
        int[] keys = {10, 20, 30, 40, 50};

        // Вставляем узлы и проверяем свойства Красно-черного дерева
        for (int key : keys) {
            tree.insert(key);
            assertTrue(tree.verifyProperties());
        }

        // Дополнительная проверка свойств после всех вставок
        assertTrue(tree.checkRedBlackProperties(tree.root));
    }

    @Test
    public void testFindExistingAndNonExisting() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);

        assertNotNull(tree.find(10));
        assertNotNull(tree.find(5));
        assertNull(tree.find(99));
    }

    @Test
    public void testDeleteLeaf() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);

        tree.delete(5);
        assertNull(tree.find(5));
        assertTrue(tree.verifyProperties());
    }


    @Test
    public void testBlackUAndLeft() {
        RedBlackTree tree = new RedBlackTree();
        int[] keys = {50, 75, 60};
        int black = 0;
        for (int key : keys) {
            tree.insert(key);
            assertTrue(tree.verifyProperties());

            List<Integer> sequence = tree.getTraversalSequence();
            boolean containsKey = false;
            for (Integer val : sequence) {
                if (val == key) {
                    containsKey = true;
                    break;
                }
            }
            assertTrue(containsKey);

            // Проверяем упорядоченность
            for (int i = 1; i < sequence.size(); i++) {
                assertTrue(sequence.get(i) > sequence.get(i-1));
            }
            if (key == 75) {
                black = tree.countBlackNodes(tree.root.left);
            }
            if (key == 60) {
                assertEquals(black, tree.countBlackNodes(tree.root.left));
            }
        }
    }

    @Test
    public void testLeftUAndRight() {
        RedBlackTree tree = new RedBlackTree();
        int[] keys = {100, 75, 80};
        int black = 0;
        for (int key : keys) {
            tree.insert(key);
            assertTrue(tree.verifyProperties());

            List<Integer> sequence = tree.getTraversalSequence();
            boolean containsKey = false;
            for (Integer val : sequence) {
                if (val == key) {
                    containsKey = true;
                    break;
                }
            }
            assertTrue(containsKey);

            // Проверяем упорядоченность
            for (int i = 1; i < sequence.size(); i++) {
                assertTrue(sequence.get(i) > sequence.get(i-1));
            }
            if (key == 75) {
                black = tree.countBlackNodes(tree.root.left);
            }
            if (key == 80) {
                assertEquals(black, tree.countBlackNodes(tree.root.left));
            }
        }
    }

    @Test
    public void testLeftAndURedRight() {
        RedBlackTree tree = new RedBlackTree();
        int[] keys = {100, 75, 125, 80};
        int black = 0;
        for (int key : keys) {
            tree.insert(key);
            assertTrue(tree.verifyProperties());

            List<Integer> sequence = tree.getTraversalSequence();
            boolean containsKey = false;
            for (Integer val : sequence) {
                if (val == key) {
                    containsKey = true;
                    break;
                }
            }
            assertTrue(containsKey);

            // Проверяем упорядоченность
            for (int i = 1; i < sequence.size(); i++) {
                assertTrue(sequence.get(i) > sequence.get(i-1));
            }
            if (key == 125) {
                black = tree.countBlackNodes(tree.root.left);
            }
            if (key == 80) {
                assertEquals(black + 1, tree.countBlackNodes(tree.root.left));
            }
        }
    }

    @Test
    public void testRightRotate() {
        RedBlackTree tree = new RedBlackTree();
        int[] keys = {10, 5, 15, 3, 18, 17};
        int black = 0;
        for (int key : keys) {
            tree.insert(key);
            assertTrue(tree.verifyProperties());

            List<Integer> sequence = tree.getTraversalSequence();
            boolean containsKey = false;
            for (Integer val : sequence) {
                if (val == key) {
                    containsKey = true;
                    break;
                }
            }
            assertTrue(containsKey);

            // Проверяем упорядоченность
            for (int i = 1; i < sequence.size(); i++) {
                assertTrue(sequence.get(i) > sequence.get(i-1));
            }
        }
    }


    @Test
    public void testDeleteAll() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(1);

        tree.delete(10);
        tree.delete(5);
        tree.delete(1);
        List<Integer> sequence = tree.getTraversalSequence();
        assertEquals(0, sequence.size());
        assertTrue(tree.verifyProperties());
    }
    @Test
    public void testDeleteRedWithNull() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(15);
        tree.insert(5);

        tree.delete(5);
        List<Integer> sequence = tree.getTraversalSequence();
        assertEquals(2, sequence.size());
        assertTrue(tree.verifyProperties());
        assertNull(tree.find(5));
    }
    @Test
    public void testDeleteBlackWithNull() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(15);
        tree.insert(5);
        tree.insert(1);
        tree.insert(3);

        tree.delete(15);
        List<Integer> sequence = tree.getTraversalSequence();
        assertEquals(4, sequence.size());
        assertTrue(tree.verifyProperties());
        assertNull(tree.find(15));
    }
    @Test
    public void testDeleteWithOneNull() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(15);
        tree.insert(1);
        tree.insert(3);

        tree.delete(1);
        List<Integer> sequence = tree.getTraversalSequence();
        assertEquals(3, sequence.size());
        assertTrue(tree.verifyProperties());
        assertNull(tree.find(1));

    }

    @Test
    public void testCountBlackNodes() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        int blackNodesLeft = tree.countBlackNodes(tree.root.left);
        int blackNodesRight = tree.countBlackNodes(tree.root.right);

        assertEquals(blackNodesLeft, blackNodesRight);
    }
    @Test
    public void testTransplantRoot() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);

        Node newRoot = tree.find(5);
        tree.transplant(tree.root, newRoot);

        assertEquals(5, tree.root.data);
    }

    @Test
    public void testMinimumInSubtree() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);

        Node min = tree.minimum(tree.root);
        assertEquals(3, min.data);
    }
    @Test
    public void testFixDeleteWithRedSibling() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
        tree.insert(12);
        tree.insert(20);

        tree.delete(3); // Брат (7) красный

        assertTrue(tree.verifyProperties());
    }

    @Test
    public void testFixDeleteWithBlackSiblingAndBlackChildren() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(20);

        tree.delete(5); // Брат (15) черный, оба ребенка — TNULL (черные)

        assertTrue(tree.verifyProperties());
    }

    @Test
    public void testFixDeleteWithBlackSiblingAndRedLeftChild() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(12); // Левый ребенок брата (15) красный

        tree.delete(5);

        assertTrue(tree.verifyProperties());
    }
    @Test
    public void testFixInsertWithRedUncle() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3); // Дядя (15) красный

        assertTrue(tree.verifyProperties());
    }

    @Test
    public void testFixInsertWithBlackUncleAndRightChild() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(7); // Дядя (TNULL) черный, узел — правый ребенок

        assertTrue(tree.verifyProperties());
    }

    @Test
    public void testFixInsertWithBlackUncleAndLeftChild() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(4); // Дядя (TNULL) черный, узел — левый ребенок

        assertTrue(tree.verifyProperties());
    }

    @Test
    public void testBlackRoot() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.root.color = false;
        assertFalse(tree.verifyProperties());
    }

    @Test
    public void testRedChildren() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.root.left.color = false;
        assertFalse(tree.verifyProperties());
    }

    //----------------------------------------------------------------

    @Test
    public void testFixDeleteWhenSiblingIsRed() {
        // Пример, когда удаляется узел и его брат красный (вызовет поворот)
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(1);
        tree.insert(6);
        tree.insert(12);
        tree.insert(17);

        tree.delete(1); // Удаление черного узла с красным братом
        assertTrue(tree.verifyProperties());
    }

    @Test
    public void testFixDeleteBlackSiblingWithTwoBlackChildren() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(1);  // левый лист
        tree.insert(6);  // правый от 5
        tree.insert(12);
        tree.insert(17);

        tree.delete(1);  // провоцирует случай с черным братом и черными детьми
        assertTrue(tree.verifyProperties());
    }

    @Test
    public void testFixDeleteBlackSiblingWithRedRightChild() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(1);
        tree.insert(6);
        tree.insert(12);
        tree.insert(17);
        tree.insert(20);  // чтобы у 15 был красный ребенок

        tree.delete(12); // провоцирует случай, где у брата красный правый ребенок
        assertTrue(tree.verifyProperties());
    }

    @Test
    public void testFixDeleteBlackSiblingWithRedLeftChild() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(1);
        tree.insert(6);
        tree.insert(12);
        tree.insert(17);
        tree.insert(13); // левый ребенок у 15

        tree.delete(17); // брат с красным левым ребенком
        assertTrue(tree.verifyProperties());
    }

    @Test
    public void testDeleteRoot() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.delete(10);
        assertNull(tree.root.parent);
        assertTrue(tree.root.color); // root должен быть черным
    }

    @Test
    public void testDeleteLeafNode() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        tree.delete(5);
        assertNull(tree.find(5));
        assertTrue(tree.verifyProperties());
    }

    @Test
    public void testDeleteNodeWithOneChild() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(1); // делает 5 узлом с одним ребенком

        tree.delete(5);
        assertNull(tree.find(5));
        assertTrue(tree.verifyProperties());
    }

    @Test
    public void testDeleteNodeWithTwoChildren() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(12);
        tree.insert(17);

        tree.delete(15);
        assertNull(tree.find(15));
        assertTrue(tree.verifyProperties());
    }

    @Test
    public void testNodeWithMinimumDepthMoreOne() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(100);
        tree.insert(150);
        tree.insert(125);
        tree.insert(25);
        tree.insert(12);
        tree.insert(50);
        tree.insert(75);
        tree.insert(45);

        tree.delete(25);
        assertNull(tree.find(25));
        assertTrue(tree.verifyProperties());
    }

    @Test
    public void testRightBlackNodeWithBrotherDifferentColor() {
        RedBlackTree tree = new RedBlackTree();
        int[] keys = {100, 75, 125, 80, 40, 214,46,346,3,57};
        int black = 0;
        for (int key : keys) {
            tree.insert(key);
            assertTrue(tree.verifyProperties());
        }
        for (int key : keys) {
            tree.delete(key);
            assertTrue(tree.verifyProperties());
        }
        assertTrue(tree.verifyProperties());
    }
}