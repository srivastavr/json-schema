/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.json.schema;

import org.junit.Assert;

public class TestSupport {

  public static void expectFailure(final Schema failingSchema, final Object input) {
    expectFailure(failingSchema, null, input);
  }

  public static void expectFailure(final Schema failingSchema,
      final Schema expectedViolatingSchema,
      final String expectedPointer, final Object input) {
    try {
      failingSchema.validate(input);
      Assert.fail(failingSchema + " did not fail for " + input);
    } catch (ValidationException e) {
      Assert.assertSame(expectedViolatingSchema, e.getViolatedSchema());
      if (expectedPointer != null) {
        Assert.assertEquals(expectedPointer, e.getPointerToViolation());
      }
    }
  }

  public static void expectFailure(final Schema failingSchema, final String expectedPointer,
      final Object input) {
    expectFailure(failingSchema, failingSchema, expectedPointer, input);
  }

}
