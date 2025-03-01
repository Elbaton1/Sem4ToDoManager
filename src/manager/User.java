package manager;
public class User {
    private String nm;   
    private TList tlist; 

    public User(String n) {
        nm = n;
        tlist = new TList();
    }
 
    public String getNm() {
        return nm;
    }

    public void addTsk(Task t) {
        tlist.addTsk(t);
    }

    public void markTskCmplt(int idx) {
        tlist.markTskCmplt(idx);
    }

    public void delTsk(int idx) {
        tlist.delTsk(idx);
    }

    public String getTskLst() {
        return tlist.getAllTsk();
    }
}
