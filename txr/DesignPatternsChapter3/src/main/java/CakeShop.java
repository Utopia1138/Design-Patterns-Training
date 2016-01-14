import org.txr.designpatterns.chapter3.cake.BiscuitCake;
import org.txr.designpatterns.chapter3.cake.Cake;
import org.txr.designpatterns.chapter3.cake.SpongeCake;
import org.txr.designpatterns.chapter3.cake.decorators.FruitDecorator;
import org.txr.designpatterns.chapter3.cake.decorators.SugarIcingDecorator;
import org.txr.designpatterns.chapter3.cake.decorators.WhippedCreamDecorator;

public class CakeShop {
		public static void main (String [] args) {
		// simple cake
			Cake cake = new SpongeCake();
			Cake simpleCake = new SugarIcingDecorator(cake);
			showCake(simpleCake);
		// elaborated cake
			Cake base = new BiscuitCake();
			Cake fruitCake = new FruitDecorator(base);
			Cake whippedCreamCake = new WhippedCreamDecorator(fruitCake);
		
			showCake(whippedCreamCake);

			
		}
		
		public static void showCake(Cake cake) {
			System.out.println(cake.cost());
			System.out.println(cake.getDescription());
		}
}
