package org.red.singleton;

import org.red.factory.Map;
import org.red.observer.Mouse;
import org.red.observer.MouseEvent;

/**
 * Applies the singleton pattern to a few previous types to
 * show where it might be applicable in typical graphics processing
 * scenarios.
 * 
 * Running this ain't particularly exciting, it's the same output as
 * the previous chapter, org.red.factory.Application
 */
public class Application {
	public static void main(String[] args) {
		GlobalGraphicsContext ctx = GlobalGraphicsContext.getInstance();

		ctx.register(MapCache.getInstance().get());

		// This could be a singleton too - you only want to track one
		// mouse, right?
		Mouse mouse = new Mouse(ctx.getWindowRef());
		mouse.bind(Application::onMouseEvent);

		new Thread(ctx).start();
		ctx.run(); // Oops, ran it twice.
	}

	public static void onMouseEvent( MouseEvent event ) {
		if ( event.getType() == MouseEvent.Type.CLICK ) {
			Map map = MapCache.getInstance().get();

			if ( map != null ) {
				GlobalGraphicsContext.getInstance()
					.remove(0)
					.register(map);
			}
		}
	}
}
