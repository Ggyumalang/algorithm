package exercise.study.desgin_pattern;

public class Decorator {

    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " : " + coffee.getCost() + "원");

        MilkDecorator milkDecorator = new MilkDecorator(coffee);
        System.out.println(milkDecorator.getDescription() + " : " + milkDecorator.getCost() + "원");

        SyrupDecorator syrupDecorator = new SyrupDecorator(milkDecorator);
        System.out.println(syrupDecorator.getDescription() + " : " + syrupDecorator.getCost() + "원");

        milkDecorator = new MilkDecorator(syrupDecorator);
        System.out.println(milkDecorator.getDescription() + " : " + milkDecorator.getCost() + "원");
    }

    // 1. 기본 컴포넌트 인터페이스
    interface Coffee {
        String getDescription();
        int getCost();
    }


    // 2. 기본 객체
    static class SimpleCoffee implements Coffee {

        @Override public String getDescription() {
            return "기본 커피";
        }

        @Override public int getCost() {
            return 2000;
        }
    }

    // 3. 데코레이터 추상 클래스
    abstract static class CoffeeDecorator implements Coffee {
        protected Coffee decoratedCoffee; // 컴포지션

        public CoffeeDecorator(Coffee coffee) {
            this.decoratedCoffee = coffee;
        }

        public String getDescription() {return  decoratedCoffee.getDescription();}
        public int getCost() {return  decoratedCoffee.getCost();}
    }

    // 4. 구체적인 데코레이터들
    static class MilkDecorator extends CoffeeDecorator {

        public MilkDecorator(Coffee coffee) {
            super(coffee);
        }

        public String getDescription() {return  decoratedCoffee.getDescription() + ", 우유 추가";}
        public int getCost() {return  decoratedCoffee.getCost() + 500;}
    }

    static class SyrupDecorator extends CoffeeDecorator {

        public SyrupDecorator(Coffee coffee) {
            super(coffee);
        }

        public String getDescription() {return  decoratedCoffee.getDescription() + ", 시럽 추가";}
        public int getCost() {return  decoratedCoffee.getCost() + 300;}
    }
}
