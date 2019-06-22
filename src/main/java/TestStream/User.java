package TestStream;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * author sheyang
 * created at 2018/8/22
 */
public class User implements Comparable<User>{
    private int age;//年龄
    private String name;//姓名
    private String password;//密码
    private short gendar;//性别，0未知，1男，2女
    private boolean hasMarried;//是否已婚


    private String getPassword() {
        return password;
    }

    private User(int age, String name, String password, short gendar,
                 boolean hasMarried) {
        super();
        this.age = age;
        this.name = name;
        this.password = password;
        this.gendar = gendar;
        this.hasMarried = hasMarried;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if(!(obj instanceof User))return false;
        User u = (User)obj;
        return age == u.age
                && gendar == u.gendar
                && hasMarried == u.hasMarried
                && name.equals(u.name)
                && password.equals(u.getPassword());
    }

    @Override
    public String toString() {
        return "{\"age\":\"" + age + "\", \"name\":\"" + name
                + "\", \"password\":\"" + password + "\", \"gendar\":\""
                + gendar + "\", \"hasMarried\":\"" + hasMarried + "\"} \n";
    }

    Comparator<User> equalComparator = (o1, o2) -> {
        // TODO Auto-generated method stub
        //首先比较年龄大小，因为年龄的区分度比较高
        if(o1.age>o2.age)return 1;
        if(o1.age<o2.age)return -1;
        //如果年龄相同就比较性别，女的排在前面
        if(o1.gendar>o2.gendar)return 1;
        if(o1.gendar<o2.gendar)return -1;
        //如果性别也一样就比较是否已婚
        if(o1.hasMarried && !o2.hasMarried)return 1;//结婚的排在前面
        if(!o1.hasMarried && o2.hasMarried)return 1;//结婚的排在前面
        //最后比较姓名，因为字符串比较耗时较长
        return Integer.compare(o1.name.hashCode(), o2.name.hashCode());
    };

    @Override
    public int compareTo(User o2) {
        // TODO Auto-generated method stub
        //首先比较年龄大小，因为年龄的区分度比较高
        User o1 = this;
        if(o1.age>o2.age)return 1;
        if(o1.age<o2.age)return -1;
        //如果年龄相同就比较性别，女的排在前面
        if(o1.gendar>o2.gendar)return 1;
        if(o1.gendar<o2.gendar)return -1;
        //如果性别也一样就比较是否已婚
        if(o1.hasMarried && !o2.hasMarried)return 1;//结婚的排在前面
        if(!o1.hasMarried && o2.hasMarried)return 1;//结婚的排在前面
        //最后比较姓名，因为字符串比较耗时较长
        return Integer.compare(o1.name.hashCode(), o2.name.hashCode());

    }

    private static Comparator<User> ageComparator = Comparator.comparingInt(o -> o.age);

