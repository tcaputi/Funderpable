package com.epsilonlabsllc.funderpable.ide.view;

import org.pakhama.vaadin.mvp.view.IView;

import com.vaadin.ui.Component;

public interface IIDEView extends IView{
	public void setTop(Component c);
	public void setMid(Component c);
	public void setLeft(Component c);
	public void setRight(Component c);
}
