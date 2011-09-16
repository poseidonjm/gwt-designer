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
package com.google.gdt.eclipse.designer.gxt;

import com.google.gdt.eclipse.designer.core.model.GwtModelTest;
import com.google.gdt.eclipse.designer.gxt.actions.ConfigureExtGwtOperation;
import com.google.gdt.eclipse.designer.util.ModuleDescription;
import com.google.gdt.eclipse.designer.util.Utils;

import org.eclipse.wb.internal.core.utils.jdt.core.ProjectUtils;
import org.eclipse.wb.tests.designer.core.annotations.DisposeProjectAfter;

import org.eclipse.core.resources.IFile;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Test for {@link ConfigureExtGwtOperation}.
 * 
 * @author scheglov_ke
 */
public class ConfigureExtGwtOperationTest extends GwtModelTest {
  ////////////////////////////////////////////////////////////////////////////
  //
  // Life cycle
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected void tearDown() throws Exception {
    waitEventLoop(10);
    super.tearDown();
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Exit zone :-) XXX
  //
  ////////////////////////////////////////////////////////////////////////////
  public void _test_exit() throws Exception {
    System.exit(0);
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Test
  //
  ////////////////////////////////////////////////////////////////////////////
  @DisposeProjectAfter
  public void test_0() throws Exception {
    IFile moduleFile = getFileSrc("test/Module.gwt.xml");
    ModuleDescription moduleDescription = Utils.getExactModule(moduleFile);
    new ConfigureExtGwtOperation(moduleDescription, ExtGwtTests.GXT_LOCATION).run(null);
    waitForAutoBuild();
    // gxt.jar should be added
    assertTrue(ProjectUtils.hasType(
        m_testProject.getJavaProject(),
        "com.extjs.gxt.ui.client.widget.Component"));
    // resources should be added
    assertFileExists("war/ExtGWT/css/gxt-all.css");
    // module file updated
    assertEquals(
        getSourceDQ(
            "<module>",
            "  <inherits name='com.google.gwt.user.User'/>",
            "  <inherits name='com.extjs.gxt.ui.GXT'/>",
            "  <entry-point class='test.client.Module'/>",
            "</module>"),
        getFileContentSrc("test/Module.gwt.xml"));
    // HTML file updated
    {
      String htmlContent = getFileContent("war/Module.html");
      assertThat(htmlContent).contains(
          "<link type=\"text/css\" rel=\"stylesheet\" href=\"ExtGWT/css/gxt-all.css\">");
    }
  }
}