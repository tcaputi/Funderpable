package com.epsilonlabsllc.funderpable.ide.presenter;

import java.io.File;
import java.util.Arrays;

import org.pakhama.vaadin.mvp.annotation.event.EventListener;
import org.pakhama.vaadin.mvp.event.EventScope;
import org.pakhama.vaadin.mvp.presenter.impl.Presenter;
import org.phakama.maven.MavenBuilder;
import org.phakama.maven.execution.MavenBuildRecord;
import org.phakama.maven.execution.logging.MavenBuildRecordCallback;
import org.phakama.maven.model.MavenBuildProject;
import org.phakama.maven.model.MavenEnvironmentConfiguration;
import org.vaadin.artur.icepush.ICEPush;

import com.epsilonlabsllc.funderpable.chat.presenter.ChatPresenter;
import com.epsilonlabsllc.funderpable.collaborators.presenter.CollaboratorsPresenter;
import com.epsilonlabsllc.funderpable.console.event.ConsoleUpdateEvent;
import com.epsilonlabsllc.funderpable.console.presenter.ConsolePresenter;
import com.epsilonlabsllc.funderpable.editor.presenter.EditorPresenter;
import com.epsilonlabsllc.funderpable.filebroswer.presenter.FileBrowserPresenter;
import com.epsilonlabsllc.funderpable.header.event.BuildEvent;
import com.epsilonlabsllc.funderpable.header.presenter.HeaderPresenter;
import com.epsilonlabsllc.funderpable.ide.event.NotificationEvent;
import com.epsilonlabsllc.funderpable.ide.view.IIDEView;
import com.epsilonlabsllc.funderpable.model.ProjectSession;

public class IDEPresenter extends Presenter<IIDEView>{
	private static final long serialVersionUID = -9168881220884649657L;
	
	private ProjectSession projectSession;

	public void init(ProjectSession ps, String username, ICEPush pusher){
		this.projectSession = ps;
		
		HeaderPresenter headerPresenter = createChild(HeaderPresenter.class);
		headerPresenter.init(ps.getSessionId());
		ChatPresenter chatPresenter = createChild(ChatPresenter.class);
		chatPresenter.init(ps.getSessionId(), username, pusher);
		FileBrowserPresenter fileBrowserPresenter = createChild(FileBrowserPresenter.class);
		fileBrowserPresenter.init(new File("C:\\Users\\Tom\\Documents\\Personal\\Java\\collabeditor\\src")); //TODO: integrate with Sandizzle
		EditorPresenter editorPresenter = createChild(EditorPresenter.class);
		editorPresenter.init(ps.getSessionId(), ps.getEs(), pusher);
		CollaboratorsPresenter collaboratorsPresenter = createChild(CollaboratorsPresenter.class);
		collaboratorsPresenter.init(pusher);
		ConsolePresenter consolePresenter = createChild(ConsolePresenter.class);
		consolePresenter.init(projectSession.getSessionId(), pusher);
		
		getView().setTop(headerPresenter.getView().getComponent());
		getView().setLeft(fileBrowserPresenter.getView().getComponent());
		getView().setMid(editorPresenter.getView().getComponent());
		getView().setRight(chatPresenter.getView().getComponent());
		getView().setBottom(consolePresenter.getView().getComponent());
		getView().setProjectName(ps.getProject().getName());
		getView().setCollaboratorsView(collaboratorsPresenter.getView().getComponent());
	}
	
	@EventListener(event = BuildEvent.class)
	public void onBuild(BuildEvent e) {
		MavenBuildProject buildProject = this.projectSession.getBuildProject();
		// TODO fix
		MavenEnvironmentConfiguration config = new MavenEnvironmentConfiguration("C:\\Users\\Tom\\Desktop\\stuff\\m2", "C:\\Users\\Tom\\Desktop\\stuff\\logs");
		MavenBuilder.build(config, buildProject, Arrays.asList("clean", "install"), new MavenBuildRecordCallback(config, buildProject) {
			
			@Override
			public void onLog(String log) {
				dispatch(new ConsoleUpdateEvent(projectSession.getSessionId(), log), EventScope.UNIVERSAL);
			}
			
			@Override
			public void onFinish(MavenBuildRecord record, boolean success) {
				dispatch(new NotificationEvent(projectSession.getSessionId(), "Build Completed"), EventScope.UNIVERSAL);
			}
		});
	}
	
	@EventListener(event = NotificationEvent.class)
	public void onNotificationEvent(NotificationEvent e){
		if(e.getSessionId() == projectSession.getSessionId()) getView().printToast(e.getMessage());
	}
}
