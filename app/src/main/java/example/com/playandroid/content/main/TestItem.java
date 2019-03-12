package example.com.playandroid.content.main;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/12 16:09
 */
public class TestItem {
    private String name;
    private int age;
    private String pitcure;

    public TestItem(String name, int age, String pitcure) {
        this.name = name;
        this.age = age;
        this.pitcure = pitcure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPitcure() {
        return pitcure;
    }

    public void setPitcure(String pitcure) {
        this.pitcure = pitcure;
    }
}
