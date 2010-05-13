package window;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import processing.core.PApplet;

@SuppressWarnings("serial")

public class BorderlessWindow extends JFrame {
	
	public static int MODIFIER_MASK = MouseEvent.CTRL_DOWN_MASK | MouseEvent.SHIFT_DOWN_MASK;

	public BorderlessWindow(PApplet app, String title) {
		
		super(title);

		setSize(400, 300);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.BLACK);

		Mover wm = new Mover();

		JPanel p = (JPanel)getContentPane();
		p.setBackground(Color.BLACK);
		p.addMouseListener(wm);
		p.addMouseMotionListener(wm);

		// prevent applet resizing (borked in opengl in java mode)
		p.setLayout(null);
		p.add(app);

		// allow moving using the app itself
		app.addMouseListener(wm);
		app.addMouseMotionListener(wm);

		setVisible(true);

		System.err.println("Hold down CTRL+SHIFT and drag to move window, double click to maximize");
		
	}

	public class Mover implements MouseMotionListener, MouseListener {
	   
		protected Point clickStart = new Point(), winClickStart = new Point();
	    protected boolean expanded = false;

	    public void mouseDragged(MouseEvent e) {
	    	if ((e.getModifiersEx() & MODIFIER_MASK) != MODIFIER_MASK) return;
	        Point p = e.getPoint();
	        SwingUtilities.convertPointToScreen(p, e.getComponent());
	        p.translate(winClickStart.x - clickStart.x, winClickStart.y - clickStart.y);
	        setLocation(p);
	    }

	    public void mousePressed(MouseEvent e) {
	    	if ((e.getModifiersEx() & MODIFIER_MASK) != MODIFIER_MASK) return;
	        clickStart.x = e.getX();
	        clickStart.y = e.getY();
	        SwingUtilities.convertPointToScreen(clickStart, e.getComponent());
	        getLocation(winClickStart);
	    }

	    public void mouseMoved(MouseEvent e) { }

	    public void mouseClicked(MouseEvent e) {
	    	if ((e.getModifiersEx() & MODIFIER_MASK) != MODIFIER_MASK) return;
	        if (e.getClickCount() == 2) {
	            if (expanded) {
	            	setExtendedState(JFrame.NORMAL);
	                expanded = false;
	            } else {
	            	setExtendedState(JFrame.MAXIMIZED_BOTH);
	            	expanded = true;
	            }
	        }
	    }

	    public void mouseReleased(MouseEvent e) { }
	    public void mouseEntered(MouseEvent e) { }
	    public void mouseExited(MouseEvent e) { }
	}
}