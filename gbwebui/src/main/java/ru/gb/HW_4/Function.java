package ru.gb.HW_4;

public class Function {

    public static double getAreaOfTriangle(int a, int b, int c) throws ThisTriangleNotExistException, SideOfTriangleIsBelowZeroException {
        if (a < 0 || b < 0 || c < 0) {
            throw new SideOfTriangleIsBelowZeroException();
        }
        else if (a + b <= c || a + c <= b || b + c <= a) {
            throw new ThisTriangleNotExistException();
        } else {
            Integer p = (a + b + c) / 2;
            Double area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
            return area;
        }
    }
}
