package example.com.playandroid.content.register;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard_Y_Wang
 * @des 2018/11/13 22:16
 */
public class TestGson {
    public static void main(String[] args) {
        Class gaoyi = new Class();
        gaoyi.name = "高一三班";
        List<Student> students = new ArrayList<>();
        students.add(new Student("tom",1));
        students.add(new Student("amy",2));
        students.add(new Student("kobe",3));
        students.add(new Student("james", 4));
        List<Student> testList = new ArrayList<>();
        testList.add(null);
        testList.add(null);
        System.out.println(testList.size());
        gaoyi.students = testList;
        Gson gson = new Gson();
        String s = gson.toJson(gaoyi);
        System.out.println(s);
    }
}
