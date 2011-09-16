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
package com.google.gdt.eclipse.designer.mobile.device;

import com.google.gdt.eclipse.designer.core.model.DisposeStateJavaTest;
import com.google.gdt.eclipse.designer.uibinder.DisposeStateXmlTest;

import org.eclipse.wb.tests.designer.core.DesignerSuiteTests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * GWT mobile devices tests.
 * 
 * @author scheglov_ke
 */
public class DeviceTests extends DesignerSuiteTests {
  public static Test suite() {
    TestSuite suite = new TestSuite("gwt.mobile.device");
    suite.addTest(createSingleSuite(DeviceManagerTest.class));
    suite.addTest(createSingleSuite(DeviceSelectionSupportJavaGefTest.class));
    {
      suite.addTestSuite(DisposeStateJavaTest.class);
      suite.addTest(createSingleSuite(DeviceSelectionSupportXmlGefTest.class));
      suite.addTestSuite(DisposeStateXmlTest.class);
    }
    return suite;
  }
}
