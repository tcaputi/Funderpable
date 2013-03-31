package com.epsilonlabsllc.funderpable.ide.view;

import org.pakhama.vaadin.mvp.view.impl.View;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;

public class IDEView extends View implements IIDEView{
	private static final long serialVersionUID = 8872717071186816793L;

	private VerticalLayout layout = new VerticalLayout();
	private HorizontalLayout mainLayout = new HorizontalLayout();
	private HorizontalSplitPanel redBlueSplit = new HorizontalSplitPanel();
	private HorizontalSplitPanel blueGreenSplit = new HorizontalSplitPanel();
	
	public IDEView(){
        redBlueSplit.setMinSplitPosition(150.0f, Component.UNITS_PIXELS);
        redBlueSplit.setSplitPosition(350.0f, Component.UNITS_PIXELS);
        redBlueSplit.setSizeFull();
        blueGreenSplit.setFirstComponent(redBlueSplit);
        blueGreenSplit.setMinSplitPosition(150.0f, Component.UNITS_PIXELS);
        blueGreenSplit.setSplitPosition(250.0f, Component.UNITS_PIXELS, true);
        blueGreenSplit.setSizeFull();
        mainLayout.addComponent(blueGreenSplit);
        mainLayout.setExpandRatio(blueGreenSplit, 1);
        layout.addComponent(mainLayout);
        mainLayout.setSizeFull();
        layout.setExpandRatio(mainLayout, 1.0f);
        layout.setSizeFull();
        addComponent(layout);
	}
	
	@Override
	public void setTop(Component c) {
		layout.addComponentAsFirst(c);
	}

	@Override
	public void setMid(Component c) {
		redBlueSplit.setSecondComponent(c);
	}

	@Override
	public void setLeft(Component c) {
		redBlueSplit.setFirstComponent(c);
	}

	@Override
	public void setRight(Component c) {
		blueGreenSplit.setSecondComponent(c);
	}
}
