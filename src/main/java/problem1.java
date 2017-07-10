/**
 * descriptions
 *
 * @author baham
 * @since 1.0.0
 */
public class problem1 {

    public static void main(String... args) {

    }

    static class PriorityQueue<T> {
        Heap elements;
        public void enqueue(T element) {

        }

        public T dequeue() {
            T ret = (T) elements.popRoot();
            return ret;
        }
    }

    static class Heap<T> {
        Node rootNode;

        public T popRoot() {
            T ret = null;
            return ret;
        }
        public void add(T element) {

        }

        private void reorderNodefromBottom(Node node) {

        }

        private void reorderNodefromRoot() {

        }
    }

    static class Node<T> {
        Node parent;
        Node left;
        Node right;
        T element;

        public Node(T element, Node parent, Node left, Node right) {
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }
        public Node getLeft() {
            return left;
        }
        public Node getRight() {
            return right;
        }
        public T getElement() {
            return element;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
        public void setLeft(Node left) {
            this.left = left;
        }
        public void setRight(Node right) {
            this.right = right;
        }
        public void setElement(T element) {
            this.element = element;
        }
    }
}
