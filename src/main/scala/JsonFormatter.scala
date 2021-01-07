import spray.json._
class JsonFormatter extends DefaultJsonProtocol {

  implicit val personFormat = jsonFormat2(Person)
  implicit val userAddedFormat = jsonFormat2(UserAdded)
}
