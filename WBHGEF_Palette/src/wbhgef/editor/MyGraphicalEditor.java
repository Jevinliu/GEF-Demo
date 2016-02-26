package wbhgef.editor;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import wbhgef.actions.AppContextMenuProvider;
import wbhgef.actions.CopyNodeAction;
import wbhgef.actions.PasteNodeAction;
import wbhgef.actions.RenameAction;
import wbhgef.editpart.AppEditPartFactory;
import wbhgef.model.Employee;
import wbhgef.model.Enterprise;
import wbhgef.model.NodeCreationFactory;
import wbhgef.model.Service;
import wbhgef.part.tree.AppTreeEditPartFactory;

public class MyGraphicalEditor extends GraphicalEditorWithPalette {

	public static final String ID = "WBHGEF.mygraphicaleditor";
	private GraphicalViewer viewer;
	private Enterprise model;
	private KeyHandler keyHandler;

	public MyGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		viewer = getGraphicalViewer();
		viewer.setEditPartFactory(new AppEditPartFactory()); // 添加editpart工厂，通过工厂创建editpart

		// 缩放实现
		double[] zoomLevels;
		ArrayList<String> zoomContributions;
		ScalableRootEditPart rootEditPart = new ScalableRootEditPart();
		viewer.setRootEditPart(rootEditPart);
		ZoomManager manager = rootEditPart.getZoomManager();
		getActionRegistry().registerAction(new ZoomInAction(manager));
		getActionRegistry().registerAction(new ZoomOutAction(manager));
		zoomLevels = new double[] { 0.25, 0.5, 0.75, 1.0, 1.5, 2.0, 2.5, 3.0,
				4.0, 5.0, 10.0, 20.0 };
		manager.setZoomLevels(zoomLevels);

		zoomContributions = new ArrayList<String>();
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		manager.setZoomLevelContributions(zoomContributions);

		// 快捷键的实现
		KeyHandler keyHandler = new KeyHandler();
		keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
				getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		keyHandler.put(KeyStroke.getPressed('+', SWT.KEYPAD_ADD, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		keyHandler.put(KeyStroke.getPressed('-', SWT.KEYPAD_SUBTRACT, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));
		viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.NONE),
				MouseWheelZoomHandler.SINGLETON);
		viewer.setKeyHandler(keyHandler);

