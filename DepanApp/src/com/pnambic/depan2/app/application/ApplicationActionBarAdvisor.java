package com.pnambic.depan2.app.application;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

  private IWorkbenchAction aboutAction;

  private IWorkbenchAction closeAction;

  private IWorkbenchAction closeAllAction;

  private IWorkbenchAction exportAction;

  private IWorkbenchAction findAction;

  private IWorkbenchAction importAction;

  private IWorkbenchAction newWizardAction;

  private IWorkbenchAction newWizardDDAction;

  private IWorkbenchAction preferencesAction;

  private IWorkbenchAction printAction;

  private IWorkbenchAction propertiesAction;

  private IWorkbenchAction quitAction;

  private IWorkbenchAction redoAction;

  private IWorkbenchAction refreshAction;

  private IWorkbenchAction renameAction;

  private IWorkbenchAction revertAction;

  private IWorkbenchAction saveAction;

  private IWorkbenchAction saveAllAction;

  private IWorkbenchAction saveAsAction;

  private IWorkbenchAction selectAllAction;

  private IWorkbenchAction undoAction;

  public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
    super(configurer);
  }

  @Override
  protected void makeActions(IWorkbenchWindow window) {
    super.makeActions(window);

    aboutAction = createWindowAction(window, ActionFactory.ABOUT);
    closeAction = createWindowAction(window, ActionFactory.CLOSE);
    closeAllAction = createWindowAction(window, ActionFactory.CLOSE_ALL);
    exportAction = createWindowAction(window, ActionFactory.EXPORT);
    findAction = createWindowAction(window, ActionFactory.FIND);
    importAction = createWindowAction(window, ActionFactory.IMPORT);
    newWizardAction = createWindowAction(window, ActionFactory.NEW);
    newWizardDDAction = createWindowAction(window, ActionFactory.NEW);
    preferencesAction = createWindowAction(window, ActionFactory.PREFERENCES);
    printAction = createWindowAction(window, ActionFactory.PRINT);
    propertiesAction = createWindowAction(window, ActionFactory.PROPERTIES);
    quitAction = createWindowAction(window, ActionFactory.QUIT);
    redoAction = createWindowAction(window, ActionFactory.REDO);
    refreshAction = createWindowAction(window, ActionFactory.REFRESH);
    renameAction = createWindowAction(window, ActionFactory.RENAME);
    revertAction = createWindowAction(window, ActionFactory.REVERT);
    saveAction = createWindowAction(window, ActionFactory.SAVE);
    saveAllAction = createWindowAction(window, ActionFactory.SAVE_ALL);
    saveAsAction = createWindowAction(window, ActionFactory.SAVE_AS);
    selectAllAction = createWindowAction(window, ActionFactory.SELECT_ALL);
    undoAction = createWindowAction(window, ActionFactory.UNDO);
  }

  @Override
  protected void fillMenuBar(IMenuManager menuBar) {
    super.fillMenuBar(menuBar);

    menuBar.add(makeDepanMenu());
    menuBar.add(makeEditMenu());
    // Room for depan features, etc.
    menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
    menuBar.add(makeWindowMenu());
    menuBar.add(makeHelpMenu());
  }

  @Override
  protected void fillCoolBar(ICoolBarManager coolBar) {
    super.fillCoolBar(coolBar);

    IToolBarManager toolbar = new ToolBarManager(coolBar.getStyle());
    toolbar.add(saveAction);
    toolbar.add(ContributionItemFactory.NEW_WIZARD_SHORTLIST
        .create(getActionBarConfigurer().getWindowConfigurer().getWindow()));

    coolBar.add(toolbar);
    // allow contributions here with id "additions" (MB_ADDITIONS)
    coolBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
  }

  private MenuManager makeDepanMenu() {
    // Nested contribution for new
    MenuManager newMenu = new MenuManager("New...", "New...");
    IContributionItem newWizards =
        ContributionItemFactory.NEW_WIZARD_SHORTLIST.create(getActionBarWindow());
    newMenu.add(newWizards);

    MenuManager result = new MenuManager("&DepAn", "DepAn");
    result.add(newMenu);
    result.add(new Separator());
    result.add(closeAction);
    result.add(saveAsAction);
    result.add(closeAllAction);
    result.add(new Separator());
    result.add(saveAction);
    result.add(saveAsAction);
    result.add(saveAllAction);
    result.add(revertAction);
    result.add(new Separator());
    result.add(renameAction);
    result.add(refreshAction);
    result.add(new Separator());
    result.add(printAction);
    result.add(new Separator());
    result.add(importAction);
    result.add(exportAction);
    result.add(new Separator());
    result.add(propertiesAction);
    result.add(new Separator());
    result.add(quitAction);
    return result;
  }

  private MenuManager makeEditMenu() {

    MenuManager result = new MenuManager("&Edit", "Edit");
    result.add(undoAction);
    result.add(redoAction);
    result.add(new Separator());
    result.add(selectAllAction);
    return result;
  }

  private MenuManager makeWindowMenu() {
    MenuManager result = new MenuManager("Views", "Views");
    IContributionItem views =
        ContributionItemFactory.VIEWS_SHORTLIST.create(getActionBarWindow());
    result.add(views);

    MenuManager windowMenu = new MenuManager("&Window", "Window");
    windowMenu.add(result);
    windowMenu.add(preferencesAction);
    return windowMenu;
  }

  private MenuManager makeHelpMenu() {
    MenuManager result = new MenuManager("&Help", "Help");
    result.add(aboutAction);
    return result;
  }

  private IWorkbenchWindow getActionBarWindow() {
    return getActionBarConfigurer().getWindowConfigurer().getWindow();
  }

  private IWorkbenchAction createWindowAction(IWorkbenchWindow window, ActionFactory factory) {
    IWorkbenchAction result = factory.create(window);
    register(result);
    return result;
  }
}
