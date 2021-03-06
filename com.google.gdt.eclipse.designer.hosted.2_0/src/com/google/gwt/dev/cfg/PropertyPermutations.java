/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.google.gwt.dev.cfg;

import com.google.gwt.core.ext.PropertyOracle;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Generates all possible permutations of properties in a module. Each
 * permutation consists of the list of active property values associated with
 * that permutation. That list of property values is represented as an array of
 * Strings corresponding to the list of properties returned by
 * {@link Properties#getBindingProperties()}.
 */
public class PropertyPermutations implements Iterable<String[]> {

  /**
   * Returns the list of all permutations. This method must return results in a
   * consistently sorted order over multiple invocations.
   */
  private static List<String[]> allPermutationsOf(Properties properties) {
    BindingProperty[] bindingProperties = getOrderedPropertiesOf(properties);

    List<String[]> permutations = new ArrayList<String[]>();
    if (bindingProperties.length > 0) {
      permute(bindingProperties, null, 0, permutations);
    } else {
      permutations.add(new String[0]);
    }
    return permutations;
  }

  private static BindingProperty[] getOrderedPropertiesOf(Properties properties) {
    /*
     * We delete items from this set, but want to retain the original order as
     * much as possible.
     */
    Set<BindingProperty> bindingProps = new LinkedHashSet<BindingProperty>(
        properties.getBindingProperties());

    // Accumulates the order in which the properties should be evaluated
    Map<String, BindingProperty> evaluationOrder = new LinkedHashMap<String, BindingProperty>(
        bindingProps.size());

    /*
     * Insert a property after all of the properties that it depends upon have
     * been inserted.
     */
    while (!bindingProps.isEmpty()) {
      boolean changed = false;

      for (Iterator<BindingProperty> it = bindingProps.iterator(); it.hasNext();) {
        BindingProperty prop = it.next();

        Set<String> deps = prop.getRequiredProperties();
        if (evaluationOrder.keySet().containsAll(deps)) {
          it.remove();
          evaluationOrder.put(prop.getName(), prop);
          changed = true;
        }
      }

      if (!changed) {
        throw new IllegalStateException(
            "Cycle detected within remaining property dependencies "
                + bindingProps.toString());
      }
    }

    return evaluationOrder.values().toArray(
        new BindingProperty[evaluationOrder.size()]);
  }

  private static void permute(BindingProperty[] properties, String[] soFar,
      int whichProp, List<String[]> permutations) {
    int lastProp = properties.length - 1;

    BindingProperty prop = properties[whichProp];

    // Find the last-one-wins Condition
    Condition winner = null;
    if (prop.getConditionalValues().size() == 1) {
      winner = prop.getRootCondition();
    } else {
      BindingProperty[] answerable = new BindingProperty[soFar.length];
      System.arraycopy(properties, 0, answerable, 0, soFar.length);
      PropertyOracle propertyOracle = new StaticPropertyOracle(answerable,
          soFar, new ConfigurationProperty[0]);

      for (Condition cond : prop.getConditionalValues().keySet()) {
        try {
          if (cond.isTrue(TreeLogger.NULL, propertyOracle, null, null)) {
            winner = cond;
          }
        } catch (UnableToCompleteException e) {
          throw new IllegalStateException(
              "Should never get here for simple properties", e);
        }
      }
    }

    assert winner != null;

    String[] options = prop.getAllowedValues(winner);
    for (int i = 0; i < options.length; i++) {
      String knownValue = options[i];

      String[] nextStep = new String[whichProp + 1];
      if (whichProp > 0) {
        System.arraycopy(soFar, 0, nextStep, 0, soFar.length);
      }
      nextStep[whichProp] = knownValue;

      if (whichProp < lastProp) {
        permute(properties, nextStep, whichProp + 1, permutations);
      } else {
        // Finished this permutation.
        permutations.add(nextStep);
      }
    }
  }

  private final Properties properties;
  private final List<String[]> values;

  public PropertyPermutations(Properties properties) {
    this.properties = properties;
    this.values = allPermutationsOf(properties);
  }

  public PropertyPermutations(Properties properties, int firstPerm, int numPerms) {
    this.properties = properties;
    values = allPermutationsOf(properties).subList(firstPerm,
        firstPerm + numPerms);
  }

  public PropertyPermutations(PropertyPermutations allPermutations,
      int firstPerm, int numPerms) {
    this.properties = allPermutations.properties;
    values = allPermutations.values.subList(firstPerm, firstPerm + numPerms);
  }

  public BindingProperty[] getOrderedProperties() {
    return getOrderedPropertiesOf(properties);
  }

  public String[] getOrderedPropertyValues(int permutation) {
    return values.get(permutation);
  }

  /**
   * Enumerates each permutation as an array of strings such that the index of
   * each string in the array corresponds to the property at the same index in
   * the array returned from {@link #getOrderedProperties()}.
   */
  public Iterator<String[]> iterator() {
    return values.iterator();
  }

  public int size() {
    return values.size();
  }
}
