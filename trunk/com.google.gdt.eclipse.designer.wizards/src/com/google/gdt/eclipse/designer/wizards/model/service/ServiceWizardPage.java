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
package com.google.gdt.eclipse.designer.wizards.model.service;

import com.google.gdt.eclipse.designer.wizards.model.common.AbstractGwtWizardPage;
import com.google.gdt.eclipse.designer.wizards.model.common.IMessageContainer;

import org.eclipse.wb.internal.core.utils.ui.GridDataFactory;

import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * Wizard page for RemoteService creation.
 * 
 * @author scheglov_ke
 * @coverage gwt.wizard.ui
 */
public class ServiceWizardPage extends AbstractGwtWizardPage {
  private final IPackageFragment m_selectedPackage;
  private ServiceComposite m_serviceComposite;

  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public ServiceWizardPage(IPackageFragment selectedPackage) {
    m_selectedPackage = selectedPackage;
    setTitle("New GWT RemoteService");
    setMessage("Create a new GWT RemoteService");
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // GUI
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected void createPageControls(Composite parent) {
    // create GWT parameters composite
    {
      IMessageContainer messagesContainer = IMessageContainer.Util.forWizardPage(this);
      m_serviceComposite =
          new ServiceComposite(parent, SWT.NONE, messagesContainer, m_selectedPackage);
      GridDataFactory.create(m_serviceComposite).grab().fill();
    }
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Creation
  //
  ////////////////////////////////////////////////////////////////////////////
  public void createService() throws Exception {
    IPackageFragment packageFragment = m_serviceComposite.getPackageFragment();
    String serviceName = m_serviceComposite.getServiceName();
    //
    CreateServiceOperation operation = new CreateServiceOperation();
    operation.create(packageFragment, serviceName);
  }
}
