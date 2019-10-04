public class Main {

    public static void main(String[] args) {
	// write your code here
        int a=233;
        System.out.println(((Integer)a).hashCode());

        int b=-233;
        System.out.println(((Integer)b).hashCode());

        double c=233.;
        System.out.println(((Double)c).hashCode());

        String d = "BossCap";
        System.out.println(d.hashCode());

        Student zn = new Student(3, 100, "zhangning");
        System.out.println(zn.hashCode());
    }
}
