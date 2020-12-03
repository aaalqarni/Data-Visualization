package prefuse.demos;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

import prefuse.render.AbstractShapeRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

public class FinalRenderer extends AbstractShapeRenderer
{
	//protected RectangularShape m_box = new Rectangle2D.Double();
	protected Ellipse2D m_box = new Ellipse2D.Double();
	
	@Override
	protected Shape getRawShape(VisualItem item) 
	{	
		m_box.setFrame(item.getX(), item.getY(), (Integer) item.get("age")/3, (Integer) item.get("age")/3);
		return m_box;
	}
}
