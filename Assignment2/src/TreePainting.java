import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import javax.swing.JComponent;


/**
* This class creates an object that looks like a tree.
* @author Haimi Nguyen
* @date 02/11/2017
**/

public class TreePainting extends JComponent implements MouseListener{

	/** Number of branches **/
	public static final int NUM_BRANCHES = 8;

	/** Diameter of the blossoms **/
	public static final int BLOSSOM_DIAM = 25;

	/** Golden ratio makes 
	the trunk length:branch length ratio aesthetically appealing **/
	public static final double GOLDEN_RATIO = 1.618;

	/** Points where mouse is pressed and released **/
	public Point2D pressedPoint, releasedPoint;

	/** constructor of TreePainting class **/
	public TreePainting(){
		
	}

	/** implement the mouseClicked method from MouseListener interface **/
	public void mouseClicked (MouseEvent e){
		
	}

	/** implement the mousePressed method from MouseListener interface
	get coordinates of the point where mouse is pressed **/
	public void mousePressed (MouseEvent e){
		pressedPoint = new Point2D.Double(e.getX(),e.getY());
		
	}

	/** implement the mouseReleased method from MouseListener interface
	get coordinates of the point where mouse is released **/
	public void mouseReleased (MouseEvent e){
		releasedPoint = new Point2D.Double(e.getX(),e.getY());
		repaint();
		
	}

	/** implement the mouseEntered method from MouseListener interface **/
	public void mouseEntered (MouseEvent e){

	}

	/** implement the mouseExited method from MouseListener interface **/
	public void mouseExited (MouseEvent e){

	}

	/**
	* Compute the x and y coordinates of the point that is length away
	* from point p at an angle
	* @param p the original point
	* @param length distance from original point and new point
	* @param angle the angle between line connecting two points and horizontal line through p
	* @return a 2D point
	**/

	public Point2D computeEndpoint(Point2D p, double length, double angle){
		Point2D branchTip = new Point2D.Double(p.getX()+length*Math.cos(angle),	//calculate x from cos
								  p.getY()+length*Math.sin(angle));	//calcualte y from sin
		return branchTip;
	}

	/** override paint method from JComponent class
	paint method will execute painting of trees and background
	based on the points from mouse's behaviors
	* @param g graphics g to draw on */

	public void paint (Graphics g){
		
		// paint the background
		paintBackground(g);

		// evoke methods in MouseListener
		addMouseListener(this);

		if (releasedPoint != null){		// if user has released mouse
		paintTrunk(g);					// then paint the trunk
		paintBranches(g);				// then paint the branches (+blossoms)

		}

	}

	/** paintTrunk method paints a trunk
	* @param g graphics to draw on **/

	public void paintTrunk(Graphics g){
		
		g.setColor(new Color(36,150,13));		// set color of trunk to be green
		Graphics2D g2D = (Graphics2D) g;		// cast g to a Graphics2D object
		g2D.setStroke(new BasicStroke(10));		// set the thickness of trunk
		g2D.drawLine((int)pressedPoint.getX(),(int)pressedPoint.getY(),		// draw trunk from pressed point
					(int)releasedPoint.getX(),(int)releasedPoint.getY());	// to released point
	}

	/** paintBranches method paints branches starting out radially
	from the tip of trunk
	* @param g the graphics to draw **/

	public void paintBranches(Graphics g){
		// calculate the angle of the trunk
		double theta = Math.atan2(pressedPoint.getY()-releasedPoint.getY(), 
								  pressedPoint.getX()-releasedPoint.getX());
		Graphics2D g2D = (Graphics2D) g;	// casting g to Graphics2D
		g.setColor(new Color(36,150,13));	// set color of branches to be green

		// calculate length of trunk
		double distance = Math.sqrt(Math.pow(pressedPoint.getX()-releasedPoint.getX(),2)
									+Math.pow(pressedPoint.getY()-releasedPoint.getY(),2));
		
		// loop to create branches
		for (double d = 1+theta; d < 5+theta; d = d + 4/(double)NUM_BRANCHES){	// branches are within certain angle range and only a number of branches are displayed 
			Point2D tip = computeEndpoint(releasedPoint, distance/GOLDEN_RATIO, d); // find the tip of each branch 
			g2D.setStroke(new BasicStroke(7));										// set thickness of branches
			g2D.drawLine((int)releasedPoint.getX(),(int)releasedPoint.getY(),		// draw branches from tip of trunk to tip of each branch
						(int)tip.getX(),(int)tip.getY());
			paintBlossoms(g, tip);													// call paintBlossoms to draw a circle at the tip of the branch, current color has been changed
			g.setColor(new Color(36,150,13));										// reset to the original color of the branches so that branches have same color
		}
	}

	/** paintBlossoms method paints circle at a point given
	* @param g graphics to draw
	* @param start the center of the circle **/

	public void paintBlossoms(Graphics g, Point2D start){
		g.setColor(new Color((int)(Math.random() * 0x1000000)));			// set random color for circles

		g.fillOval((int)(start.getX()-BLOSSOM_DIAM/4),(int)(start.getY()-BLOSSOM_DIAM/4),(int)BLOSSOM_DIAM/2,(int)BLOSSOM_DIAM/2); // draw circles with start point at center and fixed diameter
	}

	/** paintBackground method gives the background a certain color 
	* @param g graphics to draw **/

	protected void paintBackground(Graphics g){
		g.setColor(new Color(0,0,0));			// set color to black
		g.fillRect(0,0,getWidth(),getHeight());	// draw a black rectangle as big as the background
	}

}