package manager;
public class Task {
    private String desc;   
    private boolean cmplt; 

    public Task(String d) {
        desc = d;
        cmplt = false;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String d) {
        desc = d;
    }

    public boolean isCmplt() {
        return cmplt;
    }

    public void markCmplt() {
        cmplt = true;
    }

    @Override
    public String toString() {
        return desc + " [" + (cmplt ? "Done" : "Pending") + "]";
    }
}
