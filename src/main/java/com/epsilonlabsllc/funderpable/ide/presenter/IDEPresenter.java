package com.epsilonlabsllc.funderpable.ide.presenter;

import java.io.File;

import org.pakhama.vaadin.mvp.presenter.impl.Presenter;
import org.vaadin.artur.icepush.ICEPush;

import com.epsilonlabsllc.funderpable.chat.presenter.ChatPresenter;
import com.epsilonlabsllc.funderpable.editor.presenter.EditorPresenter;
import com.epsilonlabsllc.funderpable.filebroswer.presenter.FileBrowserPresenter;
import com.epsilonlabsllc.funderpable.header.presenter.HeaderPresenter;
import com.epsilonlabsllc.funderpable.ide.view.IIDEView;
import com.epsilonlabsllc.funderpable.model.ProjectSession;

public class IDEPresenter extends Presenter<IIDEView>{
	private static final long serialVersionUID = -9168881220884649657L;

	public void init(ProjectSession ps, String username, ICEPush pusher){
		HeaderPresenter headerPresenter = createChild(HeaderPresenter.class);
		headerPresenter.init(ps.getProject().getName());
		ChatPresenter chatPresenter = createChild(ChatPresenter.class);
		chatPresenter.init(ps.getSessionId(), username, pusher);
		FileBrowserPresenter fileBrowserPresenter = createChild(FileBrowserPresenter.class);
		fileBrowserPresenter.init(new File("C:\\Users\\Tom\\Documents\\Personal\\Java\\collabeditor\\src")); //TODO: integrate with Sandizzle
		EditorPresenter editorPresenter = createChild(EditorPresenter.class);
		editorPresenter.init(ps.getEs(), pusher);
		getView().setTop(headerPresenter.getView().getComponent());
		getView().setLeft(fileBrowserPresenter.getView().getComponent());
		getView().setMid(editorPresenter.getView().getComponent());
		getView().setRight(chatPresenter.getView().getComponent());
	}
}
