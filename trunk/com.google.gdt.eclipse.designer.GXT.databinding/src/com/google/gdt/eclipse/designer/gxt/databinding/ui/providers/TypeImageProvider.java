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
package com.google.gdt.eclipse.designer.gxt.databinding.ui.providers;

import com.google.gdt.eclipse.designer.gxt.databinding.Activator;

import org.eclipse.swt.graphics.Image;

import java.util.Collection;

/**
 * 
 * @author lobas_av
 * 
 */
public final class TypeImageProvider {
  public static final Image SELF_OBJECT_PROPERTY_IMAGE = Activator.getImage("SelfObject.png");
  public static final Image OBJECT_IMAGE = Activator.getImage("types/Object.png");
  public static final Image STRING_IMAGE = Activator.getImage("types/String.png");
  public static final Image BOOLEAN_IMAGE = Activator.getImage("types/Boolean.png");
  public static final Image NUMBER_IMAGE = Activator.getImage("types/Number.png");
  public static final Image ARRAY_IMAGE = Activator.getImage("types/Array.png");
  public static final Image COLLECTION_IMAGE = Activator.getImage("types/Collection.png");

  ////////////////////////////////////////////////////////////////////////////
  //
  // Image
  //
  ////////////////////////////////////////////////////////////////////////////
  /**
   * @return {@link Image} association with given {@link Class}.
   */
  public static Image getImage(Class<?> type) {
    // unknown type accept as object
    if (type == null) {
      return OBJECT_IMAGE;
    }
    // string
    if (type == String.class || type == byte.class || type == char.class) {
      return STRING_IMAGE;
    }
    // boolean
    if (type == boolean.class || type == Boolean.class) {
      return BOOLEAN_IMAGE;
    }
    // arithmetic
    if (type == int.class
        || type == short.class
        || type == long.class
        || type == float.class
        || type == double.class) {
      return NUMBER_IMAGE;
    }
    // array
    if (type.isArray()) {
      return ARRAY_IMAGE;
    }
    // Collection
    if (Collection.class.isAssignableFrom(type)) {
      return COLLECTION_IMAGE;
    }
    // other accept as object
    return OBJECT_IMAGE;
  }
}