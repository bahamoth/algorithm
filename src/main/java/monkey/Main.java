package monkey;

import java.util.Scanner;

/**
 * descriptions
 *
 * @author baham
 * @since 1.0.0
 */
public class Main {
    int k;
    int h;
    int w;
    char[][] map;
    boolean[][] visited;
    Point destination;
    int shortest = 2000000000;

    void setUp() {
        Scanner scanner = new Scanner(System.in);
        k = scanner.nextInt();
        h = scanner.nextInt();
        w = scanner.nextInt();
        map = new char[h][w];
        visited = new boolean[h][w];
        for(int i=0;i<h;++i) {
            map[i] = scanner.next().toCharArray();
        }
        for(int i=0 ; i<h ; ++i) {
            for (int j=0 ; j<w ; ++j) {
                visited[i][j] = false;
            }
        }
        visited[0][0] = true;
        destination = new Point(w-1, h-1);
    }

    void setUp(boolean isTest) {
        if (isTest==false)
            setUp();
        else {
            k = 1;
            h = 4;
            w = 4;
            map = new char[h][w];
            visited = new boolean[h][w];
            map[0] = "0000".toCharArray();
            map[1] = "1000".toCharArray();
            map[2] = "0010".toCharArray();
            map[3] = "0100".toCharArray();
            for(int i=0 ; i<h ; ++i) {
                for (int j=0 ; j<w ; ++j) {
                    visited[i][j] = false;
                }
            }
            visited[0][0] = true;
            destination = new Point(w-1, h-1);
        }
    }

    int solve() {
        int horseMove = k;
        int distance = 0;
        Queue<Point> searchQueue = new Queue<>();
        if (horseMove > 0) {
            --horseMove;
            pickHorsePoints(new Point(0,0), searchQueue);
        }
        pickPoints(new Point(0,0), searchQueue);

        return search(searchQueue, horseMove, distance);
    }

    Point[] points(Point position) {
        Point[] ret = {
                new Point(position.x-1, position.y),
                new Point(position.x+1, position.y),
                new Point(position.x, position.y-1),
                new Point(position.x, position.y+1)
        };
        return ret;
    }

    Point[] horsePoints(Point position) {
        Point[] ret = {
                new Point(position.x-1, position.y-2),
                new Point(position.x-1, position.y+2),
                new Point(position.x+1, position.y-2),
                new Point(position.x+1, position.y+2),
                new Point(position.x-2, position.y-1),
                new Point(position.x-2, position.y+1),
                new Point(position.x+2, position.y-1),
                new Point(position.x+2, position.y+1)
        };
        return ret;
    }

    void pickPoints(Point position, Queue sQueue) {
        Point[] points = points(position);

        for (int i=0 ; i<4 ; ++i) {
            if (points[i].x>=0 && points[i].x<w && points[i].y>=0 && points[i].y<h &&
                    visited[points[i].y][points[i].x] == false &&
                    !sQueue.find(new Point(points[i].x, points[i].y)))
                sQueue.enqueue(new Point(points[i].x, points[i].y));
        }
    }

    void pickHorsePoints(Point position, Queue sQueue) {
        Point[] hp = horsePoints(position);

        for (int i=0 ; i<8 ; ++i) {
            if (hp[i].x>=0 && hp[i].x<w && hp[i].y>=0 && hp[i].y<h &&
                    visited[hp[i].y][hp[i].x] == false &&
                    !sQueue.find(new Point(hp[i].x, hp[i].y)))
                sQueue.enqueue(new Point(hp[i].x, hp[i].y));
        }
    }

    int search(Queue<Point> sQueue, int horseMove, int distance) {
        if (sQueue.count==0)
            return shortest;

        Queue<Point> newSQueue = new Queue<>();
        if (horseMove > 0) {
            --horseMove;
            pickHorsePoints(new Point(0,0), newSQueue);
        }
        pickPoints(new Point(0,0), newSQueue);


        return search(newSQueue, horseMove, distance+1);
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
            return "("+x+", "+y+")";
        }

        @Override
        public boolean equals(Object obj) {
            Point rhs = (Point)obj;
            return rhs.x==x&&rhs.y==y ? true : false;
        }
    }

    static class Element<T> {
        Element<T> next;
        Element<T> prev;
        T value;

        public Element() {}
        public Element(T value) {
            this.value = value;
        }
    }

    static class Queue<T> {
        Element<T> last;
        Element<T> first;
        int count=0;

        void enqueue(T value) {
            Element<T> newLast = new Element(value);
            if (last!=null) {
                last.prev = newLast;
                newLast.next = last;
            } else {
                first = newLast;
            }
            last = newLast;
            ++count;
        }

        T dequeue() {
            if (first==null)
                return null;
            T ret = first.value;
            first = first.prev;
            --count;

            return ret;
        }

        boolean find(T value) {
            boolean ret = false;
            for(Element iter=last ; iter!=first ; iter=iter.next) {
                if (value.equals(iter.value)) {
                    ret = true;
                    break;
                }
            }
            if (first!=null && value.equals(first.value))
                ret = true;
            return ret;
        }

        @Override
        public String toString() {
            String ret = "";
            for (Element iter=last ; iter!=first ; iter=iter.next) {
                ret += iter.value.toString();
            }
            if (first!=null)
                ret += first.value.toString();
            return ret;
        }
    }
}
