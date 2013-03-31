package com.epsilonlabsllc.funderpable.header.view;

import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.view.impl.View;

import com.epsilonlabsllc.funderpable.header.event.SaveFileEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class HeaderView extends View implements IHeaderView{
	private static final long serialVersionUID = -2382322714579392754L;
	
	private HorizontalLayout layout = new HorizontalLayout();
	private Button saveButton = new Button("Save");
	private Label projectLabel = new Label();
	
	public HeaderView(){
		saveButton.addListener(new ClickListener() {
			private static final long serialVersionUID = -2532681713979871463L;

			@Override
			public void buttonClick(ClickEvent event) {
				dispatch(new SaveFileEvent(), EventScope.PARENT);
			}
		});
		
		layout.addComponent(projectLabel);
		layout.addComponent(saveButton);
	}
	
	@Override
	public void setProjectName(String name) {
		projectLabel.setValue(name);
	}
}
