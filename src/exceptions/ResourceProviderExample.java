package exceptions;

public class ResourceProviderExample {
    public static void main(String[] args) {
//        기능: 프로퍼티 파일을 읽어와서 특정 키의 값을 출력한다.
        String propertyFilename = "test.properties";
        ResourceProvider res = new ResourceProvider(propertyFilename);

        System.out.println(res.read("0000"));
        System.out.println(res.read("1111"));
        System.out.println(res.read("0001"));

        res.write("8888", "PalPalPalPal");
        System.out.println(res.read("8888"));

        res.write("AAAA", "EIEIEIEI");
        System.out.println(res.read("AAAA"));
    }
}
