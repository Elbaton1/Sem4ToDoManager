package manager;
public class TList {
    private class Node {
        Task tsk;  
        Node nxt;  

        Node(Task t) {
            tsk = t;
            nxt = null;
        }
    }

    private Node hd;  

    public TList() {
        hd = null;
    }

    public void addTsk(Task t) {
        if (hd == null) {
            hd = new Node(t);
        } else {
            Node cur = hd;
            while (cur.nxt != null) {
                cur = cur.nxt;
            }
            cur.nxt = new Node(t);
        }
    }

    public void markTskCmplt(int idx) {
        Node cur = hd;
        int i = 0;
        while (cur != null) {
            if (i == idx) {
                cur.tsk.markCmplt();
                break;
            }
            i++;
            cur = cur.nxt;
        }
    }

    public void delTsk(int idx) {
        if (hd == null)
            return;
        if (idx == 0) {
            hd = hd.nxt;
            return;
        }
        Node cur = hd;
        int i = 0;
        while (cur.nxt != null) {
            if (i == idx - 1) {
                cur.nxt = cur.nxt.nxt;
                break;
            }
            i++;
            cur = cur.nxt;
        }
    }

    public String getAllTsk() {
        String s = "";
        Node cur = hd;
        int i = 0;
        while (cur != null) {
            s += i + ". " + cur.tsk.toString() + "\n";
            i++;
            cur = cur.nxt;
        }
        return s;
    }
}
