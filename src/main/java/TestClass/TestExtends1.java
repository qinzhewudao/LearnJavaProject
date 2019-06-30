package TestClass;

/**
 * author sheyang
 * created at 2018/8/3
 */
public class TestExtends1 {
    public static void main(String[] args) {
        Shape shape = new Circle();
        System.out.println(shape.name);
        shape.printType();
        shape.printName();
    }
}

class Shape {
    String name = "shape";

    Shape() {
        System.out.println("shape constructor");
    }

    static void printName() {
        System.out.println("shape");
    }

    public void printType() {
        System.out.println("this is shape");
    }
}

class Circle extends Shape {
    public String name = "circle";

    Circle() {
        System.out.println("circle constructor");
    }

    public static void printName() {
        System.out.println("circle");
    }

    public void printType() {
        System.out.println("this is circle");
    }
}
