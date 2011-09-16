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
package com.google.gdt.eclipse.designer.core.model.property;

import com.google.gdt.eclipse.designer.core.model.property.accessor.AccessorTests;

import org.eclipse.wb.tests.designer.core.DesignerSuiteTests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Tests for GWT properties.
 * 
 * @author scheglov_ke
 */
public class PropertyTests extends DesignerSuiteTests {
  public static Test suite() {
    TestSuite suite = new TestSuite("gwt.model.property");
    suite.addTest(AccessorTests.suite());
    suite.addTest(createSingleSuite(ImagePropertyEditorTest.class));
    suite.addTest(createSingleSuite(ImageUrlPropertyEditorTest.class));
    suite.addTest(createSingleSuite(AlignStringPropertyEditorTest.class));
    suite.addTest(createSingleSuite(DateTimeFormatPropertyEditorTest.class));
    suite.addTest(createSingleSuite(StylePropertyEditorTest.class));
    return suite;
  }
}
