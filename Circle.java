// You are given a circle represented as (radius, xCenter, yCenter) and an axis-aligned rectangle represented as (x1, y1, x2, y2), where (x1, y1) are the coordinates of the bottom-left corner, and (x2, y2) are the coordinates of the top-right corner of the rectangle.

// Return true if the circle and rectangle are overlapped otherwise return false. In other words, check if there is any point (xi, yi) that belongs to the circle and the rectangle at the same time.

public class Circle {
    public static boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int closestX = Math.max(x1, Math.min(xCenter, x2));
        int closestY = Math.max(y1, Math.min(yCenter, y2));

        int distanceX = xCenter - closestX;
        int distanceY = yCenter - closestY;

        return (distanceX * distanceX + distanceY * distanceY) <= radius * radius;
    }

    public static void main(String args[]) {
        System.out.println(checkOverlap(1, 0, 0, 1, -1, 3, 1)); // true
        System.out.println(checkOverlap(1, 1, 1, -3, -3, 3, 3)); // true
        System.out.println(checkOverlap(1, 1, 1, 1, -3, 2, -1)); // false
    }
}