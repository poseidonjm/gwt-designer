/*
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gdt.eclipse.designer.gxt.wizards;

import org.eclipse.wb.internal.core.wizards.AbstractDesignWizardPage;

/**
 * Wizard for <code>com.extjs.gxt.ui.client.widget.LayoutContainer</code>.
 * 
 * @author scheglov_ke
 * @coverage ExtGWT.wizard
 */
public final class LayoutContainerWizard extends GxtWizard {
  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public LayoutContainerWizard() {
    setWindowTitle("New Ext-GWT LayoutContainer");
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Wizard
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected AbstractDesignWizardPage createMainPage() {
    return new LayoutContainerWizardPage();
  }
}
