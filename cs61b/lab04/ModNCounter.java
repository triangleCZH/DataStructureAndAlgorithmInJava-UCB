public class ModNCounter extends Counter{
    private int Mod;
	public ModNCounter(int Mod){
		this.Mod=Mod;
	}
	public int value(){
		return super.value() % Mod;
	}
	public static void main(String[] args){
		/*ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
		modCounter.reset();
		modCounter.increment();
		System.out.println(modCounter.value()); // still prints 1*/
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
	}
}
