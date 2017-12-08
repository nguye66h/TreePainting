import java.awt.Color;
import java.awt.Graphics;

/** This class inherits TreePainting class
	it draws the same tree with a blue background
	@author: Haimi Nguyen
	@date: 02/17/2017 **/

public class FallTreePainting extends TreePainting{

/** override paintBackground method to make background blue 
	@param g graphics to draw 
	**/
	protected void paintBackground(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(0,0,getWidth(),getHeight());
	}
}