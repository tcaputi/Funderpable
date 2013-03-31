package com.epsilonlabsllc.funderpable.editor.view;

import java.io.File;
import java.util.HashMap;

import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.view.impl.View;
import org.vaadin.aceeditor.collab.DocDiff;
import org.vaadin.aceeditor.collab.gwt.shared.Doc;
import org.vaadin.artur.icepush.ICEPush;
import org.vaadin.diffsync.Shared;

import com.epsilonlabsllc.funderpable.CollaborativeEditor;
import com.epsilonlabsllc.funderpable.editor.event.SwitchFileEvent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;

public class EditorView extends View implements IEditorView{
	private static final long serialVersionUID = 8720241933920091350L; 

	HashMap<CollaborativeEditor, File> editors = new HashMap<CollaborativeEditor, File>();
	private TabSheet tabSheet = new TabSheet();
	
	public EditorView(){
		tabSheet.setSizeFull();
		tabSheet.addListener(new SelectedTabChangeListener() {
			private static final long serialVersionUID = -1956438755250247642L;

			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				EditorView.this.dispatch(new SwitchFileEvent(editors.get(tabSheet.getSelectedTab())), EventScope.PARENT);
			}
		});
		addComponent(tabSheet);
		setExpandRatio(tabSheet, 1.0f);
	}

	@Override
	public void createEditor(File file, Shared<Doc, DocDiff> sharedText, ICEPush pusher) {
		CollaborativeEditor newEditor = new CollaborativeEditor(file, sharedText, pusher);
		editors.put(newEditor, file);
		Tab tab = tabSheet.addTab(newEditor);
		tab.setCaption(file.getName());
		tabSheet.setSelectedTab(newEditor);
	}
}
