/*
 * Copyright 2019 ABSA Group Limited
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

package za.co.absa.spline.harvester.builder.read

import com.databricks.spark.xml.XmlRelation
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.apache.spark.sql.sources.BaseRelation
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatestplus.mockito.MockitoSugar

import scala.util.{Failure, Try}

class NoOpReadRelationHandlerSpec extends AnyFlatSpec
                                          with Matchers
                                          with MockitoSugar {

  it should "unconditionally return false for isApplicable" in {
    val handler: ReadRelationHandler = NoOpReadRelationHandler()
    handler.isApplicable(mock[BaseRelation]) mustBe false
    handler.isApplicable(mock[XmlRelation]) mustBe false
  }

  it should "throw an exception if applied" in {
    val operationFailed: Boolean = Try(NoOpReadRelationHandler().apply(mock[BaseRelation],
                                                                           mock[LogicalPlan])) match {
      case _: Failure[_] => true
      case _ => false
    }
    operationFailed mustBe true
  }
}
