package com.epsilonlabsllc.funderpable;

import org.pakhama.vaadin.mvp.ui.MVPApplication;
import org.vaadin.artur.icepush.ICEPush;

import com.epsilonlabsllc.funderpable.ide.presenter.IDEPresenter;
import com.epsilonlabsllc.funderpable.model.ProjectSession;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class Funderpable extends MVPApplication {
 
//	private VerticalLayout layout = new VerticalLayout();
//	private HorizontalLayout header = new HorizontalLayout();
//	private HorizontalLayout mainLayout = new HorizontalLayout();
//	private FileBrowserPresenter fileBrowser;
//	private CollaborativeEditor editor;
//	private ChatPresenter chatPresenter;
//	private HorizontalSplitPanel redBlueSplit = new HorizontalSplitPanel();
//	private HorizontalSplitPanel blueGreenSplit = new HorizontalSplitPanel();
//	
//	private static EditorSession editorSession;
//	private static ChatSession chatSession;
//	
//	private ICEPush pusher = new ICEPush();
//	private String username;
	
	//TODO: Short-term: fix layout, add save button and attach to saveFile method in EditorSession
	//TODO: Long-term: add support for multiple files per sessions and multiple sessions for the application
	//TODO: Hackathon: add figure out how input params will work
	
	private static final ProjectSession projectSession = new ProjectSession(4L, null);
	private ICEPush pusher =  new ICEPush();
	
	private Window window = new Window();
	private VerticalLayout layout = new VerticalLayout();
	
    @Override
    public void init() {
    	
    	IDEPresenter idePresenter = createPresenter(IDEPresenter.class);
    	idePresenter.init(projectSession, "caputit1", pusher);
    	
    	setMainWindow(window);
    	window.setSizeFull();
    	window.getContent().setSizeFull();
    	window.addComponent(layout);
    	Component ide = idePresenter.getView().getComponent();
    	layout.addComponent(pusher);
    	layout.setSizeFull();
    	layout.setExpandRatio(pusher, 0.0f);
    	layout.addComponent(ide);
    	layout.setExpandRatio(ide, 1.0f);
    	ide.setSizeFull();
    	
//    	this.username = "caputit1";
//
//    	if(editorSession == null) editorSession = new EditorSession(new File("C:\\Users\\Tom\\Documents\\Personal\\Java\\collabeditor\\src\\main\\java\\com\\epsilonlabsllc\\collabeditor\\Funderpable.java")); // file being edited
//    	if(chatSession == null) chatSession = new ChatSession();
//    	
//    	
//    	this.editor = new CollaborativeEditor(editorSession, pusher);
//    	this.chatPresenter = createPresenter(ChatPresenter.class);
//    	this.chatPresenter.init(1L, this.username, this.pusher);
//    	this.fileBrowser = createPresenter(FileBrowserPresenter.class);
//    	this.fileBrowser.init(new File("C:\\Users\\Tom\\Documents\\Personal\\Java\\collabeditor\\src\\main\\java\\com")); //project directory
//    	this.mainLayout.addComponent(pusher);
//    	this.mainLayout.setExpandRatio(pusher, 0);
//    	
//        Window mainWindow = new Window("Combinedcollaborativeeditor Application");
//        setMainWindow(mainWindow);
//        
//        layout.setSizeFull();
//        mainLayout.setSizeFull();
//        editor.setSizeFull();
//        
//        Label title = new Label(editorSession.getFile().getName());
//        Button saveButton = new Button("Save");
//        saveButton.addListener(new ClickListener() {
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//				editorSession.saveFile();
//			}
//		});
//        
//        header.addComponent(title);
//        header.addComponent(saveButton);
//        
//        redBlueSplit.setFirstComponent(this.fileBrowser.getView().getComponent());
//        redBlueSplit.setSecondComponent(editor);
//        redBlueSplit.setMinSplitPosition(150.0f, Component.UNITS_PIXELS);
//        redBlueSplit.setSplitPosition(350.0f, Component.UNITS_PIXELS);
//        
//        blueGreenSplit.setFirstComponent(redBlueSplit);
//        blueGreenSplit.setSecondComponent(this.chatPresenter.getView().getComponent());
//        blueGreenSplit.setMinSplitPosition(150.0f, Component.UNITS_PIXELS);
//        blueGreenSplit.setSplitPosition(250.0f, Component.UNITS_PIXELS, true);
//        blueGreenSplit.setSizeFull();
//        mainLayout.addComponent(blueGreenSplit);
//        mainLayout.setExpandRatio(blueGreenSplit, 1);
//        
//        layout.addComponent(header);
//        layout.addComponent(mainLayout);
//        layout.setExpandRatio(mainLayout, 1.0f);
//        layout.setExpandRatio(header, 0.0f);
//        
//        mainWindow.setContent(layout);
    }
}

