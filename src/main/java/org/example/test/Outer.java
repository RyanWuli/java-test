package org.example.test;

/**
 * @Author: Ryan
 * @Date: 2020/5/5 16:24
 * @Version: 1.0
 * @Description: 内部类 demo
 */
public class Outer {

    private static int number = 10;
    private int j = 20;
    public int k = 15;
    private String name = "java";

    private static void outer_funOne() {
        System.out.println("外部类的静态方法：outer_funOne");
    }

    public void outer_funTwo() {
        System.out.println("外部类普通方法：outer_funTwo");
    }

    // 成员内部类
    class Member {
        // 成员内部类不允许定义静态变量
//        static int i = 0;
        int j = 50; // 内部类和外部类的同名属性可以共存
        public void outer_funTwo() { // 可以和外部类同名方法
            System.out.println("内部同名方法：outer_funTwo");
        }

        // 内部方法
        public void member_funOne() {
            System.out.println("内部类访问内部变量：" + j); // 自己的属性也可以加 this.j
            System.out.println("内部类访问外部变量：" + Outer.this.j); // 不能直接.j因为不是静态
            System.out.println("内部类访问外部（内部没有）：" + name); // 只有外部有的属性则可以直接使用
            // 内部调用外部方法
            outer_funOne();
        }
    }

    public static void outer_funThree() {
        // 外部类静态方法访问内部类成员
        Outer outer = new Outer(); // 建立外部类对象
        Member member = outer.new Member(); // 根据外部类创建内部类
        member.member_funOne(); // 使用内部方法
        System.out.println("使用内部变量" + member.j);
    }



// 局部内部类的测试
//    public static void main(String[] args) {
//        // 创建内部类简写
//        Outer.Member member = new Outer().new Member();
//        member.member_funOne();
//        member.outer_funTwo();
//    }



    /*******************************************************************************静态内部类***********************************************************************************/
    private static class StaticDemo {
        static int j = 100;
        String name = "C#";
        static void static_funOne() {  // 静态类中的静态方法
            outer_funOne(); // 外部静态方法
            System.out.println("只能访问外部类的静态变量：" + number);
        }
        void static_funTwo() {
            System.out.println("静态类中的非静态方法：static_funTwo");
        }
    }

    public void outer_static_funThree() {
        System.out.println(StaticDemo.j); // 访问静态类的属性
        StaticDemo.static_funOne(); // 访问静态内部类的方法
        StaticDemo staticDemo = new StaticDemo();
        staticDemo.static_funTwo(); // 访问静态内部类中的非静态方法
    }

//    public static void main(String[] args) {
//        new Outer().outer_static_funThree();
//    }

    /*******************************************************************************局部内部类***********************************************************************************/

    // 定义外部类方法
    public void part_funOne(int k) {
        final int number = 100;
        int j = 50;
        // 方法内部的类（局部内部类）
        class partDemo {
            public partDemo(int k) {
                part_funTwo(k);
            }
            int number = 300; // 不可以定义静态变量
            // 内部类的方法
            public void part_funTwo(int k) {
                System.out.println("内部类方法：part_funTwo");
                System.out.println(name); // 没有同名变量，可直接访问外部变量
                System.out.println("访问外部类与内部类的同名变量" + Outer.this.name);
                System.out.println("内部类方法传入的参数：" + k);
            }
        }
        new partDemo(k);
    }

    public static void main(String[] args) {
        // 访问内部类必须先有外部类
        Outer outer = new Outer();
        outer.part_funOne(11);
    }
}
