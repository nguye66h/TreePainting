import java.awt.Color;
import java.awt.Graphics;

/** This class inherits TreePainting class
	it draws the same tree with a gray background
	@author: Haimi Nguyen
	@date: 02/17/2017 **/

public class WinterTreePainting extends TreePainting{

/** override paintBackground method to make background gray 
	@param g graphics to draw 
	**/
	protected void paintBackground(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(0,0,getWidth(),getHeight());
	}
}