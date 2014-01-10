package classhotswap;

public class HotSwapLoader extends ClassLoader {
	public Class loadByte(byte b[]){
		return this.defineClass(null, b, 0, b.length);
	}
}
