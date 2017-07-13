package monkey;

import java.util.Scanner;

public class Main {
    int k;
    int h;
    int w;
    char[][] map;
    boolean[][][] isVisited;
    Point destination;

    void setUp() {
        Scanner scanner = new Scanner(System.in);
        k = scanner.nextInt();
        w = scanner.nextInt();
        h = scanner.nextInt();
        map = new char[h][w];
        isVisited = new boolean[h][w][k+1];
        for(int i=0;i<h;++i) {
            for(int j=0;j<w;++j)
                map[i][j] = scanner.nextInt()==1 ? '1' : '0';
        }
        for(int i=0 ; i<h ; ++i) {
            for (int j=0 ; j<w ; ++j) {
                for (int l=0 ; l<k+1 ; ++l) {
                    isVisited[i][j][l] = false;
                    if (i==0 && j==0)
                        isVisited[i][j][l] = true;
                }
            }
        }
        destination = new Point(w-1, h-1);
    }

    void setUp(boolean isTest) {
        if (isTest==false)
            setUp();
        else {
            k = 6;
            h = 10;
            w = 10;
            map = new char[h][w];
            isVisited = new boolean[h][w][k+1];
            map[0] = "0111".toCharArray();
            map[1] = "0011".toCharArray();
            map[2] = "1011".toCharArray();
            map[3] = "1110".toCharArray();

            map[0] = "0000000000".toCharArray();
            map[1] = "0111111110".toCharArray();
            map[2] = "0011111110".toCharArray();
            map[3] = "0111111110".toCharArray();
            map[4] = "0101111110".toCharArray();
            map[5] = "0111111110".toCharArray();
            map[6] = "0110111110".toCharArray();
            map[7] = "0111110110".toCharArray();
            map[8] = "0111011100".toCharArray();
            map[9] = "0000000010".toCharArray();

            for(int i=0 ; i<h ; ++i) {
                for (int j=0 ; j<w ; ++j) {
                    for (int l=0 ; l<k+1 ; ++l) {
                        isVisited[i][j][l] = false;
                        if (i==0 && j==0)
                            isVisited[i][j][l] = true;
                    }
                }
            }
            destination = new Point(w-1, h-1);
        }
    }

    //진입점
    int solve() {
        int horseMove = 0;
        int distance = 0;
        Queue<Point> searchQueue = new Queue<>();
        if (horseMove < k) {
            pickHorsePoints(new Point(0,0, horseMove+1), searchQueue);
        }
        pickPoints(new Point(0,0), searchQueue);

        return search(searchQueue, distance);
    }

    //원숭이가 움직일 수 있는 4지점
    Point[] points(Point position) {
        Point[] ret = {
                new Point(position.x-1, position.y, position.horseMove),
                new Point(position.x+1, position.y, position.horseMove),
                new Point(position.x, position.y-1, position.horseMove),
                new Point(position.x, position.y+1, position.horseMove)
        };
        return ret;
    }

    //말처럼 움직일 수 있는 8지점
    Point[] horsePoints(Point position) {
        Point[] ret = {
                new Point(position.x-1, position.y-2, position.horseMove),
                new Point(position.x-1, position.y+2, position.horseMove),
                new Point(position.x+1, position.y-2, position.horseMove),
                new Point(position.x+1, position.y+2, position.horseMove),
                new Point(position.x-2, position.y-1, position.horseMove),
                new Point(position.x-2, position.y+1, position.horseMove),
                new Point(position.x+2, position.y-1, position.horseMove),
                new Point(position.x+2, position.y+1, position.horseMove)
        };
        return ret;
    }

    //입력받은 position에서 원숭이처럼 움직일 수 있는 경로를 입력받은 queue에 적재
    void pickPoints(Point position, Queue sQueue) {
        Point[] points = points(position);

        for (int i=0 ; i<4 ; ++i) {
            if (points[i].x>=0 && points[i].x<w && points[i].y>=0 && points[i].y<h &&
                    isVisited[points[i].y][points[i].x][points[i].horseMove] == false &&
                    map[points[i].y][points[i].x] == '0' /*&& !sQueue.find(points[i])*/) {
                sQueue.enqueue(points[i]);
                isVisited[points[i].y][points[i].x][points[i].horseMove] = true;
            }
        }
    }

    //입력받은 position에서 말처럼 움직일 수 있는 경로를 입력받은 queue에 적재
    void pickHorsePoints(Point position, Queue sQueue) {
        Point[] hp = horsePoints(position);

        for (int i=0 ; i<8 ; ++i) {
            if (hp[i].x>=0 && hp[i].x<w && hp[i].y>=0 && hp[i].y<h &&
                    isVisited[hp[i].y][hp[i].x][hp[i].horseMove] == false &&
                    map[hp[i].y][hp[i].x] == '0' /*&& !sQueue.find(hp[i])*/) {
                sQueue.enqueue(hp[i]);
                isVisited[hp[i].y][hp[i].x][hp[i].horseMove] = true;
            }
        }
    }

    int search(Queue<Point> sQueue, int distance) {
        //System.out.println("current sQueue: "+sQueue.toString());
        //더 이상 경로를 찾을 수 없으면 -1 리턴
        if (sQueue.count==0) {
            //System.out.println("cannot find path");
            return -1;
        }

        ++distance;
        Queue<Point> newSQueue = new Queue<>();
        //기존 queue 에 적재된 node 들을 순회하며 다음 steop의 경로를 찾기 위한 신규 queue 생성
        for (Point iter=sQueue.dequeue() ; iter!=null ; iter=sQueue.dequeue()) {
            //System.out.println("current: "+iter+", distance: "+distance);
            isVisited[iter.y][iter.x][iter.horseMove] = true;
            if (destination.isSamePoint(iter)) {
                //System.out.println("found path, distance: "+distance);
                return distance;
            }
            pickPoints(new Point(iter.x,iter.y, iter.horseMove), newSQueue);
            if (iter.horseMove < k) {
                pickHorsePoints(new Point(iter.x,iter.y, iter.horseMove+1), newSQueue);
            }
        }

        return search(newSQueue, distance);
    }

    public static void main(String... args) {
        Main sol = new Main();
        sol.setUp();
        System.out.println(sol.solve());
    }

    static class Point {
        int x;
        int y;
        int horseMove;
        public Point() {}
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int horseMove) {
            this.x = x;
            this.y = y;
            this.horseMove = horseMove;
        }

        @Override
        public String toString() {
            return "("+x+", "+y+")hm:"+horseMove+" ";
        }

        @Override
        public boolean equals(Object obj) {
            Point rhs = (Point)obj;
            return rhs.x==x&&rhs.y==y&&rhs.horseMove==horseMove ? true : false;
        }

        public boolean isSamePoint(Point p) {
            return p.x==x&&p.y==y ? true : false;
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
            for(Element iter=last ; iter!=null ; iter=iter.next) {
                if (value.equals(iter.value)) {
                    ret = true;
                    break;
                }
            }
            return ret;
        }

        @Override
        public String toString() {
            String ret = "";
            for (Element iter=last ; iter!=null ; iter=iter.next) {
                ret += iter.value.toString();
            }
            return ret;
        }
    }
}

