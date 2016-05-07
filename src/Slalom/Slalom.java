package Slalom;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import Slalom.model.Model;
import Slalom.view.View;
import Slalom.controller.Controller;
import Slalom.event.SlalomEvent;

public class Slalom 
{
	public static void main(String[] args) 
	{
    	BlockingQueue<SlalomEvent> eventQueue = new LinkedBlockingQueue<SlalomEvent>();
		View view = new View(eventQueue);
    	Model model = new Model();
		Controller controller = new Controller(model, view, eventQueue);
		controller.startController();
	}
}
