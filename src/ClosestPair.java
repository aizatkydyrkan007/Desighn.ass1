import java.util.*;

public class ClosestPair {
    // класс точки
    static class Point {
        double x, y;
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
    // находим минимальное расстояние между точками
    public static double findClosest(Point[] points) {
        if (points.length < 2) return Double.MAX_VALUE;
        Point[] sorted = points.clone();
        Arrays.sort(sorted, Comparator.comparingDouble(p -> p.x));
        return solve(sorted, 0, sorted.length - 1);
    }
    // рекурсивное решение
    private static double solve(Point[] pts, int left, int right) {
        if (right - left <= 3) {
            return bruteForce(pts, left, right);
        }
        int mid = (left + right) / 2;
        double dLeft = solve(pts, left, mid);
        double dRight = solve(pts, mid + 1, right);
        double d = Math.min(dLeft, dRight);
        List<Point> strip = new ArrayList<>();
        double midX = pts[mid].x;
        for (int i = left; i <= right; i++) {
            if (Math.abs(pts[i].x - midX) < d) {
                strip.add(pts[i]);
            }
        }
        strip.sort(Comparator.comparingDouble(p -> p.y));
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < d; j++) {
                d = Math.min(d, distance(strip.get(i), strip.get(j)));
            }
        }
        return d;
    }
    // проверка всех расстояний (для маленьких массивов)
    private static double bruteForce(Point[] pts, int left, int right) {
        double min = Double.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                min = Math.min(min, distance(pts[i], pts[j]));
            }
        }
        return min;
    }
    // считаем расстояние
    private static double distance(Point a, Point b) {
        // формула расстояния между двумя точками
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
    // проверка
    public static void main(String[] args) {
        Point[] points = {
                new Point(2, 3),
                new Point(12, 30),
                new Point(40, 50),
                new Point(5, 1),
                new Point(12, 10),
                new Point(3, 4)
        };
        double result = findClosest(points);
        System.out.println("Минимальное расстояние: " + result);
    }
}