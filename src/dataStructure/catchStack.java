package dataStructure;

import java.util.Arrays;
import java.util.Stack;
import java.util.Vector;

/*
 [ Stack 에서 제공되는 5개 메소드 ]
 boolean empty();
 E peek();
 E pop();
 E push(E item);
 int search(Object o);

 Ref: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Stack.html
*/

public class catchStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        stack.add("apple"); // Stack은 Vector를 상속하는 자식 클래스라서 Vector에서 제공하는 add 메소드를 사용할 수 있었다.
        stack.push("banana");
        stack.push("carrot");

        // Stack 출력 방법
        // 1. toString() --> Vector의 toString()을 실행하고, 결과적으로 AbstractCollection의 toString()을 실행한다.
        System.out.println(stack.toString()); // [apple, banana, carrot]

        // 2. toArray(T[]) --> Vector의 Object[] toArray()를 실행하고, 결과적으로 Arrays.copyOf() 를 실행한다.
        String[] arr = new String[stack.size()];
        arr = stack.toArray(arr); // stack 크기보다 큰 배열을 넘기면 빈 공간에 null이 저장되고,
        // 작은 배열을 넘기면 동일한 데이터 타입의 새로운 배열로 반환한다.
        System.out.print("Print stack: ");
        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
        System.out.println();

        System.out.println(stack.peek()); // carrot

        stack.pop();
        System.out.println(stack.peek()); // banana

        System.out.println(stack.pop()); // banana

        System.out.println(stack.search("apple")); // 1
        System.out.println(stack.search("pineapple")); // -1
        System.out.println(stack.search(3)); // -1

        stack.pop();
        System.out.println(stack.empty()); // true
    }
}

// Vector는 모든 get, set에 synchronized가 선언되어 있다.
// synchronized는 해당 메소드를 실행할 때 동기화 작업을 처리해준다는 것이다.
// 그 말은 A 스레드가 어떤 임계구역에 접근하면, 동시에 접근한 B가 block 당한다는 것이다.
// 멀티스레드 환경에 필요한 설정일 수는 있으나, Vector에 담긴 데이터를 한 개씩 접근할 때마다 block, unblock 작업이 실행되기 때문에,
// 불필요한 동기화 작업이 너무 많이 발생하고, 따라서 해당 자료구조를 사용하지 않는 것이 좋다고 한다.
//
// Stack은 Vector를 상속하기 때문에 Vector와 마찬가지로 사용하지 않는 것이 좋다고 하자.
// 문제가 synchronized 키워드 때문이라면, 오버라이딩을 해서 없애고 사용하면 되지 않을까? 라는 생각이 들었다.
// synchronized는 처음 보는 키워드이고 프로세스 레벨에서 제어되는 것이라서, 혹시 오버라이딩을 함에도 절대적인 영향을 미치는 키워드인가 싶었다.
// 다행히도 이 키워드 또한 오버라이딩할 때 선언하지 않으면 사라지는 것이었다.
//
// 하지만 오버라이딩으로 키워드를 지워도 발생할 수 있는 문제가 있다.
//
// 첫째, Vector<E> vs = new Stack<>(); 처럼 참조변수를 부모 클래스로 만든 경우에 그렇다.
// 이때는 push 를 사용하지 못한다. Vector에서 제공하는 메소드(add)만 사용할 수 있다.
// 그러면 오버라이딩한 의미가 없고, 그럴 일도 거의 없을 테니 문제가 안된다고 하자.
// 둘째, 오버라이딩한 메소드에서 상속받은 메소드를 사용하는 경우이다.
// push는 synchronized가 없는 메소드라 안심하고 사용해도 될거 같지만, push 안에서 사용하는 addElement메소드가 synchronized가 붙은 메소드이다.

// Ref: https://aahc.tistory.com/8