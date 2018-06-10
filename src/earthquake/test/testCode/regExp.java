package earthquake.test.testCode;

public class regExp {
    public static void main(String[] args){
        String str2 = "abd123:adad46587:asdadasadsfgi#%^^9090";
        System.out.println(str2.replaceAll("[0-9]", "*"));
        System.out.println(str2.replaceAll("\\d", "*"));

    }
}
