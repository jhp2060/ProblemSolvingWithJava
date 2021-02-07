import java.util.LinkedList;

public class _DataStructureInJava {

    private static void LinkedListAsQueue() {
        LinkedList<Integer> q = new LinkedList<>();
        int n;

        // stack : push pop
        // queue : add pop

        // 인덱스 0으로 삽입
        q.push(1);
        q.push(2);
        q.push(3);

        // 인덱스 size - 1 로 삽입
        q.add(-1);
        q.add(-2);
        q.add(-3);

        //n = q.pop(); // 인덱스 0 부터 pop
        //n = q.poll(); // 인덱스 0 부터 poll
        //n = q.pollLast() // 인덱스 size - 1부터 poll
        n = q.remove(); // 인덱스 0 부터 remove
        System.out.println("end");
    }

    public static void main(String[] args) {
        LinkedListAsQueue();
    }
}
