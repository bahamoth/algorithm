package bfs;

import java.util.Scanner;

/**
 * descriptions
 *
 * @author baham
 * @since 1.0.0
 */
public class Main {
    int n;
    int m;
    char[][] maze;
    boolean[][] visited;
    int shortest = 2000000000;

    void setUp() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        maze = new char[n][m];
        for (int i = 0; i < n; ++i) {
            maze[i] = scanner.next().toCharArray();
        }
        visited = new boolean[n][m];
        for(int i=0 ; i<n ; ++i) {
            for(int j=9 ; j<m ; ++j) {
                visited[i][j] = false;
            }
        }
        visited[0][0] = true;
    }

    void setUp(boolean isTest) {
        if (isTest == false)
            setUp();
        else {
            n = 100;
            m = 100;
            maze = new char[n][m];
            for(int i=0 ; i<n ; ++i) {
                for(int j=0 ; j<m ; ++j) {
                    maze[i][j] = String.format("%d", (int)(Math.random()+0.8)).toCharArray()[0];
                    //System.out.print(maze[i][j]+" ");
                }
                //System.out.println();
            }
//            maze[0] = new char[]{'1', '1', '0', '1', '1', '0'};
//            maze[1] = new char[]{'1', '1', '0', '1', '1', '0'};
//            maze[2] = new char[]{'1', '1', '1', '1', '0', '0'};
//            maze[3] = new char[]{'1', '1', '1', '1', '0', '1'};

            visited = new boolean[n][m];
            for(int i=0 ; i<n ; ++i) {
                for(int j=9 ; j<m ; ++j) {
                    visited[i][j] = false;
                }
            }
            visited[0][0] = true;
        }
    }

    int solve() {
       int ret;
       int distance = 1;

       Queue<Point> searchQueue = new Queue<>();
       pickPoints(new Point(0,0), searchQueue);
       ret = search(new Point(m-1, n-1), searchQueue, distance);

       return ret;
    }

    int search(Point destination, Queue<Point> sQueue, int distance) {
        if (sQueue.count==0)
            return -1;

        distance += 1;
        Queue<Point> newSQueue = new Queue<>();
        for (Point iter=sQueue.dequeue() ; iter!=null ; iter=sQueue.dequeue()) {
            visited[iter.y][iter.x] = true;
            //System.out.println("path: "+iter+", search queue: "+sQueue+", distance: "+distance);
            if (iter.equals(destination)) {
                if (shortest>distance)
                    shortest=distance;
                return shortest;
            }
            pickPoints(iter, newSQueue);
        }
        return search(destination, newSQueue, distance);
    }

    void pickPoints(Point current, Queue<Point> sQueue) {
        Point[] points = new Point[4];
        if (current.x-1 >= 0)
            points[0] = new Point(current.x-1, current.y);
        if (current.y-1 >=0)
            points[1] = new Point(current.x, current.y-1);
        if (current.x+1 < m)
            points[2] = new Point(current.x+1, current.y);
        if (current.y+1 < n)
            points[3] = new Point(current.x, current.y+1);

        for (int i=0 ; i<4 ; ++i) {
            if (points[i] == null)
                continue;
            if (!visited[points[i].y][points[i].x] &&
                    maze[points[i].y][points[i].x]=='1' &&
                    !sQueue.find(points[i])) {
                sQueue.enqueue(points[i]);
            }
        }
        //System.out.println("picked: "+sQueue);
    }



    public static void main(String... args) {
        Main sol = new Main();
        sol.setUp(true);
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

        @Override
        public boolean equals(Object obj) {
            Point t = (Point) obj;
            return  t.x==x && t.y==y ? true : false;
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

        public boolean find(T element) {
            boolean ret = false;
            for (Element iter = last ; iter != first ; iter = iter.next) {
                if (element.equals(iter.value)) {
                    ret = true;
                    break;
                }
            }
            if (first!=null && element.equals(first.value)) {
                ret = true;
            }

            return ret;
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

        @Override
        public String toString() {
            String ret = "";
            if (count == 0)
                return ret;
            for (Element iter=last ; iter!=first ; iter=iter.next)
                ret += iter.value.toString();
            if (first!=null)
                ret += first.value.toString();
            return ret;
        }
    }
}
