/*
 * Copyright 2012-2013 Institut National des Sciences Appliquées de Lyon (INSA-Lyon)
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

package fr.insalyon.citi.golo.compiler.parser;

import fr.insalyon.citi.golo.internal.testing.TestUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

import static fr.insalyon.citi.golo.internal.testing.Tracing.println;
import static fr.insalyon.citi.golo.internal.testing.Tracing.shouldTrace;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParserSanityTest {

  @DataProvider(name = "golo-files")
  public static Iterator<Object[]> data() {
    return TestUtils.goloFilesIn("src/test/resources/for-parsing-and-compilation".replaceAll("/", File.separator));
  }

  @Test(dataProvider = "golo-files")
  public void check_parse(File goloFile) throws FileNotFoundException, ParseException {
    if (shouldTrace) {
      println();
      println(">>> Parsing: " + goloFile);
    }

    GoloParser parser = new GoloParser(new FileReader(goloFile));
    ASTCompilationUnit compilationUnit = parser.CompilationUnit();

    assertThat(compilationUnit, notNullValue());

    if (shouldTrace) {
      compilationUnit.dump("% ");
      println();
    }
  }
}
