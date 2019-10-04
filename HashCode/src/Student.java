public class Student {

    private int cls;
    private int grade;
    private String name;

    public Student(int cls, int grade, String name){
        this.cls = cls;
        this.grade = grade;
        this.name = name;
    }

    @Override
    public int hashCode(){

        int B = 31;
        int hash = 0;
        hash = hash*B + cls;
        hash = hash*B + grade;
        hash = hash*B + name.toLowerCase().hashCode();
        return hash;
    }
}
