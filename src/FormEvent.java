import java.util.EventObject;

public class FormEvent extends EventObject {
	
    private static final long serialVersionUID = 3302432343515592238L;
    
    private int numGenInt;
	
	public int getNumGenInt() {
		return numGenInt;
	}

	public void setNumGenInt(int numGenInt) {
		this.numGenInt = numGenInt;
	}

	public FormEvent(Object source){
		super(source);
	}
	
	public FormEvent(Object source, int numGenInt){
		super(source);
		this.numGenInt = numGenInt;
	}
}