    public static void main(String[] args){

        ArrayList<User> users = new ArrayList<>();
        users.add(new User(22, "王旭", "123456", (short)1, true));
        users.add(new User(22, "王旭", "123456", (short)1, true));
        users.add(new User(22, "王旭", "123456", (short)1, true));
        users.add(new User(21, "孙萍", "a123456", (short)2, false));
        users.add(new User(23, "步传宇", "b123456", (short)1, false));
        users.add(new User(18, "蔡明浩", "c123456", (short)1, true));
        users.add(new User(17, "郭林杰", "d123456", (short)1, false));
        users.add(new User(5, "韩凯", "e123456", (short)1, true));
        users.add(new User(22, "韩天琪", "f123456", (short)2, false));
        users.add(new User(21, "郝玮", "g123456", (short)2, false));
        users.add(new User(19, "胡亚强", "h123456", (short)1, false));
        users.add(new User(14, "季恺", "i123456", (short)1, false));
        users.add(new User(17, "荆帅", "j123456", (short)1, true));
        users.add(new User(16, "姜有琪", "k123456", (short)1, false));

        //传统方式排序：
        Long time = System.currentTimeMillis();
        users.sort(ageComparator);
        System.out.println("耗时"+(System.currentTimeMillis()-time));
        System.out.println(users);

        //java8排序方式，利用stream
        long time2 = System.currentTimeMillis();
        List<User> sortedUsers = users.stream().sorted(ageComparator).collect(Collectors.toList());
        System.out.println("耗时"+(System.currentTimeMillis()-time2));
        System.out.println(sortedUsers);

        /*场景一（2）选出年龄最小的三个人


        有时候我们也许并不需要获得排序的所有结果，只需要获得前几名就可以了，比如我想获得年龄最小的三个人

        传统方法排序限制：

        首先进行上面的排序，然后取出数组的前三个元素
        */

        long time3 = System.currentTimeMillis();
        users.sort(ageComparator);
        List<User> userList = users.subList(0,3);
        System.out.println("耗时"+(System.currentTimeMillis()-time3));
        System.out.println(userList);


        //Java8方式排序限

        long time4 = System.currentTimeMillis();
        List<User> resultArr = users.stream().sorted(ageComparator).limit(3).collect(Collectors.toList());
        System.out.println("耗时"+(System.currentTimeMillis()-time4));
        System.out.println(resultArr);

        //传统方法去重：

        users.sort(ageComparator);
        time = System.currentTimeMillis();
        int length = users.size();
        for(int i=1;i<length;i++){
            if(users.get(i).equals(users.get(i-1))){
                users.remove(i);
                i--;
                length--;
            }
        }
        System.out.println("耗时"+(System.currentTimeMillis()-time));
        System.out.println(users);

        //java8去重
        time = System.currentTimeMillis();
        resultArr = users.stream().sorted().distinct().collect(Collectors.toList());
        System.out.println("耗时"+(System.currentTimeMillis()-time));
        System.out.println(resultArr);


        //场景三：按条件筛选
        //
        //这种场景也许是最常见的一种应用场景了，在许多元素构成的数组中筛选出我们需要的满足特定条件的元素，在这里我们把所有姓韩的筛选出来
        //
        //传统方法：
        time = System.currentTimeMillis();
        resultArr = new ArrayList<>();//用于存放结果
        for(User u:users){
            if(u.name.startsWith("韩"))resultArr.add(u);
        }
        System.out.println("耗时"+(System.currentTimeMillis()-time));
        System.out.println(resultArr);


        //Java8方法：

        time = System.currentTimeMillis();
        resultArr = users.stream().filter(t->t.name.startsWith("韩")).collect(Collectors.toList());
        System.out.println("耗时"+(System.currentTimeMillis()-time));
        System.out.println(resultArr);


        //其中，java8采用的泛型进行处理，上面的t->t.name中的t是Stream<T>的泛型，而这个T又是List<T>中的泛型，
        // t可以换成其他任何字母，并且也可以点出User类的相关方法，并且还可以支持复合筛选，比如我们要筛选姓韩的女生，可以这样写

        resultArr = users.stream().filter(t->t.name.startsWith("韩") && t.gendar==2).collect(Collectors.toList());
        System.out.println(resultArr);
        //也可以这样写
        resultArr = users.stream().filter(t->t.name.startsWith("韩")).filter(t->t.gendar==2).collect(Collectors.toList());
        System.out.println(resultArr);

        //还可以改变条件的顺序：
        resultArr = users.stream().filter(t->t.gendar==2).filter(t->t.name.startsWith("韩")).collect(Collectors.toList());
        System.out.println(resultArr);

//        场景四：只列出所有人的名字和婚姻状况
//
//        这次要用的.map()函数，map()就是为了只显示对象的一部分信息而准备的。
//
//        传统方式：

        time = System.currentTimeMillis();
        List<String> marryStatus = new ArrayList<>();
        for(User u:users){
            marryStatus.add(u.name+":".concat(u.hasMarried?"已婚":"未婚")+"\n");
        }
        System.out.println("耗时"+(System.currentTimeMillis()-time));
        System.out.println(marryStatus);

        //java8方式：

        time = System.currentTimeMillis();
        marryStatus = users.stream().map(t->t.name+":".concat(t.hasMarried?"已婚":"未婚")+"\n").collect(Collectors.toList());
        System.out.println("耗时"+(System.currentTimeMillis()-time));
        System.out.println(marryStatus);

//        场景五：判断当前数组是否包含某些特定元素
//
//                如果我要看看现在的用户中是否有未成年人怎么办呢
//
//        传统方法：

        time = System.currentTimeMillis();
        boolean isChild = false;
        for(User u:users){
            if(u.age<18){
                isChild = true;
                break;
            }
        }
        System.out.println("耗时"+(System.currentTimeMillis()-time)+isChild);
//        结果：耗时0 true
//        Java8方法：

        time = System.currentTimeMillis();
        isChild = users.stream().anyMatch(t->t.age<18);
        System.out.println("耗时"+(System.currentTimeMillis()-time)+isChild);


//        场景六：确认所有元素均满足某一条件
//
//                这里以查看所有人是否都已婚为例
//
//        传统方法：

        time = System.currentTimeMillis();
        boolean allMarried = true;
        for(User u:users){
            if(!u.hasMarried){
                allMarried = false;
                break;
            }
        }
        System.out.println("耗时"+(System.currentTimeMillis()-time)+allMarried);

//        结果：耗时0 false
//        java8方法：

        time = System.currentTimeMillis();
        allMarried = users.stream().allMatch(t->t.hasMarried);
        System.out.println("耗时"+(System.currentTimeMillis()-time)+allMarried);
//
//        场景七：求和求平均值
//
//        求和这种操作在用户管理上十分频繁，java8的流操作省去了循环，节省了大量代码，比如我们要求所有用户的平均年龄
//
//        传统方法:

        time = System.currentTimeMillis();
        int sum = 0;
        for(User u:users){
            sum+=u.age;
        }
        System.out.println("耗时"+(System.currentTimeMillis()-time)+sum/users.size());
//        结果：耗时0 平均年龄18
//        java8方法：
//
//        这里先用map方法把所有元素的age取出来，然后调用Integer.sum方法进行聚合（reduce函数），
// 得到年龄和，返回是一个OptionalInt对象，这里面包含一个int，但也有可能为null，注意这里reduce()函数的参数是一个方法，
// 注意Java8支持将函数作为参数传入了，有点像c++写法，规则是完整类名::方法名(方法参数...)

        time = System.currentTimeMillis();
        OptionalInt sum2 = users.stream().mapToInt(t->t.age).reduce(Integer::sum);
        System.out.println("耗时"+(System.currentTimeMillis()-time)+sum2.getAsInt()/users.size());

//        场景八：分组
//
//        比如我们要按用户的年龄进行分组，相同年龄的人分在同一组，用一个Map<Integer,List<User>>存放，key是年龄，value是该年龄的所有用户
//
//        传统方法：

        time = System.currentTimeMillis();
        Map<Integer,List<User>> group = new HashMap<>();
        for(User u:users){
            List<User> list = group.computeIfAbsent(u.age, k -> new ArrayList<>());
            list.add(u);
        }
        System.out.println("耗时"+(System.currentTimeMillis()-time));
        System.out.println(group);

        //Java8方法：
        time = System.currentTimeMillis();
        group = users.stream().collect(Collectors.groupingBy(t->t.age));
        System.out.println("耗时"+(System.currentTimeMillis()-time));
        System.out.println(group);

        //如果想按是否结婚分组，也就是key变成bool，那就应该这么写
        Map<Boolean,List<User>> group2 = users.stream().collect(Collectors.partitioningBy(t->t.hasMarried));

//        场景九：链式操作
//
//        如果我们需要打印所有女生的名字，那么同样可以一行代码搞定，思路是先通过源Stream通过筛选得到一个新Stream，
// 再对这个新的Stream进行操作，如此循环,注意这里使用的forEach()函数是遍历Stream中的每一个元素，参数是方法，
// 注意Java8支持将函数作为参数传入了，有点像c++写法，规则是完整类名::方法名(方法参数...)

        time = System.currentTimeMillis();
        users.stream().filter(t->t.gendar==2).map(t->t.name).forEach(System.out::println);
        System.out.println("耗时"+(System.currentTimeMillis()-time));

        System.out.println(LocalDate.now().with(DayOfWeek.MONDAY));

        System.out.println(LocalDateTime.of(LocalDate.now().with(DayOfWeek.MONDAY),LocalTime.of(0,0,0)));







    }

}
