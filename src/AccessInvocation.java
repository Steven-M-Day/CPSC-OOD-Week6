
import java.lang.reflect.*;

public class AccessInvocation implements InvocationHandler {

	Vehicle vehicle;
	 
	public AccessInvocation(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
 
	public Object invoke(Object proxy, Method method, Object[] args) 
			throws IllegalAccessException {
  
		try {
			if (method.getName().startsWith("get")) {
				return method.invoke(vehicle, args);
   			} else if (method.getName().equals("setRating")) {
				throw new IllegalAccessException();
			} else if (method.getName().startsWith("set")) {
				return method.invoke(vehicle, args);
			} 
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } 
		return null;
	}
}
