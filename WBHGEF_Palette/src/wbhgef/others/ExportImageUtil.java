package wbhgef.others;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;

/**
 * 
 * ExportImageUtil
 * <p>
 * GEF导出图片工具类
 * </p>
 * 
 * @see
 */
public class ExportImageUtil {
    
    public static boolean export(IEditorPart editorPart, File file, int format)
            throws IOException {
        Assert.isNotNull(editorPart, "null editorPart passed to export image.");
        Assert.isNotNull(file, "null file passed to export image.");
        if (format != SWT.IMAGE_BMP && format != SWT.IMAGE_JPEG
                && format != SWT.IMAGE_PNG)
            throw new IllegalArgumentException(
                    "Export image's format is not supported.");
        if (!(editorPart instanceof GraphicalEditor))
            return false;
        GraphicalViewer viewer = editorPart.getAdapter(GraphicalViewer.class);
        if (viewer == null)
            return false;
        LayerManager layerManager = (LayerManager) viewer.getEditPartRegistry()
                .get(LayerManager.ID);
        IFigure rootFigure = layerManager
                .getLayer(LayerConstants.PRINTABLE_LAYERS);
        Rectangle bounds = rootFigure.getBounds();
        Image img = new Image(Display.getDefault(), bounds.width,
                bounds.height);
        img.setBackground(ColorConstants.white);
        GC imageGC = new GC(img);
        
        Graphics imgGraphics = new SWTGraphics(imageGC);
        imgGraphics.translate(-bounds.x, -bounds.y);
        rootFigure.paint(imgGraphics);
        
        ImageLoader loader = new ImageLoader();
        loader.data = new ImageData[] { img.getImageData() };
        OutputStream os = new FileOutputStream(file);
        loader.save(os, format);
        
        imageGC.dispose();
        img.dispose();
        os.close();
        return true;
    }
}
