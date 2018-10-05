
import java.lang.reflect.*;

public class NoAccessInvocation implements InvocationHandler {

	Vehicle vehicle;
	 
	public NoAccessInvocation(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
 
	public Object invoke(Object proxy, Method method, Object[] args) 
			throws IllegalAccessException {
  
		try {
			if (method.getName().startsWith("get")) {
				return method.invoke(vehicle, args);
   			} else if (method.getName().equals("setRating")) {
				return method.invoke(vehicle, args);
			} else if (method.getName().startsWith("set")) {
				throw new IllegalAccessException();
			} 
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } 
		return null;
	}
}
