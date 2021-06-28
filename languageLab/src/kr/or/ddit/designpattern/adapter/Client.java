package kr.or.ddit.designpattern.adapter;

public class Client {
	private Target target;
	
	public void setTarget(Target target) {
		this.target = target;
	}

	public static void main(String[] args) {
		Client client = new Client();
//		Target target = new OtherConcrete();
		Adaptee adaptee = new Adaptee();
		Target target = new Adapter(adaptee);
		
		client.setTarget(target);
		client.target.request();
	}
}
