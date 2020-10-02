import java.util.LinkedList;

public class _DataStructureInJava {

    private static void LinkedListAsQueue() {
        LinkedList<Integer> q = new LinkedList<>();
        int n;

        // 값이 작은 인덱스로 삽입
        q.push(1);
        q.push(2);
        q.push(3);

        // 값이 높은 인덱스로 삽입
        q.add(-1);
        q.add(-2);
        q.add(-3);

        //n = q.pop(); // 인덱스 0 부터 pop
        //n = q.poll(); // 인덱스 0 부터 poll
        n = q.remove(); // 인덱스 0 부터 remove
        System.out.println("end");
    }

    public static void main(String[] args) {
        LinkedListAsQueue();
    }
}
