public class Calculator {

    static final int[][] rings = Main.RINGS;

    public static int magnitude(int x, int z) {
        return (int) Math.sqrt(x * x + z * z);
    }

    public static int closestRing(int x, int z) {
        int magnitude = magnitude(x, z);
        if (magnitude <= rings[0][1])
            return 0;
        for (int i = 1; i < rings.length; i++) {
            if (rings[i][0] <= magnitude && rings[i][1] >= magnitude)
                return i;
            if (rings[i - 1][1] < magnitude && rings[i][0] > magnitude) {
                if (magnitude - rings[i - 1][1] < rings[i][0] - magnitude)
                    return i - 1;
                else
                    return i;
            }
        }
        return 7;
    }

    public static int distanceToClosestRing(int x, int z) {
        int magnitude = magnitude(x, z);
        int closestRing = closestRing(x, z);
        if (rings[closestRing][0] > magnitude)
            return rings[closestRing][0] - magnitude;
        else if (rings[closestRing][1] < magnitude)
            return rings[closestRing][1] - magnitude;
        else
            return 0;
    }

    public static int distanceToMiddleClosestRing(int x, int z) {
        int magnitude = magnitude(x, z);
        int closestRing = closestRing(x, z);
        int middleOfRing = (rings[closestRing][0] + rings[closestRing][1]) / 2;
        return middleOfRing - magnitude;
    }

    public static int[] locationOfClosestRing(int x, int z) {
        int magnitude = magnitude(x, z);
        int distanceToClosestRing = distanceToClosestRing(x, z);
        double normalX = x / (double) magnitude;
        double normalZ = z / (double) magnitude;
        int targetX = (int) (x + normalX * distanceToClosestRing);
        int targetZ = (int) (z + normalZ * distanceToClosestRing);
        int[] result = { targetX, targetZ };
        return result;
    }

    public static int[] locationOfMiddleClosestRing(int x, int z) {
        int magnitude = magnitude(x, z);
        int distanceToMiddleClosestRing = distanceToMiddleClosestRing(x, z);
        double normalX = x / (double) magnitude;
        double normalZ = z / (double) magnitude;
        int targetX = (int) (x + normalX * distanceToMiddleClosestRing);
        int targetZ = (int) (z + normalZ * distanceToMiddleClosestRing);
        int[] result = { targetX, targetZ };
        return result;
    }
}
