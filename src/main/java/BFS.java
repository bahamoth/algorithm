import java.util.Scanner;

/**
 * descriptions
 *
 * @author baham
 * @since 1.0.0
 */
public class BFS {
    int n;
    int m;
    char[][] maze;

    void setUp() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        maze = new char[n][m];
        for (int i = 0; i < n; ++i) {
            maze[i] = scanner.next().toCharArray();
        }
    }

    void setUp(boolean isTest) {
        if (isTest == false)
            setUp();
        else {
            n = 4;
            m = 6;
            maze = new char[n][m];
            maze[0] = new char[]{'1', '0', '1', '1', '1', '1'};
            maze[1] = new char[]{'1', '0', '1', '0', '1', '0'};
            maze[2] = new char[]{'1', '0', '1', '0', '1', '1'};
            maze[3] = new char[]{'1', '1', '1', '0', '1', '1'};
        }
    }

    int solve() {
       int ret = 0;
       int distance = 0;

       Queue<Point> searchQueue = new Queue<>();
       Queue<Point> visitedQueue = new Queue<>();




       return ret;
    }

    int visit(Point current, Point destination, Queue<Point> vQueue, int distance) {
        int ret = 0;

        return ret;
    }



    public static void main(String... args) {
        BFS sol = new BFS();
        sol.setUp(true);

        Queue<Point> queue = new Queue<>(new Point(0,0));
        queue.enqueue(new Point(1,1));
        queue.enqueue(new Point(2,2));
        queue.enqueue(new Point(3,3));
        System.out.println(queue.dequeue().toString()+queue.count);
        System.out.println(queue.dequeue().toString()+queue.count);
        System.out.println(queue.dequeue().toString()+queue.count);

        System.out.println(sol.solve());
    }

    static class Point {
        int x;
        int y;
        public Point() {}
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return new String("("+x+", "+y+")");
        }
    }

    static class Element<T> {
        T value;
        Element next;
        Element prev;
        public Element() {}
        public Element(T value) {
            this.value = value;
        }
    }

    static class Queue<T> {
        Element<T> first;
        Element<T> last;
        int count = 0;

        public Queue() {}
        public Queue(T root) {
            Element<T> first = new Element(root);
            this.first = first;
            this.last = first;
            count = 1;
        }

        public void enqueue(T element) {
            count++;

            Element<T> ele = new Element<>(element);
            if (last != null) {
                ele.next = last;
                last.prev = ele;
                last = ele;
            } else {
                first = ele;
                last = ele;
            }
        }

        public T dequeue() {
            count--;

            if(first==null)
                return null;
            Element tmpFirst = first.prev;
            T ret = first.value;
            if (tmpFirst != null)
                tmpFirst.next = null;
            first = tmpFirst;
            return ret;
        }
    }
}
