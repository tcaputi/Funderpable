package com.epsilonlabsllc.funderpable.filebroswer.view;

import org.pakhama.vaadin.mvp.view.IView;

public interface IFileBrowserView extends IView{
	public void populateNodeOrOpen(String file, Object parent);
}
