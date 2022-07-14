package uk.gov.hmrc.transitmovementsconverter.models

import play.api.libs.json.Reads
import play.api.libs.json.OWrites
import scalaxb.XMLFormat

trait ConversionFormat[T] {

  def xmlFormat: XMLFormat[T]
  def jsonReads: Reads[T]
  def jsonWrites: OWrites[T]

}
