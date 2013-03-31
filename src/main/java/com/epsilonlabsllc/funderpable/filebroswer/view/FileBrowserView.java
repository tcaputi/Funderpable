package com.epsilonlabsllc.funderpable.filebroswer.view;

import java.io.File;
import java.io.IOException;

import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.view.impl.View;

import com.epsilonlabsllc.funderpable.filebroswer.event.FileOpenEvent;
import com.epsilonlabsllc.funderpable.filebroswer.event.NodeExpandEvent;
import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.ExpandEvent;
import com.vaadin.ui.Tree.ExpandListener;

public class FileBrowserView extends View implements IFileBrowserView{
	private static final long serialVersionUID = -912854854936184981L;

	private Panel explorerPanel = new Panel("Filesystem explorer");
	private Tree tree = new Tree();

	public FileBrowserView(){
		addComponent(explorerPanel);
		explorerPanel.addComponent(tree);
		explorerPanel.setHeight("100%");
		explorerPanel.setScrollable(true);
		setExpandRatio(explorerPanel, 1.0f);
		explorerPanel.setWidth("100%");
		tree.addListener(new ExpandListener() {
			private static final long serialVersionUID = 2009509321192976933L;
			@Override
			public void nodeExpand(ExpandEvent event) {
				final Item i = tree.getItem(event.getItemId());				
				FileBrowserView.this.dispatch(new NodeExpandEvent(event.getItemId().toString(), event.getItemId(), tree.hasChildren(i)), EventScope.PARENT);
			}
		});
		
		tree.addListener(new ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				final Item i = tree.getItem(event.getItemId());				
				FileBrowserView.this.dispatch(new NodeExpandEvent(event.getItemId().toString(), event.getItemId(), tree.hasChildren(i)), EventScope.PARENT);
			}
		});
	}

	@Override
	public void populateNodeOrOpen(String file, Object parent) {
		final File subdir = new File(file);
		final File[] files = subdir.listFiles();

		if(subdir.isFile()){
			dispatch(new FileOpenEvent(subdir), EventScope.PARENT);
		}else{
			for (int x = 0; x < files.length; x++) {
				String path = "";
				try {
					path = files[x].getCanonicalPath().toString();
				} catch (IOException e) {
					e.printStackTrace();
				}
				tree.addItem(path);
				// set parent if this item has one
				if (parent != null) tree.setParent(path, parent);
				// check if item is a directory and read access exists
				if (files[x].isDirectory() && files[x].canRead()) tree.setChildrenAllowed(path, true);
				else tree.setChildrenAllowed(path, false);
			}
		}
	}
}
