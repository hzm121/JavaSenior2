import model.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HowGetClass {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ParseException {
        //反射的三种方式
        //方式一:
        Student student = new Student();
        Class clazz1 = student.getClass();
        //方式二:
        Class clazz2 = Student.class;
        //方式三:
        Class clazz3 = Class.forName("model.Student");
        //获取所有构造器
        Constructor[] declaredConstructors = clazz1.getDeclaredConstructors();
        for (Constructor c: declaredConstructors
             ) {
            System.out.println(c);
        }
        //获取指定构造器
        Constructor constructor = clazz1.getConstructor(new Class[]{String.class, String.class, Integer.class, Date.class});
        System.out.println(constructor);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = simpleDateFormat.parse("19970517");
        //通过获取的构造器实例化对象
        Student student1 = (Student) constructor.newInstance("1","hzm",23,date);
        student.goToClass();
    }
}
