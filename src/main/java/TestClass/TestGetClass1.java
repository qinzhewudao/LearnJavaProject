package TestClass;

import java.util.Arrays;
import java.util.Objects;

/**
 * author sheyang
 * created at 2018/8/7
 */
public class TestGetClass1 {
    public static void main(String[] args)
    {
        Human aPerson = new Human();
        Class c1      = aPerson.getClass();
        System.out.println(c1.getName());

        Human anotherPerson = new Woman();
        Class c2      = anotherPerson.getClass();
        System.out.println(c2.getName());

        System.out.println("package name = "+c2.getPackage());

        Class c3      = null;
        try {
            c3 = Class.forName("TestClass.Human");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(Objects.requireNonNull(c3).getName());

        Class c4      = Woman.class;
        System.out.println(c4.getName());

        System.out.println("all methods of women class are below \n"+ Arrays.toString(c4.getMethods()));
    }
}

class Human
{
    /**
     * accessor
     */
    public int getHeight()
    {
        return this.height;
    }

    /**
     * mutator
     */
    public void growHeight(int h)
    {
        this.height = this.height + h;
    }
    private int height;
}


class Woman extends Human
{
    /**
     * new method
     */
    public Human giveBirth()
    {
        System.out.println("Give birth");
        return (new Human());
    }

}