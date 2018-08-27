package heap;

/**
 * descriptions
 *
 * @author baham
 * @since 1.0.0
 */
public class Main {

    public static void main(String... args) {

    }

    static class PriorityQueue<T> {
        public void enqueue(T value, int priority) {

        }

        public T dequeue() {
            T ret=null;
            return ret;
        }
    }

    static class Element<T> {
        T value;
        int priority;
        Element parent;
        Element left;
        Element right;
        Element lChild;
        Element rChild;

        public Element() {
        }
        public Element(T value, int priority) {
            this.value = value;
            this.priority = priority;
        }
    }

    static class Heap<T> {
        Element<T> root;
        Element<T> last;
        int count;

        public void add(T value, int priority) {
            if (root==null) {
                Element<T> ret = new Element<>(value, priority);
                root = ret;
                last = ret;
                count++;
                return;
            }
            
        }

        public T pop() {
            T ret = null;
            return ret;
        }
    }
}
