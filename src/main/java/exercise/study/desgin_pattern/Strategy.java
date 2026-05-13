package exercise.study.desgin_pattern;

import java.util.PriorityQueue;

public class Strategy {

    public static void main(String[] args) {
        // 아래와 같이 일부분의 전략 ( 정렬 순서 ) 을 외부에서 주입하는 방법
        // 현재는 지금 이 클래스가 외부이고, PriorityQueue 클래스가 내부가 된다.
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);

        pq.offer(1);
        pq.offer(2);

        System.out.println("pq = " + pq);

        // 쇼핑카트로
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setPaymentStrategy(new KakaoPayStrategy());
        shoppingCart.checkout(100);

        shoppingCart.setPaymentStrategy(new CreditCardStrategy());
        shoppingCart.checkout(100);

        shoppingCart.setPaymentStrategy(new PaypalStrategy());
        shoppingCart.checkout(100);
    }

    static class ShoppingCart {
        private PaymentStrategy paymentStrategy;

        public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
            this.paymentStrategy = paymentStrategy;
        }

        public void checkout(int amount) {
            paymentStrategy.pay(amount);
        }
    }

    interface PaymentStrategy {
        void pay(int amount);
    }

    static class CreditCardStrategy implements PaymentStrategy {
        public void pay(int amount) {
            System.out.println(amount + "원을 신용카드로 결제");
        }
    }

    static class KakaoPayStrategy implements PaymentStrategy {
        public void pay(int amount) {
            System.out.println(amount + "원을 카카오페이로 결제");
        }
    }

    static class PaypalStrategy implements PaymentStrategy {
        public void pay(int amount) {
            System.out.println(amount + "원을 페이팔로 결제");
        }
    }
}