		// 为viewer添加上下文菜单
		ContextMenuProvider provider = new AppContextMenuProvider(viewer,
				getActionRegistry());
		viewer.setContextMenu(provider);

	}

	@Override
	protected void createActions() {
		super.createActions();
		// 对Action进行注册
		ActionRegistry registry = getActionRegistry();
		IAction action = new RenameAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		IAction copyAction = new CopyNodeAction(this);
		registry.registerAction(copyAction);
		getSelectionActions().add(copyAction.getId());
		IAction pasteAction = new PasteNodeAction(this);
		registry.registerAction(pasteAction);
		getSelectionActions().add(pasteAction.getId());

	}

	@Override
	public Object getAdapter(Class type) {
		if (type == ZoomManager.class) {
			return ((ScalableRootEditPart) getGraphicalViewer()
					.getRootEditPart()).getZoomManager();
		}
		if (type == IContentOutlinePage.class) {
			return new OutlinePage();
		}
		return super.getAdapter(type);
	}

	@Override
	protected void initializeGraphicalViewer() {
		model = createEnterprise();
		viewer.setContents(model); // 设置model
		viewer.addDropTargetListener(new MyTemplateTransferDropTargetListener(
				viewer));

	}

	@Override
	protected void initializePaletteViewer() { // 调色板视图器
		super.initializePaletteViewer();
		PaletteViewer pv = getPaletteViewer();
		pv.addDragSourceListener(new TemplateTransferDragSourceListener(pv));
	}

	public Enterprise createEnterprise() {
		Enterprise enterprise = new Enterprise();
		enterprise.setName("同福客栈");
		enterprise.setAddress("西绒线胡同七号");
		enterprise.setCapital(8000000);
		Service service_QianTang = new Service();
		service_QianTang.setName("前堂");
		service_QianTang.setEtage(2);
		service_QianTang.setLayout(new Rectangle(30, 50, 250, 150));
		Employee empolyee_1 = new Employee();
		empolyee_1.setName("掌柜");
		empolyee_1.setPrenom("佟");
		empolyee_1.setLayout(new Rectangle(25, 40, 60, 40));
		service_QianTang.addChild(empolyee_1);
		Employee empolyee_2 = new Employee();
		empolyee_2.setName("展堂");
		empolyee_2.setPrenom("白");
		empolyee_2.setLayout(new Rectangle(100, 60, 60, 40));
		service_QianTang.addChild(empolyee_2);
		Employee empolyee_3 = new Employee();
		empolyee_3.setName("秀才");
		empolyee_3.setPrenom("吕");
		empolyee_3.setLayout(new Rectangle(180, 90, 60, 40));
		service_QianTang.addChild(empolyee_3);
		enterprise.addChild(service_QianTang);
		Service service_HouChu = new Service();
		service_HouChu.setName("后厨");
		service_HouChu.setEtage(1);
		service_HouChu.setLayout(new Rectangle(220, 230, 250, 150));
		Employee employee_4 = new Employee();
		employee_4.setName("大嘴");
		employee_4.setPrenom("李");
		employee_4.setLayout(new Rectangle(40, 70, 60, 40));
		service_HouChu.addChild(employee_4);
		Employee employee_5 = new Employee();
		employee_5.setName("芙蓉");
		employee_5.setPrenom("郭");
		employee_5.setLayout(new Rectangle(170, 100, 60, 40));
		service_HouChu.addChild(employee_5);
		enterprise.addChild(service_HouChu);
		return enterprise;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {

	}

	// outline 视图

	protected class OutlinePage extends ContentOutlinePage {
		private SashForm sash;
		private DisposeListener disposeListener;
		private ScrollableThumbnail thumbnail;

		public OutlinePage() {
			super(new TreeViewer());
		}

		@Override
		public void createControl(Composite parent) {
			sash = new SashForm(parent, SWT.VERTICAL);
			getViewer().createControl(sash);
			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(new AppTreeEditPartFactory());
			getViewer().setContents(model);

			getSelectionSynchronizer().addViewer(getViewer());

			// 添加鸟瞰图
			Canvas canvas = new Canvas(sash, SWT.BORDER);
			LightweightSystem lws = new LightweightSystem(canvas);

			thumbnail = new ScrollableThumbnail(
					(Viewport) ((ScalableRootEditPart) getGraphicalViewer()
							.getRootEditPart()).getFigure()); // 获取比例化得rooteditpart
			thumbnail.setSource(((ScalableRootEditPart) getGraphicalViewer()
					.getRootEditPart())
					.getLayer(LayerConstants.PRINTABLE_LAYERS));
			lws.setContents(thumbnail);
			disposeListener = new DisposeListener() {

				@Override
				public void widgetDisposed(DisposeEvent e) {
					if (thumbnail != null) {
						thumbnail.deactivate();
						thumbnail = null;
					}
				}
			};
			getGraphicalViewer().getControl().addDisposeListener(
					disposeListener);

			// 复制粘贴
			IActionBars bars = getSite().getActionBars();
			ActionRegistry ar = getActionRegistry();
			bars.setGlobalActionHandler(ActionFactory.COPY.getId(),
					ar.getAction(ActionFactory.COPY.getId()));
			bars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
					ar.getAction(ActionFactory.PASTE.getId()));

		}

		@Override
		public void init(IPageSite pageSite) {
			super.init(pageSite);
			IActionBars bars = getSite().getActionBars();
			bars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
					getActionRegistry().getAction(ActionFactory.UNDO.getId()));
			bars.setGlobalActionHandler(ActionFactory.REDO.getId(),
					getActionRegistry().getAction(ActionFactory.REDO.getId()));
			bars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
					getActionRegistry().getAction(ActionFactory.DELETE.getId()));
			bars.updateActionBars();
			getViewer().setKeyHandler(keyHandler);
			ContextMenuProvider provider = new AppContextMenuProvider(
					getViewer(), getActionRegistry());
			getViewer().setContextMenu(provider);

		}

		@Override
		public Control getControl() {
			return sash;
		}

		public void dispose() {
			getSelectionSynchronizer().removeViewer(getViewer());
			if (getGraphicalViewer().getControl() != null
					&& !getGraphicalViewer().getControl().isDisposed()) {
				getGraphicalViewer().getControl().removeDisposeListener(
						disposeListener);
			}
			super.dispose();
		}

	}

	// 画板树
	@Override
	protected PaletteRoot getPaletteRoot() {
		PaletteRoot root = new PaletteRoot();
		PaletteGroup manipGroup = new PaletteGroup("编辑对象工具");
		root.add(manipGroup);
		SelectionToolEntry selectionToolEntry = new SelectionToolEntry();
		manipGroup.add(selectionToolEntry);
		manipGroup.add(new MarqueeToolEntry()); // 选取框工具
		root.setDefaultEntry(selectionToolEntry);

		PaletteSeparator sep2 = new PaletteSeparator();
		root.add(sep2);
		PaletteGroup instGroup = new PaletteGroup("创建元素工具");
		root.add(instGroup);
		instGroup.add(new CreationToolEntry("", "", new NodeCreationFactory(
				Service.class), null, null));
		instGroup.add(new CombinedTemplateCreationEntry("Service",
				"创建一个service", new NodeCreationFactory(Service.class), null,
				null));
		instGroup.add(new CombinedTemplateCreationEntry("Employee",
				"创建一个employee类", new NodeCreationFactory(Employee.class), null,
				null));
		return root;
	}

}
