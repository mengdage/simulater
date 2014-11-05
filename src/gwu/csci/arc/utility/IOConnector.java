package gwu.csci.arc.utility;

import gwu.csci.arc.gui.UI;

import java.util.Random;

public class IOConnector {
	private static IOConnector io;
	private UI ui;
	private IOConnector() {
		// TODO Auto-generated constructor stub
		
	}
	public static IOConnector getInstance() {
		if (io == null) {
			io = new IOConnector();
		}
		return io;
	}

	public void printString(String s) {
		System.out.println(s);
	}
	
	
	public int readInt() {
		if(ui == null){
			ui = UI.getUIinstance();
		}
		return ui.returnPI();
	}
	
	public void printInt(int i) {
		printString(i+"");
	}
	public void printErr(String s) {
		System.err.println(s);
	}
	
	public int readIntFromConsole() {
		return ui.getPI();
	}
	public void printToConsole(String s) {
//		System.out.println(s);
		if(ui == null){
			ui = UI.getUIinstance();
		}
		ui.print2console(s);
	}
	public void printToLog(String s) {
//		System.out.println(s);
		if(ui == null){
			ui = UI.getUIinstance();
		}
		ui.print2log(s);
	}
}
