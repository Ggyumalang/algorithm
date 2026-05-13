package exercise.study.desgin_pattern;

public class Proxy {

    public static void main(String[] args) {
        Image image = new ProxyImage("test_image.jpg");

        System.out.println("--- 첫 번째 디스플레이 호출 ---");
        image.display();
        System.out.println("--- 두 번째 디스플레이 호출 ---");
        image.display();
    }

    // 1. 공통 인터페이스
    interface Image {
        void display();
    }

    // 2. 실제 객체 (무거운 작업을 가정)
    static class RealImage implements Image {
        private String fileName;

        public RealImage(String fileName) {
            this.fileName = fileName;
            loadFromDisk(fileName); // 생성 시 디스크에서 읽어오는 무거운 작업
        }

        private void loadFromDisk(String fileName) {
            System.out.println("디스크에서 " + fileName + " 로딩 중...");
        }

        @Override public void display() {
            System.out.println(fileName + " 화면에 출력");
        }
    }

    // 3. 프록시 객체 (접근 제어 / 지연 로딩)
    static class ProxyImage implements Image {
        private RealImage realImage;
        private String fileName;

        public ProxyImage(String fileName) {
            this.fileName = fileName;
        }

        @Override public void display() {
            // 실제 이미지가 처음 display() 호출될 때까지 생성을 미룸 (지연 로딩)
            if(realImage == null) {
                realImage = new RealImage(fileName);
            }
            realImage.display();
        }
    }
}
