import java.util.*;

//Alunos: João Alba, Nicole Taufenbach

public class Main {
    public static void main(String[] args){
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(3, 0));
        points.add(new Point(3, 3));
        points.add(new Point(0, 3));

        points.sort(Comparator.comparingInt(Point::getY));
        sortByAngle(points.get(0), points);

        Stack<Point> stack = new Stack<>();
        stack.push(points.get(0));
        stack.push(points.get(1));

        for (int i = 2; i < points.size(); i++) {
            Point subject = points.get(i);
            Point middle = stack.pop();
            Point start = stack.peek();

            if(isCurvaEsquerda(start, middle, subject)){
                stack.push(middle);
                stack.push(subject);
            }
        }
        System.out.println((stack.size() != points.size()) ? "Yes" : "No");
    }

    private static void sortByAngle(Point minYPoint, List<Point> points){
        points.sort((a, b) -> {
            if(a.equals(minYPoint)){
                return -1;
            }

            if(b.equals(minYPoint)){
                return 1;
            }

            return produtoVetorial(minYPoint, a, b) > 0 ? -1 : 1;
        });
    }

    private static int produtoVetorial(Point a, Point b, Point c){
        return ((b.getX() - a.getX()) * (c.getY() - a.getY())) - ((b.getY() - a.getY()) * (c.getX() - a.getX()));
    }

    private static boolean isCurvaEsquerda(Point minYPoint, Point a, Point b){
        return produtoVetorial(minYPoint, a, b) > 0;
    }

    private static class Point {

        private final int X;
        private final int Y;

        public Point(int x, int y) {
            X = x;
            Y = y;
        }

        public int getX() {
            return X;
        }

        public int getY() {
            return Y;
        }
    }
}
