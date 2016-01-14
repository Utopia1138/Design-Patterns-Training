package chapter3;

import java.awt.*;
import java.awt.event.*;

import chapter3.paint.OilBased;
import chapter3.paint.WaterBased;

public class DecoratorDemo {

	private Frame mainFrame;

	public DecoratorDemo() {
		prepareGUI();
	}

	private int computeNewColourComponent(int newComponent,
			int existingComponent, double newOpacity) {
		int colourDifference = newComponent - existingComponent;
		int newColourComponent = (int) (newComponent - (colourDifference * newOpacity));
		return newColourComponent;
	}

	private void decorateCanvas(chapter3.paint.Paint p)
			throws InterruptedException {

		Color existingBackground = mainFrame.getBackground();

		int newRed = computeNewColourComponent(p.getR(),
				existingBackground.getRed(), p.getOpacity());
		int newGreen = computeNewColourComponent(p.getG(),
				existingBackground.getGreen(), p.getOpacity());
		int newBlue = computeNewColourComponent(p.getB(),
				existingBackground.getBlue(), p.getOpacity());

		mainFrame.setBackground(new Color(newRed, newGreen, newBlue));

		if (p.getWrappedObject() != null) {
			System.out.println("Waiting 5s before applying: r:"
					+ p.getWrappedObject());
			Thread.sleep(5000);
			decorateCanvas(p.getWrappedObject());
		}

		mainFrame.setVisible(true);
	}

	public static void main(String[] args) throws InterruptedException {
		DecoratorDemo decoratorDemo = new DecoratorDemo();

		// This doesn't work in the way I want it to work ...
		// I'd like it to apply "inside to out" rather than "outside to in".
		// Unfortunately I only realised this /after/ I'd written it!
		OilBased o = new OilBased();
		o.setColour(200, 50, 50);
		WaterBased w = new WaterBased(o);
		w.setColour(50, 50, 200);
		OilBased o2 = new OilBased(w);
		o2.setColour(0, 255, 0);

		decoratorDemo.decorateCanvas(o2);
	}

	private void prepareGUI() {
		mainFrame = new Frame("Java AWT Examples");
		mainFrame.setSize(400, 400);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		mainFrame.setVisible(true);
	}

}
