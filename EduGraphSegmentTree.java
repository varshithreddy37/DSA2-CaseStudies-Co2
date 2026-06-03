class SegmentTree {
    int[] tree;
    int n;

    SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l)
            return 0;

        if (l <= start && end <= r)
            return tree[node];

        int mid = (start + end) / 2;

        return query(2 * node + 1, start, mid, l, r)
                + query(2 * node + 2, mid + 1, end, l, r);
    }

    void update(int node, int start, int end, int idx, int value) {
        if (start == end) {
            tree[node] = value;
        } else {
            int mid = (start + end) / 2;

            if (idx <= mid)
                update(2 * node + 1, start, mid, idx, value);
            else
                update(2 * node + 2, mid + 1, end, idx, value);

            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }
}

public class EduGraphSegmentTree {
    public static void main(String[] args) {

        int[] scores = {70, 85, 90, 65, 88, 92, 75};

        SegmentTree st = new SegmentTree(scores);

        System.out.println("Student Scores:");
        for (int score : scores)
            System.out.print(score + " ");

        System.out.println("\n");

        System.out.println("Sum of scores from index 1 to 4: "
                + st.query(0, 0, scores.length - 1, 1, 4));

        st.update(0, 0, scores.length - 1, 3, 80);

        System.out.println("Updated score at index 3 to 80");

        System.out.println("New sum from index 1 to 4: "
                + st.query(0, 0, scores.length - 1, 1, 4));
    }
}