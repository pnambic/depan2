package com.pnambic.depan2.app.application;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

  public static final String PERSPECTIVE_ID =
      "com.pnambic.depan2.app.application.perspective"; //$NON-NLS-1$

  @Override
  public void createInitialLayout(IPageLayout layout) {
  }
}
