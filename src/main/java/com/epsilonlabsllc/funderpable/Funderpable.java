package com.epsilonlabsllc.funderpable;

import java.io.File;

import org.vaadin.artur.icepush.ICEPush;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class Funderpable extends Application {
 
	private VerticalLayout layout = new VerticalLayout();
	private HorizontalLayout header = new HorizontalLayout();
	private HorizontalLayout mainLayout = new HorizontalLayout();
	private FileBrowser fileBrowser;
	private CollaborativeEditor editor;
	private Chat chat;
	private HorizontalSplitPanel redBlueSplit = new HorizontalSplitPanel();
	private HorizontalSplitPanel blueGreenSplit = new HorizontalSplitPanel();
	
	private static EditorSession editorSession;
	private static ChatSession chatSession;
	private static File directory;
	
	private ICEPush pusher = new ICEPush();
	private String username;
	
	//TODO: Short-term: fix layout, add save button and attach to saveFile method in EditorSession
	//TODO: Long-term: add support for multiple files per sessions and multiple sessions for the application
	//TODO: Hackathon: add figure out how input params will work
	
    @Override
    public void init() {
    	this.directory = new File("C:/Users/Tom/Documents/Personal/Java/FileBrowser/src");
    	this.username = "caputit1";

    	if(editorSession == null) editorSession = new EditorSession(new File("C:/Users/Tom/Documents/Personal/Java/collabeditor/src/main/java/com/epsilonlabsllc/collabeditor/Funderpable.java"));
    	if(chatSession == null) chatSession = new ChatSession();
    	
    	this.fileBrowser = new FileBrowser(directory);
    	this.editor = new CollaborativeEditor(editorSession, pusher);
    	this.chat = new Chat(chatSession, username, pusher);
    	mainLayout.addComponent(pusher);
    	mainLayout.setExpandRatio(pusher, 0);
    	
        Window mainWindow = new Window("Combinedcollaborativeeditor Application");
        setMainWindow(mainWindow);
        
        layout.setSizeFull();
        mainLayout.setSizeFull();
        fileBrowser.setSizeFull();
        editor.setSizeFull();
        chat.setSizeFull();
        
        Label title = new Label(editorSession.getFile().getName());
        Button saveButton = new Button("Save");
        saveButton.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				editorSession.saveFile();
			}
		});
        
        header.addComponent(title);
        header.addComponent(saveButton);
        
        redBlueSplit.setFirstComponent(fileBrowser);
        redBlueSplit.setSecondComponent(editor);
        redBlueSplit.setMinSplitPosition(150.0f, Component.UNITS_PIXELS);
        redBlueSplit.setSplitPosition(350.0f, Component.UNITS_PIXELS);
        
        blueGreenSplit.setFirstComponent(redBlueSplit);
        blueGreenSplit.setSecondComponent(chat);
        blueGreenSplit.setMinSplitPosition(150.0f, Component.UNITS_PIXELS);
        blueGreenSplit.setSplitPosition(250.0f, Component.UNITS_PIXELS, true);
        blueGreenSplit.setSizeFull();
        mainLayout.addComponent(blueGreenSplit);
        mainLayout.setExpandRatio(blueGreenSplit, 1);
        
        layout.addComponent(header);
        layout.addComponent(mainLayout);
        layout.setExpandRatio(mainLayout, 1.0f);
        layout.setExpandRatio(header, 0.0f);
        
        mainWindow.setContent(layout);
    }
}

