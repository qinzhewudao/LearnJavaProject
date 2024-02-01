package HeadFirst;

/**
 * author sheyang
 * created at 2018/10/16
 */
public interface State {

    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispense();
}
